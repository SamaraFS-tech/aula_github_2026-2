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

    /**
     * Requisito #14 - Realizar saque.
     * Critérios de aceitação:
     *  - Impedir saques com valores negativos ou zerados.
     *  - Verificar se a conta possui saldo suficiente.
     *  - Subtrair o valor do saldo em caso de sucesso.
     * @return true se o saque foi realizado, false caso contrário.
     */
    public boolean sacar(double valor) {
        if (!ativa) {
            return false;
        }
        if (valor <= 0) {
            return false;
        }
        if (this.saldo < valor) {
            return false; // Saldo insuficiente
        }
        this.saldo -= valor;
        historico.add(new Movimentacao("Saque", valor));
        return true;
    }

    /**
     * Requisito #15 - Transferência entre duas contas (operação única).
     * Debita a origem e credita o destino somente se a origem tiver saldo.
     * @return true se a transferência foi realizada, false caso contrário.
     */
    public boolean transferir(Conta destino, double valor) {
        if (destino == null) {
            return false;
        }
        if (!this.ativa || !destino.isAtiva()) {
            return false;
        }
        if (valor <= 0) {
            return false;
        }
        if (this.saldo < valor) {
            return false; // Saldo insuficiente na origem
        }
        this.saldo -= valor;
        this.historico.add(new Movimentacao("Transferência-Enviada", valor));
        destino.saldo += valor;
        destino.historico.add(new Movimentacao("Transferência-Recebida", valor));
        return true;
    }

    public boolean realizarOperacao(String tipo, double valor) {
        if (tipo.equalsIgnoreCase("Saque")) {
            return sacar(valor);
        }
        if (tipo.equalsIgnoreCase("Depósito")) {
            return depositar(valor);
        }
        return false; // Tipo de operação desconhecido
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