

create procedure add_customer_message--添加用户信息存储过程
    @Customer_id varchar(20) ,
	@Customer_vip bit,--是否会员
	@Customer_vip_money float,--会员卡金额
	@Customer_tel varchar(20) --顾客联系电话
as
insert into Customer(Customer_id,Customer_vip,Customer_vip_money,Customer_tel)
values (@Customer_id,@Customer_vip,@Customer_vip_money,@Customer_tel)

create procedure add_book_message
	@Book_id varchar(20), --图书号
	@Book_in_price float,--进价
	@Book_out_price float,--图书售价
	@Book_name varchar(20),--书名
	@Supplier_name varchar(20),--供应商名字
	@Book_storage_time datetime,
	@Book_stock int--库存
as
insert into Book(Book_id,Book_in_price,Book_out_price,Book_name,Supplier_name,Book_storage_time ,Book_stock)
values (@Book_id, @Book_in_price, @Book_out_price, @Book_name, @Supplier_name, @Book_storage_time,@Book_stock)

------------------------------------------------------------------------------------------------


create procedure add_supplier_message
	@Supplier_id varchar(20),--供应商号
	@Supplier_name varchar(20),--供应商名字
	@Supplier_city varchar(20),--供应商城市
	@Supplier_tel varchar(20)--供应商电话
as
insert into supplier(Supplier_id, Supplier_name, Supplier_city, Supplier_tel)
values (@Supplier_id, @Supplier_name, @Supplier_city, @Supplier_tel)
/*
exec add_supplier_message 'GY10001', '人民邮电出版社', '北京','18010411467'
exec add_supplier_message 'GY10002', '高等教育出版社', '上海','18013341500'
*/
----------------------------------------------------------------------------------------------


create procedure add_Orderform--订单表
	@Orderform_id varchar(20),--订单编号
	@Orderform_time datetime,--订单时间
	@Customer_id varchar(20),--顾客编号
	@Supplier_id varchar(20),--供应商编号
	@Supplier_total int--供应量
as 
	insert into Orderform values(@Orderform_id, @Orderform_time, @Customer_id, @Supplier_id, @Supplier_total)
	
 ----------------------------------------------------------------------------------------------


 create proc add_Order_detail--订单明细表
	@Orderform_id varchar(20),--订单编号
	@Book_num int ,--图书数量
	@Book_id varchar(20),--图书号
	@Pay_total float--小计
as
	insert into Order_detail values(@Orderform_id, @Book_num, @Book_id, @Pay_total)

 ---------------------------------------------------------------------------------------
 
 
 
------------------------------------------------------------------------------------------------
/*
添加进货记录
这里更新订单明细
更新库存的书
*/
-----------------------------------------------------------------
drop proc Supply_ExistentBook
GO

create proc Supply_ExistentBook--供应存在的书存储过程
    @Orderform_id varchar(20), --订单编号
	@Book_num int ,--图书数量
	@Book_id varchar(20)--图书号
	---Pay_total float--小计
as
	begin 
		declare @Pay_total float, @Book_price float, @time datetime,@Supplier_id varchar(20)     --小计 图书单价  
		select @Book_price = Book_in_price  from Book where @Book_id = Book_id
		select @Supplier_id =Supplier.Supplier_id from Book,Supplier where Book.Supplier_name=Supplier.Supplier_name and Book.Book_id=@Book_id --查询供应商号
		/*Book.Book_id = Order_detail.Book_id and Order_detail.Orderform_id = Orderform.Orderform_id */
		
		set @Pay_total = @Book_price * @Book_num --计算总金额
		
		--select   from Book where @Book_id = Book_id
		set @time = getdate()--获取当前时间
		update Book set Book_storage_time =@time where  Book_id= @Book_id--更新入库时间
		
		insert into Order_detail values(@Orderform_id, @Book_num, @Book_id, @Pay_total)--更新订单明细表
		
		update Book set Book_stock += @Book_num where Book_id =  @Book_id --更新图书库
		
		if((select Orderform.Orderform_id from Orderform where Orderform_id=@Orderform_id)=@Orderform_id)
		begin
			update Orderform set Supplier_total+=@Book_num where Orderform_id=@Orderform_id--更新该条订单
		end
		
		else
		begin
		insert into Orderform values(@Orderform_id, @time,null,@Supplier_id,@Book_num)--创建一条新订单
		end
	end
	go

 ---测试代码
 /*
 begin tran
 exec  Supply_ExistBook 'GY10001',5,'BK10001'
 SELECT * FROM Order_detail
 SELECT * FROM BOOK
 select * from Orderform
 ROLLBACK
 */ 
 
 drop proc Supply_NonExistentBook
 go
 create proc Supply_NonExistentBook--供应不存在的书存储过程
	@Orderform_id varchar(20),--订单号
	@Book_id varchar(20),--图书号
	@Book_name varchar(20),--书名
	@Book_in_price float,--进价
	@Book_out_price float,--售价
	@Supplier_name varchar(20),--出版商
	@Supplier_id varchar(20),--供应商号
	--Book_storage_time datetime,--图书入库时间
	@Book_stock int--库存/进货数量
as
	begin
	declare @Book_storage_time datetime, @Pay_total float --图书入库时间,进价综合
	set @Book_storage_time = GETDATE()
	set @Pay_total = @Book_in_price * @Book_stock
	exec add_book_message @Book_id, @Book_in_price, @Book_out_price, @Book_name, @Supplier_name, @Book_storage_time,@Book_stock
	exec add_Order_detail @Orderform_id, @Book_stock, @Book_id, @Pay_total
	
	if((select Orderform.Orderform_id from Orderform where Orderform_id=@Orderform_id)=@Orderform_id)
		begin
		update Orderform set Supplier_total+=@Book_stock where Orderform_id=@Orderform_id--更新该条订单
		end
		
		else
		begin
		insert into Orderform values(@Orderform_id, @Book_storage_time,null,@Supplier_id,@Book_stock)--创建一条新订单
		end
	end

/*----------------------------------------------------------------------------
函数 输入进货时间，返回距离现在有几天

-------------------------*/
drop function diff_date
go
create function diff_date(@date datetime)
returns int
begin
	return datediff(day,@date,getdate())
end

----------------------------------------
/*会员判断函数   输入顾客号，判断是否为会员*/
drop function vip_judge

create function vip_judge(@customer_id varchar)
returns int
begin
	declare @flag int
	select @flag = Customer_vip from Customer where @customer_id = Customer_id
	if ( @flag = 1)
	return 1
	return 0;
end
--------------------------------------------------------

/*判断一本书在日期限制下是否打折  特价书
输入图书号、限制天数   返回是否打折（0/1）
*/

drop function discount

create function discount(@book_id varchar(20))
returns int
begin
	declare @time DATETIME
	select @time = Book_storage_time
	from Book
	where @book_id = Book_id
	if @time = null
	return 0
	if((dbo.diff_date(@time) - 30 ) > 0 )
	
	return 1
	
	return 0
end
/*----------------------------------------------
卖书存储过程
---------------------------------------*/

drop proc sell_book
go
create proc sell_book
	@Orderform_id varchar(20),--订单号
	@Book_id varchar(20),--图书号
	@Customer_id varchar(20),--顾客编号
	@Buy_num int	--购买数量
as
	begin
		declare @time datetime--图书的入库时间
		declare @flag int --是否是特价书
		declare @vip int --是否是会员
		declare @vip_money float --会员卡金额
		declare @book_price float --图书的价钱
		declare @money float --实际交易额 
		select @time = Book_storage_time, @book_price = Book_out_price from Book where @Book_id = Book_id--查询图书的入库时间、图书价格
		set @flag = dbo.discount(@time)
		if(@flag = 1 )--是否特价
			begin
			set @money = @Buy_num * @book_price* 0.7--特价书打7折
				select @vip = Customer_vip from Customer where Customer_id = @Customer_id
				if(@vip = 1)--是否会员
					begin
						update Customer set Customer_vip_money += @money where Customer_id = @Customer_id--更新会员卡的金额
					end
				
			end
		else
			begin 
				set @money = @Buy_num * @book_price
				select @vip = Customer_vip from Customer where Customer_id = @Customer_id
				
				select @vip_money = Customer_vip_money from Customer where Customer_id = @Customer_id
				if( @vip = 1 )
					begin
						if(@vip_money>500)
						begin
							set @money = @money * 0.8 -- 会员8折
							update Customer set Customer_vip_money = Customer_vip_money + @money where Customer_id = @Customer_id
						end	
					else
						begin
							update Customer set Customer_vip_money = Customer_vip_money + @money where Customer_id = @Customer_id
						end
					end
			end
		
		update Book set Book_stock-= @Buy_num where @Book_id = Book_id
		exec add_Order_detail @Orderform_id,@Buy_num,@Book_id,@money--添加订单明细
		
		if((select Orderform.Orderform_id from Orderform where Orderform_id=@Orderform_id)=@Orderform_id)
		begin
		update Orderform set Supplier_total+=@Buy_num where Orderform_id=@Orderform_id--更新该条订单
		end
		
		else
		begin
			insert into Orderform values(@Orderform_id, @time,@Customer_id,null,@Buy_num)--创建一条新订单
		end
	end
------------------------------------------------------------------------------------
/*
触发器部分

*/
DROP TRIGGER Order_detail_TRIGGER
go

CREATE TRIGGER Order_detail_TRIGGER
ON Order_detail
for INSERT, UPDATE 
AS 
	DECLARE @Book_id char(20)
   
    SELECT @Book_id=Book_id FROM inserted /*---从inserted临时表中获取插入的记录行信息*/

if((select Book_id from Book where Book_id=@Book_id) is null)
	begin
	print '没有这本书'
   ROLLBACK TRANSACTION
         RETURN
      END
GO


CREATE TRIGGER Book_TRIGGER
ON Book
for INSERT, UPDATE 
AS 
	DECLARE @Book_id char(20),@Book_stock int
   
    SELECT @Book_id=Book_id,@Book_stock=Book_stock FROM inserted /*---从inserted临时表中获取插入的记录行信息*/
   
if(UPDATE(Book_stock))
	begin
	if((select Book_stock from Book where Book_id=@Book_id)<0)
	begin
		 ROLLBACK TRANSACTION
         RETURN
       END
    end
GO
