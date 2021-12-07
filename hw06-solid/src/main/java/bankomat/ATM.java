package bankomat;

import bankomat.element.Banknote;

import java.util.List;

public interface ATM {
    void cashIn(List<Banknote> banknotes);

    List<Banknote> cashOut(long cash);

    long getRest();
}
