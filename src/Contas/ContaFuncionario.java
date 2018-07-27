/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contas;


/**
 *
 * @author Rodemarck Jr
 */
public abstract class ContaFuncionario extends Conta{
	private String tipoFuncionario;
		
	public ContaFuncionario(Pessoa dados,String tipo) {
		super(dados,"funcionario");
		this.tipoFuncionario= tipo;
	}
	
	

	public String getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(String tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	@Override
	public String toString() {
		return "ContaFuncionario [dados=" + super.toString() + ", tipoFuncionario=" + this.tipoFuncionario+ "]";
	}
	
	public boolean equals(ContaFuncionario conta) {
		return super.equals(conta);
	}
	
}
