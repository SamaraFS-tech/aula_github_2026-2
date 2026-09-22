public class SaqueTest {

    private static int testes = 0;
    private static int falhas = 0;

    private static void checar(String descricao, boolean condicao) {
        testes++;
        if (condicao) {
            System.out.println("[OK]   " + descricao);
        } else {
            falhas++;
            System.out.println("[FALHA] " + descricao);
        }
    }

    private static Cliente criarCliente(String nome, String cpf) {
        Cliente c = new Cliente();
        c.setNome(nome);
        c.setCpf(cpf);
        return c;
    }

    public static void main(String[] args) {
        System.out.println("=== Testes do Requisito #14 - Realizar Saque ===\n");

        Conta conta = new Conta(criarCliente("Ana", "111"));
        conta.depositar(100.0);

        // ---------- Impedir valores negativos e zerados ----------
        double saldoAntes = conta.getSaldo();
        checar("Saque de 0 (zero) é rejeitado", !conta.sacar(0.0));
        checar("Saque negativo (-10) é rejeitado", !conta.sacar(-10.0));
        checar("Saldo inalterado após saques inválidos", conta.getSaldo() == saldoAntes);

        // ---------- Saldo insuficiente ----------
        checar("Saque superior ao saldo é rejeitado", !conta.sacar(100.01));
        checar("Saldo inalterado após saque sem saldo", conta.getSaldo() == saldoAntes);

        // ---------- Saque válido subtrai do saldo ----------
        checar("Saque de 40 é aceito", conta.sacar(40.0));
        checar("Saldo atualizado para 60", Math.abs(conta.getSaldo() - 60.0) < 0.0001);

        // ---------- Saque de todo o saldo ----------
        checar("Saque de todo o saldo é aceito", conta.sacar(60.0));
        checar("Saldo zerado após saque total", conta.getSaldo() == 0.0);

        // ---------- Histórico ----------
        Conta contaHist = new Conta(criarCliente("Bia", "222"));
        contaHist.depositar(50.0);
        int histAntes = contaHist.getHistorico().size();
        contaHist.sacar(20.0);
        checar("Saque registra movimentação no histórico",
                contaHist.getHistorico().size() == histAntes + 1);

        // ---------- Integração com realizarOperacao("Saque") ----------
        Conta contaOp = new Conta(criarCliente("Caio", "333"));
        contaOp.depositar(30.0);
        checar("realizarOperacao('Saque', 10) é aceito", contaOp.realizarOperacao("Saque", 10.0));
        checar("Saldo de 20 após realizarOperacao", contaOp.getSaldo() == 20.0);
        checar("realizarOperacao('Saque', 100) é rejeitado", !contaOp.realizarOperacao("Saque", 100.0));

        System.out.println("\n=== Resultado: " + (testes - falhas) + "/" + testes + " testes passaram ===");
        if (falhas > 0) {
            System.exit(1);
        }
    }
}