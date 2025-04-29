package com.mahesh.weather_app.view;

import java.awt.*;

public class Theme {
    public static Color PRIMARY_COLOR = new Color(0, 102, 204);
    public static Color BACKGROUND_COLOR = Color.WHITE;
    public static Color INPUT_BACKGROUND = new Color(224, 242, 254);
    public static Color TEXT_COLOR = Color.BLACK;
    public static Color SECONDARY_TEXT_COLOR = Color.GRAY;

    public static final Color DARK_BACKGROUND = new Color(60, 63, 65);
    public static final Color DARK_INPUT_BG = new Color(80, 83, 85);
    public static final Color DARK_TEXT_COLOR = new Color(187, 187, 187);
    public static final Color DARK_BUTTON_BG = new Color(70, 73, 75);
    public static final Color BUTTON_GREEN = new Color(0x34A853);
    public static final Color DARK_BUTTON_GREEN = new Color(0x2E8B46);

    public static final Font DEFAULT_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font TEMPERATURE_FONT = new Font("Segoe UI", Font.BOLD, 36);
    public static final Font DESCRIPTION_FONT = new Font("Segoe UI", Font.PLAIN, 18);
    public static final Font DETAIL_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    public static final Font LARGE_FONT = new Font("Segoe UI", Font.PLAIN, 20);

    public static final int COMPONENT_ARC = 15;
    public static final int BUTTON_ARC = 20;
    public static final int TEXT_COMPONENT_ARC = 15;

    public static final Dimension BUTTON_SIZE = new Dimension(100, 40);
    public static final Dimension BUTTON_SIZE_LARGE = new Dimension(120, 40);

    public static void setDarkTheme(boolean isDark) {
        if (isDark) {
            BACKGROUND_COLOR = DARK_BACKGROUND;
            INPUT_BACKGROUND = DARK_INPUT_BG;
            PRIMARY_COLOR = DARK_TEXT_COLOR;
            TEXT_COLOR = DARK_TEXT_COLOR;
            SECONDARY_TEXT_COLOR = DARK_TEXT_COLOR;
        } else {
            BACKGROUND_COLOR = Color.WHITE;
            INPUT_BACKGROUND = new Color(224, 242, 254);
            PRIMARY_COLOR = new Color(0, 102, 204);
            TEXT_COLOR = Color.BLACK;
            SECONDARY_TEXT_COLOR = Color.GRAY;
        }
    }
}