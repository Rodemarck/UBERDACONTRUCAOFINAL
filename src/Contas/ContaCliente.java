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
public class ContaCliente extends Conta{
	private String tipoCliente;
	private boolean verificacao;
        private boolean disponibilidade;

	public ContaCliente(Pessoa pessoa) {
		super(pessoa,"cliente");
	}
	public boolean equals(ContaCliente conta) {
		return super.equals(conta);
	}

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

        
        
}
