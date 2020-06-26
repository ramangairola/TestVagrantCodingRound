package com.testvagrant.testcases;

import com.testvagrant.Base.BaseClass;
import com.testvagrant.api.ApiAutomation;
import com.testvagrant.pages.HomePage;
import com.testvagrant.pages.WeatherPage;
import com.testvagrant.pojo.WeatherDetails;
import com.testvagrant.util.Utils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCaseClass extends BaseClass {
    HomePage homePage;
    WeatherPage weatherPage;
    ApiAutomation apiAutomation;
    public WeatherDetails uiweatherDetails;
    public WeatherDetails apiweatherDetails;
    Utils utils;
    boolean ui = true;

    public TestCaseClass(){
        super();
    }

    @BeforeMethod
    public void setup(){
        if(ui){
            initialization();
            ui = false;
        }
        homePage = new HomePage();
        weatherPage =  new WeatherPage();
        apiAutomation = new ApiAutomation();
        utils = new Utils();

    }

    @Test()
    public void checkUIWeatherInfo(){
        //Open url
        driver.get(properties.getProperty("uiUrl"));

        //Click Submenu Dropdown
        homePage.clickSubMenuDropDown();

        //Click on weather option
        weatherPage = homePage.clickOnWeatherOption();

        //Check whether city exists in map
        if(weatherPage.checkCityExistsInMap()){
            //set weather info in WeatherDetails class object
            uiweatherDetails = weatherPage.validateAndSetWeatherInfo();
        }else {
            //Select city
            Assert.assertTrue(weatherPage.selectCity(),"Unable to find city searched for : "+properties.getProperty("cityToSearch"));
            //Check city exists in map after selecting it
            Assert.assertTrue(weatherPage.checkCityExistsInMap());
            //set weather info in WeatherDetails class object
            uiweatherDetails = weatherPage.validateAndSetWeatherInfo();
        }

    }

    @Test()
    public void checkAPIWeatherInfo(){
        //Hit the get call to get response from weather api
        Response response = apiAutomation.getWeatherResponse();
        //set weather info in WeatherDetails class object
        apiweatherDetails = apiAutomation.setApiWeatherInfo(response);
    }

    @Test(dependsOnMethods = {"checkUIWeatherInfo","checkAPIWeatherInfo"})
    public void checkHumidityWeatherInfoBetweenUIandAPI(){
        //Check object value for Humidity
        if(!uiweatherDetails.getHumidity().isEmpty() && !apiweatherDetails.getHumidity().isEmpty()){
            boolean humidityCheck = utils.varianceLogic("Humidity",Double.parseDouble(uiweatherDetails.getHumidity()),Double.parseDouble(apiweatherDetails.getHumidity()));
            Assert.assertTrue(humidityCheck,"Humidity from UI : "+Double.parseDouble(uiweatherDetails.getHumidity())+" Humidity from API : "+Double.parseDouble(apiweatherDetails.getHumidity()));
        }else{
            Assert.fail("Object empty for humidity");
        }
    }

    @Test(dependsOnMethods = {"checkUIWeatherInfo","checkAPIWeatherInfo"})
    public void checkTempInCelsiusWeatherInfoBetweenUIandAPI(){
        //Check object value for Temperature in Celsius
        if(!uiweatherDetails.getTempDegree().isEmpty() || !apiweatherDetails.getTempDegree().isEmpty()){
            boolean tempInCel = utils.varianceLogic("Temperature",Double.parseDouble(uiweatherDetails.getTempDegree()),Double.parseDouble(apiweatherDetails.getTempDegree()));
            Assert.assertTrue(tempInCel,"Temperature in Celsius from UI : "+Double.parseDouble(uiweatherDetails.getTempDegree())+ " Temperature in Celsius from API : "+Double.parseDouble(apiweatherDetails.getTempDegree()));
        }else {
            Assert.fail("Object empty for temperature in Celsius");
        }

    }

    @Test(dependsOnMethods = {"checkUIWeatherInfo","checkAPIWeatherInfo"})
    public void checkTempInFahrenheitWeatherInfoBetweenUIandAPI(){
        //Check object value for Temperature in Fahrenheit
        if(!uiweatherDetails.getTempFahren().isEmpty() && !apiweatherDetails.getTempFahren().isEmpty()){
            boolean tempInFah = utils.varianceLogic("Temperature",Double.parseDouble(uiweatherDetails.getTempFahren()),Double.parseDouble(apiweatherDetails.getTempFahren()));
            Assert.assertTrue(tempInFah,"Temperature in Fahrenheit from UI : "+Double.parseDouble(uiweatherDetails.getTempFahren())+ " Temperature in Fahrenheit from API : "+Double.parseDouble(apiweatherDetails.getTempFahren()));
        }else {
            Assert.fail("Object empty for temperature in Fahrenheit");
        }

    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }
}
