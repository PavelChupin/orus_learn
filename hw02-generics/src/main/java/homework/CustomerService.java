package homework;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    Map<Customer, String> customers = new HashMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        return customers.entrySet()
                .stream()
                .min(Comparator.comparing(o -> o.getKey().getScores()))
                .orElse(null);  // это "заглушка, чтобы скомилировать"
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return customers.entrySet()
                .stream()
                .filter(x -> x.getKey().getScores() > customer.getScores())
                .min(Comparator.comparing(o -> o.getKey().getScores()))
                .orElse(null); // это "заглушка, чтобы скомилировать"
    }

    public void add(Customer customer, String data) {
        this.customers.put(customer, data);
    }
}