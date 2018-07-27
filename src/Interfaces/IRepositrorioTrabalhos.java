/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Contas.ContaCliente;
import Contas.ContaFuncionarioCampo;

/**
 *
 * @author Rodemarck Jr
 */
public interface IRepositrorioTrabalhos {

	//public boolean mudarEmpregado(FuncionarioCampo novoEmpregado);
	public boolean demitirEmpregado();
	public boolean contratarEmpregado(ContaFuncionarioCampo contratado);
	
}