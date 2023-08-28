package steps;

import cd.connect.example.model.BookInput;
import cd.connect.example.model.BookList;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cd.connect.service.ApiService;
import cucumber.api.java.en.When;

import javax.ws.rs.core.Response;

import static org.fest.assertions.api.Assertions.assertThat;


public class MyStepdefs {

	private ApiService apiService;
	private BookList bookList;

	public MyStepdefs(ApiService apiService) {
		this.apiService = apiService;
	}

	@Given("^I call get books api$")
	public void callGetBooksApi() throws Throwable {
		bookList = apiService.getBooksApi().listBooks();
	}

	@Then("^I should get a list of books$")
	public void shouldGetAListOfBooks() throws Throwable {
		System.out.println(bookList.getBooks().size());
		assertThat(bookList.getBooks().size()).isEqualTo(3);
	}

	@Given("^I add a book with id (.*) and name (.*)$")
	public void addABookWithIdAndName(String id, String name) throws Throwable {
		BookInput body = new BookInput();
		body.id(id);
		body.name(name);

		apiService.getBooksApi().postBook(body);
	}

	@Then("^I should be able to find this book in the system by id (.*)$")
	public void shouldHaveABookWithId(String id) throws Throwable {
		BookInput book = apiService.getBooksApi().getBook(id);
		assertThat(book.name).isNotEmpty();
	}

	@Given("^I update a book with id (.*) with a new name (.*)$")
	public void updateABookWithNewName(String id, String name) throws Throwable {
		BookInput body = new BookInput();
		body.id(id);
		body.name(name);

		apiService.getBooksApi().updateBook(id, body);
	}

	@Then("^A book with id (.*) should have a name (.*)$")
	public void aBookWithIdShouldHaveAName(String id, String name) throws Throwable {
		assertThat(apiService.getBooksApi().getBook(id).name).isEqualTo(name);
	}

	@When("^I delete a book with id (.*)$")
	public void deleteABookWithId(String id) throws Throwable {
		Response response = apiService.getBooksApi().deleteBook(id);
		assertThat(response.getStatus()).isEqualTo(204);
	}

	@Then("^a book with id (.*) should not be in the list$")
	public void aBookWithIdShouldNotBeInTheList(String id) throws Throwable {
		bookList = apiService.getBooksApi().listBooks();

		for(BookInput book : bookList.getBooks()){
			assertThat(book.id).isNotEqualTo(id);
		}
	}
}
