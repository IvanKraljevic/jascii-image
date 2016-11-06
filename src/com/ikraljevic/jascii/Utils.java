package com.ikraljevic.jascii;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Utils {
    public static Properties loadProperties(final String propertiesPath) throws IOException {
        validateFileExistence(propertiesPath);
        Properties p = new Properties();
        p.load(Files.newInputStream(Paths.get(propertiesPath)));
        return p;
    }

    public static BufferedImage loadImage(final String path) throws IOException {
        validateFileExistence(path);
        return ImageIO.read(Paths.get(path).toFile());
    }

    public static int calculateCapacity(BufferedImage image) {
        return image.getHeight() + image.getHeight() * image.getWidth();
    }

    public static BufferedImage scale(BufferedImage image, int desiredWidth, int desiredHeight, double scaleX, double scaleY) {
        BufferedImage scaledImage =  new BufferedImage(desiredWidth, desiredHeight, image.getType());
        AffineTransform transformation = AffineTransform.getScaleInstance(scaleX, scaleY);
        scaledImage
          .createGraphics()
          .drawRenderedImage(image, transformation);
        return scaledImage;
    }

    private static void validateFileExistence(final String path) {
        if (!Files.exists(Paths.get(path))) {
            throw new IllegalArgumentException("The specified file doesn't exist! " + path);
        }
    }
}
