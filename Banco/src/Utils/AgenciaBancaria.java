package Utils;

import Cliente.Conta;

public interface AgenciaBancaria {

    public void addContaNaAgencia(Conta conta);

    public void excluirContaDaAgencia(Conta conta);

    public void depositarNaConta(Double valor, Conta conta);

    public void sacarNaConta(Double valor, Conta conta);

    public void transferirDaConta(Double valor, Conta contRemet, Conta contDest);

    public String extratoNaConta(Conta conta);
}
