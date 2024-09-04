import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int rand = (new Random()).nextInt(10);
        int guess = -1;

        while (guess != rand) {
            System.out.print("Guess the number: ");
            guess = (new Scanner(System.in)).nextInt();
        }

        System.out.println("Good job!");
    }
}