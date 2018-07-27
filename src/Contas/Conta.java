/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contas;

import java.time.LocalDate;

/**
 *
 * @author Rodemarck Jr
 */
public abstract class Conta {
	protected Pessoa pessoa;
	protected String tipo;
	
	
	public Conta(Pessoa pessoa,String tipo){
		this.pessoa=pessoa;
		this.tipo=tipo;
	}
	
	public Conta(String email, String senha, String login, LocalDate dataDeCriacao, 
		   LocalDate ultimaSessao, String numeroCartaoCredito) {
		this.pessoa = new Pessoa(email, senha, login,numeroCartaoCredito);
                this.getDadosPessoais().setDataDeCriacao(dataDeCriacao);
                this.getDadosPessoais().setUltimaSessao(dataDeCriacao);
	}
	
	public boolean equals(Conta conta) {
		return this.getDadosPessoais().equals(conta.getDadosPessoais()) &&
                        this.getTipo().equals(conta.getTipo());
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	
	public Pessoa getDadosPessoais() {
		return this.pessoa;
	}
	 
	public void setDadosPessoais(Pessoa dados) {
		this.pessoa=dados;
	}
        public String toString(){
            return "Conta "+this.tipo+" ["+this.pessoa;
        }
}
