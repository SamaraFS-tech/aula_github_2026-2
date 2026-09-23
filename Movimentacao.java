import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimentacao {
    private String tipo; // Ex: Saque, Depósito, Transferência
    private double valor;
    private LocalDateTime dataHora;

    public Movimentacao(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public String formatarParaExibicao() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("[%s] %-15s | R$ %.2f", dataHora.format(formatador), tipo, valor);
    }
}