import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Menu mainMenu =  new Menu("Menu Principal", Arrays.asList("Conta", "Cliente", "Operacoes", "Sair"));
		System.out.println(mainMenu.getSelection() + "foi selecionada");
        int op = mainMenu.getSelection();
        while (op!=4){
            System.out.println( op + "foi selecionada");
            op = mainMenu.getSelection();
        }

        System.out.println("Fim");
	}

}