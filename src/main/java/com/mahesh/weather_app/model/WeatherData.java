package com.mahesh.weather_app.model;

public class WeatherData {
    private double temperature;
    private double humidity;
    private double windSpeed;
    private String description;
    private String iconId;
    private String location;

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public double getHumidity() { return humidity; }
    public void setHumidity(double humidity) { this.humidity = humidity; }

    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getIconId() { return iconId; }
    public void setIconId(String iconId) { this.iconId = iconId; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}