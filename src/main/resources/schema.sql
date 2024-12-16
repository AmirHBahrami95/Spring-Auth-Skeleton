CREATE DATABASE IF NOT EXISTS skel;
USE skel;

DROP TABLE IF EXISTS usr;
DROP TABLE IF EXISTS usr_auth;
DROP TABLE IF EXISTS usr_info;
DROP VIEW  IF EXISTS usr_auth_view;
DROP VIEW  IF EXISTS usr_extended_view;

CREATE TABLE IF NOT EXISTS usr(
	uname VARCHAR(32) PRIMARY KEY,
	pass VARCHAR(255),
	is_enabled BOOLEAN DEFAULT TRUE
); 

CREATE TABLE IF NOT EXISTS usr_auth(
	usr_uname VARCHAR(32) PRIMARY KEY,
	authority ENUM('USER','ADMIN') DEFAULT 'USER' 
);

/* customize based on your business needs */
CREATE TABLE IF NOT EXISTS usr_info(
	usr_uname VARCHAR(32) PRIMARY KEY,
	email VARCHAR(32) NOT NULL,
	fname VARCHAR(32) DEFAULT NULL,
	lname VARCHAR(32) DEFAULT NULL,
	phone_no VARCHAR(16) DEFAULT NULL,
	nat_code VARCHAR(32) DEFAULT NULL
);

/* you can add other tables like usr_addr if u want */

/* to get authorities for each user */
CREATE VIEW IF NOT EXISTS usr_auth_view AS 
SELECT 
	u.uname AS uname,
	u.pass AS pass,
	u.is_enabled AS is_enabled,
	ue.authority AS authority
FROM usr u 
INNER JOIN usr_auth ue 
ON u.uname=ue.usr_uname;

/* to get all user's information (except for authorities) */
CREATE VIEW IF NOT EXISTS usr_extended_view AS 
SELECT 
	u.uname AS uname,
	u.pass AS pass,
	u.is_enabled AS is_enabled,
	ui.email AS email,
	ui.fname AS fname,
	ui.lname AS lname,
	ui.phone_no AS phone_no,
	ui.nat_code AS nat_code
FROM usr u
INNER JOIN usr_info ui
ON u.uname=ui.usr_uname;
	