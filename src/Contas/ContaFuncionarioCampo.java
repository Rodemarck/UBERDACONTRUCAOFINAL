/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contas;

import Contas.ContaFuncionario;

/**
 *
 * @author Rodemarck Jr
 */
public class ContaFuncionarioCampo extends ContaFuncionario {
	private boolean disponibilidade;
        private final String especialidade;
	public ContaFuncionarioCampo(Pessoa dados,String especialidade) {
		super(dados,"campo");
		this.disponibilidade=true;
                this.especialidade=especialidade;
	}

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }
        
	public boolean equals(ContaFuncionarioCampo conta) {
		return super.equals(conta) && this.getEspecialidade().equals(conta.getEspecialidade());
	}
}
