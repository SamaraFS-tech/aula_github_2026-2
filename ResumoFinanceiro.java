import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResumoFinanceiro {

    // Classe que representa uma movimentação
    static class Movimentacao {
        private String tipo;
        private double valor;

        public Movimentacao(String tipo, double valor) {
            this.tipo = tipo;
            this.valor = valor;
        }

        public String getTipo() {
            return tipo;
        }

        public double getValor() {
            return valor;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Histórico da conta
        List<Movimentacao> historico = new ArrayList<>();

        // Algumas movimentações de exemplo
        historico.add(new Movimentacao("Depósito", 1000.00));
        historico.add(new Movimentacao("Saque", 200.00));
        historico.add(new Movimentacao("Depósito", 500.00));
        historico.add(new Movimentacao("Transferência-Saída", 100.00));
        historico.add(new Movimentacao("Transferência-Entrada", 300.00));

        double totalDepositos = 0.0;
        double totalSaques = 0.0;
        double totalTransferencias = 0.0;

        int quantidadeOperacoes = historico.size();

        // Percorre todas as movimentações
        for (Movimentacao movimentacao : historico) {

            String tipo = movimentacao.getTipo();
            double valor = movimentacao.getValor();

            if (tipo.equals("Depósito")) {
                totalDepositos += valor;

            } else if (tipo.equals("Saque")) {
                totalSaques += valor;

            } else if (tipo.equals("Transferência-Saída")
                    || tipo.equals("Transferência-Entrada")) {

                totalTransferencias += valor;
            }
        }

        // Calcula o saldo
        double saldo = totalDepositos
                - totalSaques
                - 100.00
                + 300.00;

        // Exibe o resumo
        System.out.println("\n======================================");
        System.out.println("          RESUMO FINANCEIRO");
        System.out.println("======================================");

        System.out.printf("Total de depósitos:       R$ %.2f%n", totalDepositos);
        System.out.printf("Total de saques:          R$ %.2f%n", totalSaques);
        System.out.printf("Total de transferências:  R$ %.2f%n", totalTransferencias);

        System.out.println("--------------------------------------");

        System.out.println("Quantidade de operações: " + quantidadeOperacoes);

        System.out.printf("Saldo atual:              R$ %.2f%n", saldo);

        System.out.println("======================================");

        scanner.close();
    }
}
```
