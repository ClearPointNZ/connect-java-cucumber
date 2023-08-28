package cd.connect.service;


import cd.connect.example.api.BooksService;
import org.glassfish.jersey.client.proxy.WebResourceFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class ApiService {


	private Client client = ClientBuilder.newClient();
	private WebTarget webTarget = client.target("http://v4nf773olwkwrivb4-mock.stoplight-proxy.io/latest");

	public BooksService getBooksApi() {
		return WebResourceFactory.newResource(BooksService.class, webTarget);
	}

}
