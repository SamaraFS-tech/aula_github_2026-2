import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Scanner s = new Scanner(System.in);

        Menu mainMenu =  new Menu("Menu Principal", Arrays.asList("Conta", "Cliente", "Operacoes", "Sair"));
        int op = mainMenu.getSelection();
        System.out.println(op + " foi selecionada");
        while (op != 4){
            System.out.println(op + " foi selecionada");
            switch (op) {
                case 2:
                    Menu menuCliente = new Menu("Menu Cliente", Arrays.asList("Cadastrar Cliente", "Encontrar Cliente", "Voltar"));
                    int op1 = menuCliente.getSelection();
                    while (op1 != 3){
                        System.out.println(op1 + " foi selecionada");
                        if(op1 == 1){
                            Cliente c = new Cliente();
                            c.cadastrarCliente();
                            clientes.add(c);
                        }
                        if(op1 == 2){
                            String cpf = "";
                            while(cpf.isEmpty()){
                                System.out.println("Insira o CPF para busca:");
                                cpf = s.nextLine().trim();
                                if(cpf.isEmpty()){
                                    System.out.println("Entrada inválida! \n");
                                }
                            }
                            for(Cliente c : clientes){
                                if (c.getCpf().equals(cpf)){
                                    System.out.println("Nome do cliente: " + c.getNome() + "\n" + "CPF do cliente: " + c.getCpf() + "\n");
                                }
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