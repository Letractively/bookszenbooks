/* Book Categories */
INSERT INTO `bzb`.`bookcategory` (`categoryId` , `name` ) VALUES ( NULL , 'Biology' );
INSERT INTO `bzb`.`bookcategory` (`categoryId` , `name` ) VALUES ( NULL , 'Computer Science' );
INSERT INTO `bzb`.`bookcategory` (`categoryId` , `name` ) VALUES ( NULL , 'English' );
INSERT INTO `bzb`.`bookcategory` (`categoryId` , `name` ) VALUES ( NULL , 'Mathematics' );
INSERT INTO `bzb`.`bookcategory` (`categoryId` , `name` ) VALUES ( NULL , 'Physics' );
INSERT INTO `bzb`.`bookcategory` (`categoryId` , `name` ) VALUES ( NULL , 'Psychology' );

/* Lexicon Languages */
INSERT INTO `bzb`.`lexiconlanguage` (`language`, `name`) VALUES ('en', 'English');

/* Lexicon Topics */
INSERT INTO `bzb`.`lexicontopic` (`name`) VALUES ('global');
INSERT INTO `bzb`.`lexicontopic` (`name`) VALUES ('register');
INSERT INTO `bzb`.`lexicontopic` (`name`) VALUES ('login');
INSERT INTO `bzb`.`lexicontopic` (`name`) VALUES ('search');
INSERT INTO `bzb`.`lexicontopic` (`name`) VALUES ('checkout');

/* Users */
INSERT INTO `bzb`.`user` (`userId` ,`email` ,`password` ,`firstName` ,`lastName` ,`address` ,`city` ,`state` ,`country` ,
`postalCode` ,`phone` ,`joinDate` ,`birthDate` ,`validated` ,`superUser`)
VALUES (NULL , 'student@worcester.edu', MD5( 'test' ) , 'Rick', 'Varella', '10 Pryor Rd', 'Leicester', 'MA', 'US',
'01524', '508-892-5304', '2009-11-16 02:43:41', '1987-07-09', '1', '1');

/* System Settings */
INSERT INTO `bzb`.`systemsetting` (`key` ,`value` ,`title` ,`description`,`fieldType`)
VALUES ('siteTitle', 'Books Zen Books', 'Site Title', 'The name of the site.', 'text');
INSERT INTO `bzb`.`systemsetting` (`key` ,`value` ,`title` ,`description`,`fieldType`)
VALUES ('charset', 'UTF-8', 'Site Charset', 'The character set to use for displaying web pages.', 'text');
INSERT INTO `bzb`.`systemsetting` (`key` ,`value` ,`title` ,`description`,`fieldType`)
VALUES ('resultsPerPage', '20', 'Results Per Page', 'The number of results to display per page.', 'text');
INSERT INTO `bzb`.`systemsetting` (`key` ,`value` ,`title` ,`description`,`fieldType`)
VALUES ('useHttps', '0', 'Use https', 'Enable this setting to allow connections through SSL.', 'yes_no');
INSERT INTO `bzb`.`systemsetting` (`key` ,`value` ,`title` ,`description`,`fieldType`)
VALUES ('maxCookieLifetime', '1800', 'Maximum Cookie Lifetime', 'The maximum amount of time a cookie will be kept on the user\'s system before expiring.', 'text');

/* Books */
INSERT INTO `bzb`.`book` (`isbn`, `categoryId`, `title`, `author`, `pages`, `publisher`, `publishDate`, `language`)
VALUES ('9781890774448', '1', 'Java Servlets and JSP', 'Murach', '730', 'Mike Murach & Associates', '2008-01-23', 'en');
INSERT INTO `bzb`.`book` (`isbn`, `categoryId`, `title`, `author`, `pages`, `publisher`, `publishDate`, `language`)
VALUES ('7781810775548', '2', 'Math Goodies', 'Some Dude', '1089', 'Ronald McDonald', '1993-11-09', 'en');

/* Book Listings */
INSERT INTO `bzb`.`booklisting` (`listId` ,`userId` ,`isbn` ,`price` ,`comment` ,`currency` ,`edition` ,
`listDate` ,`active` ,`condition`)
VALUES ( NULL , '1', '9781890774448', '10.00', 'Awesome book. Jeff''s class rocks!', 'usd', '2',
'2009-11-16 09:51:48', '1', 'new');
INSERT INTO `bzb`.`booklisting` (`listId` ,`userId` ,`isbn` ,`price` ,`comment` ,`currency` ,`edition` ,
`listDate` ,`active` ,`condition`)
VALUES ( NULL , '1', '7781810775548', '50.00', 'Boring book, I can\'t believe Ronald McDonald published this!', 'usd', '2',
'2009-11-14 09:51:48', '1', 'poor');

/* Lexicon Entries */
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('welcome', 'global', 'en', 'Welcome back, %s.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('logIn', 'global', 'en', 'Log In');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('logOut', 'global', 'en', 'Log Out');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('forgotPassword', 'register', 'en', 'Forgot Password?');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('email', 'register', 'en', 'Email Address');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('password', 'register', 'en', 'Password');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('firstName', 'register', 'en', 'First Name');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('lastName', 'register', 'en', 'Last Name');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('address', 'register', 'en', 'Address');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('city', 'register', 'en', 'City');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('state', 'register', 'en', 'State');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('country', 'register', 'en', 'Country');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('postalCode', 'register', 'en', 'Postal Code');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('phoneNumber', 'register', 'en', 'Phone Number');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('birthDate', 'register', 'en', 'Birth Date');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('invalidLogin', 'register', 'en', 'The email and password you provided do not match any registered accounts.');