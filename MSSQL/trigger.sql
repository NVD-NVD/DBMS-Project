USE CUOIKY

/* số lượng  hàng đặt phải nhỏ hơn số lượng tồn kho
cập nhật hàng trong kho sau khi đặt hàng hoặc cập nhật  */

go

CREATE TRIGGER DatHang ON DETAIL AFTER INSERT AS 

BEGIN
	IF((SELECT QUANTILY FROM INSERTED)>(SELECT QUANTILY_WH FROM WAREHOUSE join inserted on inserted.goodsid=warehouse.goodsid
	Where INSERTED.GOODSID=warehouse.goodsid)  )
	BEGIN
		PRINT'BAN DA NHAP NHIEU HON SL SP TON KHO';
		ROLLBACK;
	END
	ELSE
	BEGIN

		UPDATE WAREHOUSE
		SET QUANTILY_WH = QUANTILY_WH - (
		SELECT QUANTILY
		FROM inserted
		WHERE GOODSID = WAREHOUSE.GOODSID
	)
	FROM WAREHOUSE
	JOIN inserted ON WAREHOUSE.GOODSID = inserted.GOODSID
	END
END
GO
/* cập nhật hàng trong kho sau khi cập nhật đặt hàng */
--DROP TRIGGER CAPNHATDATHANG
GO

CREATE TRIGGER CAPNHATDATHANG on DETAIL after update AS
BEGIN
	IF((SELECT QUANTILY_WH+DELETED.QUANTILY-INSERTED.QUANTILY
		FROM WAREHOUSE INNER JOIN DELETED ON WAREHOUSE.GOODSID=DELETED.GOODSID
		INNER JOIN INSERTED ON WAREHOUSE.GOODSID=INSERTED.GOODSID)
		<0)
	BEGIN
		PRINT'CAP NHAT HANG KHONG THANH CONG'
		ROLLBACK
	END
	ELSE
   BEGIN
   UPDATE WAREHOUSE SET QUANTILY_WH = QUANTILY_WH -
	   (SELECT QUANTILY FROM inserted WHERE GOODSID = WAREHOUSE.GOODSID) +
	   (SELECT QUANTILY FROM deleted WHERE GOODSID = WAREHOUSE.GOODSID)
	   FROM WAREHOUSE 
	   JOIN deleted ON WAREHOUSE.GOODSID = deleted.GOODSID
	END
END
GO
/* cập nhật hàng trong kho khi đơn hàng bị hủy*/
--DROP TRIGGER HUYHANG
GO
CREATE TRIGGER HUYHANG ON DETAIL 
AFTER DELETE AS
BEGIN
 UPDATE  WAREHOUSE
 SET QUANTILY_WH+=(SELECT QUANTILY FROM DELETED WHERE GOODSID=WAREHOUSE.GOODSID)
					FROM WAREHOUSE JOIN DELETED ON WAREHOUSE.GOODSID=DELETED.GOODSID
END

GO