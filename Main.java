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
                    
                case 3: // Operacoes (Ainda não implementado)
                    System.out.println("\nMenu de Operações em desenvolvimento...\n");
                    break;

                default:
                    break;
            }
            op = mainMenu.getSelection();
            System.out.println(op + " foi selecionada");
        }

        System.out.println("Fim");
    }
}