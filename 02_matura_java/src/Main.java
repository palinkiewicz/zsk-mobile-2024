import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

        System.out.println(zadanie41(lines));
    }

    private static long zadanie41(ArrayList<Integer> lines) {
        ArrayList<Integer> lookingFor = new ArrayList<>();

        for (int i = 0; Math.pow(3, i) <= 100000; i++) {
            lookingFor.add((int) Math.pow(3, i));
        }

        return lines.stream().filter(lookingFor::contains).count();
    }
}