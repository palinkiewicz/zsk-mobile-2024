package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            FileWriter wyniki4 = new FileWriter("wyniki4.txt");
            Scanner sc = new Scanner(new File("./src/main/java/org/example/data/przyklad.txt"));
            ArrayList<Integer> lines = new ArrayList<>();

            while (sc.hasNextLine()) {
                lines.add(Integer.parseInt((sc.nextLine())));
            }


            wyniki4.write("Zadanie 4.1:\n");
            ArrayList<Integer> z41 = zadanie41(lines);
            wyniki4.write(z41.size() + " " + z41.get(0));

            wyniki4.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("I/O exception");
        }
    }

    private static ArrayList<Integer> zadanie41(ArrayList<Integer> lines) {
        ArrayList<Integer> results = new ArrayList<>();

        for (int num : lines) {
            int num_copy = num;
            int last = num % 10;

            while (num / 10 > 0) num /= 10;

            if (last == num) results.add(num_copy);
        }

        return results;
    }
}