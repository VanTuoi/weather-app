package com.mahesh.weather_app;

import com.mahesh.weather_app.controller.WeatherController;
import com.mahesh.weather_app.model.WeatherService;
import com.mahesh.weather_app.view.WeatherView;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1");
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                WeatherService service = new WeatherService();
                WeatherView view = new WeatherView();
                WeatherController controller = new WeatherController(service, view);
                view.setController(controller);
                view.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                    "Failed to initialize application: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}