Create database elanBanking;
use elanBanking;

CREATE TABLE users
(
username varchar(25),
pass varchar(25),
account_no integer(25) PRIMARY KEY AUTO_INCREMENT,
role varchar(25)
); 
 
INSERT INTO users (username,pass,role)
VALUES ('elan','elan','admin'); 

INSERT INTO users (username,pass,role)
VALUES ('user1','user1','user'); 

INSERT INTO users (username,pass,role)
VALUES ('user2','user2','user'); 

INSERT INTO users (username,pass,role)
VALUES ('user3','user3','user'); 

CREATE TABLE registration_details
(
username varchar(25) PRIMARY KEY,
pass varchar(25),
email varchar(50),
phone varchar(20),
address varchar(80),
idType varchar(20),
id varchar(20)
); 

CREATE TABLE account_details
(
account_no integer(25) PRIMARY KEY,
balance float(25),
score integer(25)
); 

INSERT INTO account_details (account_no,balance,score)
VALUES ('1','20000','700'); 

INSERT INTO account_details (account_no,balance,score)
VALUES ('2','20000','750'); 

INSERT INTO account_details (account_no,balance,score)
VALUES ('3','20000','650'); 

CREATE TABLE transaction_details
(
account_no integer(25),
transaction_date varchar(50),
transaction_amount float(50),
transaction_action varchar(20)
); 

INSERT INTO transaction_details (account_no,transaction_date,transaction_amount,transaction_action)
VALUES (1234,sysdate(),200,'credit'); 

CREATE TABLE cheque_details
(
account_no int(25),
cheque_no int(25),
amount float(50),
image longblob
); 

CREATE TABLE loan_details
(
account_no int(25),
loan_amount float(25),
tenure int(25),
status varchar(20)
); 

