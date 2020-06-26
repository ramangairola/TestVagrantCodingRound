package com.testvagrant.api;

import com.testvagrant.Base.BaseClass;
import com.testvagrant.pojo.WeatherDetails;
import com.testvagrant.util.Utils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiAutomation extends BaseClass {

    Utils utils;

    /**
     * Get response from weather api
     * @name getWeatherResponse
     * @return Response
     */
    public Response getWeatherResponse(){

        RestAssured.baseURI = properties.getProperty("apiUri");
        Response response = given().log().all().header("Content-type","application/json")
                .queryParam("q",properties.getProperty("cityToSearch"))
                .queryParam("appid",properties.getProperty("appid"))
                .when().get("/data/2.5/weather")
                .then().assertThat().statusCode(200).extract().response();
        return response;
    }

    /**
     * Set Weather Info from api
     * @name setApiWeatherInfo
     * @return WeatherDetails
     */
    public WeatherDetails setApiWeatherInfo(Response response){
        utils = new Utils();
        String resString = response.asString();
        JsonPath jsonPath = new JsonPath(resString);
        String tempInKelvin = jsonPath.getString("main.temp");
        WeatherDetails weatherDetails = new WeatherDetails();
        weatherDetails.setTempDegree(utils.returnTempInCel(tempInKelvin));
        weatherDetails.setTempFahren(utils.returnTempInFahren(tempInKelvin));
        weatherDetails.setHumidity(jsonPath.getString("main.humidity"));
        return weatherDetails;
    }
}
