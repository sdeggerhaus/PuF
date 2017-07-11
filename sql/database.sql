DROP TABLE IF EXISTS scores;

CREATE TABLE scores (
	SiD int(11) NOT NULL AUTO_INCREMENT,
	SDate Date NOT NULL,
	SUser varchar(50) NOT NULL,
	SValue int(11) NOT NULL,
	SRounds int(3) NULL,
	SLefties int(3) NULL,
	PRIMARY KEY (SiD)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;