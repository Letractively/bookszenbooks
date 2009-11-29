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
`postalCode` ,`phone` ,`joinDate` ,`birthDate` ,`validated` ,`superUser` ,`validationCode`)
VALUES (NULL , 'student@worcester.edu', MD5( 'test' ) , 'Rick', 'Varella', '10 Pryor Rd', 'Leicester', 'MA', 'US',
'01524', '508-892-5304', '2009-11-16 02:43:41', '1987-07-09', '1', '1', NULL);

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
INSERT INTO `bzb`.`systemsetting` (`key` ,`value` ,`title` ,`description`,`fieldType`)
VALUES ('validEmailDomains', 'worcester.edu', 'Valid Email Domains', 'The email domains that are allowed to register an account.', 'text');

/* Books */
INSERT INTO `bzb`.`book` (`isbn`, `categoryId`, `title`, `author`, `pages`, `publisher`, `publishDate`, `edition`, `language`)
VALUES ('9781890774448', '1', 'Java Servlets and JSP', 'Murach', '730', 'Mike Murach & Associates', '2008-01-23', '2', 'en');
INSERT INTO `bzb`.`book` (`isbn`, `categoryId`, `title`, `author`, `pages`, `publisher`, `publishDate`, `edition`, `language`)
VALUES ('7781810775548', '2', 'Math Goodies', 'Some Dude', '1089', 'Ronald McDonald', '1993-11-09', '1', 'en');

/* Book Listings */
INSERT INTO `bzb`.`booklisting` (`listId` ,`userId` ,`isbn` ,`price` ,`comment` ,`currency` ,
`listDate` ,`active` ,`condition`)
VALUES ( NULL , '1', '9781890774448', '10.00', 'Awesome book. Jeff''s class rocks!', 'usd',
'2009-11-16 09:51:48', '1', 'new');
INSERT INTO `bzb`.`booklisting` (`listId` ,`userId` ,`isbn` ,`price` ,`comment` ,`currency` ,
`listDate` ,`active` ,`condition`)
VALUES ( NULL , '9', '7781810775548', '50.00', 'Boring book, I can\'t believe Ronald McDonald published this!', 'usd',
'2009-11-14 09:51:48', '1', 'poor');

/* Lexicon Entries */
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('welcome', 'global', 'en', 'Welcome back, %s.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('logIn', 'global', 'en', 'Log In');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('logOut', 'global', 'en', 'Log Out');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('search', 'global', 'en', 'Search' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('go', 'global', 'en', 'Go' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('advancedSearch', 'global', 'en', 'Advanced Search' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('registerAccount', 'global', 'en', 'Register Account');

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
VALUES ('phone', 'register', 'en', 'Phone Number');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('birthDate', 'register', 'en', 'Birth Date');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('invalidLogin', 'register', 'en', 'The email and password you provided do not match any registered accounts.');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('confirmPassword', 'register', 'en', 'Confirm Password');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('agreeTerms', 'register', 'en', 'I agree with the Terms and Conditions of this website.');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('registerDesc', 'register', 'en', 'Use the below form to register for a new account. Registering for an account gives you access to member-only features.');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('emailInvalid', 'register', 'en', 'The entered email address is invalid.');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('emailRegistered', 'register', 'en', 'The entered email address is already registered.');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('passwordInvalid', 'register', 'en', 'The entered password is invalid.');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('firstNameInvalid', 'register', 'en', 'You must enter a first name.');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('lastNameInvalid', 'register', 'en', 'You must enter a last name.');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('passwordNotMatch', 'register', 'en', 'The entered passwords do not match.');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('birthDateInvalid', 'register', 'en', 'The entered birthdate is not a valid date.');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('agreeTermsEmpty', 'register', 'en', 'You must agree to the Terms and Conditions first.');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('home', 'global', 'en', 'Home' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('guest', 'global', 'en', 'guest' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('notValidated', 'register', 'en', 'You must <a href="accountLogin?action=confirm">validate your account</a> first before logging in.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('confirmDesc', 'register', 'en', 'Thanks for registering an account on our website! An email with a Confirmation Code has been sent to the email address your provided during registration. To complete your registration, copy the code in the email message to the below form.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('confirmCode', 'register', 'en', 'Confirmation Code' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('checkCode', 'register', 'en', 'Check Code' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('confirmSuccess', 'register', 'en', 'Thanks, your email address has been confirmed and your account has been activated; you can now log in!' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('codeInvalid', 'register', 'en', 'The entered code is invalid.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('confirmAccount', 'register', 'en', 'Confirm Account' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('registerSuccess', 'register', 'en', 'Registration Success' );

INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('author', 'book', 'en', 'Author' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('title', 'book', 'en', 'Title' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('isbn', 'book', 'en', 'ISBN' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('publisher', 'book', 'en', 'Publisher' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('subject', 'book', 'en', 'Subject' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('condition', 'book', 'en', 'Condition' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('language', 'book', 'en', 'Language' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('price', 'book', 'en', 'Price' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('publishDate', 'book', 'en', 'Publish Date' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('new', 'book', 'en', 'New' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('good', 'book', 'en', 'Good' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('acceptable', 'book', 'en', 'Acceptable' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('poor', 'book', 'en', 'Poor' );

INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('sortBy', 'search', 'en', 'Sort By' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('relevance', 'search', 'en', 'Relevance' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('searchResults', 'search', 'en', 'Search Results' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('by', 'search', 'en', 'by' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('listedBy', 'search', 'en', 'Listed by' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('on', 'search', 'en', 'on' );