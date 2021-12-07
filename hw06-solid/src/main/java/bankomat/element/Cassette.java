package bankomat.element;

import bankomat.exeption.CassetteExeption;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Cassette {
    private final Nominal nominal;
    private final Deque<Banknote> cassetteBanknotes = new ArrayDeque<>();

    public Cassette(Nominal nominal) {
        this.nominal = nominal;
    }

    public long getSumAmountInCassette() {
        return (long) cassetteBanknotes.size() * nominal.getNominal();
    }

    public List<Banknote> pollBanknotes(int count) {
        final List<Banknote> banknotes = new ArrayList<>();
        for (int i = cassetteBanknotes.size() < count ? cassetteBanknotes.size() : count; i > 0; i--) {
            banknotes.add(cassetteBanknotes.pollLast());
        }
        return banknotes;
    }

    public List<Banknote> pollAllBanknotes() {
        final List<Banknote> banknotes = new ArrayList<>(cassetteBanknotes);
        cassetteBanknotes.clear();
        return banknotes;
    }

    public void addBanknotes(List<Banknote> banknotes) {
        if (banknotes.stream().anyMatch(b -> b.getNominal() != nominal)) {
            throw new CassetteExeption("An unsupported note has been transferred to the cassette.");
        }
        cassetteBanknotes.addAll(banknotes);
    }

    public int getCountBanknotesInCassette() {
        return cassetteBanknotes.size();
    }

    @Override
    public String toString() {
        return "Cassette{" +
                "nominal=" + nominal +
                ", cassetteBanknotes=" + cassetteBanknotes +
                '}';
    }
}