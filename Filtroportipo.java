import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FiltroMovimentacoes {

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

        @Override
        public String toString() {
            return String.format("%-20s | R$ %.2f", tipo, valor);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        
        List<Movimentacao> historico = new ArrayList<>();

        historico.add(new Movimentacao("Depósito", 1000.00));
        historico.add(new Movimentacao("Saque", 200.00));
        historico.add(new Movimentacao("Depósito", 500.00));
        historico.add(new Movimentacao("Transferência", 300.00));
        historico.add(new Movimentacao("Saque", 100.00));
        historico.add(new Movimentacao("Transferência", 150.00));

        System.out.println("=================================");
        System.out.println("       HISTÓRICO DA CONTA");
        System.out.println("=================================");

        System.out.println("1 - Todas as operações");
        System.out.println("2 - Apenas saques");
        System.out.println("3 - Apenas depósitos");
        System.out.println("4 - Apenas transferências");

        System.out.print("\nEscolha o filtro: ");
        int opcao = scanner.nextInt();

        System.out.println("\n---------- RESULTADO ----------");

        boolean encontrou = false;

        for (Movimentacao movimentacao : historico) {

            boolean mostrar = false;

            switch (opcao) {

                case 1:
                    // Mostra todas
                    mostrar = true;
                    break;

                case 2:
                 
                    if (movimentacao.getTipo().equalsIgnoreCase("Saque")) {
                        mostrar = true;
                    }
                    break;

                case 3:
                    
                    if (movimentacao.getTipo().equalsIgnoreCase("Depósito")) {
                        mostrar = true;
                    }
                    break;

                case 4:
                 
                    if (movimentacao.getTipo().equalsIgnoreCase("Transferência")) {
                        mostrar = true;
                    }
                    break;

                default:
                    System.out.println("Opção inválida!");
                    scanner.close();
                    return;
            }

            if (mostrar) {
                System.out.println(movimentacao);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma movimentação encontrada.");
        }

        System.out.println("--------------------------------");

        scanner.close();
    }
}