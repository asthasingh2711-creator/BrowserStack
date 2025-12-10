package utils;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.v3.*;

import java.io.InputStream;
import java.util.List;

public class GcpTranslate {

    private static final String PROJECT_ID = "focus-album-480720-a3";
    private static TranslationServiceClient client;

    static {
        try {
            InputStream keyStream = GcpTranslate.class
                    .getClassLoader()
                    .getResourceAsStream("focus-album-480720-a3-379387d219cc.json");

            if (keyStream == null) {
                throw new RuntimeException("Could not load GCP key file");
            }

            GoogleCredentials credentials = GoogleCredentials.fromStream(keyStream)
                    .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

            TranslationServiceSettings settings = TranslationServiceSettings.newBuilder()
                    .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                    .build();

            client = TranslationServiceClient.create(settings);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String translate(String text) {
        try {
            LocationName parent = LocationName.of(PROJECT_ID, "global");

            TranslateTextRequest request = TranslateTextRequest.newBuilder()
                    .setParent(parent.toString())
                    .setMimeType("text/plain")
                    .setTargetLanguageCode("en")
                    .addContents(text)
                    .build();

            TranslateTextResponse response = client.translateText(request);
            return response.getTranslations(0).getTranslatedText();

        } catch (Exception e) {
            e.printStackTrace();
            return text;
        }
    }
}
