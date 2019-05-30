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

  @Test
  public void testGetProjects() throws URISyntaxException{
    final String baseUrl = "http://localhost:"+randomServerPort+"/api/v1/graphql/";
    URI uri = new URI(baseUrl);

		JSONObject body = new JSONObject();
		body.put("query", "query { projects { id name description address city state zip_code rooms { id }}}");

    HttpEntity<JSONObject> request = new HttpEntity<JSONObject>(body);

    ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

    String expected = "{\"data\":{\"projects\":[{\"id\":1,\"name\":\"House 1\",\"description\":\"Big, white house\",\"address\":\"123 Fake St.\",\"city\":\"Denver\",\"state\":\"CO\",\"zip_code\":\"80205\",\"rooms\":[{\"id\":1},{\"id\":2}]},{\"id\":3,\"name\":\"House 4\",\"description\":\"made by graphql\",\"address\":\"another address\",\"city\":null,\"state\":null,\"zip_code\":null,\"rooms\":[{\"id\":4},{\"id\":5}]},{\"id\":2,\"name\":\"Updated House\",\"description\":\"Small with picket fence\",\"address\":\"456 Random Lane\",\"city\":\"Different City\",\"state\":\"CO\",\"zip_code\":\"80205\",\"rooms\":[{\"id\":3}]}]}}";

    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());
    Assert.assertEquals(expected, result.getBody());
  }

  @Test
  public void testCreateProject() throws URISyntaxException{
    final String baseUrl = "http://localhost:"+randomServerPort+"/api/v1/graphql/";
    URI uri = new URI(baseUrl);

		JSONObject body = new JSONObject();
		body.put("query", "mutation { createProject (project: {name: \"House 4\", description: \"made by graphql\", address: \"another address\"}, rooms: [{name: \"new room\", type: \"new type\"}, {name: \"new room 2\", type: \"room 2 type\"}]) {id name description address rooms {id name type}}}");

    HttpEntity<JSONObject> request = new HttpEntity<JSONObject>(body);

    ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

    String expected = "{\"data\":{\"createProject\":{\"id\":3,\"name\":\"House 4\",\"description\":\"made by graphql\",\"address\":\"another address\",\"rooms\":[{\"id\":4,\"name\":\"new room\",\"type\":\"new type\"},{\"id\":5,\"name\":\"new room 2\",\"type\":\"room 2 type\"}]}}}";
    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());
    Assert.assertEquals(expected, result.getBody());
  }

  @Test
  public void testUpdateProject() throws URISyntaxException{
    final String baseUrl = "http://localhost:"+randomServerPort+"/api/v1/graphql/";
    URI uri = new URI(baseUrl);

		JSONObject body = new JSONObject();
		body.put("query", "mutation{updateProject(project: {id: 2, name: \"Updated House\", city: \"Different City\"}) {id name city state}}");

    HttpEntity<JSONObject> request = new HttpEntity<JSONObject>(body);

    ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

    String expected = "{\"data\":{\"updateProject\":{\"id\":2,\"name\":\"Updated House\",\"city\":\"Different City\",\"state\":\"CO\"}}}";
    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());
    Assert.assertEquals(expected, result.getBody());
  }

  @Test
  public void testDeleteProject() throws URISyntaxException{
    final String baseUrl = "http://localhost:"+randomServerPort+"/api/v1/graphql/";
    URI uri = new URI(baseUrl);

		JSONObject body = new JSONObject();
		body.put("query", "mutation{deleteProject(id: 2)}");

    HttpEntity<JSONObject> request = new HttpEntity<JSONObject>(body);

    ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

    String expected = "{\"data\":{\"deleteProject\":null}}";
    //Verify request succeed
    Assert.assertEquals(200, result.getStatusCodeValue());
    Assert.assertEquals(expected, result.getBody());
  }

  @Test
  public void testUpdateProjectMaterial() throws URISyntaxException{
    final String baseUrl = "http://localhost:"+randomServerPort+"/api/v1/graphql/";
    URI uri = new URI(baseUrl);

    //Before update
		JSONObject body1 = new JSONObject();
		body1.put("query", "{room(id: 2) {id roomMaterials{element_type material{ name vendor brand model_number manual_url notes quantity unit_price}}}}");

    HttpEntity<JSONObject> request1 = new HttpEntity<JSONObject>(body1);

    ResponseEntity<String> result1 = this.restTemplate.postForEntity(uri, request1, String.class);

    String expected1 = "{\"data\":{\"room\":{\"id\":2,\"roomMaterials\":[{\"element_type\":\"Shower\",\"material\":{\"name\":\"Material 1\",\"vendor\":\"HD\",\"brand\":\"Kenmoore\",\"model_number\":\"abc1\",\"manual_url\":null,\"notes\":null,\"quantity\":null,\"unit_price\":null}}]}}}";

    Assert.assertEquals(200, result1.getStatusCodeValue());
    Assert.assertEquals(expected1, result1.getBody());

    //Update request
    JSONObject body2 = new JSONObject();
		body2.put("query", "mutation{updateProjectMaterial(roomMaterial_id: 3, element_type: \"Flooring\", material: {name: \"Updated material\", brand: \"Updated Brand\", model_number: \"Updated number\", vendor: \"Updated vendor\", manual_url: \"Updated url\", notes: \"Updated notes\", quantity: 5, unit_price: 500}) {id element_type material{name brand vendor manual_url unit_price quantity model_number notes}}}");

    HttpEntity<JSONObject> request2 = new HttpEntity<JSONObject>(body2);

    ResponseEntity<String> result2 = this.restTemplate.postForEntity(uri, request2, String.class);

    String expected2 = "{\"data\":{\"updateProjectMaterial\":{\"id\":3,\"element_type\":\"Flooring\",\"material\":{\"name\":\"Updated material\",\"brand\":\"Updated Brand\",\"vendor\":\"Updated vendor\",\"manual_url\":\"Updated url\",\"unit_price\":500.0,\"quantity\":5.0,\"model_number\":\"Updated number\",\"notes\":\"Updated notes\"}}}}";

    //Verify request succeed
    Assert.assertEquals(200, result2.getStatusCodeValue());
    Assert.assertEquals(expected2, result2.getBody());

    //Verify database after request
    JSONObject body3 = new JSONObject();
		body3.put("query", "{room(id: 2) {id roomMaterials{element_type material{ name vendor brand model_number manual_url notes quantity unit_price}}}}");

    HttpEntity<JSONObject> request3 = new HttpEntity<JSONObject>(body3);

    ResponseEntity<String> result3 = this.restTemplate.postForEntity(uri, request3, String.class);

    String expected3 = "{\"data\":{\"room\":{\"id\":2,\"roomMaterials\":[{\"element_type\":\"Flooring\",\"material\":{\"name\":\"Updated material\",\"vendor\":\"Updated vendor\",\"brand\":\"Updated Brand\",\"model_number\":\"Updated number\",\"manual_url\":\"Updated url\",\"notes\":\"Updated notes\",\"quantity\":5.0,\"unit_price\":500.0}}]}}}";

    Assert.assertEquals(200, result3.getStatusCodeValue());
    Assert.assertEquals(expected3, result3.getBody());
  }
}
