package bankomat;

import java.util.List;

public interface Bankomat {
    void cashIn(List<Banknote> banknotes);

    List<Banknote> cashOut(long cash);

    long getRest();
}
