package homework.tests;

import homework.annotation.*;

public class TestClass {

    @BeforeAll
    public static void beforeTests() {
        System.out.println("Start method BeforeAll!!!");
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Start method BeforeEach!!!");
    }

    @homework.annotation.Test
    public void test1() {
        System.out.println("Start method Test1!!!");
    }

    @homework.annotation.Test
    public void test2() {
        System.out.println("Start method Test2!!!");
    }

    @Test
    public void test3() {
        System.out.println("Start method Test3!!!");
    }

    @AfterEach
    public void endTest() {
        System.out.println("Start method AfterEach!!!");
    }

    @AfterAll
    public static void afterTests() {
        System.out.println("Start method AfterAll!!!");
    }
}
