package com.testvagrant.util;

import com.testvagrant.Base.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class Utils extends BaseClass {
    /**
     * Get screenshot and capture as Base64
     * @name captureAsBase64
     * @return String
     */
    public static String captureAsBase64()
    {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
    /**
     * Return temperature in Fahrenheit
     * @name returnTempInFahren
     * @return String
     */
    public static String returnTempInFahren(String tempInKelvin){
        double tempInFah = (Double.parseDouble(tempInKelvin) - 273.15)*1.8+32;
        return String.valueOf(tempInFah);
    }

    /**
     * Return temperature in Celsius
     * @name returnTempInCel
     * @return String
     */
    public static String returnTempInCel(String tempInKelvin){
        double tempInCel = Double.parseDouble(tempInKelvin) - 273.15;
        return String.valueOf(tempInCel);
    }

    /**
     * Return temperature in Celsius
     * @name returnTempInCel
     * @return String
     */
    public boolean varianceLogic(String type, double val1, double val2){
        switch (type.toLowerCase()){
            case "humidity":{
                if(Math.abs(val1-val2)<=10){
                    return true;
                }
                break;
            }
            case "temperature":{
                if(Math.abs(val1-val2)<=5){
                    return true;
                }
                break;
            }
            default:{
                Assert.fail("Wrong type");
            }
        }
        return false;
    }

    /**
     * Get screenshot and return its path
     * @name getScreenshot
     * @return String
     */
    public static String getScreenshot() {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationLoc = System.getProperty("user.dir")+ "\\screenshots\\" + System.currentTimeMillis() + ".png";
        try {
            FileUtils.copyFile(scrFile, new File(destinationLoc));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destinationLoc;
    }
}
