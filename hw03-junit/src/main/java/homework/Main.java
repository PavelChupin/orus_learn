package homework;

import homework.myjunit.MyJunit;
import homework.tests.*;

public class Main {
    public static void main(String[] args) {
        new MyJunit().start(TestClass.class);
        System.out.println();
        System.out.println("Test Exeption");
        new MyJunit().start(TestClassExeption.class);
        System.out.println();
        System.out.println("Test NotBeforeAll");
        new MyJunit().start(TestClassNotBeforeAll.class);
        System.out.println();
        System.out.println("Test NotBeforeEach");
        new MyJunit().start(TestClassNotBeforeEach.class);
        System.out.println();
        System.out.println("Test NotAfterAll");
        new MyJunit().start(TestClassNotAfterAll.class);
        System.out.println();
        System.out.println("Test NotAfterEach");
        new MyJunit().start(TestClassNotAfterEach.class);
    }
}