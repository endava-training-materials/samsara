package com.endavatraining;

import org.testng.annotations.Test;
import io.restassured.RestAssured;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RESTClient {

	@Test
	public void testGetApiUsers() {

		String expectedFirstName = "Finn";
		try {
			Response res = RestAssured.given().auth().basic("admin", "password")
					.headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
					.get("http://localhost:9010/api/users");

			int status = res.getStatusCode();
			JSONObject jsonObjectBody = new JSONObject(res.getBody().asString());
			String sBody = jsonObjectBody.toString();
			System.out.println("Response body is: " + sBody);

			String firstName = jsonObjectBody.getJSONObject("_embedded").getJSONArray("users").getJSONObject(0)
					.getString("firstName");
			System.out.println(firstName);

			assert status == 200 : "Expected 200 but got: " + status;
			assert firstName.equals(expectedFirstName) : "Expected first name to be: " + expectedFirstName
					+ " but got: " + firstName;

		} catch (JSONException ex1) {
			System.out.print("JSON exception: " + ex1.getMessage());
		} catch (Exception ex2) {
			System.out.print("Exception: " + ex2.getMessage());
		}

	}

	@Test
	public void testPostApiUsers() {

		Date dt = new Date();

		String firstName = "First " + dt.getTime();
		String lastName = "Last " + dt.getTime();
		String username = "Username " + dt.getTime();
		String password = "password";
		String about = "About" + dt.getTime();
		String secretQuestion = "Secret Question" + dt.getTime();
		String secretAnswer = "Secret Answer" + dt.getTime();
		String email = "test@gmail.com";

		String userJson = "{\r\n" + "	\"firstName\" : \"" + firstName + "\",\r\n" + "	\"lastName\" : \"" + lastName
				+ "\",\r\n" + "	\"username\" : \"" + username + "\",\r\n" + "	\"password\" : \"" + password
				+ "\",\r\n" + "	\"about\" : \"" + about + "\",\r\n" + "	\"secretQuestion\" : \"" + secretQuestion
				+ "\",\r\n" + "	\"secretAnswer\" : \"" + secretAnswer + "\",\r\n" + "	\"email\" : \"" + email
				+ "\"\r\n" + "}";

		try {
			Response res = RestAssured.given().auth().basic("admin", "password")
					.headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).body(userJson)
					.post("http://localhost:9010/api/users");

			int status = res.getStatusCode();
			JSONObject jsonObjectBody = new JSONObject(res.getBody().asString());
			String sBody = jsonObjectBody.toString();
			System.out.println("Response body is: " + sBody);

			assert status == 200 : "Expected 200 but got: " + status;
		} catch (JSONException ex1) {
			System.out.print("JSON exception: " + ex1.getMessage());
		} catch (Exception ex2) {
			System.out.print("Exception: " + ex2.getMessage());
		}

	}
	
//	private void iterateThroughJSON(JSONObject jsonObj){
//		System.out.println("Iterate from json child elements");
//		for (int i = 0; i < jsonObj.names().length(); i++) {
//			System.out.print("key = " + jsonObj.names().getString(i) + " value = "
//					+ jsonObj.get(jsonObj.names().getString(i)));
//		}
//	}

}
