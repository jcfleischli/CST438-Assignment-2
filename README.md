# cst438hw2
CST438 Assignment 2 - Coding a Restful controller

Objectives in this assignment:

•	learn how to code a spring Rest Controller.  A spring controller returns html,  a rest controller returns data in json format.  A Rest Controller is intended to be used by JavaScript AJAX requests or by mobile applications or other computer to computer data exchanges.

•	code a method that uses spring RestTemplate to call an external Rest service “api.openweathermap.org”  to retrieve current weather information for a city.

•	create  spring Service classes for city information that combines information from the city table in the MySQL sample world database with current weather data.

•	use TDD test strategy to write test cases for the service and rest controller classes.  These test classes will use MOCKS isolate the code under test.  Calls to the repository and to the remote weather service will be stubbed out with spring MOCKS that return data for the test so that an actual database or actual weather service will not be used in the test.  This will make the test repeatable and predictable and faster to execute.

Requirements

•	To get city information on a city such as Miami, user enters the URL in the browser address bar localhost:8080/cities/Miami

•	The controller  class method will call the City Service class which will return data from the world.city sample table and get current weather for the city.

•	The html page returned by the controller will contain city information, the country name, the current temp and local time of the temperature reading. 
