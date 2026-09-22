public class Conta {
    private final int numero;
    private double saldo;

    public Conta(int numero) {
        this.numero = numero;
        this.saldo = 0.0;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado com sucesso!");
            System.out.println("Novo saldo: R$ " + this.saldo);
        } else {
            System.out.println("Erro: O valor do depósito deve ser maior que zero!");
        }
    }
}
