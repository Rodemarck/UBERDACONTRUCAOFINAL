/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conta;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Rodemarck Jr
 */
public class Pessoa {
	private int idade;
        private String nome;
	private String email;
	private String senha;
	private String login;
	private String numeroCartao;
        private LocalDate dataDeCriacao;
	private LocalDate ultimaSessao;

    public Pessoa(int idade,String nome,String email,String senha,String login,String numeroCartao){
        if( nome != null && email!= null && senha != null && login != null && numeroCartao != null){
            this.idade=idade;
            this.nome=nome;
            this.email=email;
            this.senha=senha;
            this.login=login;
            this.numeroCartao=numeroCartao;
        }
    }
    public Pessoa(String email,String senha,String login,String numeroCartao){
        if(email!=null && senha!=null && login!=null&&numeroCartao!=null){
            this.idade=0;
            this.nome="";
            this.email=email;
            this.senha=senha;
            this.login=login;
            this.numeroCartao=numeroCartao;
        }
    }
    public Pessoa(){
        if(email!=null && senha!=null && login!=null&&numeroCartao!=null){
            this.idade=0;
            this.nome="";
            this.email="";
            this.senha="";
            this.login="";
            this.numeroCartao="";
        }
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDate getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDate dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public LocalDate getUltimaSessao() {
        return ultimaSessao;
    }

    public void setUltimaSessao(LocalDate ultimaSessao) {
        this.ultimaSessao = ultimaSessao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }


   public boolean equals(Object obj) {
	return (obj!=null && obj instanceof Pessoa && this.nome.equals(((Pessoa) obj).getNome()) && 
            this.email.equals(((Pessoa) obj).getEmail()) && this.login.equals(((Pessoa) obj).getLogin()) &&
            this.numeroCartao.equals(((Pessoa) obj).getNumeroCartao()) && this.senha.equals(((Pessoa) obj).getSenha()));
   }

    @Override
    public String toString() {
        return "Pessoa{" + "idade=" + idade + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", login=" + login + ", numeroCartao=" + numeroCartao + ", dataDeCriacao=" + dataDeCriacao + ", ultimaSessao=" + ultimaSessao + '}';
    }
   
   
    

    
    

}
