package bankomat.exeption;

public class CassetteExeption extends RuntimeException {
    public CassetteExeption(String message) {
        super(message);
    }
}