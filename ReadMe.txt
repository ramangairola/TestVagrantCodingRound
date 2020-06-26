This project consist of a Maven project using following dependencies:
1. Selenium
2. TestNG
3. ExtentReport
4. RestAssured
5. WebdriverManager

This project follows Page object model approach.

This project is TestNG Project and to run test cases we need to run it from TestNG.xml file placed in (src<main<java<resources).

All the input data is stored in config.properties file stored in (src<main<java<com<testvagrant<config<config.properties)
1. uiUrl = https://www.ndtv.com/
2. apiUri = https://api.openweathermap.org
3. browser = chrome
4. cityToSearch = Alwar
5. appid = 7fe67bf08c80ded756e598d6f8fedaea

Reports are generated in reports folder with today's date time stamp(format: yyyyMMddHHmmss).

The test file contains 5 test cases:
1. In this test case we try to open NDTV website. Then goto Weather sectin. Their we first check whether the city whose temperature we want to check appears in the map. If yes we don't need to search it from the search field. We first check temp info are avaiable for that city, then click on that city and store all the weather info in an object1. If the city doesn't appears in the map we first search it in the search field and then validate after selecting it that it must appear in the map and then we do the approach we did earlier.

2. In this test we try to hit the api using get call. Then parse its data and store them in object2.

3. This test case depends on previous two test cases. In this test we first check whether any object should not be null. Then we check for the Humidity value from both the objects(ui and api).

4. This test case depends on previous two test cases. In this test we first check whether any object should not be null. Then we check for the Temperature in celsius value from both the objects(ui and api).

5. This test case depends on previous two test cases. In this test we first check whether any object should not be null. Then we check for the Temperature in fahrenheit value from both the objects(ui and api).

Variance logic for humidity is +-(10)
Variance logic for temperature is +-(5)
