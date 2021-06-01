USE CUOIKY
GO
CREATE PROCEDURE [dbo].[SPSuaKhachHang]
@USER_ID integer, @NAME nvarchar(30), @ADDRESS nvarchar(30), @PHONE varchar(15)
as
Begin
Update USER_INFOR set NAME=@NAME, ADDRESS=@ADDRESS, PHONE=@PHONE
where USER_ID =@USER_ID 
End

go

CREATE PROCEDURE [dbo].[SPSuaHoaDon]
@ID_BILL integer, @ID_USER integer,@DATE date =NULL, @SUM Money,@STATUS STATUS
as
Begin
Update BILL set ID_BILL=@ID_BILL, ID_USER=@ID_USER, DATE=@DATE,STATUS=@STATUS
where ID_BILL=@ID_BILL
End
go
CREATE PROCEDURE [dbo].[SPSuaTaiKhoan]
@EMAIL varchar(30), @password varchar(12)
as
Begin
Update USERS set password=@password
Where EMAIL=@EMAIL
End