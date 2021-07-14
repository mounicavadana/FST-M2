package restAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Project {
	
	

	String sshKey;
	int idkey;
    
String name="sdet1mounikakey";
RequestSpecification requestSpec;
 
@BeforeClass
public void setUp() {
    // Create request specification
    requestSpec = new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
        .addHeader("Authorization", "token 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")
        .setBaseUri("https://api.github.com")
        .build();
   
    sshKey = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDW9TYyH3BFso3BWxvLXw8R9pdtuLKfoslhGugewsZalRPWVG3TRJnhJ1cqNGQj/xi0105RgOQB/59wT9V3jKuDtszBWNWUuXHuvn3wkYJKk26PmQw7QH04+Kj/JL3n9SvH9zpQ9rt9tRt8MZpUxpDAiRxbJTvh3QVBk4AmeRa09thFkBQZ+cleBlUMSzMm2UNlKwiLouAcbF0KhaDQ7hb9D07iYrISkaMO1YKaYcpFRnYi/XQrN+MLn6zrQi+i+93ZYbKtX0ofnkPPl9PYRotETtcjezy3D1lhfvZ5ilZkyUrI0rFvGP/A12oOnNRGVxNXHPjt8u8+L0h+W+3PGHr9";
}
@Test(priority=1)
  public void post() {
	
	  String reqBody = "{"
	            + "\"title\": \"sdet1mounikakey\","
	            + "\"key\": \""+ sshKey
	        +"\" }";

	  System.out.println(reqBody);
		
			Response response = 
	        		given().spec(requestSpec).when().body(reqBody).post("/user/keys"); // Set headers
	          System.out.println(response.getBody().asPrettyString());

	        // Assertion
	       response.then().body("title", equalTo(name));
	        JsonPath jsonPathEvaluator=  response.jsonPath();
	     idkey=jsonPathEvaluator.get("id");

	        System.out.println(idkey);
	       
  }
@Test(priority=2)
public void getInfo() {
  
    		   Response response = 
	            given().log().all().spec(requestSpec) // Set headers
	            .pathParam("keyId", idkey) // Set path parameter
	            .get( "/user/keys/{keyId}");
    		   
    		  
    		   String body = response.getBody().asPrettyString();
    		   System.out.println(body);
    		   response.then().body("title", equalTo(name));
    		   response.then().statusCode(200);
}

@Test(priority=3)
public void deleteInfo() {
  
    		   Response response = 
	            given().log().all().spec(requestSpec) // Set headers
	            .pathParam("keyId", idkey) // Set path parameter
	            .delete( "/user/keys/{keyId}");
    		   
    		   String body = response.getBody().asPrettyString();
    		   System.out.println(body);
    		   response.then().statusCode(204);
    		   
}


}
