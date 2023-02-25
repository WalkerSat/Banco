package Inicio;

import java.util.ArrayList;
import Cliente.Conta;
import Utils.*;

public class Agencia implements AgenciaBancaria {
    private static int count = 1;
    private String nomeAgencia;
    private int numeroAgencia;
    private ArrayList<Conta> listContas = new ArrayList<Conta>();

    public Agencia(String nomeAgencia) {
        this.nomeAgencia = nomeAgencia;
        this.numeroAgencia = count;
        count++;
    }

    public String getNomeAgencia() {
        return nomeAgencia;
    }

    public void setNomeAgencia(String nomeAgencia) {
        this.nomeAgencia = nomeAgencia;
    }

    public int getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(int numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public ArrayList<Conta> getListContas() {
        return listContas;
    }

    public double SaldoTotalAgencia() {
        Double total = 0.0;
        for (Conta c : listContas) {
            total += c.getSaldo();
        }
        return total;
    }

    public int QuantDeContas() {
        return listContas.size();
    }

    @Override
    public void addContaNaAgencia(Conta conta) {
        listContas.add(conta);
    }

    @Override
    public void excluirContaDaAgencia(Conta conta) {
        listContas.remove(conta);
    }

    @Override
    public String extratoNaConta(Conta conta) {
        return conta.extrato();
    }

    @Override
    public void depositarNaConta(Double valor, Conta conta) {
        conta.depositar(valor);
    }

    @Override
    public void sacarNaConta(Double valor, Conta conta) {
        conta.sacar(valor);
    }

    @Override
    public void transferirDaConta(Double valor, Conta contRemet, Conta contDest) {
        contRemet.transferir(contRemet, contDest, valor);
    }

    @Override
    public String toString() {
        return "NOME DA AGÊNCIA: " + getNomeAgencia() + "\n" +
                "NÚMERO DA AGÊNCIA: " + getNumeroAgencia() + "\n" +
                "SALDO TOTAL NA AGÊNCIA: " + FormataValor.toStringSaldo(SaldoTotalAgencia()) + "\n" +
                "TOTAL DE CONTAS: " + QuantDeContas() + "\n";
    }

    public String toString(Conta conta) {
        return "NOME: " + conta.getPessoa().getNome() + "\n" +
                "IDADE: " + conta.getPessoa().getIdade() + "\n" +
                "CPF: " + conta.getPessoa().getCpf() + "\n" +
                "SALDO: " + FormataValor.toStringSaldo(conta.getSaldo()) + "\n" +
                "NÚMERO DA CONTA: " + conta.getNumConta() + "\n" +
                "NÚMERO DA AGÊNCIA: " + getNumeroAgencia() + "\n";
    }

    public String toStringContaSaldo(Conta conta) {
        return "NÚMERO AGÊNCIA: " + getNumeroAgencia() + "\n" +
                "NÚMERO CONTA: " + conta.getNumConta() + "\n" +
                "NOME CLIENTE: " + conta.getPessoa().getNome() + "\n" +
                "CPF/CNPJ CLIENTE: " + conta.getPessoa().getCpf() + "\n" +
                "SALDO: " + FormataValor.toStringSaldo(conta.getSaldo()) + "";
    }

    public String toStringContasPorAgencia(Conta conta) {
        return "NÚMERO AGÊNCIA: " + getNumeroAgencia() + "\n" +
                "NÚMERO CONTA: " + conta.getNumConta() + "\n" +
                "SALDO: " + FormataValor.toStringSaldo(conta.getSaldo()) + "\n";
    }

    public String listarContasDaAgencia() {
        String lista = "\n";
        if (listContas.size() > 0) {
            for (Conta c : listContas) {
                lista += c.toString();
            }
        } else {
            lista = "NÃO HA CONTAS CADASTRADAS NESTA AGÊNCIA";
        }
        return lista;
    }

    public boolean verificarCPFCNPJ(String cpfCnpj) {
        boolean retorno = false;
        for (Conta c : listContas) {
            if (c.verificaCPFCliente(cpfCnpj))
                retorno = true;
        }
        return retorno;
    }

    public Conta retornaContaCliente(int numeroConta) {
        Conta conta = null;
        for (Conta c : listContas) {
            if (c.getNumConta() == numeroConta)
                conta = c;
        }
        return conta;
    }

    public Conta retornaContaCliente(String cpfCnpj) {
        Conta conta = null;
        if (listContas.size() > 0) {
            for (Conta c : listContas) {
                if (c.getPessoa().getCpf().equals(cpfCnpj)) {
                    conta = c;
                }
            }
        }
        return conta;
    }

}
