package com.mahesh.weather_app.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class WeatherService {
    private final String apiKey;

    public WeatherService() {
        this.apiKey = loadApiKey();
    }

    private String loadApiKey() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Cannot find config.properties");
            }
            Properties prop = new Properties();
            prop.load(input);
            String key = prop.getProperty("OPENWEATHER_API_KEY");
            if (key == null || key.isEmpty()) {
                throw new RuntimeException("OPENWEATHER_API_KEY not found in config.properties");
            }
            return key;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public WeatherData getWeather(String location) throws IOException {
        String url = buildUrl(location);
        String json = callApi(url);
        return parseJson(json, location);
    }

    private String buildUrl(String location) throws IOException {
        return "https://api.openweathermap.org/data/2.5/weather?q="
            + URLEncoder.encode(location, StandardCharsets.UTF_8)
            + "&appid=" + apiKey
            + "&units=metric";
    }

    private String callApi(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() != 200) {
            throw new IOException("HTTP error code: " + connection.getResponseCode());
        }

        try (InputStream is = connection.getInputStream()) {
            return new String(is.readAllBytes());
        }
    }

    private WeatherData parseJson(String json, String location) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
        WeatherData data = new WeatherData();

        JsonObject main = obj.getAsJsonObject("main");
        JsonObject wind = obj.getAsJsonObject("wind");
        JsonObject weather = obj.getAsJsonArray("weather").get(0).getAsJsonObject();

        data.setTemperature(main.get("temp").getAsDouble());
        data.setHumidity(main.get("humidity").getAsDouble());
        data.setWindSpeed(wind.get("speed").getAsDouble());
        data.setDescription(weather.get("description").getAsString());
        data.setIconId(weather.get("icon").getAsString());
        data.setLocation(location);

        return data;
    }
}
