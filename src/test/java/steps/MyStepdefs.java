package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cd.connect.samples.slackapp.api.MessagesService;
import cd.connect.samples.slackapp.api.Messagelist;
import cd.connect.service.ApiService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static org.fest.assertions.api.Assertions.assertThat;


public class MyStepdefs {


	private ApiService apiService;

	Messagelist messagelist;

	Response slackList;

	public MyStepdefs(ApiService apiService) {
		this.apiService = apiService;
	}


	@Given("^I call get messages api for user id (.*) from date (.*) to date (.*)$")
	public void iCallGetMessagesApiForUserUser_idFromTo(String userId, BigDecimal fromDate, BigDecimal toDate) throws Throwable {

				messagelist = apiService.messagesApi().gETMessages(userId, fromDate, toDate);
	}

	@Then("^I should get a list of messages$")
	public void iShouldGetAListOfMessages() throws Throwable {
		assertThat(messagelist).isNotEmpty();
	}

	@Given("^a message is sent to a slack channel$")
	public void aMessageIsSentThroughSlackApi() throws Throwable {

		slackList = apiService.slackApi();
		System.out.println(slackList.toString());

	}

	@Then("^a valid response is received with (.*)$")
	public void theResponseShouldContainMessage(int status) throws Throwable {

		assertThat(slackList.toString()).contains("ok");
		assertThat(slackList.getStatus()).isEqualTo(status);

	}
}
