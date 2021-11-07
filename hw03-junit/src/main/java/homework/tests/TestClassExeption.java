package homework.tests;

import homework.annotation.*;

public class TestClassExeption {

    @BeforeAll
    public static void beforeTests() {
        System.out.println("Start method BeforeAll!!!");
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Start method BeforeEach!!!");
    }

    @Test
    public void test() {
        System.out.println("test failed!!!!");
        throw new RuntimeException("test failed!!!!");
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
