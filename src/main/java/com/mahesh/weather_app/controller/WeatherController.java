package com.mahesh.weather_app.controller;

import com.mahesh.weather_app.model.WeatherData;
import com.mahesh.weather_app.model.WeatherService;
import com.mahesh.weather_app.view.WeatherView;

import java.io.IOException;
import javax.swing.SwingWorker;

public class WeatherController {
    private final WeatherView view;
    private final WeatherService weatherService;
    private boolean isCelsius = true;

    public WeatherController(WeatherService weatherService, WeatherView view) {
        this.weatherService = weatherService;
        this.view = view;
    }

    public void fetchWeather(String location) {
        if (location == null || location.trim().isEmpty()) {
            view.showError("Please enter a location");
            return;
        }

        view.setLoading(true);

        new SwingWorker<WeatherData, Void>() {
            @Override
            protected WeatherData doInBackground() throws Exception {
                return weatherService.getWeather(location);
            }

            @Override
            protected void done() {
                try {
                    WeatherData data = get();
                    if (!view.isCelsius()) {
                        double tempF = (data.getTemperature() * 9/5) + 32;
                        data.setTemperature(tempF);
                    }
                    view.updateUI(data);
                } catch (Exception e) {
                    view.showError("Error fetching weather data: " + e.getMessage());
                } finally {
                    view.setLoading(false);
                }
            }
        }.execute();
    }
}