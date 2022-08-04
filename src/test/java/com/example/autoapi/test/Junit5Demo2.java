package com.example.autoapi.test;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class Junit5Demo2 {

    @Tag("P0")
    @Timeout(2)
    @Test
    public void testNormal(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Junit5Demo2.testNormal");

    }

}
