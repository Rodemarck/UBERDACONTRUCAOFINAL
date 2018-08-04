package Conta;

import java.util.ArrayList;

import Repositorio.RepositrorioTrabalhos;
import interfaces.IRepositorioTrabalhos;

public class CadastroTrabalho{
    private IRepositorioTrabalhos repositorio= RepositrorioTrabalhos.getInstance() ;

    public void cadastrar(Trabalho novoTrabalho) {
        this.repositorio.cadastrar(novoTrabalho);
    }

    public void atualizarTrabalho(Trabalho trabalhoAntigo,Trabalho trabalhoNovo) {
        this.repositorio.atualizarTrabalho(trabalhoAntigo, trabalhoNovo);
    }


    public void deletar(Trabalho trabalho) {
        this.repositorio.deletar(trabalho);
    }

    public Trabalho procurarTrabalho(ContaCliente empregador) {
        return this.repositorio.procurarTrabalho(empregador);
    }

    public Trabalho procurarTrabalho(ContaFuncionarioCampo empregado) {
        return this.repositorio.procurarTrabalho(empregado);
    }

    public boolean existeTrabalho(Trabalho trabalho) {
        if(this.repositorio.existeTrabalho(trabalho)==true) {
            return true;
        }
        return false;
    }

    public Trabalho[] listarTrabalhos(String especialidade) {
        return this.repositorio.listarTrabalhos(especialidade);
    }

    public ArrayList<Trabalho> trabalhosAtivos(){
        return this.repositorio.trabalhosAtivos();
    }

    public ArrayList<Trabalho> trabalhosnaoAtivos(){
        return this.repositorio.trabalhosnaoAtivos();
    }

    public void sairDoTrabalho(ContaFuncionarioCampo conta) {
        this.repositorio.sairDoTrabalho(conta);
    }

    public void demitirEmpregado(ContaCliente conta) {
        this.repositorio.demitirEmpregado(conta);
    }

    public void encerrarTrabalho(Trabalho trabalho) {
        this.repositorio.encerrarTrabalho(trabalho);
    }
}