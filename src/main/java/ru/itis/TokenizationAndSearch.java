package ru.itis;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class TokenizationAndSearch {

    private final File tokensFile = new File("src/main/resources/lemmas.txt");
    private final File invertedIndexFile = new File("src/main/resources/result.txt");

    void start() {
        try {
            createInvertedArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createInvertedArray() throws IOException {
        try (Scanner sc = new Scanner(tokensFile);
             BufferedWriter writer = new BufferedWriter(new FileWriter(invertedIndexFile, true))) {
            DocSearcher docSearcher = new DocSearcher();
            int count = 0;
            Set<String> words = new HashSet<>();
            while (sc.hasNext()) {
                Set<Integer> docIdsSet = new HashSet<>();
                System.out.println(count++);
                String[] wordArray = sc.nextLine().split(" ");
                for (String s : wordArray) {
                    if (words.add(s)) {
                        docIdsSet.addAll(docSearcher.getDocIdsWithToken(s));
                    }
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("word", wordArray[0]);
                jsonObject.put("count", docIdsSet.size());
                jsonObject.put("inverted_array", docIdsSet);
                writer.append(jsonObject.toString() + "\n");
            }
        }
    }
}
