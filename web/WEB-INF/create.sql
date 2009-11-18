CREATE TABLE ShoppingCartEntry (
  userId int(10) NOT NULL,
  listId int(10) NOT NULL,
  PRIMARY KEY (userId,
  listId)) CHARACTER SET UTF8;

CREATE TABLE SystemSetting (
  `key`       varchar(50) NOT NULL,
  value       text NOT NULL,
  title       varchar(100) NOT NULL,
  description text,
  field_type varchar(20) NOT NULL default 'text',
  PRIMARY KEY (`key`)) CHARACTER SET UTF8;

CREATE TABLE LexiconTopic (
  name varchar(50) NOT NULL,
  PRIMARY KEY (name)) CHARACTER SET UTF8;

CREATE TABLE LexiconLanguage (
  language varchar(2) NOT NULL,
  name     varchar(50) NOT NULL,
  PRIMARY KEY (language)) CHARACTER SET UTF8;

CREATE TABLE LexiconEntry (
  `key`    varchar(50) NOT NULL,
  topic    varchar(50) NOT NULL,
  language varchar(2) NOT NULL,
  value    text NOT NULL,
  PRIMARY KEY (`key`,
  topic,
  language)) CHARACTER SET UTF8;

CREATE TABLE BookCategory (
  categoryId int(10) NOT NULL AUTO_INCREMENT,
  name  varchar(30) NOT NULL UNIQUE,
  PRIMARY KEY (catId)) CHARACTER SET UTF8;

CREATE TABLE BookOrder (
  orderId       int(10) NOT NULL AUTO_INCREMENT,
  buyerId       int(10) NOT NULL,
  listId        int(10),
  orderDate     datetime NOT NULL,
  paymentMethod varchar(20) NOT NULL,
  PRIMARY KEY (orderId)) CHARACTER SET UTF8;

CREATE TABLE `User` (
  userId      int(10) NOT NULL AUTO_INCREMENT,
  email       varchar(50) NOT NULL UNIQUE,
  password    varchar(50) NOT NULL,
  firstName   varchar(30) NOT NULL,
  lastName    varchar(30) NOT NULL,
  address     varchar(100) NOT NULL,
  city        varchar(100) NOT NULL,
  state       varchar(50) NOT NULL,
  country     varchar(50) NOT NULL,
  postalCode  varchar(5) NOT NULL,
  phone       varchar(15) NOT NULL,
  joinDate    datetime NOT NULL,
  birthDate   date NOT NULL,
  validated tinyint(1) NOT NULL,
  superUser tinyint(1) NOT NULL,
  PRIMARY KEY (userId)) CHARACTER SET UTF8;

CREATE TABLE Book (
  isbn        bigint(13) NOT NULL,
  categoryId  int(10) NOT NULL,
  title       varchar(100) NOT NULL,
  author      varchar(100) NOT NULL,
  pages       smallint(5) NOT NULL,
  publisher   varchar(30) NOT NULL,
  publishDate date NOT NULL,
  language    varchar(2) NOT NULL,
  PRIMARY KEY (isbn)) CHARACTER SET UTF8;

CREATE TABLE BookListing (
  listId      int(10) NOT NULL AUTO_INCREMENT,
  userId      int(10) NOT NULL,
  isbn        bigint(13) NOT NULL,
  price       decimal(5, 2) NOT NULL,
  comment     text,
  currency    varchar(10) NOT NULL,
  edition     smallint(3) NOT NULL,
  listDate    datetime NOT NULL,
  isActive    tinyint(1) NOT NULL,
  `condition` varchar(10) NOT NULL,
  PRIMARY KEY (listId)) CHARACTER SET UTF8;
