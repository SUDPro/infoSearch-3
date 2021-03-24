package ru.itis;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

class TokenizationAndSearch {

    private File tokensFile = new File("src/main/resources/tokens.txt");

    void start() {
        try {
            createInvertedArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createInvertedArray() throws IOException {
        Scanner sc = new Scanner(tokensFile);
        DocSearcher docSearcher = new DocSearcher();
        File invertedIndexFile = new File("src/main/resources/invertedIndex.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(invertedIndexFile, true));
        int i = 0;
        while (sc.hasNext()) {
            System.out.println(i++);
            String word = sc.nextLine();
            Set<Integer> docIds = docSearcher.getDocIdsWithToken(word);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("word", word);
            jsonObject.put("count", docIds.size());
            jsonObject.put("inverted_array", docIds);
            writer.append(jsonObject.toString() + "\n");
        }
        writer.close();
    }
}
