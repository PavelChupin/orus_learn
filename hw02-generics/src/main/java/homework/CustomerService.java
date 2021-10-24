package homework;


import java.util.*;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final NavigableMap<Customer, String> customers = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        Optional<Map.Entry<Customer, String>> entry = Optional.of(customers.firstEntry());
        return getCustomerStringSimpleEntry(entry);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Optional<Map.Entry<Customer, String>> entry = Optional.ofNullable(customers.higherEntry(customer));
        return getCustomerStringSimpleEntry(entry);
    }

    private AbstractMap.SimpleEntry<Customer, String> getCustomerStringSimpleEntry(Optional<Map.Entry<Customer, String>> entry) {
        return entry.isPresent() ? new AbstractMap.SimpleEntry<>(new Customer(entry.get().getKey().getId(), entry.get().getKey().getName(), entry.get().getKey().getScores()), entry.get().getValue()) : null;
    }

    public void add(Customer customer, String data) {
        this.customers.put(customer, data);
    }
}