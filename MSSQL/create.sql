CREATE DATABASE CUOIKY
GO

USE CUOIKY

GO
CREATE TABLE USERS(
	ID INTEGER NOT NULL IDENTITY(10000,1),
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
	PRIMARY KEY(USER_ID)
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
CREATE TABLE PRODUCT(
	ID INTEGER CONSTRAINT MaSP PRIMARY KEY NOT NULL IDENTITY(100000,1),
	NAME CHAR(50) CONSTRAINT TenSP NOT NULL,
	CONTENT CHAR,
	PRICE INTEGER CONSTRAINT GIA CHECK(PRICE>=0) NOT NULL,
	QUANTILY INTEGER CONSTRAINT SLTONKHO CHECK(QUANTILY>=0) NOT NULL,
	IMGURL CHAR,
	)
GO
CREATE TABLE BILL(
	ID_BILL INTEGER CONSTRAINT MAHD UNIQUE NOT NULL IDENTITY(100000,1),
	ID_USER INTEGER CONSTRAINT IDKH FOREIGN KEY(ID_USER) REFERENCES USERS(ID) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
	DATE Smalldatetime CONSTRAINT NGAY NOT NULL,
	SUM Money CONSTRAINT TONG CHECK(SUM>=0),
	STATUS bit,
	PRIMARY KEY (ID_BILL)
	)
	Go
CREATE TABLE DETAIL(
	ID_BILL INTEGER REFERENCES BILL(ID_BILL) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
	ID_PRODUCT INTEGER REFERENCES PRODUCT(ID) ON UPDATE CASCADE NOT NULL ,
	QUANTILY INTEGER CONSTRAINT SL CHECK(QUANTILY>=0) NOT NULL,
	PRIMARY KEY (ID_BILL,ID_PRODUCT)
	)
Go
CREATE TABLE CART(
	ID INTEGER NOT NULL PRIMARY KEY,
	ID_USER INTEGER NOT NULL,
	ID_PRODUCT INTEGER NOT NULL,
	QUANTILY INTEGER NOT NULL
)
