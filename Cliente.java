import java.util.Scanner;

public class Cliente {
    private String nome;
    private String cpf;

    public getNome(){
        return nome;
    }
    public String getCpf(){
        return cpf;
    }
    public setNome(String nome){
        this.nome = nome;
    }
    public setCpf(int cpf){
        this.cpf = cpf;
    }

    public void cadastrarCliente(){
        Scanner s = new Scanner(System.in);
        String cpf = "";
        while(cpf.isEmpty()){
            System.out.println("Insira o CPF do cliente:");
            cpf = s.nextLine().trim();
            if(cpf.isEmpty()){
                System.out.println("Entrada inválida! \n");
                // Entrada não pode não ser int nem ser 0.
            }
            break;
        }
        this.setCpf(cpf);
        System.out.println("Insira o nome do cliente");
        Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
        self.setNome(str);
    }

}
