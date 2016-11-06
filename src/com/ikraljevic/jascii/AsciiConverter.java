package com.ikraljevic.jascii;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Properties;

import static com.ikraljevic.jascii.Utils.calculateCapacity;
import static com.ikraljevic.jascii.Utils.scale;

public class AsciiConverter {
    public static final String CHARACTER_MAP_SHORT = "@%#*+=-:. ";
    public static final String CHARACTER_MAP_LONG = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ";

    private final String selectedCharacterMap;

    public AsciiConverter(final Properties p) {
        Objects.requireNonNull(p);
        selectedCharacterMap = p.getProperty("characterMap") != null && p.getProperty("characterMap").equals("short")
          ? CHARACTER_MAP_SHORT
          : CHARACTER_MAP_LONG;
    }

    public String convert(final BufferedImage originalImage) {
        final BufferedImage image = scale(originalImage, originalImage.getWidth(), originalImage.getHeight() / 2, 1., 0.5);
        final StringBuilder asciiBuilder = new StringBuilder(calculateCapacity(image));
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color pixel = new Color(image.getRGB(x, y));
                asciiBuilder.append(convertPixel(pixel));
            }
            asciiBuilder.append('\n');
        }

        return asciiBuilder.toString();
    }

    private char convertPixel(final Color pixel) {
        double grayScaleValue = (pixel.getRed() + pixel.getBlue() + pixel.getGreen()) / 3;
        int index = Double.valueOf(grayScaleValue / 255. * (selectedCharacterMap.length() - 1)).intValue();
        return selectedCharacterMap.charAt(index);
    }
}
