//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package interfaces;

import Conta.Conta;
import Conta.Pessoa;
import java.util.ArrayList;

public interface IRepositorioContas {
    void cadastrar(Conta var1);

    Conta procurarConta(String var1);

    void deletarConta(Conta var1);

    void atualizarConta(Conta var1, Conta var2);

    boolean existe(Conta var1);

    public boolean checarLogin(String login);

    public boolean checarEmail(String email);

    public boolean checarNumeroCartaoCredito(String cartao);

    public boolean login(String login, String senha);

    public boolean loginPorEmail(String email, String senha);

    public Conta getContas(String login);

    public Conta getContasPorEmail(String email);

    public void desloga();

    public void cadastrarConta(Pessoa dados, String tipo, String especialidade);

    public Conta getUsuario();

    public void atualizar(Pessoa dados);

    public void atualiar(Conta conta, Pessoa dados);

    public boolean isLoagado();

    public ArrayList<Conta> getRepositorioContas();
}
