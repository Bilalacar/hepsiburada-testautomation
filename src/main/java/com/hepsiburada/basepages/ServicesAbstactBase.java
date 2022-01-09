package com.hepsiburada.basepages;

import org.testng.Assert;
import com.jayway.jsonpath.JsonPath;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

public abstract class ServicesAbstactBase extends AbstractBase {

	protected String requestBody = null;
	protected String url = null;
	protected String groceryNamePath = "$.data[*].name";

	/**
	 * Get API servisini çağırıp response u string olarak döner.
	 * 
	 * @return string response.
	 * @author Bilal Acar
	 */
	public String servisCagirGet(String url, String serviceName, int statusCode) {

		Response response = null;

		try {
			RequestSpecification requestSpec = serviceHeaders();

			response = given().spec(requestSpec)
			                  .when()
			                  .get(url + serviceName)
			                  .then()
			                  .extract()
			                  .response();

			LogINFO(serviceName + " Servisi Response: " + response.asString());
			Control(response.getStatusCode() == statusCode, serviceName
			                                                + " Servisi Status Code:"
			                                                + statusCode
			                                                + " kontrolu basarılı", serviceName
			                                                                        + " Servisi Status Code:"
			                                                                        + statusCode
			                                                                        + " kontrolu başarısız. Servisten dönen Status: "
			                                                                        + response.getStatusLine());
		} catch (Exception e) {
			LogFAIL("açıklama" + e.toString());
			Assert.assertTrue(false, e.toString());
		}
		return response.asString();
	}

	/**
	 * Post API servisini çağırıp response u string olarak döner.
	 * 
	 * @return string response.
	 * @author Bilal Acar
	 */
	public String servisCagirPost(String url, String serviceName, String requestbody, int statusCode) {

		Response response = null;

		try {
			RequestSpecification requestSpec = serviceHeaders();

			response = given().spec(requestSpec)
			                  .body(requestbody)
			                  .when()
			                  .post(url + serviceName)
			                  .then()
			                  .extract()
			                  .response();

			LogINFO(serviceName + " Servisi Response: " + response.asString());
			Control(response.getStatusCode() == statusCode, serviceName
			                                         + " Servisi Status Code:"
			                                         + statusCode
			                                         + " kontrolu basarılı", serviceName
			                                                                 + " Servisi Status Code:"
			                                                                 + statusCode
			                                                                 + " kontrolu başarısız. Servisten dönen Status: "
			                                                                 + response.getStatusLine());
		} catch (Exception e) {
			LogFAIL("açıklama" + e.toString());
			Assert.assertTrue(false, e.toString());
		}
		return response.asString();
	}

	protected RequestSpecification serviceHeaders() {

		return new RequestSpecBuilder().setContentType("application/json")
		                               .addHeader("accept", "application/json, application/xml, text/json, text/x-json, text/javascript, text/xml")
		                               .setRelaxedHTTPSValidation()
		                               .build();
	}

	/**
	 * API servisinden dönen GroceryName i döner.
	 * 
	 * @return string output.
	 * @author Bilal Acar
	 */
	public String getGroceryName(String body) {

		List<String> groceryName = new ArrayList<String>();

		try {
			groceryName = JsonPath.parse(body)
			                      .read(groceryNamePath);
			return groceryName.get(0);
		} catch (Exception e) {
			LogINFO("Parse Error " + e);
			e.printStackTrace();
		}
		return groceryName.get(0);
	}
}
