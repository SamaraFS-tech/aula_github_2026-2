import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Conta> contas = new ArrayList<>();

        Menu mainMenu = new Menu("Menu Principal", Arrays.asList("Conta", "Cliente", "Operacoes", "Sair"));
        int op = mainMenu.getSelection();

        while (op != 4) {
            switch (op) {
                case 1:
                    menuConta(clientes, contas);
                    break;
                case 2:
                    menuCliente(clientes);
                    break;
                case 3:
                    // Requisito #23 - Menu de Operações com opções mapeadas por número
                    menuOperacoes(contas);
                    break;
            }
            op = mainMenu.getSelection();
        }

        System.out.println("Fim");
    }

    /** Menu Conta: Abrir Conta / Listar Contas / Voltar. */
    private static void menuConta(ArrayList<Cliente> clientes, ArrayList<Conta> contas) {
        Menu menuConta = new Menu("Menu Conta", Arrays.asList("Abrir Conta", "Listar Contas", "Voltar"));
        int opConta = menuConta.getSelection();

        while (opConta != 3) {
            if (opConta == 1) {
                System.out.println("Informe o CPF do cliente para vincular à nova conta:");
                String cpfBusca = SC.nextLine().trim();

                Cliente clienteEncontrado = null;
                for (Cliente c : clientes) {
                    if (c.getCpf().equals(cpfBusca)) {
                        clienteEncontrado = c;
                        break;
                    }
                }

                if (clienteEncontrado != null) {
                    Conta novaConta = new Conta(clienteEncontrado);
                    contas.add(novaConta);
                    System.out.println("\nConta aberta com sucesso!");
                    System.out.println(novaConta.toString() + "\n");
                } else {
                    System.out.println("\nCliente não encontrado. Cadastre o cliente primeiro.\n");
                }
            } else if (opConta == 2) {
                System.out.println("\n--- Lista de Contas ---");
                if (contas.isEmpty()) {
                    System.out.println("Nenhuma conta cadastrada.");
                } else {
                    for (Conta conta : contas) {
                        System.out.println(conta.toString());
                    }
                }
                System.out.println("-----------------------\n");
            }
            opConta = menuConta.getSelection();
        }
    }

    /** Menu Cliente: Cadastrar Cliente / Encontrar Cliente / Voltar. */
    private static void menuCliente(ArrayList<Cliente> clientes) {
        Menu menuCliente = new Menu("Menu Cliente", Arrays.asList("Cadastrar Cliente", "Encontrar Cliente", "Voltar"));
        int op1 = menuCliente.getSelection();

        while (op1 != 3) {
            if (op1 == 1) {
                Cliente c = new Cliente();
                c.cadastrarCliente();
                clientes.add(c);
                System.out.println("\nCliente cadastrado com sucesso!\n");
            } else if (op1 == 2) {
                System.out.println("Informe o CPF que deseja buscar:");
                String cpf = SC.nextLine().trim();

                if (cpf.isEmpty()) {
                    System.out.println("Entrada inválida! O CPF não pode ser vazio.\n");
                } else {
                    boolean achou = false;
                    for (Cliente c : clientes) {
                        if (c.getCpf().equals(cpf)) {
                            System.out.println("\nNome do cliente: " + c.getNome());
                            System.out.println("CPF do cliente: " + c.getCpf() + "\n");
                            achou = true;
                        }
                    }
                    if (!achou) {
                        System.out.println("\nNenhum cliente encontrado com este CPF.\n");
                    }
                }
            }
            op1 = menuCliente.getSelection();
        }
    }

    /**
     * Menu Operações (Requisito #23).
     * Opções mapeadas por número:
     *   1 - Depositar (#18)
     *   2 - Sacar (#14)
     *   3 - Transferir entre contas (#15)
     *   4 - Extrato / Relatório de Transações (#15)
     *   5 - Voltar
     */
    private static void menuOperacoes(ArrayList<Conta> contas) {
        Menu menuOperacoes = new Menu("Menu Operações",
                Arrays.asList("Depositar", "Sacar", "Transferir", "Extrato (Relatório de Transações)", "Voltar"));
        int opOperacoes = menuOperacoes.getSelection();

        while (opOperacoes != 5) {
            switch (opOperacoes) {
                case 1:
                    realizarDeposito(contas);
                    break;
                case 2:
                    realizarSaque(contas);
                    break;
                case 3:
                    realizarTransferencia(contas);
                    break;
                case 4:
                    gerarRelatorio(contas);
                    break;
                default:
                    System.out.println("Opção inválida!\n");
            }
            opOperacoes = menuOperacoes.getSelection();
        }
    }

    /** Requisito #18 - Realizar depósito. */
    private static void realizarDeposito(ArrayList<Conta> contas) {
        System.out.println("Informe o número da conta (ex: 12345-6):");
        Conta conta = localizarConta(contas, SC.nextLine().trim());

        if (conta == null) {
            System.out.println("Conta não encontrada.\n");
            return;
        }

        System.out.println("Informe o valor do depósito:");
        double valor = lerValor();

        if (valor <= 0) {
            System.out.println("Falha no depósito: o valor deve ser maior que zero.\n");
        } else if (conta.depositar(valor)) {
            System.out.println("Depósito realizado com sucesso. Novo saldo: R$ " + conta.getSaldo() + "\n");
        } else {
            System.out.println("Falha no depósito. Verifique o status da conta.\n");
        }
    }

    /** Requisito #14 - Realizar saque. */
    private static void realizarSaque(ArrayList<Conta> contas) {
        System.out.println("Informe o número da conta (ex: 12345-6):");
        Conta conta = localizarConta(contas, SC.nextLine().trim());

        if (conta == null) {
            System.out.println("Conta não encontrada.\n");
            return;
        }

        System.out.println("Informe o valor do saque:");
        double valor = lerValor();

        if (valor <= 0) {
            System.out.println("Falha no saque: o valor deve ser maior que zero.\n");
        } else if (conta.getSaldo() < valor) {
            System.out.println("Falha no saque: saldo insuficiente.\n");
        } else if (conta.sacar(valor)) {
            System.out.println("Saque realizado com sucesso. Novo saldo: R$ " + conta.getSaldo() + "\n");
        } else {
            System.out.println("Falha no saque. Verifique o status da conta.\n");
        }
    }

    /** Requisito #15 - Transferência única entre 2 contas. */
    private static void realizarTransferencia(ArrayList<Conta> contas) {
        System.out.println("Informe o número da conta de ORIGEM (ex: 12345-6):");
        Conta origem = localizarConta(contas, SC.nextLine().trim());

        System.out.println("Informe o número da conta de DESTINO (ex: 12345-6):");
        Conta destino = localizarConta(contas, SC.nextLine().trim());

        if (origem == null || destino == null) {
            System.out.println("Conta de origem ou destino não encontrada.\n");
            return;
        }
        if (origem == destino) {
            System.out.println("Origem e destino devem ser contas diferentes.\n");
            return;
        }

        System.out.println("Informe o valor da transferência:");
        double valor = lerValor();

        if (valor <= 0) {
            System.out.println("Falha na transferência: o valor deve ser maior que zero.\n");
        } else if (origem.transferir(destino, valor)) {
            System.out.println("Transferência realizada com sucesso.");
            System.out.println("Novo saldo da origem (" + origem.getNumeroConta() + "): R$ " + origem.getSaldo());
            System.out.println("Novo saldo do destino (" + destino.getNumeroConta() + "): R$ " + destino.getSaldo() + "\n");
        } else {
            System.out.println("Falha na transferência. Verifique o saldo da origem ou o status das contas.\n");
        }
    }

    /** Requisito #15 - Gerar Relatório de Transações (extrato do histórico). */
    private static void gerarRelatorio(ArrayList<Conta> contas) {
        System.out.println("Informe o número da conta para o extrato:");
        Conta conta = localizarConta(contas, SC.nextLine().trim());

        if (conta == null) {
            System.out.println("Conta não encontrada.\n");
            return;
        }

        System.out.println("Filtrar a partir da data (dd/MM/yyyy) ou deixe em branco para ver tudo:");
        String dataInicioStr = SC.nextLine();
        java.time.LocalDate dataInicio = null;

        if (!dataInicioStr.trim().isEmpty()) {
            try {
                dataInicio = java.time.LocalDate.parse(dataInicioStr, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Exibindo todo o histórico.");
            }
        }

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("\n--- Extrato da Conta ").append(conta.getNumeroConta()).append(" ---\n");
        relatorio.append("Titular: ").append(conta.getCliente().getNome()).append("\n\n");

        boolean encontrouMov = false;
        for (Movimentacao mov : conta.getHistorico()) {
            java.time.LocalDate dataMov = mov.getDataHora().toLocalDate();
            if (dataInicio != null && dataMov.isBefore(dataInicio)) {
                continue;
            }
            relatorio.append(mov.formatarParaExibicao()).append("\n");
            encontrouMov = true;
        }

        if (!encontrouMov) {
            relatorio.append("Nenhuma movimentação encontrada no período.\n");
        }

        relatorio.append("----------------------------------\n");
        relatorio.append("Saldo Atual: R$ ").append(conta.getSaldo()).append("\n");

        System.out.println(relatorio.toString());

        System.out.println("Deseja exportar este extrato para arquivo TXT? (S/N)");
        if (SC.nextLine().equalsIgnoreCase("S")) {
            try {
                java.io.FileWriter writer = new java.io.FileWriter("Extrato_" + conta.getNumeroConta() + ".txt");
                writer.write(relatorio.toString());
                writer.close();
                System.out.println("Arquivo gerado com sucesso!\n");
            } catch (java.io.IOException e) {
                System.out.println("Erro ao gerar o arquivo.\n");
            }
        }
    }

    /** Lê um valor monetário do usuário, retornando 0 em caso de entrada inválida. */
    private static double lerValor() {
        try {
            return Double.parseDouble(SC.nextLine().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido!");
            return 0;
        }
    }

    /**
     * Requisito #18 - Localizar a conta pelo número.
     * @return a Conta correspondente ou null se não encontrada.
     */
    private static Conta localizarConta(ArrayList<Conta> contas, String numero) {
        for (Conta c : contas) {
            if (c.getNumeroConta().equals(numero)) {
                return c;
            }
        }
        return null;
    }
}