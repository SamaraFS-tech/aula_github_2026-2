import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Conta> contas = new ArrayList<>(); // Lista para armazenar as contas

        Menu mainMenu = new Menu("Menu Principal", Arrays.asList("Conta", "Cliente", "Operacoes", "Sair"));
        int op = mainMenu.getSelection();
        System.out.println(op + " foi selecionada");

        while (op != 4) {
            switch (op) {
                case 1: // Menu Conta
                    Menu menuConta = new Menu("Menu Conta", Arrays.asList("Abrir Conta", "Listar Contas", "Voltar"));
                    int opConta = menuConta.getSelection();
                    System.out.println(opConta + " foi selecionada");

                    while (opConta != 3) {
                        if (opConta == 1) {
                            System.out.println("Informe o CPF do cliente para vincular à nova conta:");
                            Scanner s = new Scanner(System.in);
                            String cpfBusca = s.nextLine().trim();

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
                        System.out.println(opConta + " foi selecionada");
                    }
                    break;

                case 2: // Menu Cliente
                    Menu menuCliente = new Menu("Menu Cliente", Arrays.asList("Cadastrar Cliente", "Encontrar Cliente", "Voltar"));
                    int op1 = menuCliente.getSelection();
                    System.out.println(op1 + " foi selecionada");

                    while (op1 != 3) {
                        if (op1 == 1) {
                            Cliente c = new Cliente();
                            c.cadastrarCliente();
                            clientes.add(c);
                            System.out.println("\nCliente cadastrado com sucesso!\n");
                        }
                        else if (op1 == 2) {
                            System.out.println("Informe o CPF que deseja buscar:");
                            Scanner s = new Scanner(System.in);
                            String cpf = s.nextLine().trim();

                            if (cpf.isEmpty()) {
                                System.out.println("Entrada inválida! O CPF não pode ser vazio.\n");
                                break;
                            }

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
                        op1 = menuCliente.getSelection();
                        System.out.println(op1 + " foi selecionada");
                    }
                    break;

                case 3: // Operacoes
                    Menu menuOperacoes = new Menu("Menu Operações", Arrays.asList("Depositar", "Realizar Operação", "Extrato (Histórico)", "Voltar"));
                    int opOperacoes = menuOperacoes.getSelection();

                    while (opOperacoes != 4) {
                        Scanner scOp = new Scanner(System.in);

                        if (opOperacoes == 1) {
                            // Requisito #18 - Realizar Depósito
                            System.out.println("Informe o número da conta (ex: 12345-6):");
                            String numContaDeposito = scOp.nextLine().trim();
                            Conta contaDeposito = localizarConta(contas, numContaDeposito);

                            if (contaDeposito == null) {
                                System.out.println("Conta não encontrada.\n");
                            } else {
                                System.out.println("Informe o valor do depósito:");
                                double valorDeposito;
                                try {
                                    valorDeposito = Double.parseDouble(scOp.nextLine().trim());
                                } catch (NumberFormatException e) {
                                    System.out.println("Valor inválido!\n");
                                    valorDeposito = 0;
                                }

                                if (valorDeposito <= 0) {
                                    System.out.println("Falha no depósito: o valor deve ser maior que zero.\n");
                                } else if (contaDeposito.depositar(valorDeposito)) {
                                    System.out.println("Depósito realizado com sucesso. Novo saldo: R$ " + contaDeposito.getSaldo() + "\n");
                                } else {
                                    System.out.println("Falha no depósito. Verifique o status da conta.\n");
                                }
                            }
                        }
                        else if (opOperacoes == 2) {
                            System.out.println("Informe o número da conta (ex: 12345-6):");
                            String numConta = scOp.nextLine();
                            Conta contaAlvo = null;

                            for (Conta c : contas) {
                                if (c.getNumeroConta().equals(numConta)) {
                                    contaAlvo = c;
                                    break;
                                }
                            }

                            if (contaAlvo != null) {
                                System.out.println("Tipo (Saque, Depósito, Transferência-Saída, Transferência-Entrada):");
                                String tipo = scOp.nextLine();
                                System.out.println("Valor:");
                                double valor = 0;
                                try { valor = Double.parseDouble(scOp.nextLine()); } catch(Exception e){}

                                if (contaAlvo.realizarOperacao(tipo, valor)) {
                                    System.out.println("Operação realizada com sucesso. Novo saldo: R$ " + contaAlvo.getSaldo() + "\n");
                                } else {
                                    System.out.println("Falha na operação. Verifique o saldo ou status da conta.\n");
                                }
                            } else {
                                System.out.println("Conta não encontrada.\n");
                            }
                        }
                        else if (opOperacoes == 3) {
                            System.out.println("Informe o número da conta para o extrato:");
                            String numConta = scOp.nextLine();
                            Conta contaAlvo = null;

                            for (Conta c : contas) {
                                if (c.getNumeroConta().equals(numConta)) {
                                    contaAlvo = c;
                                    break;
                                }
                            }

                            if (contaAlvo != null) {
                                System.out.println("Filtrar a partir da data (dd/MM/yyyy) ou deixe em branco para ver tudo:");
                                String dataInicioStr = scOp.nextLine();
                                java.time.LocalDate dataInicio = null;

                                if (!dataInicioStr.trim().isEmpty()) {
                                    try {
                                        dataInicio = java.time.LocalDate.parse(dataInicioStr, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                    } catch (Exception e) {
                                        System.out.println("Formato de data inválido. Exibindo todo o histórico.");
                                    }
                                }

                                StringBuilder relatorio = new StringBuilder();
                                relatorio.append("\n--- Extrato da Conta ").append(contaAlvo.getNumeroConta()).append(" ---\n");
                                relatorio.append("Titular: ").append(contaAlvo.getCliente().getNome()).append("\n\n");

                                boolean encontrouMov = false;
                                for (Movimentacao mov : contaAlvo.getHistorico()) {
                                    java.time.LocalDate dataMov = mov.getDataHora().toLocalDate();
                                    // Se dataInicio não for nula, ignora movimentos anteriores a essa data
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
                                relatorio.append("Saldo Atual: R$ ").append(contaAlvo.getSaldo()).append("\n");

                                System.out.println(relatorio.toString());

                                System.out.println("Deseja exportar este extrato para arquivo TXT? (S/N)");
                                if (scOp.nextLine().equalsIgnoreCase("S")) {
                                    try {
                                        java.io.FileWriter writer = new java.io.FileWriter("Extrato_" + contaAlvo.getNumeroConta() + ".txt");
                                        writer.write(relatorio.toString());
                                        writer.close();
                                        System.out.println("Arquivo gerado com sucesso!\n");
                                    } catch (java.io.IOException e) {
                                        System.out.println("Erro ao gerar o arquivo.\n");
                                    }
                                }

                            } else {
                                System.out.println("Conta não encontrada.\n");
                            }
                        }
                        opOperacoes = menuOperacoes.getSelection();
                    }
                    break;
            }
            op = mainMenu.getSelection();
            System.out.println(op + " foi selecionada");
        }

        System.out.println("Fim");
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