import java.util.Scanner;

public class Cliente {
    private String nome;
    private String cpf;

    public String getNome(){
        return nome;
    }
    public String getCpf(){
        return cpf;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setCpf(String cpf){
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
            }
        }
        this.setCpf(cpf);
        System.out.println("Insira o nome do cliente");
        String str = s.nextLine();
        this.setNome(str);
    }
}