package com.testvagrant.pages;

import com.testvagrant.Base.BaseClass;
import com.testvagrant.pojo.WeatherDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class WeatherPage extends BaseClass {

    //Search Field
    @FindBy(css = "#searchBox")
    WebElement searchBox;

    //SearchBox City List
    @FindBy(css = "#messages div")
    List<WebElement> searchBoxcityList;

    //SearchBox City Label
    @FindBy(css = " label")
    WebElement searchBoxCityLabel;

    //City checkBox
    @FindBy(css = " label input")
    WebElement cityCheckBox;

    //Map City List
    @FindBy(css = ".outerContainer")
    List<WebElement> mapCityList;

    //Weather Info Popup
    @FindBy(css = ".leaflet-popup-content")
    WebElement weatherInfo;


    // Initializing the Page Objects:
    public WeatherPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * check city already exists in map
     * @name checkCityExistsInMap
     * @return boolean
     */
    public boolean checkCityExistsInMap(){
        for(WebElement cityElement : mapCityList){
            String cityName = cityElement.getAttribute("title");
            if(cityName.toLowerCase().equals(properties.getProperty("cityToSearch").toLowerCase())){
                return true;
            }
        }
        return false;
    }

    /**
     * select city from city list
     * @name selectCity
     * @return boolean
     */
    public boolean selectCity(){
        searchBox.sendKeys(properties.getProperty("cityToSearch"));
        for(WebElement element : searchBoxcityList){
            if(element.getAttribute("style").isEmpty()){
                if( element.findElement(By.cssSelector(" label")).getAttribute("for").toLowerCase().equals(properties.getProperty("cityToSearch").toLowerCase()) ){
                    element.findElement(By.cssSelector(" label input")).click();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Validate and get Weather Info
     * @name validateAndGetWeatherInfo
     * @return WeatherDetails
     */
    public WeatherDetails validateAndSetWeatherInfo(){
        WeatherDetails weatherDetails = new WeatherDetails();
        for(WebElement cityElement : mapCityList){
            String cityName = cityElement.getAttribute("title");
            if(cityName.toLowerCase().equals(properties.getProperty("cityToSearch").toLowerCase())){
                String[] tempList = cityElement.getText().split("\n");
                if(tempList.length==3 && tempList[0].contains("℃") && tempList[1].contains("℉")){
                    return setWeatherInfo(cityElement,weatherDetails);
                }
                else {
                    Assert.fail("Temperature information not coming for city : "+properties.getProperty("cityToSearch"));
                }
            }
        }
        return weatherDetails;
    }

    /**
     * Set Weather Info from ui
     * @name setWeatherInfo
     * @return WeatherDetails
     */
    public WeatherDetails setWeatherInfo(WebElement element,WeatherDetails weatherDetails){
        try {
            element.click();
        }catch (ElementNotInteractableException e){
            e.printStackTrace();
            return null;
        }
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".leaflet-popup-content")));
        String value = weatherInfo.getText();
        String[] tempDetailList = value.split("\n");
        weatherDetails.setCondition(tempDetailList[1].split(":")[1].trim());
        weatherDetails.setWind(tempDetailList[2].split(":")[1].trim());
        weatherDetails.setHumidity(tempDetailList[3].split(":")[1].trim().replace("%",""));
        weatherDetails.setTempDegree(tempDetailList[4].split(":")[1].trim());
        weatherDetails.setTempFahren(tempDetailList[5].split(":")[1].trim());
        return weatherDetails;
    }



}
