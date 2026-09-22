import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Conta {
    private String numeroConta;
    private String agencia;
    private double saldo;
    private boolean ativa;
    private Cliente cliente;
    private List<Movimentacao> historico;

    // Construtor vincula o cliente e define os valores padrão
    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0.0; // Saldo inicial
        this.ativa = true; // Status ativo
        this.agencia = "0001"; // Agência única padrão
        this.numeroConta = gerarNumeroConta();
        this.historico = new ArrayList<>();
    }

    public List<Movimentacao> getHistorico() {
        return historico;
    }

    // Método auxiliar para simular a geração de um número único
    private String gerarNumeroConta() {
        Random random = new Random();
        int num = 10000 + random.nextInt(90000); 
        return String.valueOf(num) + "-" + random.nextInt(9);
    }

    /**
     * Requisito #18 - Realizar depósito.
     * Critérios de aceitação:
     *  - Aceitar apenas valores maiores que zero.
     *  - Atualizar o saldo da conta.
     * @return true se o depósito foi realizado, false caso contrário.
     */
    public boolean depositar(double valor) {
        if (!ativa) {
            return false;
        }
        if (valor <= 0) {
            return false;
        }
        this.saldo += valor;
        historico.add(new Movimentacao("Depósito", valor));
        return true;
    }

    public boolean realizarOperacao(String tipo, double valor) {
        // Requisito: O valor do depósito deve ser maior que zero
        if (tipo.equalsIgnoreCase("Depósito") && valor <= 0) {
            return false;
        }

        if (!ativa) {
            return false;
        }

        if (tipo.equalsIgnoreCase("Saque") || tipo.equalsIgnoreCase("Transferência-Saída")) {
            if (this.saldo >= valor) {
                this.saldo -= valor;
            } else {
                return false; // Saldo insuficiente
            }
        } else if (tipo.equalsIgnoreCase("Depósito") || tipo.equalsIgnoreCase("Transferência-Entrada")) {
            this.saldo += valor;
        }
        
        historico.add(new Movimentacao(tipo, valor));
        return true;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public String getNumeroConta() {
        return numeroConta;
    }
    public String getAgencia() {
        return agencia;
    }
    public double getSaldo() {
        return saldo;
    }
    public boolean isAtiva() {
        return ativa;
    }

    @Override
    public String toString() {
        return "Conta: " + numeroConta + " | Agência: " + agencia + 
               " | Titular: " + cliente.getNome() + " | Saldo: R$ " + saldo + 
               " | Status: " + (ativa ? "Ativa" : "Inativa");
    }
}