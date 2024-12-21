DROP DATABASE IF EXISTS skel;
CREATE DATABASE skel;
USE skel;

CREATE USER IF NOT EXISTS skel@localhost IDENTIFIED BY 'skel';
GRANT ALL ON skel.* TO skel@localhost;

CREATE TABLE IF NOT EXISTS usr(
	uname VARCHAR(32) PRIMARY KEY,
	pass VARCHAR(255),
	is_enabled BOOLEAN DEFAULT TRUE
); 

CREATE TABLE IF NOT EXISTS usr_auth(
	usr_uname VARCHAR(32) PRIMARY KEY,
	authority ENUM('USER','ADMIN') DEFAULT 'USER',
	
	FOREIGN KEY (usr_uname) 
		REFERENCES usr(uname)
		ON DELETE CASCADE
);

/* along with usr makes up the domain user */
/* customize based on your business needs */
CREATE TABLE IF NOT EXISTS usr_info(
	usr_uname VARCHAR(32) PRIMARY KEY,
	email VARCHAR(32) NOT NULL,
	fname VARCHAR(32) DEFAULT NULL,
	lname VARCHAR(32) DEFAULT NULL,
	phone_no VARCHAR(16) DEFAULT NULL,
	nat_code VARCHAR(32) DEFAULT NULL,
	
	FOREIGN KEY (usr_uname) 
		REFERENCES usr(uname)
		ON DELETE CASCADE
);

/* to get all user's information (except for authorities and token) and to show it to user */
CREATE VIEW IF NOT EXISTS domain_usr_view AS 
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

/* should be created once and only get updated upon change */
CREATE TABLE IF NOT EXISTS usr_stats(
	usr_uname VARCHAR(32) PRIMARY KEY,
	last_online DATETIME DEFAULT NULL,
	last_login DATETIME DEFAULT NULL,
	joined_at DATETIME DEFAULT NULL,
	validated BOOLEAN DEFAULT FALSE,
	
	FOREIGN KEY (usr_uname) 
		REFERENCES usr(uname)
		ON DELETE CASCADE
);

/* simple tokens instead of jwt (as of this version) */
CREATE TABLE IF NOT EXISTS usr_token(
	token VARCHAR(255) NOT NULL,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
	usr_uname VARCHAR(32) NOT NULL,
	
	FOREIGN KEY (usr_uname) 
		REFERENCES usr(uname)
		ON DELETE CASCADE	
);

/* to catch all user informations in one go */
CREATE VIEW IF NOT EXISTS usr_token_view AS
SELECT 
	u.uname AS uname,
	"-" AS pass,
	u.is_enabled AS is_enabled,
	ui.email AS email,
	ui.fname AS fname,
	ui.lname AS lname,
	ut.token AS token,
	"-" AS phone_no,
	"-" AS nat_code,
	ut.created_at AS created_at
FROM usr u INNER JOIN usr_token ut INNER JOIN usr_info ui
ON u.uname=ut.usr_uname AND u.uname=ui.usr_uname;

/* log user activities like login' and 'last online' etc.
CREATE TABLE IF NOT EXISTS usr_log(
	usr_uname VARCHAR(32) NOT NULL,
	title VARCHAR(255) NOT NULL,
	msg TEXT DEFAULT NULL,
	dt DATETIME DEFAULT CURRENT_TIMESTAMP,
	
	FOREIGN KEY (usr_uname) 
		REFERENCES usr(uname)
		ON DELETE CASCADE
);
*/

/*
TODO 
CREATE TABLE IF NOT EXISTS usr_validation(
	usr_uname VARCHAR(32) PRIMARY KEY,
	activation_code VARCHAR(32) NOT NULL,
	FOREIGN KEY usr_uname REFERENCES usr(uname)
);
*/
	