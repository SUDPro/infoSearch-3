package ru.itis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

class DocSearcher {

    private Map<Integer, File> docs = new HashMap<>();
    private static Pattern wordRegex = Pattern.compile("[^а-яА-Я]");


    DocSearcher() throws FileNotFoundException {
        for (int i = 0; i < 100; i++) {
            docs.put(i, new File(String.format("src/main/resources/files/%d.txt", i)));
        }
    }

    Set<Integer> getDocIdsWithToken(String token) throws FileNotFoundException {
        Set<Integer> docIds = new HashSet<>();
        for (Map.Entry<Integer, File> entry :
                docs.entrySet()) {
            if (searchInFile(entry.getValue(), token)) {
                docIds.add(entry.getKey());
            }
        }
        return docIds;
    }

    private boolean searchInFile(File doc, String token) throws FileNotFoundException {
        Scanner sc = new Scanner(doc).useDelimiter(" ");

        while (sc.hasNext()) {
            if (wordRegex.matcher(sc.next()).replaceAll(" ").trim().equals(token)) {
                return true;
            }
        }
        return false;
    }
}