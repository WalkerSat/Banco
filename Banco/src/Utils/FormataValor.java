package Utils;

import java.text.NumberFormat;

public class FormataValor {
    private static NumberFormat formatValor = NumberFormat.getCurrencyInstance();

    public static String toStringSaldo(double saldo) {
        return formatValor.format(saldo);
    }
}