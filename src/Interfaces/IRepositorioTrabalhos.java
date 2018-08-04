//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package interfaces;

import Conta.Conta;
import Conta.ContaCliente;
import Conta.ContaFuncionarioCampo;
import Conta.Trabalho;
import java.util.ArrayList;

public interface IRepositorioTrabalhos {
    boolean cadastrar(Trabalho var1);

    void deletar(Trabalho var1);

    Trabalho procurarTrabalho(ContaCliente var1);

    Trabalho procurarTrabalho(ContaFuncionarioCampo var1);

    Trabalho[] listarTrabalhos(String var1);

    void atualizarTrabalho(Trabalho var1, Trabalho var2);

    boolean existeTrabalho(Trabalho var1);

    void encerrarTrabalho(Trabalho var1);

    void demitirEmpregado(ContaCliente var1);

    void sairDoTrabalho(ContaFuncionarioCampo var1);

    ArrayList<Trabalho> trabalhosAtivos();

    ArrayList<Trabalho> trabalhosNaoAtivos();

    public ArrayList<Trabalho> trabalhosnaoAtivos();

    public ArrayList<String> getTodasEspecialidades();

    public ArrayList<Trabalho> getRepositrorioTrabalhos();

    public ArrayList<Trabalho> getTodosTrabalhos(Conta usuario);

    public boolean verificarDisponibilidade(Conta usuario);

    public Trabalho getTrabalho(Conta usuario);
}
