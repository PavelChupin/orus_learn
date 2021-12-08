package bankomat;

import bankomat.element.Banknote;
import bankomat.element.Cassette;
import bankomat.element.Nominal;
import bankomat.exeption.BankomatExeption;

import java.util.*;
import java.util.stream.Collectors;

public class Bankomat implements ATM {
    private final Map<Nominal, Cassette> cassettes = new TreeMap<>(Comparator.comparingInt(Nominal::getNominal).reversed());

    public Bankomat() {
        Arrays.stream(Nominal.values())
                .forEach(nomin -> cassettes.put(nomin, new Cassette(nomin)));
    }

    public long getRest() {
        return this.cassettes.values().stream()
                .mapToLong(Cassette::getSumAmountInCassette)
                .sum();
    }

    public void cashIn(List<Banknote> banknotes) {
        banknotes.stream()
                .collect(Collectors.groupingBy(Banknote::getNominal))
                .forEach(this::addBanknoteToBankomat);

    }

    private void addBanknoteToBankomat(Nominal nominal, List<Banknote> banknotes) {
        if (Arrays.stream(Nominal.values())
                .anyMatch(n -> n != nominal)
        ) {
            throw new BankomatExeption(String.format("Nominal %d of banknote not support.", nominal.getNominal()));
        }
        cassettes.get(nominal).addBanknotes(banknotes);
    }

    public List<Banknote> cashOut(long cash) {
        long rest = getRest();

        if (cash > rest || cash <= 0
                || cash % Arrays.stream(Nominal.values())
                .mapToInt(Nominal::getNominal)
                .min()
                .orElse(0) != 0) {
            throw new BankomatExeption(String.format("The requested amount %d could not be dispensed.", cash));
        } else if (cash == rest) {
            return allAmountOut();
        } else {
            return getAmountOut(cash);
        }
    }

    private List<Banknote> allAmountOut() {
        final List<Banknote> r = new ArrayList<>();
        this.cassettes.values().forEach(c -> r.addAll(c.pollAllBanknotes()));
        return r;
    }

    private List<Banknote> getAmountOut(long cash) {
        final List<Banknote> banknotesOut = new ArrayList<>();
        long cashTemp = cash;
        for (Map.Entry<Nominal, Cassette> entry : this.cassettes.entrySet()) {
            Nominal nominalKey = entry.getKey();
            Cassette cassette = entry.getValue();
            if (cassette.getCountBanknotesInCassette() > 0 && cashTemp >= nominalKey.getNominal()) {
                List<Banknote> bt = cassette.pollBanknotes(calcCountOfBanknotes(nominalKey, cashTemp));
                banknotesOut.addAll(bt);
                cashTemp -= (long) bt.size() * nominalKey.getNominal();
            }

            if (cashTemp == 0) {
                break;
            }
        }

        if (cashTemp > 0) {
            cashIn(banknotesOut);
            throw new BankomatExeption(String.format("The requested amount %d could not be dispensed.", cash));
        }
        return banknotesOut;
    }

    private int calcCountOfBanknotes(Nominal nominal, long cash) {
        return (int) (cash / nominal.getNominal());
    }

    @Override
    public String toString() {
        return "Bankomat{" +
                "cassettes=" + cassettes +
                '}';
    }
}