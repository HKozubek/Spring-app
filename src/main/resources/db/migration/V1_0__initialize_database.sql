CREATE TABLE IF NOT EXISTS `scripts`(

	`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` varchar(50) NOT NULL,
--	UNIQUE
	`script` TEXT

);
INSERT INTO SCRIPTS (NAME, SCRIPT) VALUES ('hello_world' , 'return "Hello World."');
INSERT INTO SCRIPTS (NAME, SCRIPT) VALUES ('addition' , 'return p1 + p2');