package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String code;
        code = creatCode();

        Start(code);

        System.out.println("Congratulations! You guessed the secret code.");
    }

    public static boolean printGrade(String num, String code) {
        int bulls = 0;
        int cows = 0;

        bulls = checkBulls(num, code);
        cows = checkCows(num, code);

        if(bulls == num.length()) {
            System.out.printf("Grade: %d %s \n", bulls, (bulls == 1 ? "bull" : "bulls"));
            return true;
        }


        if (bulls == 0 && cows == 0)
            System.out.println("Grade: None");
        else if (bulls > 0 && cows > 0)
            System.out.println("Grade: "+ bulls + " " + (bulls == 1 ? "bull" : "bulls") + " and " + cows + " " + (cows == 1 ? "cow" : "cows"));
        else
            System.out.println("Grade: " + (cows > 0 ? cows : bulls) + " " + (cows > 0 ? (cows == 1 ? "cow" : "cows") : (bulls == 1 ? "bull" : "bulls") ) );

        return false;
    }

    public static int checkBulls(String num ,String code) {
        int bulls = 0;

        for (int i = 0; i < code.length() && i < num.length(); i++) {
            if (code.charAt(i) == num.charAt(i))
                bulls++;
        }

        return bulls;
    }

    public static int checkCows(String num, String code) {
        int cows = 0;

        for (int i = 0; i < code.length(); i++) {
            for (int j = 0; j < num.length(); j++) {
                if(i != j) {
                    if (code.charAt(i) == num.charAt(j))
                        cows++;
                }
            }
        }
        return cows;
    }

    public static void Start(String code) {
        Scanner in = new Scanner(System.in);
        String num;
        boolean isFind = false;

        while (!isFind) {
            num = in.nextLine();
            isFind = printGrade(num, code);
        }
    }

    public static String creatCode() {
        Scanner in = new Scanner(System.in);
        Random r = new Random();
        char randomNumber;
        StringBuilder code = new StringBuilder();
        int size = 0;
        int symbolsAmount = 0;
        int counter = 0;
        boolean isCorrect = true;

        try {
            System.out.println("Input the length of the secret code:");
            size = in.nextInt();
            if (size <= 0)
                throw new NumberFormatException("The is Negative or zero");
            System.out.println("Input the number of possible symbols in the code:");
            symbolsAmount = in.nextInt();
            if (symbolsAmount <= 0)
                throw new NumberFormatException("The is Negative or zero");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            System.exit(0);
        }

        if (size >= symbolsAmount) {
            System.out.print("Error: size smaller that Symbols amount");
            System.exit(0);
        }

        if (size > 36 || symbolsAmount > 36) {
            System.out.println("Error: can't generate a secret number with a length of " + size + " because there aren't enough unique digits.");
            System.exit(0);
        }

        if (symbolsAmount < 11) {
            code.append(r.nextInt(symbolsAmount - 1));
        } else {
            if (r.nextBoolean()) {
                code.append(r.nextInt(9));
            } else {
                if (symbolsAmount == 11)
                    code.append('a');
                else
                    code.append(r.nextInt(symbolsAmount - 11) + 'a');
            }
        }
         while (counter != size - 1) {

             if (symbolsAmount < 11) {
                 randomNumber = (char)(r.nextInt(symbolsAmount - 1) + '0');
             } else {
                 if (r.nextBoolean()) {
                     randomNumber =(char)(r.nextInt(9) + '0');
                 } else {
                     if (symbolsAmount == 11)
                         randomNumber = 'a';
                     else
                        randomNumber = (char)(r.nextInt(symbolsAmount - 11) + 'a');
                 }
             }

             for (int i = 0; i < code.length() && isCorrect; i++) {
                 if (code.charAt(i) == randomNumber)
                     isCorrect = false;
             }

             if (isCorrect) {
                 code.append((char)randomNumber);
                 counter++;
             }
             isCorrect = true;

         }

         System.out.println("The secret is prepared: " + (printX(size)) + (symbolsAmount < 11 ? " (0-" + (symbolsAmount - 1) + ")" : " (0-9, a-" + (char)(symbolsAmount - 11 + 'a') + ")."));
         System.out.println(code.toString());
         return code.toString();

    }

    public static String printX(int size) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append('*');
        }
        return s.toString();
    }
}
