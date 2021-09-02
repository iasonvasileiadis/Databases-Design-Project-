CREATE TABLE idiwtes (
i_name varchar(50),
i_email varchar(50),
i_afm int NOT NULL,
i_mobile int,
PRIMARY KEY (i_afm)
);
=============================================================
CREATE TABLE cars(
frame_number varchar(40) NOT NULL ,
traffic_number varchar(20) ,
manufacturer varchar(20) ,
model varchar(20) NOT NULL,
creation_date varchar (20) ,             
condition_type varchar(4) 
PRIMARY KEY (frame_number)
);
=============================================================
CREATE TABLE pwlhseis(
kwd_pwlhshs(20) NOT NULL,
frame_number varchar(20),
value numeric(10,2),
mdate varchar (10),
kwd_pwlhth varchar(20),
eidos_agoras varchar(20)
PRIMARY KEY (kwd_pwlhshs) 
);
=============================================================
CREATE TABLE ergazomenoi (
name varchar(50),
email varchar(50),
afm int NOT NULL,
PRIMARY KEY (afm)
);
=============================================================
CREATE TABLE episkeues (
kwd_episkeuhs varchar(10) NOT NULL,
frame_number varchar(40) NOT NULL,
estimated_cost numeric(10,2) NOT NULL,
start_date varchar(10) NOT NULL,
stop_date varchar (10),
PRIMARY KEY(kwd_episkeuhs)
);