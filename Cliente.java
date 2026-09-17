import java.util.Scanner;

public class Cliente {
    private String nome;
    private int cpf;

    public getNome(){
        return nome;
    }
    public getCpf(){
        return cpf;
    }
    public setNome(String nome){
        this.nome = nome;
    }
    public setCpf(int cpf){
        this.cpf = cpf;
    }

    public cadastrarCliente(){
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
            break;
        }
        self.setCpf(Integer.parseInt(cpf));
        System.out.println("Insira o nome do cliente");
        Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
        self.setNome(str);
    }

}
