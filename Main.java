import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Cliente.java;

public class Main {

	public static void main(String[] args) {

        ArrayList clientes = new ArrayList<Cliente>();
        ArrayList<Conta> contas = new ArrayList<Conta>();

		Menu mainMenu =  new Menu("Menu Principal", Arrays.asList("Conta", "Cliente", "Operacoes", "Sair"));
		int op = mainMenu.getSelection();
        System.out.println(op + "foi selecionada");
        while (op!=4){
            System.out.println( op + "foi selecionada");
            switch (op) {
                case 3:
                    Menu menuOperacoes = new Menu("Menu Operacoes", Arrays.asList("Depositar", "Voltar"));
                    int op3 = menuOperacoes.getSelection();
                    System.out.println(op3 + " foi selecionada");
                    while (op3 != 2) {
                        if (op3 == 1) {
                            System.out.println("Informe o número da conta:");
                            Scanner scanConta = new Scanner(System.in);
                            int numeroConta = 0;
                            try {
                                numeroConta = Integer.parseInt(scanConta.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Número inválido!");
                            }

                            Conta contaEncontrada = null;
                            for (Conta c : contas) {
                                if (c.getNumero() == numeroConta) {
                                    contaEncontrada = c;
                                    break;
                                }
                            }

                            if (contaEncontrada != null) {
                                System.out.println("Informe o valor do depósito:");
                                Scanner scanValor = new Scanner(System.in);
                                double valor = 0;
                                try {
                                    valor = Double.parseDouble(scanValor.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Valor inválido!");
                                }
                                contaEncontrada.depositar(valor);
                            } else {
                                System.out.println("Conta não encontrada!");
                            }
                        }
                        op3 = menuOperacoes.getSelection();
                    }
                    break;
                case 2:
                    Menu menuCliente = new Menu("Menu Cliente", Arrays.aslist("Cadastrar Cliente", "Encontrar Cliente", "Voltar"));
                    int op1 = menuCliente.getSelection();
                    System.out.println(op1 + "foi selecionada");
                    while (op1 != 3){
                        System.out.println( op1 + "foi selecionada");
                    }
                    break;
                    if(op1 == 1){
                        Cliente c = new Cliente();
                        c.cadastrarCliente();
                        clientes.add(c);
                    }
                    if(op1 == 2){
                        int cpf = 0;
                        while(cpf == 0){
                            Scanner s = new Scanner(System.in);
                            try {
                                cpf = Integer.parseInt(s.nextLine());
                            }
                            catch (NumberFormatException e) {}
                            if(cpf == 0){
                                System.out.println("Entrada inválida! \n");
                                // Entrada não pode não ser int nem ser 0.
                            }
                            for( Client c : clientes){
                                if (c.getCpf() == cpf){
                                    System.out.println("Nome do cliente: " + c.getNome() + "\n" + "CPF do cliente" + c.getCpf() + "\n");
                                }
                            }
                            break;
                        }
                    }
                
            
                default:
                    break;
            }
            op = mainMenu.getSelection();
        }

        System.out.println("Fim");
	}

}