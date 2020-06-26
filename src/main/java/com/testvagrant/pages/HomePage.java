package com.testvagrant.pages;

import com.testvagrant.Base.BaseClass;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage extends BaseClass {

    //Submenu Dropdown
    @FindBy(css = "#h_sub_menu")
    private WebElement dropdownSubMenu;

    //Weather Option
    @FindBy(xpath = "//a[contains(text(),'WEATHER')]")
    private WebElement weatherLink;

    // Initializing the Page Objects:
    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Click on Submenu dropdown
     * @name clickSubMenuDropDown
     * @return void
     */
    public void clickSubMenuDropDown(){
        try {
            dropdownSubMenu.click();
        }catch (NoSuchElementException e){
            Assert.fail("Unable to click on sub menu dropdown");
        }
    }

    /**
     * Click on Weather Option
     * @name clickOnWeatherOption
     * @return WeatherPage
     */
    public WeatherPage clickOnWeatherOption(){
        try {
            weatherLink.click();
        }catch (NoSuchElementException e){
            Assert.fail("Unable to click on weather option");
        }
        return new WeatherPage();
    }



}
