# Introduction #

**Description:**
Puts an order in for the user's selected books to buy.

**Preconditions:**
  1. The user is logged in to a registered and active account.
  1. The user has at least one book in his/her shopping cart.

**Postconditions:**
  1. The user's order will be processed and the seller will be alerted that a request for his/her book(s) has been made.
  1. The selected book(s) will no longer be for sale.

# Basic Course of Action #

  1. A user wants to check out and place an order for the selected books in his/her shopping cart.
  1. The user visits his/her shopping cart page.
  1. The user clicks the "Checkout" button.
  1. The user enters his/her password for their bookstore account (for security reasons).
  1. The user enters his/her shipping address.
  1. The user is provided with a confirmation page for their order, allowing him/her to change quantities, remove items, or cancel.
  1. The user selects a billing method (Paypal or Google Checkout, for example).
  1. The user is brought to the third-party's website to continue the payment process.
  1. Once the payment is sent, the system receives confirmation that the payment completed successfully.
  1. The user returns to the bookstore website to view a confirmation screen for their order.
  1. An message is sent to the seller informing him/her about the purchased book.
  1. A message is sent to the buyer with order details.
  1. The book is removed from the listing page and can no longer be purchased.
  1. Use case ends.