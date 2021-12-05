package homework.tests;

import homework.annotation.AfterAll;
import homework.annotation.AfterEach;
import homework.annotation.BeforeAll;
import homework.annotation.Test;

public class TestClassNotBeforeEach {

    @BeforeAll
    public static void beforeTests() {
        System.out.println("Start method BeforeAll!!!");
    }

    @Test
    public void test() {
        System.out.println("NotBeforeEach!!!!");
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
