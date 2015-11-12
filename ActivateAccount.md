# Introduction #

**Description:**

Activates a new user account.

**Preconditions:**
  1. The user must have already registered a new account.
  1. The system must have sent an activation email to the user.
  1. The user must have received the email containing an activation link.

**Postconditions:**
  1. The user's account will be activated.
  1. The user will have full access to all student account features.

# Basic Course of Action #

  1. The user clicks on the activation link found in the activation email sent by the system.
  1. The system checks that the user exists in the database.
  1. The system verifies that the provided activation code matches the code stored in the database.
  1. The system activates the user's account.
  1. The system displays the Activation Success page.
  1. Use case ends.