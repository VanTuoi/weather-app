# Weather App

This is a simple weather application built using Java Swing. It allows users to search for weather information by city and displays the current temperature, weather description, humidity, and wind speed. It also features a toggle for Celsius/Fahrenheit units and a light/dark theme switch.

## Features

* **Search by City:** Enter a city name to retrieve the current weather information.
* **Temperature Display:** Shows the current temperature in either Celsius or Fahrenheit.
* **Detailed Weather Information:** Displays weather description, humidity, and wind speed.
* **Weather Icon:** Shows an icon representing the current weather condition.
* **Unit Toggle:** Switch between Celsius (°C) and Fahrenheit (°F) with a toggle button.
* **Theme Toggle:** Switch between a light and a dark theme for the application's appearance.
* **Loading Indicator:** A loading animation is displayed while fetching weather data.
* **Error Handling:** Displays an error message if weather data cannot be retrieved.

## Technologies Used

* **Java Swing:** For building the graphical user interface.
* **FlatLaf:** A modern look and feel library for Swing applications.
* **OpenWeatherMap API:** To fetch weather data based on city name.
* **JSON Processing:** For parsing the weather data received from the API.
* **ImageIO:** For handling image loading and resizing.

## Project Structure
* `controller/`: Contains the `WeatherController` class responsible for fetching weather data and updating the view.
* `model/`: Contains the `WeatherData` class representing the weather information.
* `utils/`: Contains utility classes like `ImageUtils` for image handling.
* `view/`: Contains the `WeatherView` class which builds the user interface and the `Theme` class for managing the application's appearance.
* `view/assets/icons/`: Contains image resources used in the application.
* `README.md`: This file, providing information about the application.

## Dependencies

* **FlatLaf:** A free open-source look and feel for Java Swing desktop applications. You'll need to download the JAR file and include it in your project's classpath.

## API Key

This application relies on the OpenWeatherMap API to fetch weather data. You will need to obtain your own API key from [https://openweathermap.org/api](https://openweathermap.org/api) and integrate it into the `WeatherController` class to make the application fully functional. *(Note: The provided code snippet does not include the API key handling, so you would need to add this functionality.)*

## Further Development

Possible future enhancements include:

* Displaying more detailed weather information (e.g., feels like temperature, pressure, visibility).
* Adding a forecast feature to show weather for the next few days.
* Implementing location services to automatically detect the user's city.
* Improving the UI/UX with more advanced Swing components or layout managers.
* Adding support for multiple languages.

## Author

VanTuoi