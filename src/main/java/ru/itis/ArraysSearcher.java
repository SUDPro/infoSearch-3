package ru.itis;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class ArraysSearcher {

    private static File invertedArraysFile = new File("src/main/resources/result.txt");
    private Map<String, List<Integer>> wordArrayMap = new HashMap<>();


    public ArraysSearcher() throws FileNotFoundException {
        Scanner sc = new Scanner(invertedArraysFile);
        String line;
        while (sc.hasNextLine()){
            line = sc.nextLine();
            JSONObject jsonObject = new JSONObject(line);
            List<Integer> resultArray = new ArrayList<>();
            JSONArray jsonArray = (JSONArray) jsonObject.get("inverted_array");
            for (int i = 0; i < jsonArray.length(); i++) {
                resultArray.add((Integer) jsonArray.get(i));
            }
            wordArrayMap.put((String) jsonObject.get("word"), resultArray);
        }
        System.out.println("readed");
    }

    List<Integer> getArrayByWord(String word){
        return wordArrayMap.get(word);
    }
}
