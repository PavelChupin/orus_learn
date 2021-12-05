package homework.myjunit;

import homework.annotation.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MyJunit {
    private int testSuccess = 0;
    private int testErr = 0;

    public void start(Class<?> testClass) {
        System.out.println("Test className: " + testClass.getSimpleName());
        List<Method> methods = Arrays.asList(testClass.getDeclaredMethods());

        // Запускаем метод beforeAll
        startMethodAll(BeforeAll.class, methods);

        // Запускаем основной набор тестов
        startTests(testClass, methods);

        // Запускаем метод afterAll
        startMethodAll(AfterAll.class, methods);

        //Статистика выполненных тестов для класса
        System.out.println(testClass.getSimpleName() + "\n TestSuccess: " + testSuccess + "\n TestErr: " + testErr);
    }

    private void startTests(Class<?> testClass, List<Method> methods) {
        Optional<Method> beforeMethodEach = getMethodEach(methods, BeforeEach.class);
        Optional<Method> afterMethodEach = getMethodEach(methods, AfterEach.class);

        methods.stream()
                .filter(method -> method.isAnnotationPresent(Test.class) && !Modifier.isStatic(method.getModifiers()))
                .forEach(testMethod -> {
                    Object obj = null;
                    try {
                        obj = testClass.getConstructor().newInstance();
                    } catch (ReflectiveOperationException e) {
                        throw new RuntimeException(e);
                    }
                    List<Method> mt = new ArrayList<>();
                    beforeMethodEach.ifPresent(mt::add);
                    mt.add(testMethod);
                    afterMethodEach.ifPresent(mt::add);

                    try {
                        startTest(obj, mt);
                        testSuccess++;
                    } catch (RuntimeException e) {
                        if (afterMethodEach.isPresent()) {
                            try {
                                afterMethodEach.get().invoke(obj);
                            } catch (ReflectiveOperationException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        testErr++;
                    }
                });
    }

    private void startTest(Object obj, List<Method> methods) {
        methods.forEach(method -> {
            try {
                method.invoke(obj);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Optional<Method> getMethodEach(List<Method> methods, Class annotation) {
        return methods.stream()
                .filter(method -> method.isAnnotationPresent(annotation) && !Modifier.isStatic(method.getModifiers()))
                .findFirst();
    }

    private void startMethodAll(Class annotation, List<Method> methods) {
        Optional<Method> methodAll = methods.stream()
                .filter(method -> method.isAnnotationPresent(annotation) && Modifier.isStatic(method.getModifiers()))
                .findFirst();

        if (methodAll.isPresent()) {
            try {
                methodAll.get().invoke(null);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
        }
    }
}