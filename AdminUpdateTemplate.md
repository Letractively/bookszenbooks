# Introduction #

**Description:**

Updates the content of a HTML Template in the database.

**Preconditions:**
  1. The user has the role of Administrator.
  1. The user is logged into the administration interface.
  1. The user is viewing the Update Template page for the template he wants to edit.

**Postconditions:**
  1. The selected template will be updated with the changed HTML markup content.

# Basic Course of Action #

  1. User wants to update the content of a HTML Template.
  1. User makes changes to the existing HTML markup.
  1. User submits the form.
  1. The system verifies that the template exists in the database.
  1. The system updates the template content in the database.
  1. The system deletes the cache file for the template.
  1. The system displays a success page to the user.
  1. Use case ends.