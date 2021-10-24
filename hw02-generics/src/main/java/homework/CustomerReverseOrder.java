package homework;


import java.util.*;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    private Deque<Customer> customerList = new ArrayDeque<>();

    public void add(Customer customer) {
        this.customerList.add(customer);
    }

    public Customer take() {
        return this.customerList.pollLast();
    }
}