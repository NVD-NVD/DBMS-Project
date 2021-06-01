create view ThongTinKH as
select ID_USER,name,address,phone
from USER_INFOR
create view Chitiethd as
select b.ID_USER,b.DATE,b.SUM,d.quantily
from BILL b join Detail d on b.id_bill=d.id_bill  
join cart w on d.ID_PRODUCT=w.ID_PRODUCT
create view phanquyen as
select u.email,r.role
from USERS u ,ROLE r
where u.id=r.id


GO