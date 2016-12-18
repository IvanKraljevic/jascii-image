package com.ikraljevic.jascii;

import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

import static com.ikraljevic.jascii.Utils.loadImage;

public class Main implements Runnable {
    private static final Logger log = Logger.getLogger(Main.class.getName());
    private final String path;
    private final String outputPath;

    public static void main(String[] args) {
        new Main(args[0], args[1]).run();
    }

    public Main(final String path, final String outputPath) {
        Objects.requireNonNull(path);
        Objects.requireNonNull(outputPath);
        this.path = path;
        this.outputPath = outputPath;
    }

    @Override
    public void run() {
        try {
            BufferedImage image = loadImage(path);
            log.info("Image loaded from: " + path);
            Properties converterProperties = Utils.loadProperties("resources/config.properties");
            log.info("Properties file loaded");

            AsciiConverter converter = new AsciiConverter(converterProperties);
            String asciiImage = converter.convert(image);
            log.info("Image converted");

            Files.write(Paths.get(outputPath), asciiImage.getBytes());
            log.info("Converted image saved to: " + outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
