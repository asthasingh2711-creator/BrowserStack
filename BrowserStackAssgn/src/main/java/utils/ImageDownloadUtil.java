package utils;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageDownloadUtil {

    public static void downloadImage(String imageUrl, String filePath) {
        try (InputStream in = new URL(imageUrl).openStream()) {

            Files.createDirectories(Paths.get(filePath).getParent());
            Files.copy(in, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Image saved: " + filePath);

        } catch (Exception e) {
            System.out.println("Failed to download image from: " + imageUrl);
        }
    }
}
