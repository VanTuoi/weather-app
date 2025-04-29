package com.mahesh.weather_app.utils;

import com.mortennobel.imagescaling.ResampleOp;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;

public class ImageUtils {
    public static ImageIcon resizeIcon(ImageIcon originalIcon, int width, int height) {
        if (originalIcon == null || originalIcon.getImage() == null) {
            return null;
        }

        BufferedImage bufferedImage = new BufferedImage(
            originalIcon.getIconWidth(),
            originalIcon.getIconHeight(),
            BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(originalIcon.getImage(), 0, 0, null);
        g2d.dispose();

        ResampleOp resampleOp = new ResampleOp(width, height);
        return new ImageIcon(resampleOp.filter(bufferedImage, null));
    }

    public static ImageIcon loadAndResizeIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(ImageUtils.class.getResource(path));
        return resizeIcon(icon, width, height);
    }
}