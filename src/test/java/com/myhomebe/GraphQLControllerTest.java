package com.myhomebe;

import org.json.simple.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GraphQLControllerTest{
  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  int randomServerPort;

  @Test
  public void testGetProject() throws URISyntaxException{
    final String baseUrl = "http://localhost:"+randomServerPort+"/api/v1/graphql/";
    URI uri = new URI(baseUrl);

		JSONObject body = new JSONObject();
		body.put("query", "query { project (id: 1) { id name description address city state zip_code rooms { name type description }}}");

    HttpEntity<JSONObject> request = new HttpEntity<JSONObject>(body);

    ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

    String expected = "{\"data\":{\"project\":{\"id\":1,\"name\":\"House 1\",\"description\":\"Big, white house\",\"address\":\"123 Fake St.\",\"city\":\"Denver\",\"state\":\"CO\",\"zip_code\":\"80205\",\"rooms\":[{\"name\":\"Living Room 1\",\"type\":\"Living Room\",\"description\":\"Northeast living room\"},{\"name\":\"Room 2\",\"type\":\"Kitchen\",\"description\":\"Big Kitchen\"}]}}}";

    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());
    Assert.assertEquals(expected, result.getBody());
  }
}
