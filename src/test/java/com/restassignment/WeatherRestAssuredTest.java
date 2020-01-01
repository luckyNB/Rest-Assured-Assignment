package com.restassignment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.testng.Assert;

public class WeatherRestAssuredTest {

    @Test
    public void testgetAllWeatherReport() throws ParseException {
        // Specify the URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        // request Object
        RequestSpecification httpRequest = RestAssured.given();
        // Response Object
        Response response = httpRequest.request(io.restassured.http.Method.GET, "/Nagpur");
        //	Response response = httpRequest.request(Method.GET, "/Hyderabad");
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        //status Code
        int code = response.getStatusCode();
        System.out.println("Status Code-------->" + code);
        if (code == 200) {
            System.out.println("Sucessfull excecution of test cases");
        } else {
            System.out.println("Failed");
        }
        Assert.assertEquals(code, 200);
        String statusLine = response.getStatusLine();
        System.out.println("Status Line is-->" + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    public void addCustomerDetailsToRepository() {
        // Specify the URI
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";
        // request Object
        RequestSpecification httpRequest = RestAssured.given();
        // request payload sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "Avinash");
        requestParams.put("LastName", "Patil");
        requestParams.put("UserName", "Avi");
        requestParams.put("Password", "Avi007@2011");
        requestParams.put("Email", "avinashpatil007@gmail.com");
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());//attach data to the request
        // Response Object
        Response response = httpRequest.request(io.restassured.http.Method.POST, "/register");
        // Response response = httpRequest.request(Method.GET, "/Hyderabad");
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        // status Code
        int code = response.getStatusCode();
        System.out.println("Status Code-------->" + code);
        // success code validation
        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals(successCode, "OPERATION_SUCCESS");
    }

    @Test
    public void googleMapTest() {
       // RestAssured.baseURI = "https://maps.googleapis.com";
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s";
        RequestSpecification httpRequest = RestAssured.given();
        Response response=httpRequest.request(Method.GET,url);
    //print response in current window
        String responseBody=response.asString();
        System.out.println("Response Body is"+responseBody);
    }
}
