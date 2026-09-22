import java.util.Scanner;

public class Cliente {
    private String nome;
    private int cpf;

    public String getNome(){
        return nome;
    }
    public int getCpf(){
        return cpf;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setCpf(int cpf){
        this.cpf = cpf;
    }

    public void cadastrarCliente(){
        int cpf = 0;
        while(cpf == 0){
            System.out.println("Insira o CPF do cliente:");
            Scanner s = new Scanner(System.in);
            try {
                cpf = Integer.parseInt(s.nextLine());
            }catch (NumberFormatException e) {

            }
            if(cpf == 0){
                System.out.println("Entrada inválida! O CPF não pode ser vazio ou 0.\n");
                // Entrada não pode não ser int nem ser 0.
            }else{
                 break;
            }
        }
        this.setCpf(cpf);
        System.out.println("Insira o nome do cliente:");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        this.setNome(str);
    }

}
