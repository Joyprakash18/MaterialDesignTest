package com.sayan.sample.materialdesigntest;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class UnderstandingRSATest {
    @Test
    public void generatePrimeTest(){
        assertEquals(7477, UnderstandingRSA.PrimeNumberGenerator.generatePrime());
    }

    @Test
    public void isPrimeTest(){
        assertTrue(UnderstandingRSA.PrimeNumberGenerator.isPrime(9972));
    }

    @Test
    public void findGCDTest(){
        assertEquals(1, UnderstandingRSA.LCMGenerator.findGCD(67, 71));
    }

    @Test
    public void findLCMTest(){
        assertEquals(2310, UnderstandingRSA.LCMGenerator.findLCM(67, 71));
    }

    @Test
    public void NumberNotDivisorPickerTest(){
        assertEquals(9, UnderstandingRSA.NumberNotDivisorPicker.pick(12));
    }
}