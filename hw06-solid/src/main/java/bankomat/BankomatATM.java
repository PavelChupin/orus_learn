package bankomat;

import java.util.*;
import java.util.stream.Collectors;

public class BankomatATM implements Bankomat{
    private final Map<Nominal, Deque<Banknote>> banknotes = new TreeMap<>(Comparator.comparingInt(Nominal::getNominal).reversed());

    public BankomatATM() {
        Arrays.stream(Nominal.values())
                .forEach(nomin -> banknotes.put(nomin, new ArrayDeque<>()));
    }

    public void cashIn(List<Banknote> banknotes) {
        banknotes.stream()
                .collect(Collectors.groupingBy(Banknote::getNominal))
                .forEach(this::addBanknoteToBankomat);

    }

    private void addBanknoteToBankomat(Nominal nominal, List<Banknote> banknotes) {
        if (Nominal.valueOf(nominal.toString()) == null) {
            throw new BankomatExeption(String.format("Nominal %d of banknote not support.", nominal.getNominal()));
        }
        this.banknotes.get(nominal).addAll(banknotes);
    }

    public List<Banknote> cashOut(long cash) {
        long rest = getRest();

        if (cash > rest || cash <= 0
                || cash % Arrays.stream(Nominal.values())
                .mapToInt(Nominal::getNominal)
                .min()
                .getAsInt() != 0) {
            throw new BankomatExeption(String.format("The requested amount %d could not be dispensed.", cash));
        } else if (cash == rest) {
            return allAmountOut();
        } else {
            return getAmountOut(cash);
        }
    }

    private List<Banknote> allAmountOut() {
        List<Banknote> r = new ArrayList<>();
        this.banknotes.forEach((key, value) -> {
            r.addAll(value);
            value.clear();
        });
        return r;
    }

    private List<Banknote> getAmountOut(long cash) {
        List<Banknote> banknotesOut = new ArrayList<>();
        long cashTemp = cash;
        for (Map.Entry<Nominal, Deque<Banknote>> entry : this.banknotes.entrySet()) {
            Nominal nominalKey = entry.getKey();
            Deque<Banknote> value = entry.getValue();
            if (!value.isEmpty() && cashTemp >= nominalKey.getNominal()) {
                List<Banknote> bt = getBanknote(value, calcCountOfBanknotes(nominalKey, cashTemp));
                banknotesOut.addAll(bt);
                cashTemp -= (long) bt.size() * nominalKey.getNominal();
            }
        }

        if (cash > 0){
            cashIn(banknotesOut);
            throw new BankomatExeption(String.format("The requested amount %d could not be dispensed.", cash));
        }
        return banknotesOut;
    }


    private List<Banknote> getBanknote(Deque<Banknote> banknotes, int count) {
        List<Banknote> banknote = new ArrayList<>();
        for (int i = banknotes.size() < count ? banknotes.size() : count; i > 0; i--) {
            banknote.add(banknotes.pollLast());
        }
        return banknote;
    }

    private int calcCountOfBanknotes(Nominal nominal, long cash) {
        return (int) (cash / nominal.getNominal());
    }

    public long getRest() {
        return this.banknotes.entrySet().stream()
                .mapToLong(entr -> (long) entr.getKey().getNominal() * entr.getValue().size())
                .sum();
    }

    @Override
    public String toString() {
        return "Bankomat{" +
                "banknotes=" + banknotes +
                '}';
    }
}