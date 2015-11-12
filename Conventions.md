

# Use Case Scenarios #
  * Use the TemplateUseCase template when creating new use cases.
  * Keep steps clean, simple and to the point. Try not to combine multiple steps onto one line.

# Use Case Diagrams #
_To be discussed..._

# Sequence Diagrams #

## Formatting ##

Lifelines are used to indicate the interaction elements in a diagram. From left to right, we organize these elements like so:

  1. Actors
  1. Controllers
  1. UI Elements
  1. Business Classes
  1. Database

Messages will usually be displayed from left to right, and return values will usually be displayed right to left.

Messages from one object to another should have their labels justified closest to the arrow when possible.

Lifelines are usually left as anonymous, unless we pass the object around somewhere in the diagram (eg. a return value or parameter). All anonymous lifelines begin with a colon (":") followed by a space, followed by the type of object/class.

## Stereotypes ##
Stereotypes are displayed inside << >> symbols. The following are the common stereotypes used in our diagrams:

  * Actors: `<<user>>`.
  * Controllers: `<<controller>>`
  * Model: `<<model>>`
  * GUI (pages, windows, dialogs) Elements: `<<UI>>`
  * Object Creation Messages: `<<create>>`

# Source Code #
_To be discussed..._