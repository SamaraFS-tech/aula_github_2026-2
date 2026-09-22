import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Conta> contas = new ArrayList<>();

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
                    Menu menuCliente = new Menu("Menu Cliente", Arrays.asList("Cadastrar Cliente", "Encontrar Cliente", "Voltar"));
                    int op1 = menuCliente.getSelection();
                    System.out.println(op1 + "foi selecionada");
                    while (op1 != 3){
                        if(op1 == 1){
                            Cliente c = new Cliente();
                            c.cadastrarCliente();
                            clientes.add(c);
                        }
                        if(op1 == 2){
                            System.out.println("Insira o CPF do cliente:");
                            Scanner s = new Scanner(System.in);
                            String cpf = s.nextLine().trim();
                            boolean encontrado = false;
                            for(Cliente c : clientes){
                                if (c.getCpf().equals(cpf)){
                                    System.out.println("Nome do cliente: " + c.getNome() + "\n" + "CPF do cliente: " + c.getCpf() + "\n");
                                    encontrado = true;
                                    break;
                                }
                            }
                            if(!encontrado){
                                System.out.println("Cliente não encontrado!\n");
                            }
                        }
                        op1 = menuCliente.getSelection();
                    }
                    break;
                
            
                default:
                    break;
            }
            op = mainMenu.getSelection();
        }

        System.out.println("Fim");
	}

}