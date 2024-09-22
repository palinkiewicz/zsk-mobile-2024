package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            FileWriter wyniki4 = new FileWriter("wyniki4.txt");
            Scanner sc = new Scanner(new File("./src/main/java/org/example/data/liczby.txt"));
            ArrayList<Integer> lines = new ArrayList<>();

            while (sc.hasNextLine()) {
                lines.add(Integer.parseInt((sc.nextLine())));
            }


            wyniki4.write("Zadanie 4.1:\n");
            ArrayList<Integer> z41 = zadanie41(lines);
            wyniki4.write(z41.size() + " " + z41.get(0) + "\n");

            wyniki4.write("Zadanie 4.2:\n");
            List<Integer> z42 = zadanie42(lines);
            wyniki4.write(z42.get(0) + " " + z42.get(1) + " " + z42.get(2) + " " + z42.get(3) + "\n");

            wyniki4.write("Zadanie 4.3:\n");
            List<Integer> z43 = zadanie43(lines);
            wyniki4.write("a) " + z43.get(0) + "\nb) " + z43.get(1) + "\n");

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

    private static List<Integer> zadanie42(ArrayList<Integer> lines) {
        int maxNum = 0;
        int maxFactorials = 0;
        int maxDistinctNum = 0;
        int maxDistinctFactorials = 0;

        List<Integer> primeNumbers = List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103);

        for (int num : lines) {
            int num_copy = num;
            int factCnt = 0;
            int distFactCnt = 0;

            for (int i = 0; num > 1 && i < primeNumbers.size(); i++) {

                if (num % primeNumbers.get(i) == 0) distFactCnt++;
                while (num % primeNumbers.get(i) == 0) {
                    factCnt++;
                    num /= primeNumbers.get(i);
                }
            }

            if (distFactCnt > maxDistinctFactorials) {
                maxDistinctNum = num_copy;
                maxDistinctFactorials = distFactCnt;
            }

            if (factCnt > maxFactorials) {
                maxNum = num_copy;
                maxFactorials = factCnt;
            }
        }

        return List.of(maxNum, maxFactorials, maxDistinctNum, maxDistinctFactorials);
    }

    private static List<Integer> zadanie43(ArrayList<Integer> lines) {
        int triCount = 0;
        int pentCount = 0;

        try {
            FileWriter resultsFile = new FileWriter("trojki.txt");

            for (int x : lines) {
                for (int y : lines) {
                    if (x == y || x > y || y % x != 0) continue;

                    for (int z : lines) {
                        if (y == z || y > z || z % y != 0) continue;

                        triCount++;

                        resultsFile.write(x + " " + y + " " + z + "\n");

                        for (int p : lines) {
                            if (z == p || z > p || p % z != 0) continue;

                            for (int q : lines) {
                                if (p == q || p > q || q % p != 0) continue;
                                pentCount++;
                            }
                        }
                    }
                }
            }

            resultsFile.close();
        } catch (IOException e) {
            System.out.println("I/O exception");
        }

        return List.of(triCount, pentCount);
    }
}