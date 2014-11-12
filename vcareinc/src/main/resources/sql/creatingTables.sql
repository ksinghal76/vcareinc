SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS articles_category;
DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS classified_category;
DROP TABLE IF EXISTS classified;
DROP TABLE IF EXISTS events_category;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS deals;
DROP TABLE IF EXISTS listings_category;
DROP TABLE IF EXISTS listings;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS state;
DROP TABLE IF EXISTS promotioncode;
DROP TABLE IF EXISTS price;
DROP TABLE IF EXISTS fileupload;
DROP TABLE IF EXISTS creditcard;
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS address;

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `optionType` int(11) DEFAULT NULL,
  `pageTitle` varchar(255) DEFAULT NULL,
  `parentCategory_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6DD211ED40B629B` (`parentCategory_id`),
  CONSTRAINT `FK6DD211ED40B629B` FOREIGN KEY (`parentCategory_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

CREATE TABLE `fileupload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clientFilename` varchar(255) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `uploadType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `detail` longtext,
  `optionType` int(11) DEFAULT NULL,
  `priceType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

CREATE TABLE `promotioncode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `state` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4C7D47111EC540F` (`country_id`),
  CONSTRAINT `FK4C7D47111EC540F` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `locationName` varchar(255) DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `state_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1ED033D48AE6F6F` (`state_id`),
  KEY `FK1ED033D411EC540F` (`country_id`),
  CONSTRAINT `FK1ED033D411EC540F` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
  CONSTRAINT `FK1ED033D48AE6F6F` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activate` bit(1) NOT NULL,
  `companyName` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `createdBy` tinyblob,
  `email` varchar(255) NOT NULL,
  `enable` bit(1) DEFAULT NULL,
  `faxNumber` varchar(255) DEFAULT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `resetPassword` bit(1) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `FK36EBCB21008F4F` (`address_id`),
  CONSTRAINT `FK36EBCB21008F4F` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `user` tinyblob,
  `createdBy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK26F49622BD1DF1` (`createdBy_id`),
  CONSTRAINT `FK26F49622BD1DF1` FOREIGN KEY (`createdBy_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`roles_id`),
  KEY `FK142D688AA26607E5` (`user_id`),
  KEY `FK142D688A9B53BC5E` (`roles_id`),
  CONSTRAINT `FK142D688A9B53BC5E` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK142D688AA26607E5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `classified` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` float DEFAULT NULL,
  `contactEmail` varchar(255) DEFAULT NULL,
  `contactFax` varchar(255) DEFAULT NULL,
  `contactName` varchar(255) DEFAULT NULL,
  `contactPhoneNumber` varchar(255) DEFAULT NULL,
  `detailDescription` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `promotionCode` tinyblob,
  `status` int(11) DEFAULT NULL,
  `summaryDescription` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `imageUpload_id` int(11) DEFAULT NULL,
  `price_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK660AF63373CA946F` (`price_id`),
  KEY `FK660AF63321008F4F` (`address_id`),
  KEY `FK660AF633A26607E5` (`user_id`),
  KEY `FK660AF63365F9E186` (`imageUpload_id`),
  CONSTRAINT `FK660AF63321008F4F` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK660AF63365F9E186` FOREIGN KEY (`imageUpload_id`) REFERENCES `fileupload` (`id`),
  CONSTRAINT `FK660AF63373CA946F` FOREIGN KEY (`price_id`) REFERENCES `price` (`id`),
  CONSTRAINT `FK660AF633A26607E5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `country` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `classified_category` (
  `Classified_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`Classified_id`,`category_id`),
  UNIQUE KEY `category_id` (`category_id`),
  KEY `FKECDA5AADDBA0EA5` (`Classified_id`),
  KEY `FKECDA5AAF8739985` (`category_id`),
  CONSTRAINT `FKECDA5AADDBA0EA5` FOREIGN KEY (`Classified_id`) REFERENCES `classified` (`id`),
  CONSTRAINT `FKECDA5AAF8739985` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `creditcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `payerId` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK552751C9A26607E5` (`user_id`),
  CONSTRAINT `FK552751C9A26607E5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `deals` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `conditions` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `discountPrice` float DEFAULT NULL,
  `discountType` int(11) DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `listingTitle` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `summaryDescription` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `totalDeal` int(11) DEFAULT NULL,
  `visibility` int(11) DEFAULT NULL,
  `imageUpload_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3EDA167A26607E5` (`user_id`),
  KEY `FK3EDA16765F9E186` (`imageUpload_id`),
  CONSTRAINT `FK3EDA16765F9E186` FOREIGN KEY (`imageUpload_id`) REFERENCES `fileupload` (`id`),
  CONSTRAINT `FK3EDA167A26607E5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `events` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contactName` varchar(255) DEFAULT NULL,
  `day` varchar(255) DEFAULT NULL,
  `dayOfWeek` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `eventPeriod` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `month2` int(11) DEFAULT NULL,
  `period` int(11) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `precison` varchar(255) DEFAULT NULL,
  `recurring` bit(1) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `summaryDescription` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `untilDate` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `weekOfMonth` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `imageUpload_id` int(11) DEFAULT NULL,
  `price_id` bigint(20) DEFAULT NULL,
  `promotionCode_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7C6CCD3973CA946F` (`price_id`),
  KEY `FK7C6CCD399871FA2F` (`promotionCode_id`),
  KEY `FK7C6CCD3921008F4F` (`address_id`),
  KEY `FK7C6CCD39A26607E5` (`user_id`),
  KEY `FK7C6CCD3965F9E186` (`imageUpload_id`),
  CONSTRAINT `FK7C6CCD3921008F4F` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK7C6CCD3965F9E186` FOREIGN KEY (`imageUpload_id`) REFERENCES `fileupload` (`id`),
  CONSTRAINT `FK7C6CCD3973CA946F` FOREIGN KEY (`price_id`) REFERENCES `price` (`id`),
  CONSTRAINT `FK7C6CCD399871FA2F` FOREIGN KEY (`promotionCode_id`) REFERENCES `promotioncode` (`id`),
  CONSTRAINT `FK7C6CCD39A26607E5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `events_category` (
  `Events_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`Events_id`,`category_id`),
  UNIQUE KEY `category_id` (`category_id`),
  KEY `FKFD46556473960665` (`Events_id`),
  KEY `FKFD465564F8739985` (`category_id`),
  CONSTRAINT `FKFD46556473960665` FOREIGN KEY (`Events_id`) REFERENCES `events` (`id`),
  CONSTRAINT `FKFD465564F8739985` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `listings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `additionalFileDescription` varchar(255) DEFAULT NULL,
  `bestService` bit(1) DEFAULT NULL,
  `bestValue` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `displayUrl` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `facebookPage` varchar(255) DEFAULT NULL,
  `faxNumber` varchar(255) DEFAULT NULL,
  `hourOfWork` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `listingType` int(11) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `summaryDescription` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `videoDescription` varchar(255) DEFAULT NULL,
  `videoSnippet` varchar(255) DEFAULT NULL,
  `additionalFile_id` int(11) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `imageUpload_id` int(11) DEFAULT NULL,
  `price_id` bigint(20) DEFAULT NULL,
  `promotionCode_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5418FA8F73CA946F` (`price_id`),
  KEY `FK5418FA8F9871FA2F` (`promotionCode_id`),
  KEY `FK5418FA8F21008F4F` (`address_id`),
  KEY `FK5418FA8FA26607E5` (`user_id`),
  KEY `FK5418FA8F65F9E186` (`imageUpload_id`),
  KEY `FK5418FA8FB1FFB51F` (`additionalFile_id`),
  CONSTRAINT `FK5418FA8F21008F4F` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK5418FA8F65F9E186` FOREIGN KEY (`imageUpload_id`) REFERENCES `fileupload` (`id`),
  CONSTRAINT `FK5418FA8F73CA946F` FOREIGN KEY (`price_id`) REFERENCES `price` (`id`),
  CONSTRAINT `FK5418FA8F9871FA2F` FOREIGN KEY (`promotionCode_id`) REFERENCES `promotioncode` (`id`),
  CONSTRAINT `FK5418FA8FA26607E5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK5418FA8FB1FFB51F` FOREIGN KEY (`additionalFile_id`) REFERENCES `fileupload` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `listings_category` (
  `Listings_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`Listings_id`,`category_id`),
  KEY `FKA8C8CED6C57E5` (`Listings_id`),
  KEY `FKA8C8CEF8739985` (`category_id`),
  CONSTRAINT `FKA8C8CED6C57E5` FOREIGN KEY (`Listings_id`) REFERENCES `listings` (`id`),
  CONSTRAINT `FKA8C8CEF8739985` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `articles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `promotionCode` tinyblob,
  `publicationDate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `imageUpload_id` int(11) DEFAULT NULL,
  `price_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBA9B365D73CA946F` (`price_id`),
  KEY `FKBA9B365DA26607E5` (`user_id`),
  KEY `FKBA9B365D65F9E186` (`imageUpload_id`),
  CONSTRAINT `FKBA9B365D65F9E186` FOREIGN KEY (`imageUpload_id`) REFERENCES `fileupload` (`id`),
  CONSTRAINT `FKBA9B365D73CA946F` FOREIGN KEY (`price_id`) REFERENCES `price` (`id`),
  CONSTRAINT `FKBA9B365DA26607E5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `articles_category` (
  `Articles_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`Articles_id`,`category_id`),
  UNIQUE KEY `category_id` (`category_id`),
  KEY `FK4C91F4C0F8739985` (`category_id`),
  KEY `FK4C91F4C0815C1D25` (`Articles_id`),
  CONSTRAINT `FK4C91F4C0815C1D25` FOREIGN KEY (`Articles_id`) REFERENCES `articles` (`id`),
  CONSTRAINT `FK4C91F4C0F8739985` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

