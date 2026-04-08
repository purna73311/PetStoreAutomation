package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	
		Faker faker;
		User userpayload;
		@BeforeClass 
		public void setupdata()
		{
			faker = new Faker();
			userpayload =new User();
			userpayload.setId(faker.idNumber().hashCode());
			userpayload.setUsername(faker.name().username());
			userpayload.setFirstName(faker.name().firstName());
			userpayload.setLastName(faker.name().lastName());
			userpayload.setEmail(faker.internet().safeEmailAddress());
			userpayload.setPassword(faker.internet().password(5,10));
			userpayload.setPhone(faker.phoneNumber().cellPhone());	
			
		}
		
		@Test(priority=1)
		public void testPostUser()
		{
			Response response=UserEndpoints2.createuser(userpayload);
			response.then().log ().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			
		}
		@Test(priority=2)
		public void testGetuserByName()
		{
			
			Response response=UserEndpoints2.readuser(this.userpayload.getUsername());
			response.then().log().all();
			System.out.println("Username"+userpayload.getUsername());
			Assert.assertEquals(response.getStatusCode(), 200);
			
		}
	    @Test(priority=3)
	    public void testUpdateUserByName()
	    {
	    	//update data using payload
	    	userpayload.setFirstName(faker.name().firstName());
			userpayload.setLastName(faker.name().lastName());
			userpayload.setEmail(faker.internet().safeEmailAddress());
			Response response = UserEndpoints2.Updateuser(this.userpayload.getUsername(), userpayload);
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			
			//Checking data After Update
			Response responseAfterUpdate=UserEndpoints2.readuser(this.userpayload.getUsername());
			response.then().log().all();
			Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	    }
			@Test(priority=4)
			public void testDeleteUserByName()
			{
				Response response=UserEndpoints2.deleteuser(this.userpayload.getUsername());
				Assert.assertEquals(response.getStatusCode(), 200);
			}
			
	    	

}
