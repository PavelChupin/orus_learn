package homework.tests;

import homework.annotation.AfterAll;
import homework.annotation.BeforeAll;
import homework.annotation.BeforeEach;
import homework.annotation.Test;

public class TestClassNotAfterEach {

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
        System.out.println("NotAfterEach!!!!");
    }


    @AfterAll
    public static void afterTests() {
        System.out.println("Start method AfterAll!!!");
    }
}
