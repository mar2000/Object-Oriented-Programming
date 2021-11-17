package edu.mimuw.po.marysia.proj2;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RobsonTest {

    @Test
    public void testDodowanie() throws IOException {

        Robson.fromJSON("src/test/resources/Dodawanie.json");
        assertEquals(15.0, Robson.wykonaj() , 0.17);
    }

    @Test
    public void testFibonacci() throws IOException {

        Robson.fromJSON("src/test/resources/Fibonacci.json");
        assertEquals(89.0, Robson.wykonaj() , 0.17);
    }
}
