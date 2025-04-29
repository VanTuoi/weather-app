package com.mahesh.weather_app.view;

import com.formdev.flatlaf.FlatLightLaf;
import com.mahesh.weather_app.controller.WeatherController;
import com.mahesh.weather_app.model.WeatherData;
import com.mahesh.weather_app.utils.ImageUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import static com.mahesh.weather_app.view.Theme.*;

public class WeatherView extends JFrame {
    private WeatherController controller;
    private boolean isCelsius = true;
    private boolean isDarkMode = false;

    private JTextField locationField;
    private JLabel weatherField, weatherDescLabel, humidLabel, windLabel, weatherIcon, locationLabel;
    private JPanel mainPanel;
    private JButton searchButton;
    private JToggleButton themeToggle, tempToggle;

    public WeatherView() {
        setupLookAndFeel();
        setAppIcon();
        initializeUI();
    }

    private void setupLookAndFeel() {
        try {
            FlatLightLaf.setup();
            UIManager.put("TextComponent.arc", TEXT_COMPONENT_ARC);
            UIManager.put("Button.arc", BUTTON_ARC);
            UIManager.put("Component.arc", COMPONENT_ARC);
            UIManager.put("defaultFont", DEFAULT_FONT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAppIcon() {
        try {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "/com/mahesh/weather_app/view/assets/icons/logo.png")));
            List<Image> icons = List.of(
                icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH),
                icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH),
                icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH),
                icon.getImage().getScaledInstance(128, 128, Image.SCALE_SMOOTH)
            );
            setIconImages(icons);
            if (System.getProperty("os.name").toLowerCase().contains("win")) setIconImage(icons.get(2));
        } catch (Exception e) {
            System.err.println("Could not load application icon: " + e.getMessage());
        }
    }

    private void initializeUI() {
        setTitle("Weather App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(280, 460);
        setResizable(false);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        mainPanel.add(createTopPanel(), BorderLayout.NORTH);
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
        mainPanel.add(createSettingsPanel(), BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createTopPanel() {
        JPanel panel = createTransparentPanel(new BorderLayout(5, 5), 10);

        locationLabel = createLabel("City:", TITLE_FONT, PRIMARY_COLOR);

        locationField = new JTextField();
        locationField.setBackground(INPUT_BACKGROUND);
        locationField.addActionListener(e -> controller.fetchWeather(locationField.getText()));

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setOpaque(false);
        inputPanel.add(locationField, BorderLayout.CENTER);
        inputPanel.add(createSearchButton(), BorderLayout.EAST);

        panel.add(locationLabel, BorderLayout.NORTH);
        panel.add(inputPanel, BorderLayout.CENTER);
        return panel;
    }

    private @NotNull JButton createSearchButton() {
        searchButton = new JButton("Search");
        styleButton(searchButton, PRIMARY_COLOR, Color.WHITE, BUTTON_FONT, BUTTON_SIZE);
        searchButton.addActionListener(e -> controller.fetchWeather(locationField.getText()));
        getRootPane().setDefaultButton(searchButton);
        return searchButton;
    }

    private JPanel createTransparentBoxPanel(int axis, int padding) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, axis));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = createTransparentBoxPanel(BoxLayout.Y_AXIS, 10);

        weatherIcon = new JLabel("", SwingConstants.CENTER);
        weatherIcon.setPreferredSize(new Dimension(100, 100));
        weatherIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        weatherField = createLabel("-- °C", TEMPERATURE_FONT, PRIMARY_COLOR);
        weatherDescLabel = createLabel("---", DESCRIPTION_FONT, SECONDARY_TEXT_COLOR);
        humidLabel = createLabel("Humidity: --%", DETAIL_FONT, TEXT_COLOR);
        windLabel = createLabel("Wind: -- km/h", DETAIL_FONT, TEXT_COLOR);

        for (JLabel label : List.of(weatherIcon, weatherField, weatherDescLabel, humidLabel, windLabel)) {
            panel.add(label);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        return panel;
    }

    private JPanel createSettingsPanel() {
        JPanel panel = createTransparentPanel(new FlowLayout(FlowLayout.CENTER, 10, 5), 5);

        tempToggle = new JToggleButton("°C");
        styleButton(tempToggle, BUTTON_GREEN, Color.WHITE, DETAIL_FONT.deriveFont(Font.BOLD, LARGE_FONT.getSize()), BUTTON_SIZE);
        tempToggle.addActionListener(e -> toggleTempUnit());

        themeToggle = new JToggleButton("Light");
        styleButton(themeToggle, BUTTON_GREEN, Color.WHITE, DETAIL_FONT.deriveFont(Font.BOLD), BUTTON_SIZE_LARGE);
        themeToggle.setIcon(loadThemeIcon());
        themeToggle.addActionListener(e -> toggleTheme());

        panel.add(tempToggle);
        panel.add(themeToggle);
        return panel;
    }

    private void toggleTempUnit() {
        isCelsius = !isCelsius;
        applyCelsiusIcon();
        if (controller != null && !locationField.getText().isEmpty()) {
            controller.fetchWeather(locationField.getText());
        }
    }

    private void toggleTheme() {
        isDarkMode = !isDarkMode;
        applyTheme();
    }

    private void applyCelsiusIcon() {
        tempToggle.setText(isCelsius ? "°C" : "°F");
        weatherField.setText(isCelsius ? "-- °C" : "-- °F");
    }

    private void applyTheme() {
        Theme.setDarkTheme(isDarkMode);
        getContentPane().setBackground(BACKGROUND_COLOR);
        mainPanel.setBackground(BACKGROUND_COLOR);

        Color btnColor = isDarkMode ? DARK_BUTTON_GREEN : BUTTON_GREEN;

        themeToggle.setText(isDarkMode ? "Dark" : "Light");
        themeToggle.setBackground(btnColor);
        themeToggle.setForeground(Color.WHITE);
        themeToggle.setIcon(loadThemeIcon());

        tempToggle.setBackground(btnColor);
        tempToggle.setForeground(Color.WHITE);

        updateComponentColors();
        SwingUtilities.updateComponentTreeUI(this);
    }


    private ImageIcon loadThemeIcon() {
        String path = isDarkMode ?
            "/com/mahesh/weather_app/view/assets/icons/dark.png" :
            "/com/mahesh/weather_app/view/assets/icons/light.png";
        return ImageUtils.loadAndResizeIcon(path, 30, 30);
    }

    private void updateComponentColors() {
        locationField.setBackground(INPUT_BACKGROUND);
        locationField.setForeground(TEXT_COLOR);
        for (JLabel lbl : List.of(weatherField, weatherDescLabel, humidLabel, windLabel, locationLabel)) {
            lbl.setForeground(TEXT_COLOR);
        }
        locationLabel.setForeground(PRIMARY_COLOR);
        weatherField.setForeground(PRIMARY_COLOR);
        weatherDescLabel.setForeground(SECONDARY_TEXT_COLOR);
    }

    public void setLoading(boolean isLoading) {
        searchButton.setText(isLoading ? "" : "Search");
        searchButton.setIcon(isLoading ?
            new ImageIcon(new ImageIcon(getClass().getResource(
                "/com/mahesh/weather_app/view/assets/icons/loading.gif"))
                .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT))
            : null);
    }

    public void updateUI(WeatherData data) {
        double temp = isCelsius ? data.getTemperature() : (data.getTemperature() * 9 / 5) + 32;
        weatherField.setText(String.format("%.1f °%s", temp, isCelsius ? "C" : "F"));
        weatherDescLabel.setText(data.getDescription());
        humidLabel.setText(String.format("Humidity: %.0f%%", data.getHumidity()));
        windLabel.setText(String.format("Wind: %.1f km/h", data.getWindSpeed()));
        locationLabel.setText(data.getLocation());

        try {
            weatherIcon.setIcon(new ImageIcon(new URL("https://openweathermap.org/img/wn/" + data.getIconId() + "@2x.png")));
        } catch (MalformedURLException e) {
            weatherIcon.setIcon(null);
        }
    }

    public void setController(WeatherController controller) {
        this.controller = controller;
    }

    public boolean isCelsius() {
        return isCelsius;
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // --- Helpers ---

    private JPanel createTransparentPanel(LayoutManager layout, int padding) {
        JPanel panel = new JPanel(layout);
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        return panel;
    }

    private JLabel createLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(font);
        label.setForeground(color);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private void styleButton(AbstractButton btn, Color bg, Color fg, Font font, Dimension size) {
        btn.setFont(font);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setPreferredSize(size);
    }
}
