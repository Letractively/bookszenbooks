# Introduction #

**Description:**

Updates a Lexicon entry in the database.

**Preconditions:**
  1. The user has the role of Administrator.
  1. The user is logged into the administration interface.
  1. The user is viewing the Update Lexicon page.

**Postconditions:**
  1. The selected Lexicon entry will be updated with the entered text.

# Basic Course of Action #

  1. User wants to update a Lexicon entry.
  1. User selects the Update option for the chosen entry.
  1. User enters a new internationalized text value for the Lexicon entry.
  1. User submits the form.
  1. The system verifies that a Lexicon with the selected language, topic, and key exist in the database.
  1. The system updates the value field of the entry in the database.
  1. The system deletes the Lexicon cache file for the language and topic.
  1. The system displays a success page to the user.
  1. Use case ends.