/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Repositorio.Repositorio;
import Repositorio.RepositorioContas;
import Repositorio.RepositrorioTrabalhos;
import Contas.Pessoa;
import Contas.ContaCliente;
import Contas.ContaFuncionario;
import Contas.Conta;
import Contas.ContaFuncionarioBurocratico;
import Contas.ContaFuncionarioCampo;
import Interfaces.IRepositorioContas;
import Trabalho.Trabalho;
import java.util.ArrayList;


/**
 *
 * @author Rodemarck Jr
 */
public class Controle {
    private RepositorioContas bancoDeDados = new RepositorioContas();
    private RepositrorioTrabalhos bancoDeMemoria = new RepositrorioTrabalhos();
    
    
   
    public boolean checarLogin(String login){
       return this.bancoDeDados.checarLogin(login);
    }
    public boolean checarEmail(String email){
        return this.bancoDeDados.checarEmail(email);
    }
    public boolean checarCartaoCredito(String cartao){
        return this.bancoDeDados.checarNumeroCartaoCredito(cartao);
    }
    public boolean fazerLogin(String login, String senha){
        boolean check= this.bancoDeDados.login(login, senha);
        if(check)
            this.logar(login);
        return check;
    }
    public boolean fazerLoginPorEmail(String email, String senha){
        boolean check =this.bancoDeDados.loginPorEmail(email, senha);
        if(check)
            this.logarPorEmail(email);
        return check;
    }
    private void logar(String login){
        this.bancoDeDados.getContas(login);
    }
    private Conta getConta(String login){
        return this.bancoDeDados.getContas(login);
    }
    private void logarPorEmail(String email){
        this.bancoDeDados.getContasPorEmail(email);
    }
    public void deslogar(){
        this.bancoDeDados.desloga();
    }
    public void fazerCadastro(Pessoa dados,String tipo,String especialidade){
        this.bancoDeDados.cadastrarConta(dados, tipo,especialidade);
    }
    public Conta getUsuario(){
        return this.bancoDeDados.getUsuario();
    }
    public void atualizaConta(Pessoa dados){
       this.bancoDeDados.atualizar(dados);
    }
    public void atualizaConta(Conta conta,Pessoa dados){
        this.bancoDeDados.atualiar(conta, dados);
    }
    public boolean isLogado(){
        return this.bancoDeDados.isLoagado();
    }   
    public void deletarConta(Conta conta){
        this.bancoDeDados.deletarConta(conta);
    }
    
    
    
    
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    public ArrayList<String> getTodasEspecialidades(){
        return this.bancoDeMemoria.getTodasEspecialidades();
    }
    public ArrayList<Trabalho> getRepositrorioTrabalhos(){
        return this.bancoDeMemoria.getRepositrorioTrabalhos();
    }
    public ArrayList<Trabalho> getTodosTrabalhos(){
        return this.bancoDeMemoria.getTodosTrabalhos(this.getUsuario());
    }
    public ArrayList<Trabalho> getTodosTrabalhos(Conta conta){
        return this.bancoDeMemoria.getTodosTrabalhos(conta);
    }
    public boolean verificarDisponibilidade(){
        return this.bancoDeMemoria.verificarDisponibilidade(this.getUsuario());
    }
    public boolean verificarDisponibilidade(Conta conta){
        return this.bancoDeMemoria.verificarDisponibilidade(conta);
    }
    public Trabalho getTrabalho(){
        return this.bancoDeMemoria.getTrabalho(this.getUsuario());
    }
    public Trabalho getTrabalho(Conta conta){
        return this.bancoDeMemoria.getTrabalho(conta);
    }
    /*public void criaTrabalho(){
        this.bancoDeMemoria.cadastrar(this);
    }*/
}
