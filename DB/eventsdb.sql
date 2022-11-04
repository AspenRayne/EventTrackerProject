-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema eventsdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `eventsdb` ;

-- -----------------------------------------------------
-- Schema eventsdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `eventsdb` DEFAULT CHARACTER SET utf8 ;
USE `eventsdb` ;

-- -----------------------------------------------------
-- Table `venue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `venue` ;

CREATE TABLE IF NOT EXISTS `venue` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `seat_geek_id` INT NULL,
  `city` VARCHAR(100) NULL,
  `name` VARCHAR(100) NOT NULL,
  `country` VARCHAR(100) NULL,
  `state` VARCHAR(100) NULL,
  `postal_code` VARCHAR(10) NULL,
  `ticket_url` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concert`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `concert` ;

CREATE TABLE IF NOT EXISTS `concert` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `ticket_url` VARCHAR(2000) NULL,
  `concert_date` DATETIME NOT NULL,
  `seat_geek_id` INT NULL,
  `venue_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_concert_venue1_idx` (`venue_id` ASC),
  CONSTRAINT `fk_concert_venue1`
    FOREIGN KEY (`venue_id`)
    REFERENCES `venue` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `performer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `performer` ;

CREATE TABLE IF NOT EXISTS `performer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `seat_geek_id` INT NULL,
  `name` VARCHAR(100) NOT NULL,
  `image_url` VARCHAR(1) NULL,
  `type` VARCHAR(45) NULL,
  `genre` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concert_has_performer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `concert_has_performer` ;

CREATE TABLE IF NOT EXISTS `concert_has_performer` (
  `concert_id` INT NOT NULL,
  `performer_id` INT NOT NULL,
  PRIMARY KEY (`concert_id`, `performer_id`),
  INDEX `fk_concert_has_performer_performer1_idx` (`performer_id` ASC),
  INDEX `fk_concert_has_performer_concert1_idx` (`concert_id` ASC),
  CONSTRAINT `fk_concert_has_performer_concert1`
    FOREIGN KEY (`concert_id`)
    REFERENCES `concert` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_concert_has_performer_performer1`
    FOREIGN KEY (`performer_id`)
    REFERENCES `performer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS eventsuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'eventsuser'@'localhost' IDENTIFIED BY 'eventsuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'eventsuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `venue`
-- -----------------------------------------------------
START TRANSACTION;
USE `eventsdb`;
INSERT INTO `venue` (`id`, `seat_geek_id`, `city`, `name`, `country`, `state`, `postal_code`, `ticket_url`) VALUES (1, 196, 'Morrison', 'Red Rocks', 'United States', 'CO', '80465', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `concert`
-- -----------------------------------------------------
START TRANSACTION;
USE `eventsdb`;
INSERT INTO `concert` (`id`, `title`, `ticket_url`, `concert_date`, `seat_geek_id`, `venue_id`) VALUES (1, NULL, NULL, '2022-11-04 6:00:00', NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `performer`
-- -----------------------------------------------------
START TRANSACTION;
USE `eventsdb`;
INSERT INTO `performer` (`id`, `seat_geek_id`, `name`, `image_url`, `type`, `genre`) VALUES (DEFAULT, NULL, 'Deadmau5', NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `concert_has_performer`
-- -----------------------------------------------------
START TRANSACTION;
USE `eventsdb`;
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (1, 1);

COMMIT;

