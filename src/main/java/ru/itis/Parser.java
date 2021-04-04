package ru.itis;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parser {

    private final static String OR = "or";
    private final static String AND = "and";
    private final static String NOT = "not";

    public void parse(String unparsedLine) throws FileNotFoundException {
        String[] wordsArray = unparsedLine.split(" ");
        ArraysSearcher arraysSearcher = new ArraysSearcher();
        List<Object> parsedLineWithArray = new ArrayList<>();
        for (int i = 0; i < wordsArray.length; i++) {
            if (wordsArray[i].equals(OR) | wordsArray[i].equals(AND) | wordsArray[i].equals(NOT)) {
                parsedLineWithArray.add(wordsArray[i]);
            } else {
                List<Integer> docList = arraysSearcher.getArrayByWord(wordsArray[i]);
                parsedLineWithArray.add(docList);
                continue;
            }

        }

        while (parsedLineWithArray.contains(NOT)) {
            int index = parsedLineWithArray.indexOf(NOT);
            List<Integer> integers = (List<Integer>) parsedLineWithArray.get(index + 1);
            parsedLineWithArray.remove(index + 1);
            parsedLineWithArray.set(index, getInvertedList(integers));
        }

        while (parsedLineWithArray.contains(OR)) {
            int index = parsedLineWithArray.indexOf(OR);
            List<Integer> integersBefore = (List<Integer>) parsedLineWithArray.get(index - 1);
            List<Integer> integersAfter = (List<Integer>) parsedLineWithArray.get(index + 1);
            integersAfter.addAll(integersBefore);
            parsedLineWithArray.remove(index + 1);
            parsedLineWithArray.set(index, new HashSet<>(integersAfter));
            parsedLineWithArray.remove(index - 1);
        }

        while (parsedLineWithArray.contains(AND)) {
            int index = parsedLineWithArray.indexOf(AND);
            List<Integer> integersBefore = (List<Integer>) parsedLineWithArray.get(index - 1);
            List<Integer> integersAfter = (List<Integer>) parsedLineWithArray.get(index + 1);
            parsedLineWithArray.remove(index + 1);
            parsedLineWithArray.set(index, new HashSet<>(getConcatSet(integersBefore, integersAfter)));
            parsedLineWithArray.remove(index - 1);
        }

        System.out.println(parsedLineWithArray);
    }

    List<Integer> getConcatSet(List<Integer> set1, List<Integer> set2) {
        Set<Integer> result = new HashSet<>();
        for (Integer number :
                set1) {
            if (set2.contains(number)) {
                result.add(number);
            }
        }

        for (Integer number :
                set2) {
            if (set1.contains(number)) {
                result.add(number);
            }
        }
        return new ArrayList<>(result);
    }

    List<Integer> getInvertedList(List<Integer> integers) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (!integers.contains(i)) {
                result.add(i);
            }
        }
        return result;
    }
}
