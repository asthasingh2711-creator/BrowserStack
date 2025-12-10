package utils;

import java.util.*;

public class TextUtils {

    public static Map<String, Integer> getRepeatedWords(List<String> titles) {

        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String title : titles) {

            String[] words = title.toLowerCase()
                    .replaceAll("[^a-zA-Z ]", "")  
                    .split("\\s+");

            for (String word : words) {
                if (word.length() > 0) {  
                    frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
                }
            }
        }

        return frequencyMap;
    }
}
