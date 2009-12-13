/* Book Categories */
INSERT INTO `bzb`.`booksubject` (`subjectId` , `text` ) VALUES ( NULL , 'biology' );
INSERT INTO `bzb`.`booksubject` (`subjectId` , `text` ) VALUES ( NULL , 'computerScience' );
INSERT INTO `bzb`.`booksubject` (`subjectId` , `text` ) VALUES ( NULL , 'english' );
INSERT INTO `bzb`.`booksubject` (`subjectId` , `text` ) VALUES ( NULL , 'mathematics' );
INSERT INTO `bzb`.`booksubject` (`subjectId` , `text` ) VALUES ( NULL , 'physics' );
INSERT INTO `bzb`.`booksubject` (`subjectId` , `text` ) VALUES ( NULL , 'psychology' );

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
INSERT INTO `bzb`.`systemsetting` (`key` ,`value` ,`title` ,`description`,`fieldType`)
VALUES ('bookLanguages', 'en,fr,es', 'Book Languages', 'Languages to be shown when selecting a language for a book.', 'text');
INSERT INTO `bzb`.`systemsetting` (`key` ,`value` ,`title` ,`description`,`fieldType`)
VALUES ('bookConditions', 'new,good,acceptable,poor', 'Book Conditions', 'Values describing the condition of a book.', 'text');

/* Books */
INSERT INTO `book` (`isbn`, `subjectId`, `title`, `author`, `pages`, `publisher`, `publishDate`, `edition`, `format`, `language`)
VALUES ('9781890774448', 2, 'Java Servlets and JSP', 'Joel Murach', 730, 'Mike Murach & Associates', '2008-01-23', 2, 'paperback', 'en');
INSERT INTO `book` (`isbn`, `subjectId`, `title`, `author`, `pages`, `publisher`, `publishDate`, `edition`, `format`, `language`)
VALUES ('7781810775548', 4, 'Math Goodies', 'Some Dude', 1089, 'Ronald McDonald', '1993-11-09', 1, 'hardcover', 'en');
INSERT INTO `book` (`isbn`, `subjectId`, `title`, `author`, `pages`, `publisher`, `publishDate`, `edition`, `format`, `language`)
VALUES ('0201700735', 2, 'The C++ Programming Language: Special Edition', 'Bjarne Stroustrup', 1030, 'Addison-Wesley Professional', '2000-02-11', 3, 'hardcover', 'en');
INSERT INTO `book` (`isbn`, `subjectId`, `title`, `author`, `pages`, `publisher`, `publishDate`, `edition`, `format`, `language`)
VALUES ('1890774464', 2, 'Murach''s C# 2008', 'Joel Murach', 800, 'Mike Murach & Associates', '2008-03-27', 3, 'paperback', 'en');

/* Book Listings */
INSERT INTO `bzb`.`booklisting` (`listId` ,`userId` ,`isbn` ,`price` ,`comment` ,`currency` ,`listDate` ,`active` ,`condition`)
VALUES ( NULL , '1', '9781890774448', '10.00', 'Awesome book. Jeff''s class rocks!', 'usd', '2009-11-16 09:51:48', '1', 'new');
INSERT INTO `bzb`.`booklisting` (`listId` ,`userId` ,`isbn` ,`price` ,`comment` ,`currency` ,`listDate` ,`active` ,`condition`)
VALUES ( NULL , '9', '7781810775548', '50.00', 'Boring book, I can\'t believe Ronald McDonald published this! Missing half of the front cover; page 213 is torn out.', 'usd', '2009-11-14 09:51:48', '1', 'poor');
INSERT INTO `bzb`.`booklisting` (`listId` ,`userId` ,`isbn` ,`price` ,`comment` ,`currency` ,`listDate` ,`active` ,`condition`)
VALUES (NULL , '1', '1890774464', '12.00', 'Good book!', 'usd', '2009-11-29 00:53:49', '1', 'new');
INSERT INTO `bzb`.`booklisting` (`listId`, `userId`, `isbn`, `price`, `comment`, `currency`, `listDate`, `active`, `condition`)
VALUES (NULL, '9', '0201700735', '8.00', 'Good information.', 'usd', '2009-11-23 00:56:20', '1', 'acceptable');

/* Lexicon Entries */
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('fileNotFound', 'error', 'en', 'Page Not Found' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('fileNotFoundDesc', 'error', 'en', 'The requested page was not found on the server.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('unauthorized', 'error', 'en', 'You Must Log In to Access This Feature' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('unauthorizedDesc', 'error', 'en', 'The feature you are trying to use is only available to registered members. Please log in to have access to it.' );


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
VALUES ('subjects', 'global', 'en', 'Subjects');

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
VALUES ('emailRegistered', 'register', 'en', 'An account is already registered with the email &quot;${email}&quot;.');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('emailInvalid', 'register', 'en', 'The format of the email address you entered is invalid. Email addresses must end in: @${validEmails}.');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('passwordNotMatch', 'register', 'en', 'The entered passwords do not match.');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('birthDateInvalid', 'register', 'en', 'The birthdate must be a valid date in MM/DD/YYYY format.');
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
VALUES ('emptyField', 'register', 'en', 'The ${field} field is required and cannot be left blank.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('loggedOut', 'register', 'en', 'You are now logged out of the website.' );

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
VALUES ('edition', 'book', 'en', 'Edition' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('pages', 'book', 'en', 'Pages' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('format', 'book', 'en', 'Format' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('hardcover', 'book', 'en', 'Hardcover' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('paperback', 'book', 'en', 'Paperback' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('biology', 'subject', 'en', 'Biology' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('computerScience', 'subject', 'en', 'Computer Science' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('english', 'subject', 'en', 'English' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('mathematics', 'subject', 'en', 'Mathematics' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('physics', 'subject', 'en', 'Physics' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('psychology', 'subject', 'en', 'Psychology' );

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
VALUES ('listedOn', 'search', 'en', 'Listed on' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('on', 'search', 'en', 'on' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('noResults', 'search', 'en', 'No results matching your search criteria were found.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('anyCondition', 'search', 'en', 'Any Condition' );

INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('listingNotFound', 'listing', 'en', 'Listing Not Found' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('listingIDInvalid', 'listing', 'en', 'The specified listing ID is invalid.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('listingInactive', 'listing', 'en', 'This listing is inactive and is no longer available for purchase.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('showingListing', 'listing', 'en', 'Showing Listing' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('bookDetails', 'listing', 'en', 'Textbook Details' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('by', 'listing', 'en', 'By' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('sellerComments', 'listing', 'en', 'Seller Comments' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('noComment', 'listing', 'en', 'The seller has not included a comment for this listing.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('listingDetails', 'listing', 'en', 'Listing Details' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('listDate', 'listing', 'en', 'List Date' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('sellerProfile', 'listing', 'en', 'View Seller\'s Profile' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('otherFormats', 'listing', 'en', 'Other Formats &amp; Editions' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('numberAvailable', 'listing', 'en', 'number Available' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('averagePrice', 'listing', 'en', 'Average Price' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('addToCart', 'listing', 'en', 'Add to Cart' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('beginAddListing', 'listing', 'en', 'To begin adding a new listing, enter the ISBN of your book and press &quot;Continue&quot;.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('newListing', 'listing', 'en', 'Add New Listing' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('updateListing', 'listing', 'en', 'Update Listing Details' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('continue', 'listing', 'en', 'Continue' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('invalidField', 'listing', 'en', 'The ${field} field is invalid.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('newListingNoMatch', 'listing', 'en', 'We don\'t have a book matching that ISBN yet. Please fill out the form completely to add the book to our system.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('newListingMatch', 'listing', 'en', 'We found a match for that ISBN! Please review the information below to make sure it matches your book\'s information.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('addListing', 'listing', 'en', 'Add New Listing' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('enterIsbn', 'listing', 'en', 'Enter Your Book\'s ISBN' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('enterBook', 'listing', 'en', 'Enter Your Book\'s Info' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('verifyBook', 'listing', 'en', 'Check Book Details' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('enterListing', 'listing', 'en', 'Enter Your Listing Info' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('enterListingDesc', 'listing', 'en', 'Enter your listing\'s information using the below form to finish adding your new listing.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('addSuccess', 'listing', 'en', 'Your new book listing was added successfully!' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('emptyField', 'listing', 'en', 'The ${field} field is required and cannot be left blank.' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('publishDateInvalid', 'listing', 'en', 'The Publish Date must be a valid date in MM/DD/YYYY format.');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('timeOut', 'listing', 'en', 'Sorry, your request has timed out and you must begin entering your listing data again.');
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('comments', 'listing', 'en', 'Your Comments');


INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('viewingProfile', 'profile', 'en', 'Viewing ${user}\'s Profile' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('memberSince', 'profile', 'en', 'Member Since' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('totalListings', 'profile', 'en', 'Total Listings' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('recentListings', 'profile', 'en', 'Recent Listings' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('userNotFound', 'profile', 'en', 'User Not Found' );
INSERT INTO `bzb`.`lexiconentry` (`key` ,`topic` ,`language` ,`value`)
VALUES ('userNotFoundDesc', 'profile', 'en', 'That user does not exist in the database.' );