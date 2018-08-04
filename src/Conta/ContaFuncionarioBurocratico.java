/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conta;

import Conta.ContaFuncionario;

/**
 *
 * @author Rodemarck Jr
 */
public class ContaFuncionarioBurocratico extends ContaFuncionario{
	private boolean disponibilidade;
	public ContaFuncionarioBurocratico(Pessoa dados) {
		super(dados,"burocratico");
		this.disponibilidade=true;
	}
	public boolean equals(ContaFuncionarioCampo conta) {
		return super.equals(conta);
	}
}
