import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        ArrayList<Conta> contas = new ArrayList<Conta>(); // Lista para armazenar as contas

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
                            int cpfBusca = 0;
                            try {
                                cpfBusca = Integer.parseInt(s.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida!");
                            }

                            Cliente clienteEncontrado = null;
                            for (Cliente c : clientes) {
                                if (c.getCpf() == cpfBusca) {
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
                            int cpf = 0;
                            while (cpf == 0) {
                                System.out.println("Informe o CPF que deseja buscar:");
                                Scanner s = new Scanner(System.in);
                                try {
                                    cpf = Integer.parseInt(s.nextLine());
                                } catch (NumberFormatException e) {
                                }
                                
                                if (cpf == 0) {
                                    System.out.println("Entrada inválida! O CPF não pode ser 0 ou vazio.\n");
                                    break;
                                }
                                
                                boolean achou = false;
                                for (Cliente c : clientes) {
                                    if (c.getCpf() == cpf) {
                                        System.out.println("\nNome do cliente: " + c.getNome());
                                        System.out.println("CPF do cliente: " + c.getCpf() + "\n");
                                        achou = true;
                                    }
                                }
                                if (!achou) {
                                    System.out.println("\nNenhum cliente encontrado com este CPF.\n");
                                }
                                break;
                            }
                        }
                        op1 = menuCliente.getSelection();
                        System.out.println(op1 + " foi selecionada");
                    }
                    break;
                    
                case 3: // Operacoes
                    Menu menuOperacoes = new Menu("Menu Operações", Arrays.asList("Realizar Operação", "Extrato (Histórico)", "Voltar"));
                    int opOperacoes = menuOperacoes.getSelection();
                    
                    while (opOperacoes != 3) {
                        Scanner scOp = new Scanner(System.in);
                        
                        if (opOperacoes == 1) {
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
                        else if (opOperacoes == 2) {
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
}