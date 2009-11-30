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
  fieldType varchar(20) NOT NULL default 'text',
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
  PRIMARY KEY (categoryId)) CHARACTER SET UTF8;

CREATE TABLE BookOrder (
  orderId       int(10) NOT NULL AUTO_INCREMENT,
  buyerId       int(10) NOT NULL,
  listId        int(10),
  orderDate     datetime NOT NULL,
  paymentMethod varchar(20) NOT NULL,
  PRIMARY KEY (orderId)) CHARACTER SET UTF8;

CREATE TABLE `User` (
  userId            int(10) NOT NULL AUTO_INCREMENT,
  email             varchar(50) NOT NULL UNIQUE,
  password          varchar(50) NOT NULL,
  firstName         varchar(30) NOT NULL default '',
  lastName          varchar(30) NOT NULL default '',
  address           varchar(100) default NULL,
  city              varchar(100) default NULL,
  state             varchar(50) default NULL,
  country           varchar(50) default NULL,
  postalCode        varchar(5) default NULL,
  phone             varchar(15) default NULL,
  joinDate          datetime NOT NULL,
  birthDate         date default NULL,
  validated         tinyint(1) NOT NULL default 0,
  superUser         tinyint(1) NOT NULL default 0,
  validationCode    varchar(50) default NULL,
  PRIMARY KEY (userId)) CHARACTER SET UTF8;

CREATE TABLE Book (
  isbn        varchar(13) NOT NULL,
  categoryId  int(10) NOT NULL,
  title       varchar(100) NOT NULL,
  author      varchar(100) NOT NULL,
  pages       smallint(5) NOT NULL,
  publisher   varchar(30) NOT NULL,
  publishDate date NOT NULL,
  edition     smallint(3) NOT NULL,
  format      varchar(20) NOT NULL,
  language    varchar(2) NOT NULL,
  PRIMARY KEY (isbn)) CHARACTER SET UTF8;

CREATE TABLE BookListing (
  listId      int(10) NOT NULL AUTO_INCREMENT,
  userId      int(10) NOT NULL,
  isbn        varchar(13) NOT NULL,
  price       decimal(5, 2) NOT NULL,
  comment     text,
  currency    varchar(10) NOT NULL,
  listDate    datetime NOT NULL,
  active      tinyint(1) NOT NULL,
  `condition` varchar(10) NOT NULL,
  PRIMARY KEY (listId)) CHARACTER SET UTF8;
