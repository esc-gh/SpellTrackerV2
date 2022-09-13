DROP TABLE IF EXISTS `spell`;

CREATE TABLE `spell` (
	`id` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(255) UNIQUE NOT NULL,
	`level` INT NOT NULL,
	`school` VARCHAR(255) NOT NULL,
    CHECK(`name` <> ''),
    CHECK(`school` <> ''),
    CHECK(`level` >= 0),
    CHECK(`level` <= 9)
);