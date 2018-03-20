package enigma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main
{
    private static Integer abs(Integer x) {
        return Math.abs(x);
    }

    private static Integer random(Random r) {
        return abs(r.nextInt()%5);
    }
    private static Integer[] generatePassword()
    {
        Integer[] ret = new Integer[4];
        Random random = new Random();
        Integer first = random(random);
        ret[0] = first;
        Integer second = ret[0];
        while (second.equals(ret[0])){
            second = random(random);
        }
        ret[1] = second;
        Integer  third = second;
        while (third.equals(second) || third.equals(first)){
            third = random(random);
        }
        ret[2] = third;
        Integer fourth = third;
        while(fourth.equals(third) || fourth.equals(second) || fourth.equals(first)){
            fourth = random(random);
        }
        ret[3] = fourth;
        return ret;
    }

    private static boolean mainLoop(Integer[] password)
    {
        System.out.println("Enter your guess:");

        Integer yellow = 0;
        Integer red = 0;
        Integer[] guess = input();
        boolean duplicatePresent = false;
        for (int i = 0; i < 4; i++)
        {
            for (int j = i + 1; j < 4; j++)
            {
                if (guess[i] == guess[j])
                {
                    duplicatePresent = true;
                }
            }
        }
        if (!duplicatePresent)
        {
            if (guess.length == 4)
            {
                boolean correct = false;
                if (guess[1].equals(password[1]) &&
                        guess[2].equals(password[2]) &&
                        guess[3].equals(password[3]) &&
                        guess[0].equals(password[0]))
                {
                    return true;
                }

                for (Integer i = 0; i < 4; i++)
                {
                    if (guess[i].equals(password[i]))
                    {
                        red++;
                    }
                }
                for (int i = 0; i < 4; i++)
                {
                    for (int j = 0; j < 4; j++)
                    {
                        if (password[i].equals(guess[j]) && i != j)
                        {
                            yellow++;
                        }
                    }
                }
                System.out.println("Yellow = " + yellow);
                System.out.println("Red = " + red);
                return false;
            } else
            {
                return false;
            }
        } else {
            System.out.println("Please no dups");
            return false;
        }


    }

    private static Integer[] input()
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            String s = br.readLine();
            if (s.length() != 4)
            {
                Integer[] ret = new Integer[4];
                for (int i = 0; i < 4; i++)
                {
                    ret[i] = -1;
                }
                return ret;
            }
            s = s.substring(0, 4);
            Integer[] ret = new Integer[4];
            for (Integer i = 0; i < 4; i++)
            {
                ret[i] = Character.getNumericValue(s.charAt(i));
            }
            return ret;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        Integer[] nullArr = new Integer[4];
        for (int i = 0; i < 4; i++)
        {
            nullArr[i] = -1;
        }
        return null;
    }

    static void printFourIntegerArray(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        Integer[] password = generatePassword();
        while (!mainLoop(password))
        {
            printFourIntegerArray(password);
        }
        System.out.println("You won!");
    }
}
