USE CUOIKY
GO

--hàm trả tìm sản phẩm theo tên
create function sanpham(@name_sp nvarchar(12))
returns table
AS Return
	Select*
	From PRODUCT
	Where NAME like '%'+@name_sp+'%'
--- Hàm trả về chi tiết hóa đơn theo mã hóa đơn tìm kiếm
Create function chitiethoadon (@ID_HD Int)
Returns table
AS
		Return Select *
		From DETAIL
		where DETAIL.ID_BILL = @ID_HD
Go
--- Hàm trả về hóa đơn theo ngày
go
create function TimHoaDonTheoNgay(@ngay Smalldatetime)
returns table
AS Return 
	select*
	From BILL
	Where getdate(BILL.DATE)=getdate(@ngay) 
--hàm trả tìm sản phẩm theo tên sau đó xếp tăng dần theo giá
go
create function XepTang(@tensp varchar(12))
RETURNS TABLE
AS RETURN
	Select*
	From PRODUCT
	where PRODUCT.NAME like '%'+@tensp+'%'
	ORDER BY PRICE ASC

GO