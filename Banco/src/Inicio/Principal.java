package Inicio;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import Administracao.Administrador;
import Cliente.*;
import Utils.FormataValor;

public class Principal {
    static ArrayList<Agencia> listAgencia = new ArrayList<Agencia>();
    static Administrador admin = new Administrador();
    static int numeroConvertidoInt;
    static double numeroConvertidoDouble;

    public static void main(String[] args) throws Exception {
        // Instanciamento dos objetos da classe Agencia
        for (int i = 1; i <= 10; i++) {
            Agencia agencia = new Agencia("Brasil AG " + i + "");
            listAgencia.add(agencia);
        }
        // Instanciamento dos objetos da classe Agencia
        menuDeOperacoes();
    }

    public static void menuDeOperacoes() {
        converteStringInt(JOptionPane.showInputDialog(null,
                "---------------------------------------------------------------\n" +
                        " [1] CADASTRO DE CONTAS\n" +
                        " [2] MOVIMENTACAO DE CONTAS\n" +
                        " [3] ADMINISTRACAO\n" +
                        " [0] FINALIZAR\n" +
                        "---------------------------------------------------------------\n",
                "BANCO TIO PATINHAS\n", JOptionPane.INFORMATION_MESSAGE));

        switch (numeroConvertidoInt) {
            case 1:
                cadastroDeContas();
                break;
            case 2:
                movimentacaoDeContas();
                break;
            case 3:
                administradorLogin();
                break;
            case 0:
                JOptionPane.showMessageDialog(null, "OBRIGADO POR USAR NOSSO BANCO!.", "ADEUS",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            default:
                JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA!", "ERRO", JOptionPane.ERROR_MESSAGE);
                menuDeOperacoes();
                break;
        }
    }

    public static void cadastroDeContas() {
        converteStringInt(JOptionPane.showInputDialog(null,
                "---------------------------------------------------------------\n" +
                        " [1] CRIAR CONTA\n" +
                        " [2] TRANSFERIR CONTA\n" +
                        " [3] ENCERRAR CONTA\n" +
                        " [0] VOLTAR\n" +
                        "---------------------------------------------------------------\n",
                "CADASTRO DE CONTAS\n", JOptionPane.INFORMATION_MESSAGE));

        switch (numeroConvertidoInt) {
            case 1:
                cadastrarCliente();
                break;
            case 2:
                transferirContaDeAgencia();
                break;
            case 3:
                encerrarConta();
                break;
            case 0:
                menuDeOperacoes();
                ;
            default:
                JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA!", "ERRO", JOptionPane.ERROR_MESSAGE);
                menuDeOperacoes();
                break;
        }
    }

    public static void cadastrarCliente() {
        converteStringInt(JOptionPane.showInputDialog(null, "IDADE: "));
        if (numeroConvertidoInt >= 18) {
            int idade = numeroConvertidoInt;
            String cpfCnpj = formatarCpfCnpj(JOptionPane.showInputDialog(null, "CPF/CNPJ: "));
            Agencia agencia = escolherAgencia(cpfCnpj);
            String nome = JOptionPane.showInputDialog(null, "NOME COMPLETO: ");
            Pessoa pessoa = new Pessoa(nome, idade, cpfCnpj);
            Conta conta = new Conta(pessoa);
            agencia.addContaNaAgencia(conta);
            JOptionPane.showMessageDialog(null, agencia.toString(conta), "CADASTRO REALIZADO",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "CADASTRO NEGADO PARA MENORES DE IDADE", "AVISO",
                    JOptionPane.ERROR_MESSAGE);
        }
        menuDeOperacoes();
    }

    public static void transferirContaDeAgencia() {
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA AGÊNCIA QUE ESTÁ LOCALIZADA A CONTA: "));
        Agencia agencia1 = buscarAgencia(numeroConvertidoInt);
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA CONTA: "));
        Conta conta = buscarConta(agencia1, numeroConvertidoInt);
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA AGÊNCIA QUE DESEJA TRANSFERIR A CONTA: "));
        Agencia agencia2 = buscarAgencia(numeroConvertidoInt);
        agencia2.addContaNaAgencia(conta);
        agencia1.excluirContaDaAgencia(conta);
        JOptionPane.showMessageDialog(null, "CONTA TRANSFERIDA COM SUCESSO", "SUCESSO",
                JOptionPane.INFORMATION_MESSAGE);
        menuDeOperacoes();
    }

    public static void encerrarConta() {
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA AGÊNCIA QUE ESTÁ LOCALIZADA A CONTA: "));
        Agencia agencia = buscarAgencia(numeroConvertidoInt);
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA CONTA: "));
        Conta conta = buscarConta(agencia, numeroConvertidoInt);
        agencia.excluirContaDaAgencia(conta);
        JOptionPane.showMessageDialog(null, "CONTA ENCERRADA COM SUCESSO", "AVISO",
                JOptionPane.ERROR_MESSAGE);
        menuDeOperacoes();
    }

    public static void movimentacaoDeContas() {
        converteStringInt(JOptionPane.showInputDialog(null,
                "---------------------------------------------------------------\n" +
                        " [1] DEPÓSITO\n" +
                        " [2] SAQUE\n" +
                        " [3] TRANSFÊNCIA\n" +
                        " [4] SALDO\n" +
                        " [5] EXTRADO\n" +
                        " [0] VOLTAR\n" +
                        "---------------------------------------------------------------\n",
                "MOVIMENTAÇÃO DE CONTAS\n", JOptionPane.INFORMATION_MESSAGE));

        switch (numeroConvertidoInt) {
            case 1:
                depositar();
                break;
            case 2:
                sacar();
                break;
            case 3:
                transferencia();
                break;
            case 4:
                saldoNaConta();
                break;
            case 5:
                extrato();
                break;
            case 0:
                menuDeOperacoes();
            default:
                JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA!", "ERRO", JOptionPane.ERROR_MESSAGE);
                menuDeOperacoes();
                break;
        }
    }

    public static void depositar() {
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA AGÊNCIA: "));
        Agencia agencia = buscarAgencia(numeroConvertidoInt);
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA CONTA: "));
        Conta conta = buscarConta(agencia, numeroConvertidoInt);
        converteStringDouble(JOptionPane.showInputDialog(null, "VALOR PARA DEPÓSITO: "));
        agencia.depositarNaConta(numeroConvertidoDouble, conta);
        menuDeOperacoes();
    }

    public static void sacar() {
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA AGÊNCIA: "));
        Agencia agencia = buscarAgencia(numeroConvertidoInt);
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA CONTA: "));
        Conta conta = buscarConta(agencia, numeroConvertidoInt);
        converteStringDouble(JOptionPane.showInputDialog(null, "VALOR PARA SAQUE: "));
        agencia.sacarNaConta(numeroConvertidoDouble, conta);
        menuDeOperacoes();
    }

    public static void transferencia() {
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA AGÊNCIA DO REMETENTE: "));
        Agencia agencia1 = buscarAgencia(numeroConvertidoInt);
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA CONTA DO REMETENTE: "));
        Conta conta1 = buscarConta(agencia1, numeroConvertidoInt);
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA AGÊNCIA DO DESTINATÁRIO: "));
        Agencia agencia2 = buscarAgencia(numeroConvertidoInt);
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA CONTA DO DESTINATÁRIO: "));
        Conta conta2 = buscarConta(agencia2, numeroConvertidoInt);
        converteStringDouble(JOptionPane.showInputDialog(null, "VALOR PARA TRANSFERÊNCIA: "));
        agencia1.transferirDaConta(numeroConvertidoDouble, conta1, conta2);
        menuDeOperacoes();
    }

    public static void saldoNaConta() {
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA AGÊNCIA: "));
        Agencia agencia = buscarAgencia(numeroConvertidoInt);
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA CONTA: "));
        Conta conta = buscarConta(agencia, numeroConvertidoInt);
        JOptionPane.showMessageDialog(null, agencia.toStringContaSaldo(conta), "SALDO",
                JOptionPane.INFORMATION_MESSAGE);
        menuDeOperacoes();
    }

    public static void extrato() {
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA AGÊNCIA: "));
        Agencia agencia = buscarAgencia(numeroConvertidoInt);
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA CONTA: "));
        Conta conta = buscarConta(agencia, numeroConvertidoInt);
        JOptionPane.showMessageDialog(null, agencia.extratoNaConta(conta), "EXTRATO",
                JOptionPane.INFORMATION_MESSAGE);
        menuDeOperacoes();
    }

    private static void menuAdministrador() {
        converteStringInt(JOptionPane.showInputDialog(null,
                "---------------------------------------------------------------\n" +
                        " [1] QUANTIDADE DE CONTAS/SALDO POR AGÊNCIA\n" +
                        " [2] LISTA DE CONTAS POR AGÊNCIA\n" +
                        " [3] DADOS DO CLIENTE\n" +
                        " [0] VOLTAR\n" +
                        "---------------------------------------------------------------\n",
                "MENU DO ADMINISTRADOR\n", JOptionPane.INFORMATION_MESSAGE));

        switch (numeroConvertidoInt) {
            case 1:
                quantDContasSaldoAG();
                break;
            case 2:
                listaDContasAG();
                break;
            case 3:
                dadosDoCliente();
                break;
            case 0:
                menuDeOperacoes();
            default:
                JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA!", "ERRO", JOptionPane.ERROR_MESSAGE);
                menuDeOperacoes();
                break;
        }
    }

    public static void administradorLogin() {
        String user = JOptionPane.showInputDialog(null, "USUÁRIO: ");
        if (user.equals(admin.getUsuario())) {
            String senha = JOptionPane.showInputDialog(null, "SENHA: ");
            if (senha.equals(admin.getSenha()))
                menuAdministrador();
            else
                JOptionPane.showMessageDialog(null, "SENHA INVÁLIDA!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "USUÁRIO INVÁLIDO!", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        menuDeOperacoes();
    }

    public static void quantDContasSaldoAG() {
        String lista = "\n";
        for (Agencia a : listAgencia) {
            lista += a.toString();
        }
        JOptionPane.showMessageDialog(null, lista, "LISTA COMPLETA", JOptionPane.INFORMATION_MESSAGE);
        menuAdministrador();
    }

    public static void listaDContasAG() {
        converteStringInt(JOptionPane.showInputDialog(null, "NÚMERO DA AGÊNCIA: "));
        Agencia agencia = buscarAgencia(numeroConvertidoInt);
        JOptionPane.showMessageDialog(null, agencia.listarContasDaAgencia(), "LISTA COMPLETA",
                JOptionPane.INFORMATION_MESSAGE);
        menuAdministrador();
    }

    public static void dadosDoCliente() {
        Conta conta = null;
        int count = 0;
        double total = 0.0;
        String contas = "LISTA DE CONTAS \n", inicio = "";
        String cpf = JOptionPane.showInputDialog(null, "CPF DO CLIENTE: ");
        cpf = formatarCpfCnpj(cpf);
        for (Agencia a : listAgencia) {
            conta = a.retornaContaCliente(cpf);
            if (conta != null) {
                count++;
                total += conta.getSaldo();
                inicio = conta.getPessoa().toString();
                contas += a.toStringContasPorAgencia(conta);
            }
        }
        if (count != 0) {
            inicio += "TOTAL DE CONTAS NO BANCO: " + count + "\n" + "SALDO TOTAL NO BANCO: "
                    + FormataValor.toStringSaldo(total) + "\n" + contas;
            JOptionPane.showMessageDialog(null, inicio, "INFORMAÇÕES CLIENTE",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "O CPF NÃO ESTÁ CADASTRADO NO BANCO!", "ERRO",
                    JOptionPane.ERROR_MESSAGE);
            menuAdministrador();
        }
    }

    public static Agencia escolherAgencia(String cpfCnpj) {
        Agencia agencia = null;
        String listag = "---------------------------------------------------------------\n";
        for (int i = 1; i <= 10; i++) {
            listag += "[" + i + "] Brasil AG " + i + "\n";
        }
        converteStringInt(
                JOptionPane.showInputDialog(null, listag, "ESCOLHA SUA AGÊNCIA\n", JOptionPane.INFORMATION_MESSAGE));
        if (numeroConvertidoInt > 0 && numeroConvertidoInt <= listAgencia.size()) {
            agencia = buscarAgencia(numeroConvertidoInt);
            if (agencia.verificarCPFCNPJ(cpfCnpj)) {
                JOptionPane.showMessageDialog(null, "O CPF JÁ ESTÁ CADASTRADO NESTA AGÊNCIA!", "ERRO",
                        JOptionPane.ERROR_MESSAGE);
                menuDeOperacoes();
            }
        } else {
            JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA!", "ERRO", JOptionPane.ERROR_MESSAGE);
            menuDeOperacoes();
        }
        return agencia;
    }

    public static Agencia buscarAgencia(int numeroAgencia) {
        Agencia agencia = null;
        for (Agencia a : listAgencia) {
            if (a.getNumeroAgencia() == numeroAgencia)
                agencia = a;
        }
        if (agencia == null) {
            JOptionPane.showMessageDialog(null, "AGÊNCIA NÃO ENCONTRADA", "ERRO", JOptionPane.ERROR_MESSAGE);
            menuDeOperacoes();
        }
        return agencia;
    }

    public static Conta buscarConta(Agencia agencia, int numeroConta) {
        Conta conta = null;
        conta = agencia.retornaContaCliente(numeroConta);
        if (conta == null) {
            JOptionPane.showMessageDialog(null, "CONTA NÃO ENCONTRADA!", "ERRO", JOptionPane.ERROR_MESSAGE);
            menuDeOperacoes();
        }
        return conta;
    }

    public static String formatarCpfCnpj(String cpfCnpj) {
        if (cpfCnpj.matches("[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}")) {
            cpfCnpj = cpfCnpj.replaceAll("[^0-9]", "");
            cpfCnpj = cpfCnpj.substring(0, 3) + "." + cpfCnpj.substring(3, 6) + "." +
                    cpfCnpj.substring(6, 9) + "-" + cpfCnpj.substring(9, 11);

        } else if (cpfCnpj.matches("[0-9]{2}.?[0-9]{3}.?[0-9]{3}/?[0-9]{4}-?[0-9]{2}")) {
            cpfCnpj = cpfCnpj.replaceAll("[^0-9]", "");
            cpfCnpj = cpfCnpj.substring(0, 2) + "." + cpfCnpj.substring(2, 5) + "." +
                    cpfCnpj.substring(5, 8) + "/" + cpfCnpj.substring(8, 12) + "-" +
                    cpfCnpj.substring(12, 14);
        } else {
            JOptionPane.showMessageDialog(null, "CADASTRO NEGADO, CPF/CNPJ INVÁLIDO", "AVISO",
                    JOptionPane.ERROR_MESSAGE);
            menuDeOperacoes();
        }
        return cpfCnpj;
    }

    public static void converteStringInt(String numero) {
        try {
            numeroConvertidoInt = Integer.parseInt(numero);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA!", "ERRO", JOptionPane.ERROR_MESSAGE);
            menuDeOperacoes();
        }
    }

    public static void converteStringDouble(String numero) {
        try {
            numeroConvertidoDouble = Double.parseDouble(numero);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "VALOR INVÁLIDO!", "ERRO", JOptionPane.ERROR_MESSAGE);
            menuDeOperacoes();
        }
    }

}
