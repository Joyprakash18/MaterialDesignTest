package com.sayan.sample.materialdesigntest;

import android.os.AsyncTask;
import android.support.v4.math.MathUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Random;

public class UnderstandingRSA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_understanding_rs);
        int i = PrimeNumberGenerator.generatePrime();
        int j = PrimeNumberGenerator.generateNextPrime(i);
        long multi = i * j;
        long lcm = LCMGenerator.findLCM(i - 1, j - 1);
        long notDivisor = NumberNotDivisorPicker.pick(lcm);
//        inverseMOD(notDivisor, lcm);
        int inverseMOD = inverseMOD(23, 2310);

        Toast.makeText(this, "" + i, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "" + j, Toast.LENGTH_SHORT).show();
    }

    private int inverseMOD(long notDivisor, long lcm) {
        notDivisor = notDivisor % lcm;
        for (int x = 1; x < lcm; x++)
            if ((notDivisor * x) % lcm == 1)
                return x;
        return 1;
    }

    static class PrimeNumberGenerator {
        private static final int MAX_BOUND = 10000;
        private static final int MIN_BOUND = 7000;

        static int generatePrime() {
            int num = 0;
            final Random rand = new Random(); // generate a random number
            num = rand.nextInt(MAX_BOUND + 1 - MIN_BOUND) + MIN_BOUND;

            while (!isPrime(num)) {
                num = rand.nextInt(MAX_BOUND + 1 - MIN_BOUND) + MIN_BOUND;
            }
            return num;
        }

        static int generateNextPrime(int num) {
            num++;
            while (!isPrime(num)) {
                ++num;
            }
            return num;
        }

        /**
         * Checks to see if the requested value is prime.
         */
        static boolean isPrime(int inputNum) {
            if (inputNum <= 3 || inputNum % 2 == 0)
                return inputNum == 2 || inputNum == 3; //this returns false if number is <=1 & true if number = 2 or 3
            int divisor = 3;
            while ((divisor <= Math.sqrt(inputNum)) && (inputNum % divisor != 0))
                divisor += 2; //iterates through all possible divisors
            return inputNum % divisor != 0; //returns true/false
        }
    }

    static class LCMGenerator {
        static long findLCM(int a, int b) {
            return (a * b) / findGCD(a, b);
        }

        static long findGCD(int a, int b) {
            if (b == 0) return a;
            return findGCD(b, a % b);
        }
    }

    static class NumberNotDivisorPicker {
        static long pick(long number) {
            final Random rand = new Random(); // generate a random number
            //generate a randomNumber between half of the number and the number
            long randomNumber = rand.nextInt((int) (number / 2)) + (int) number / 2;
            //check if randomNumber is a divisor of the number
            while ((number % randomNumber) == 0) {
                randomNumber++;
            }
            return randomNumber;
        }
    }
}
