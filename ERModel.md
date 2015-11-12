# The Diagram #

![http://bookszenbooks.googlecode.com/files/Entity_Relational_Diagram_12162009.png](http://bookszenbooks.googlecode.com/files/Entity_Relational_Diagram_12162009.png)

# The Details #

  * User - This table contains profile information for each user who registers.
  * Book - Each unique book has an entry in this table. It holds data specific to each book.
  * BookListing - When a user lists a book up for sale, a record is added to this table, with information specific to the user's listing.
  * BookOrder - Once an order is placed for a BookListing by a user, a record is added to this table with information specific to the order.
  * BookCategory - Every book has a category, or "major" field attached to it (eg. CS, MA, EN, etc).
  * ShoppingCartEntry - When a user adds a book to his/her cart, an entry is added to this table.
  * LexiconLanguage - These are the languages supported by the system for i18n support. They consist of a 2-digit IANA country code and a readable text name
  * LexiconTopic - These are the separate groups of lexicons for each area in the system (eg. global, login, search, etc).
  * Lexicon - This table contains records for each individual language string.
  * SystemSetting - wherever possible, system configuration settings will be stored in here so that they can be easily changed by the administrators.
  * HTMLTemplate - This table contains the HTML markup template content for each view.