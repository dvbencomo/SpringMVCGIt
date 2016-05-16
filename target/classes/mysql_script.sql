Insert into USERS (sso_id, password, first_name , last_name , email) values ('123401','12342','Daniel','Velázquez','dvbencomo@gmail.com');
Insert into USERS (sso_id, password, first_name , last_name , email) values ('123402','12352','Daniel','Velázquez','dvbencomo@gmail.com');
Insert into USERS (sso_id, password, first_name , last_name , email) values ('123403','123642','Daniel','Velázquez','dvbencomo@gmail.com');
Insert into USERS (sso_id, password, first_name , last_name , email) values ('123404','123742','Daniel','Velázquez','dvbencomo@gmail.com');
Insert into USERS (sso_id, password, first_name , last_name , email) values ('123405','123842','Daniel','Velázquez','dvbencomo@gmail.com');
Insert into USERS (sso_id, password, first_name , last_name , email) values ('123406','123942','Daniel','Velázquez','dvbencomo@gmail.com');
Insert into USERS (sso_id, password, first_name , last_name , email) values ('123407','123104','Daniel','Velázquez','dvbencomo@gmail.com');
Insert into USERS (sso_id, password, first_name , last_name , email) values ('123408','123114','Daniel','Velázquez','dvbencomo@gmail.com');
Insert into USERS (sso_id, password, first_name , last_name , email) values ('123409','123124','Daniel','Velázquez','dvbencomo@gmail.com');
Insert into USERS (sso_id, password, first_name , last_name , email) values ('123410','123134','Daniel','Velázquez','dvbencomo@gmail.com');
Insert into USERS (sso_id, password, first_name , last_name , email) values ('123411','123414','Daniel','Velázquez','dvbencomo@gmail.com');
Insert into USERS (sso_id, password, first_name , last_name , email) values ('123412','123415','Daniel','Velázquez','dvbencomo@gmail.com');
Insert into USERS (sso_id, password, first_name , last_name , email) values ('123413','123416','Daniel','Velázquez','dvbencomo@gmail.com');
Insert into USERS (sso_id, password, first_name , last_name , email) values ('123414','123417','Daniel','Velázquez','dvbencomo@gmail.com');
Insert into USERS (sso_id, password, first_name , last_name , email) values ('123415','123418','Daniel','Velázquez','dvbencomo@gmail.com');
Insert into USERS (sso_id, password, first_name , last_name , email) values ('123416','123419','Daniel','Velázquez','dvbencomo@gmail.com');


create table USERS (
   id BIGINT NOT NULL AUTO_INCREMENT,
   sso_id VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   last_name  VARCHAR(30) NOT NULL,
   email VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (sso_id)
);