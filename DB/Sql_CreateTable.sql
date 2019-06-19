
--Bookstore retail management system ������۹���ϵͳ
create database BRMSDB  --�������ݿ�
on
(
name='BRMSDB',--���������ļ���
filename='d:\db\BRMSDB.mdf',--�ļ�����ַ
size=3mb,--�ļ���ʼ��С
maxsize=100mb,--�ļ�����С
filegrowth=2mb--�ļ������ٶ�
)
log on
(
name='StuLog',
filename='d:\db\BRMSDB.ldf',
size=1mb,
maxsize=10mb,
filegrowth=10%
)
------------------------------------------------------------------------------------


use BRMSDB
go

drop table Supplier
go
create table Supplier--��Ӧ�̱�
(
	Supplier_id varchar(20) primary key not null,--��Ӧ�̺�
	Supplier_name varchar(20),--��Ӧ������
	Supplier_city varchar(20),--��Ӧ�̳���
	Supplier_tel varchar(20),--��Ӧ�̵绰
	
)
drop table Orderform
go
create table Orderform--������
(
	Orderform_id varchar(20) primary key not null,--�������
	Orderform_time datetime,--����ʱ��
	Customer_id varchar(20),--�˿ͱ��
	Supplier_id varchar(20),--��Ӧ�̱��
	Supplier_total int--��Ӧ��
)
drop table Order_detail
go
create table Order_detail--������ϸ��
(
	Orderform_id varchar(20),--�������
	Book_num int ,--ͼ������
	Book_id varchar(20),--ͼ���
	Pay_total float--С��
)
--drop table Book
select * from Supplier
drop table Book
go
create table Book--ͼ���
(
	Book_id varchar(20) primary key not null,--ͼ���
	Book_in_price float,--����
	Book_out_price float,--�ۼ�
	Book_name varchar(20),--����
	Supplier_name varchar(20),--��Ӧ������
	Book_storage_time datetime,--ͼ�����ʱ��
	Book_stock int,--���
	Book_kind varchar(20)--ͼ�����
)

drop table Pay_message
go
create table Pay_message--֧����Ϣ��
(
	Orderform_id varchar(20) primary key not null,--������
	Pay_money float,--֧���ܶ�
	Pay_mode varchar(20)--֧����ʽ
)
drop table Customer
go
create table Customer--�˿ͱ�
(
	Customer_id varchar(20) primary key not null,--�˿ͺ�
	Customer_vip bit,--�Ƿ��Ա
	Customer_vip_money float,--��Ա�����
	Customer_tel varchar(20) --�˿���ϵ�绰
) 


-------------------��������
create unique index  Orderform_index on Orderform(Orderform_time desc)

create unique index  Supplier_index on Supplier(Supplier_id asc)

create unique index  Order_detail_index on Order_detail(Orderform_id asc)

create unique index  Book_index on Book(Book_id asc)

create unique index Pay_message_index on Pay_message(Orderform_id asc)

create unique index  Customer_index on Customer(Customer_id asc)
