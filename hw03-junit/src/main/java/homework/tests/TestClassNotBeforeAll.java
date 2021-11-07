package homework.tests;

import homework.annotation.AfterAll;
import homework.annotation.AfterEach;
import homework.annotation.BeforeEach;
import homework.annotation.Test;

public class TestClassNotBeforeAll {

    @BeforeEach
    public void initTest() {
        System.out.println("Start method BeforeEach!!!");
    }

    @Test
    public void test() {
        System.out.println("NotBeforeAll!!!!");
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
