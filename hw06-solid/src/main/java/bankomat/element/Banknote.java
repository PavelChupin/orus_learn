package bankomat.element;

public class Banknote {
    private final Nominal nominal;

    public Banknote(Nominal nominal) {
        this.nominal = nominal;
    }

    public Nominal getNominal() {
        return nominal;
    }

    @Override
    public String toString() {
        return "Banknote{" +
                "nominal=" + nominal.getNominal() +
                '}';
    }

    public int getAmountOfNominal(){
        return nominal.getNominal();
    }
}