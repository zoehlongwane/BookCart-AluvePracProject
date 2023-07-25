package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import services.Services;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookCartSteps {
    Services services;
    Response response;
    private String id;
    List<String> books;

    @Given("the user sets the HTTP method {string}")
    public void theUserSetsTheHTTPMethod(String method) {
        services.setHTTPMethod(method);
    }

    @And("the service endpoint {string}")
    public void theServiceEndpoint(String endpoint) {
        services.setEndpoint(endpoint);
    }

    @When("the user searches for the book")
    public void theUserSearchesForTheBook() {
        services.build();
        response = services.send();

    }

    @When("the user searches for the book with the name {string}")
    public void theUserSearchesForTheBookWithTheName(String bookName) {
        String targetBookName = bookName;
        boolean found = false;

        for (String bookName : bookNames) {
            if (bookName.equals(targetBookName)) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Found the book: " + targetBookName);
        } else {
            System.out.println("Book not found in the list.");
        }
    }
    @Then("I should find the book id")
    public void iShouldFindTheBookId() {
        id = String.valueOf(books.get().body().
                jsonPath().getInt("bookId[0]"));
    }

    @When("the user searches for the book similar to Soul of the Sword by {string}")
    public void theUserSearchesForTheBookSimilarToSoulOfTheSwordBy(String arg0) {
        
    }

    @Then("the list of similar books include {string} with the price {string} and author as {string} and category as {string}")
    public void theListOfSimilarBooksIncludeWithThePriceAndAuthorAsAndCategoryAs(String arg0, String arg1, String arg2, String arg3) {
        boolean bookFound = false;
        for (Book book : searchResults) {
            if (book.getName().equals(expectedBookName)) {
                bookFound = true;
                // Now you can perform further assertions to check book details
                assertEquals(expectedBookDetails.get(0).getPrice(), book.getPrice());
                assertEquals(expectedBookDetails.get(0).getAuthor(), book.getAuthor());
                assertEquals(expectedBookDetails.get(0).getCategory(), book.getCategory());
                break;
            }
        }
    }


}