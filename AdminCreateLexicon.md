# Introduction #

**Description:**

Adds a new Lexicon entry to the system.

**Preconditions:**
  1. The user has the role of Administrator.
  1. The user is logged into the administration interface.
  1. The user is viewing the Add New Lexicon page.

**Postconditions:**
  1. A new Lexicon with the entered key and value will exist in the database.

# Basic Course of Action #

  1. User wants to add a new Lexicon entry.
  1. User selects the appropriate language for the new Lexicon.
  1. User selects the appropriate topic for the new Lexicon.
  1. User enters a unique key value for the Lexicon.
  1. User enters the internationalized text for the Lexicon.
  1. User submits the form.
  1. The system verifies that the selected language exists in the database.
  1. the system verifies that the selected topic exists in the database.
  1. The system verifies that the entered key is unique for the language and topic.
  1. The system adds the Lexicon entry to the database.
  1. The system deletes the Lexicon cache file for the language and topic.
  1. The system displays a success page to the user.
  1. Use case ends.