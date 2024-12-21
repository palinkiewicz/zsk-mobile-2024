import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> lines = new ArrayList<>();

        try {
            FileWriter wyniki4 = new FileWriter("wyniki4.txt");
            Scanner sc = new Scanner(new File("src/dane/liczby.txt"));

            while (sc.hasNextLine()) {
                lines.add(parseInt(sc.nextLine()));
            }

            wyniki4.write("Zadanie 4.1:\n");
            wyniki4.write(zadanie41(lines) + "\n");

            wyniki4.write("Zadanie 4.2:\n");
            zadanie42(lines).forEach(l -> {
                try {
                    wyniki4.write(l + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            wyniki4.write("Zadanie 4.3:\n");
            List<Integer> results43 = zadanie43(lines);
            wyniki4.write(String.format("pierwsza liczba ciągu: %d, długość %d, największy wspólny dzielnik %d%n", lines.get(results43.get(2)), results43.get(0), results43.get(1)));

            wyniki4.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    private static List<Integer> zadanie43(ArrayList<Integer> lines) {
        int max_len = 0, max_gcd = 0, max_index = 0;

        for (int i = 0; i < lines.size(); i++) {
            int cur_gcd = lines.get(i), j = i + 1;

            for (; j < lines.size() && gcd(cur_gcd, lines.get(j)) > 1; j++) {
                cur_gcd = gcd(cur_gcd, lines.get(j));
            }

            if (j - i > max_len) {
                max_len = j - i;
                max_gcd = cur_gcd;
                max_index = i;
            }
        }

        return List.of(max_len, max_gcd, max_index);
    }

    private static int factorial(int n) {
        return n <= 1 ? 1 : n * factorial(n - 1);
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}