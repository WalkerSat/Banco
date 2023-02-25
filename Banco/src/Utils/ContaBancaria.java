package Utils;

import Cliente.Conta;

public interface ContaBancaria {
    public void depositar(Double valor);

    public void sacar(Double valor);

    public void transferir(Conta contaRemetente, Conta contaDestinatario, Double valor);

    public String extrato();
}