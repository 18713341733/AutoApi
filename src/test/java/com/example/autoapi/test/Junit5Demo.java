package com.example.autoapi.test;

import org.junit.jupiter.api.*;

public class Junit5Demo {

    @BeforeAll
    public static void beforeAll(){
        System.out.println("Junit5Demo.beforeAll");
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("Junit5Demo.beforeEach");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("Junit5Demo.AfterAll");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("Junit5Demo.afterEach");
    }

    @Test
    public void test1(){
        System.out.println("Junit5Demo.test1");
    }

    @Test
    public void test2(){
        System.out.println("Junit5Demo.test1");
    }
}
