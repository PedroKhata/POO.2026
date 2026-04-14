package joaoPedroOliveira.banco;

import java.util.*;

public class Estatica {
    static int x = 0;

    Estatica() {
        x++;
    }

    public static void main(String[] args) {
        Estatica a1 = new Estatica();
        Estatica a2 = new Estatica();
        Estatica a3 = new Estatica();

        System.out.println(a1.x);
        System.out.println(a2.x);
        System.out.println(a3.x);

        Date data = new Date();
        System.out.println(data);
        long dataLong = data.getTime();

        GregorianCalendar gc = new GregorianCalendar();
        System.out.println(gc);
        System.out.println(gc.get(gc.DAY_OF_WEEK));
        System.out.println(gc.get(gc.DATE));
        System.out.println(gc.get(gc.MONTH));
        System.out.println(gc.get(gc.YEAR));

    }
}
