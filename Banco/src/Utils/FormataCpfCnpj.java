package Utils;

public class FormataCpfCnpj {

    public static String formataCPF(String cpf) {
        if (cpf.length() == 11) {
            cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." +
                    cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
        } else if (cpf.length() == 14) {
            cpf = cpf.substring(0, 3) + "." + cpf.substring(4, 7) + "." +
                    cpf.substring(8, 11) + "-" + cpf.substring(12, 14);
        }
        return cpf;
    }

    public static String formataCNPJ(String cnpj) {
        if (cnpj.length() == 14) {
            cnpj = cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." +
                    cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" +
                    cnpj.substring(12, 14);
        } else if (cnpj.length() == 18) {
            cnpj = cnpj.substring(0, 2) + "." + cnpj.substring(3, 6) + "." +
                    cnpj.substring(7, 10) + "/" + cnpj.substring(11, 15) + "-" +
                    cnpj.substring(16, 18);
        } else {

        }
        return cnpj;
    }
}

/*
 * if (cpfCnpj.substring(0, 3).matches("^[a-zA-Z]*$") || cpfCnpj.substring(4,
 * 7).matches("^[a-zA-Z]*$")
 * || cpfCnpj.substring(8, 11).matches("^[a-zA-Z]*$")
 * || cpfCnpj.substring(12, 14).matches("^[a-zA-Z]*$")) {
 * }
 */
