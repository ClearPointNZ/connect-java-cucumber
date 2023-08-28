Feature: Books API

  As a book shop administrator
  I want to be able to add, update, delete or view books
  So that I can manage book stock

  Scenario: Get list of books
	Given I call get books api
	Then I should get a list of books

  Scenario Outline: Create a new book
	When I add a book with id <id> and name <name>
	Then I should be able to find this book in the system by id <id>

	Examples:
	  | id       | name               |
	  | 01234567 | Peter Pan          |

  Scenario Outline: Update a book
	Given I update a book with id <id> with a new name <name>
	Then A book with id <id> should have a name <name>

	Examples:
	  |id        |name         |
	  |7654321   |Peter Pan    |


  Scenario Outline: Delete a book
	Given I add a book with id <id> and name <name>
	When I delete a book with id <id>
	And I call get books api
	Then a book with id <id> should not be in the list

	Examples:
	  | id      | name            |
	  | 2345678 | Treasure Island |
