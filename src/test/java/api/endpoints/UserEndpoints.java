 package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
	//POST User Endpoint
	public static Response createuser(User payload)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
		.when()
		        .post(Routes.post_url);
		return response;
		        
				
	}
	public static Response readuser(String username)
	{
		Response response =given()
				.pathParam("username", username)
		.when()    
		        .get(Routes.get_url);
		return response;
	}
	public static Response Updateuser(String username, User payload)
	{
		Response response =given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.body(payload)
	   .when()
	            .put(Routes.update_url);
		return response;
	}
    public static Response deleteuser(String username)
    {
    	Response response=given()
    			.pathParam("username", username)
        .when()
                .delete(Routes.delete_url);
    	return response;
    }
}
