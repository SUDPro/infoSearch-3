package ru.itis;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
//        TokenizationAndSearch ts = new TokenizationAndSearch();
//        ts.start();

        Scanner sc = new Scanner(System.in);
            while(sc.hasNextLine()){
                Parser parser = new Parser();
                parser.parse(sc.nextLine());
            }

    }
}