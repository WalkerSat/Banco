package Cliente;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import Utils.*;

public class Conta implements ContaBancaria {
    private static int count = 1;
    private double saldo;
    private int numConta;
    private Pessoa pessoa;
    private ArrayList<String> ListExtrato = new ArrayList<String>();

    public Conta(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.saldo = 0.0;
        this.numConta = count;
        count++;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        if (saldo > 0)
            this.saldo = saldo;
    }

    public int getNumConta() {
        return numConta;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    @Override
    public void depositar(Double valor) {
        if (valor > 0) {
            setSaldo(getSaldo() + valor);
            addExtrato("Depósito Efetuado", valor);
            JOptionPane.showMessageDialog(null,
                    "DEPÓSITO DE " + FormataValor.toStringSaldo(valor) + " REALIZADO", "SUCESSO",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "DEPÓSITO NEGADO!", "FALHA", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void sacar(Double valor) {
        if (valor > 0 && valor <= getSaldo()) {
            setSaldo(getSaldo() - valor);
            addExtrato("Saque Efetuado", valor);
            JOptionPane.showMessageDialog(null,
                    "SAQUE DE " + FormataValor.toStringSaldo(valor) + " REALIZADO", "SUCESSO",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "SAQUE NEGADO!", "FALHA", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void transferir(Conta contaRemetente, Conta contaDestinatario, Double valor) {
        if (valor > 0 && valor <= this.getSaldo()) {
            setSaldo(this.getSaldo() - valor);
            contaDestinatario.addExtrato("Transferência Recebida ", valor);
            contaRemetente.addExtrato("Transferência Efetuada", valor);
            contaDestinatario.setSaldo(contaDestinatario.getSaldo() + valor);
            contaRemetente.setSaldo(contaRemetente.getSaldo() - valor);
            JOptionPane.showMessageDialog(null,
                    "TRANSFERÊNCIA DE " + FormataValor.toStringSaldo(valor) + " REALIZADA!", "SUCESSO",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "TRANSFERÊNCIA NEGADA!", "FALHA", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public String extrato() {
        String retorno = "\n";
        for (String a : ListExtrato) {
            retorno += a;
        }
        return retorno;
    }

    public void addExtrato(String operacao, Double valor) {
        String aux = "" + FormatDate.toStringDate() + " - " + operacao + " : " + FormataValor.toStringSaldo(valor)
                + "\n";
        ListExtrato.add(aux);
    }

    public boolean verificaCPFCliente(String cpf) {
        Boolean retorno = false;
        if (pessoa.getCpf().equals(cpf))
            retorno = true;
        return retorno;
    }

}
