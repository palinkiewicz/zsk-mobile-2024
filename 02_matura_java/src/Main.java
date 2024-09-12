import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> lines = new ArrayList<Integer>() {};

        try {
            Scanner sc = new Scanner(new File("src/dane/przyklad.txt"));

            while (sc.hasNextLine()) {
                lines.add(parseInt(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println("Zadanie 4.1:");
        System.out.println(zadanie41(lines));

        System.out.println("Zadanie 4.2:");
        zadanie42(lines).forEach(System.out::println);
    }

    private static long zadanie41(ArrayList<Integer> lines) {
        ArrayList<Integer> lookingFor = new ArrayList<>();

        for (int i = 0; Math.pow(3, i) <= 100000; i++) {
            lookingFor.add((int) Math.pow(3, i));
        }

        return lines.stream().filter(lookingFor::contains).count();
    }

    private static List<Integer> zadanie42(ArrayList<Integer> lines) {
        return lines.stream().filter((n) -> {
            int l = 0, c = n;

            for (int m = c % 10; c > 0; c = c / 10, m = c % 10) {
                l += factorial(m);
            }

            return l == n;
        }).toList();
    }

    private static ArrayList<Integer> zadanie43(ArrayList<Integer> lines) {
        int cur_len = 0, cur_gcd = lines.get(0), max_len = 0, max_gcd = 0, max_index = 0;

        for (int i = 1; i < lines.size(); i++) {
            cur_gcd = gcd(cur_gcd, lines.get(i));


        }

        return new ArrayList<Integer>();
    }

    private static int factorial(int n) {
        return n <= 1 ? 1 : n * factorial(n - 1);
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}