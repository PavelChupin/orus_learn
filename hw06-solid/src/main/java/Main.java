import bankomat.Banknote;
import bankomat.Bankomat;
import bankomat.BankomatATM;
import bankomat.Nominal;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Bankomat bk = new BankomatATM();

        try {
            List<Banknote> bt = new ArrayList<>();
            bt.add(new Banknote(Nominal.TEN));
            bt.add(new Banknote(Nominal.FIVE_HUNDRED));
            bt.add(new Banknote(Nominal.TWO_HUNDRED));
            bt.add(new Banknote(Nominal.TWO_HUNDRED));
            bk.cashIn(bt);

            System.out.println(bk.getRest());

            List<Banknote> out = bk.cashOut(810l);
            System.out.println(out);
            System.out.println(bk.getRest());
            System.out.println(bk);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println(bk.getRest());
        }
    }
}
