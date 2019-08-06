CREATE TABLE IF NOT EXISTS `account`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `username` VARCHAR(20) NOT NULL UNIQUE,
   `password` VARCHAR(20) NOT NULL,
   `nickname` VARCHAR(20),
   `age` INT NOT NULL DEFAULT 0,
   `location` VARCHAR(20) NOT NULL,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into account (username, password, nickname, age, location) values ('lisz1012', '123', 'lisz', 18, 'Seattle');