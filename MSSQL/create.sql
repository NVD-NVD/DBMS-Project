CREATE DATABASE CUOIKY
GO

USE CUOIKY

GO
CREATE TABLE USERS(
	ID INTEGER NOT NULL,
	EMAIL VARCHAR(30) NOT NULL UNIQUE,
	PASSWORD VARCHAR(12) CONSTRAINT PASS NOT NULL,
	PRIMARY KEY(ID)
)
GO

CREATE TABLE USER_INFOR(
	USER_ID INT FOREIGN KEY (USER_ID) REFERENCES USERS(ID)  ON DELETE CASCADE  ON UPDATE CASCADE not null,
	NAME NVARCHAR(30) CONSTRAINT TEN NOT NULL,
	ADDRESS NVARCHAR(30) CONSTRAINT diachi NOT NULL ,
	PHONE VARCHAR(15) CONSTRAINT DienThoai NOT NULL unique,
	PRIMARY KEY(ID_USER)
)
go
CREATE TABLE ROLE(
	ID INTEGER PRIMARY KEY CONSTRAINT PK_ID NOT NULL,
	ROLE VARCHAR(12) CONSTRAINT CHUCVU NOT NULL)

GO
CREATE TABLE USER_ROLE(
	USER_ID INTEGER CONSTRAINT FK_IDUSER FOREIGN KEY (USER_ID) REFERENCES USERS(ID) ON DELETE CASCADE  ON UPDATE CASCADE,
	ROLE_ID INTEGER CONSTRAINT FK_IDROLE FOREIGN KEY (ROLE_ID)REFERENCES ROLE(ID) ON DELETE CASCADE ON UPDATE CASCADE
	PRIMARY KEY(USER_ID,ROLE_ID)
)

GO
CREATE TABLE WAREHOUSE(
	GOODSID INTEGER CONSTRAINT MaSP PRIMARY KEY NOT NULL,
	GOODSNAME NVARCHAR(12) CONSTRAINT TenSP NOT NULL,
	PRICE money CONSTRAINT GIA CHECK(PRICE>=0) NOT NULL,
	PRODUCER nvarchar(40) CONSTRAINT NSX not null,
	QUANTILY_WH INTEGER CONSTRAINT SLTONKHO CHECK(QUANTILY_WH>=0) NOT NULL
	)
GO
CREATE TABLE BILL(
	IDBILL INTEGER CONSTRAINT MAHD UNIQUE NOT NULL,
	IDUSER INTEGER CONSTRAINT IDKH FOREIGN KEY(IDUSER) REFERENCES USERS(ID) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
	DATE Smalldatetime CONSTRAINT NGAY NOT NULL,
	SUM Money CONSTRAINT TONG CHECK(SUM>=0)
	PRIMARY KEY (IDBILL)
	)
	Go
CREATE TABLE DETAIL(

	IDBILL INTEGER REFERENCES BILL(IDBILL) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
	GOODSID INTEGER REFERENCES WAREHOUSE(GOODSID) ON UPDATE CASCADE NOT NULL ,
	QUANTILY INTEGER CONSTRAINT SL CHECK(QUANTILY>=0) NOT NULL,
	PRIMARY KEY (IDBILL,GOODSID)
	)
Go
