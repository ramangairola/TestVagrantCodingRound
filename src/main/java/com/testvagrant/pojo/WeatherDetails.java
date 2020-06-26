package com.testvagrant.pojo;

public class WeatherDetails {

    private String condition;
    private String wind;
    private String humidity;
    private String tempDegree;
    private String tempFahren;

    public WeatherDetails(){
        condition="";
        wind="";
        humidity="";
        tempDegree="";
        tempFahren="";
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTempDegree() {
        return tempDegree;
    }

    public void setTempDegree(String tempDegree) {
        this.tempDegree = tempDegree;
    }

    public String getTempFahren() {
        return tempFahren;
    }

    public void setTempFahren(String tempFahren) {
        this.tempFahren = tempFahren;
    }
}
