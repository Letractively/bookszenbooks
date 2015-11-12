# Introduction #

**Description:**

Removes a book from the user's book listing page.

**Preconditions:**
  1. The user is logged into a registered and active account.
  1. The user has the book he/she wants to remove listed for sale.
  1. The book has not been purchased by another user.
  1. The user is on his/her "My Current Listings" Page.

**Postconditions:**
  1. The book will no longer be displayed on the user's Book Listings page.
  1. Users will no longer be able to purchase the book from the user.

# Basic Course of Action #

  1. A user wants to remove Book A from his/her book listing page.
  1. The user finds the book he/she wants to remove from the list of **active** listings.
  1. The user clicks the "Remove From Listing" button.
  1. The system displays a message confirming that the user wants to remove the book.
  1. The user agrees.
  1. The system checks that the book listing is "safe" to remove (has not been purchased already, and is active).
  1. The system determines that the book listing may be removed.
  1. The system removes the book listing.
  1. The system displays a message to the user confirming that the book has been removed.
  1. Use case ends.