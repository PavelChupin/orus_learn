import bankomat.element.Banknote;
import bankomat.ATM;
import bankomat.Bankomat;
import bankomat.element.Nominal;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ATM bk = new Bankomat();

        try {
            List<Banknote> bt = new ArrayList<>();
            bt.add(new Banknote(Nominal.TEN));
            bt.add(new Banknote(Nominal.FIVE_HUNDRED));
            bt.add(new Banknote(Nominal.TWO_HUNDRED));
            bt.add(new Banknote(Nominal.TWO_HUNDRED));
            bk.cashIn(bt);

            System.out.println(bk.getRest());

            List<Banknote> out = bk.cashOut(710l);
            System.out.println(out);
            System.out.println(bk.getRest());
            System.out.println(bk);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println(bk.getRest());
        }
    }
}
