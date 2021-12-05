package homework.tests;

import homework.annotation.AfterEach;
import homework.annotation.BeforeAll;
import homework.annotation.BeforeEach;
import homework.annotation.Test;

public class TestClassNotAfterAll {

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
        System.out.println("NotAfterAll!!!!");
    }

    @AfterEach
    public void endTest() {
        System.out.println("Start method AfterEach!!!");
    }

}
