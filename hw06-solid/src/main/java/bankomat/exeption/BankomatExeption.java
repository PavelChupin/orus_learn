package bankomat.exeption;

public class BankomatExeption extends RuntimeException{
    public BankomatExeption(String message) {
        super(message);
    }
}