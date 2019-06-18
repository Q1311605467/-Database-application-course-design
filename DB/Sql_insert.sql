

create procedure add_customer_message--����û���Ϣ�洢����
    @Customer_id varchar(20) ,
	@Customer_vip bit,--�Ƿ��Ա
	@Customer_vip_money float,--��Ա�����
	@Customer_tel varchar(20) --�˿���ϵ�绰
as
insert into Customer(Customer_id,Customer_vip,Customer_vip_money,Customer_tel)
values (@Customer_id,@Customer_vip,@Customer_vip_money,@Customer_tel)

create procedure add_book_message
	@Book_id varchar(20), --ͼ���
	@Book_in_price float,--����
	@Book_out_price float,--ͼ���ۼ�
	@Book_name varchar(20),--����
	@Supplier_name varchar(20),--��Ӧ������
	@Book_storage_time datetime,
	@Book_stock int--���
as
insert into Book(Book_id,Book_in_price,Book_out_price,Book_name,Supplier_name,Book_storage_time ,Book_stock)
values (@Book_id, @Book_in_price, @Book_out_price, @Book_name, @Supplier_name, @Book_storage_time,@Book_stock)

------------------------------------------------------------------------------------------------


create procedure add_supplier_message
	@Supplier_id varchar(20),--��Ӧ�̺�
	@Supplier_name varchar(20),--��Ӧ������
	@Supplier_city varchar(20),--��Ӧ�̳���
	@Supplier_tel varchar(20)--��Ӧ�̵绰
as
insert into supplier(Supplier_id, Supplier_name, Supplier_city, Supplier_tel)
values (@Supplier_id, @Supplier_name, @Supplier_city, @Supplier_tel)
/*
exec add_supplier_message 'GY10001', '�����ʵ������', '����','18010411467'
exec add_supplier_message 'GY10002', '�ߵȽ���������', '�Ϻ�','18013341500'
*/
----------------------------------------------------------------------------------------------


create procedure add_Orderform--������
	@Orderform_id varchar(20),--�������
	@Orderform_time datetime,--����ʱ��
	@Customer_id varchar(20),--�˿ͱ��
	@Supplier_id varchar(20),--��Ӧ�̱��
	@Supplier_total int--��Ӧ��
as 
	insert into Orderform values(@Orderform_id, @Orderform_time, @Customer_id, @Supplier_id, @Supplier_total)
	
 ----------------------------------------------------------------------------------------------


 create proc add_Order_detail--������ϸ��
	@Orderform_id varchar(20),--�������
	@Book_num int ,--ͼ������
	@Book_id varchar(20),--ͼ���
	@Pay_total float--С��
as
	insert into Order_detail values(@Orderform_id, @Book_num, @Book_id, @Pay_total)

 ---------------------------------------------------------------------------------------
 
 
 
------------------------------------------------------------------------------------------------
/*
��ӽ�����¼
������¶�����ϸ
���¿�����
*/
-----------------------------------------------------------------
drop proc Supply_ExistentBook
GO

create proc Supply_ExistentBook--��Ӧ���ڵ���洢����
    @Orderform_id varchar(20), --�������
	@Book_num int ,--ͼ������
	@Book_id varchar(20)--ͼ���
	---Pay_total float--С��
as
	begin 
		declare @Pay_total float, @Book_price float, @time datetime,@Supplier_id varchar(20)     --С�� ͼ�鵥��  
		select @Book_price = Book_in_price  from Book where @Book_id = Book_id
		select @Supplier_id =Supplier.Supplier_id from Book,Supplier where Book.Supplier_name=Supplier.Supplier_name and Book.Book_id=@Book_id --��ѯ��Ӧ�̺�
		/*Book.Book_id = Order_detail.Book_id and Order_detail.Orderform_id = Orderform.Orderform_id */
		
		set @Pay_total = @Book_price * @Book_num --�����ܽ��
		
		--select   from Book where @Book_id = Book_id
		set @time = getdate()--��ȡ��ǰʱ��
		update Book set Book_storage_time =@time where  Book_id= @Book_id--�������ʱ��
		
		insert into Order_detail values(@Orderform_id, @Book_num, @Book_id, @Pay_total)--���¶�����ϸ��
		
		update Book set Book_stock += @Book_num where Book_id =  @Book_id --����ͼ���
		
		if((select Orderform.Orderform_id from Orderform where Orderform_id=@Orderform_id)=@Orderform_id)
		begin
			update Orderform set Supplier_total+=@Book_num where Orderform_id=@Orderform_id--���¸�������
		end
		
		else
		begin
		insert into Orderform values(@Orderform_id, @time,null,@Supplier_id,@Book_num)--����һ���¶���
		end
	end
	go

 ---���Դ���
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
 create proc Supply_NonExistentBook--��Ӧ�����ڵ���洢����
	@Orderform_id varchar(20),--������
	@Book_id varchar(20),--ͼ���
	@Book_name varchar(20),--����
	@Book_in_price float,--����
	@Book_out_price float,--�ۼ�
	@Supplier_name varchar(20),--������
	@Supplier_id varchar(20),--��Ӧ�̺�
	--Book_storage_time datetime,--ͼ�����ʱ��
	@Book_stock int--���/��������
as
	begin
	declare @Book_storage_time datetime, @Pay_total float --ͼ�����ʱ��,�����ۺ�
	set @Book_storage_time = GETDATE()
	set @Pay_total = @Book_in_price * @Book_stock
	exec add_book_message @Book_id, @Book_in_price, @Book_out_price, @Book_name, @Supplier_name, @Book_storage_time,@Book_stock
	exec add_Order_detail @Orderform_id, @Book_stock, @Book_id, @Pay_total
	
	if((select Orderform.Orderform_id from Orderform where Orderform_id=@Orderform_id)=@Orderform_id)
		begin
		update Orderform set Supplier_total+=@Book_stock where Orderform_id=@Orderform_id--���¸�������
		end
		
		else
		begin
		insert into Orderform values(@Orderform_id, @Book_storage_time,null,@Supplier_id,@Book_stock)--����һ���¶���
		end
	end

/*----------------------------------------------------------------------------
���� �������ʱ�䣬���ؾ��������м���

-------------------------*/
drop function diff_date
go
create function diff_date(@date datetime)
returns int
begin
	return datediff(day,@date,getdate())
end

----------------------------------------
/*��Ա�жϺ���   ����˿ͺţ��ж��Ƿ�Ϊ��Ա*/
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

/*�ж�һ�����������������Ƿ����  �ؼ���
����ͼ��š���������   �����Ƿ���ۣ�0/1��
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
����洢����
---------------------------------------*/

drop proc sell_book
go
create proc sell_book
	@Orderform_id varchar(20),--������
	@Book_id varchar(20),--ͼ���
	@Customer_id varchar(20),--�˿ͱ��
	@Buy_num int	--��������
as
	begin
		declare @time datetime--ͼ������ʱ��
		declare @flag int --�Ƿ����ؼ���
		declare @vip int --�Ƿ��ǻ�Ա
		declare @vip_money float --��Ա�����
		declare @book_price float --ͼ��ļ�Ǯ
		declare @money float --ʵ�ʽ��׶� 
		select @time = Book_storage_time, @book_price = Book_out_price from Book where @Book_id = Book_id--��ѯͼ������ʱ�䡢ͼ��۸�
		set @flag = dbo.discount(@time)
		if(@flag = 1 )--�Ƿ��ؼ�
			begin
			set @money = @Buy_num * @book_price* 0.7--�ؼ����7��
				select @vip = Customer_vip from Customer where Customer_id = @Customer_id
				if(@vip = 1)--�Ƿ��Ա
					begin
						update Customer set Customer_vip_money += @money where Customer_id = @Customer_id--���»�Ա���Ľ��
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
							set @money = @money * 0.8 -- ��Ա8��
							update Customer set Customer_vip_money = Customer_vip_money + @money where Customer_id = @Customer_id
						end	
					else
						begin
							update Customer set Customer_vip_money = Customer_vip_money + @money where Customer_id = @Customer_id
						end
					end
			end
		
		update Book set Book_stock-= @Buy_num where @Book_id = Book_id
		exec add_Order_detail @Orderform_id,@Buy_num,@Book_id,@money--��Ӷ�����ϸ
		
		if((select Orderform.Orderform_id from Orderform where Orderform_id=@Orderform_id)=@Orderform_id)
		begin
		update Orderform set Supplier_total+=@Buy_num where Orderform_id=@Orderform_id--���¸�������
		end
		
		else
		begin
			insert into Orderform values(@Orderform_id, @time,@Customer_id,null,@Buy_num)--����һ���¶���
		end
	end
------------------------------------------------------------------------------------
/*
����������

*/
DROP TRIGGER Order_detail_TRIGGER
go

CREATE TRIGGER Order_detail_TRIGGER
ON Order_detail
for INSERT, UPDATE 
AS 
	DECLARE @Book_id char(20)
   
    SELECT @Book_id=Book_id FROM inserted /*---��inserted��ʱ���л�ȡ����ļ�¼����Ϣ*/

if((select Book_id from Book where Book_id=@Book_id) is null)
	begin
	print 'û���Ȿ��'
   ROLLBACK TRANSACTION
         RETURN
      END
GO


CREATE TRIGGER Book_TRIGGER
ON Book
for INSERT, UPDATE 
AS 
	DECLARE @Book_id char(20),@Book_stock int
   
    SELECT @Book_id=Book_id,@Book_stock=Book_stock FROM inserted /*---��inserted��ʱ���л�ȡ����ļ�¼����Ϣ*/
   
if(UPDATE(Book_stock))
	begin
	if((select Book_stock from Book where Book_id=@Book_id)<0)
	begin
		 ROLLBACK TRANSACTION
         RETURN
       END
    end
GO
