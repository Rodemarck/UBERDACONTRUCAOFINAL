/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Contas.Conta;

/**
 *
 * @author Rodemarck Jr
 */
public interface IRepositorioContas {
	public boolean login(String login,String senha);
	public boolean checarLogin(String login);
	public boolean checarEmail(String email);
	public boolean checarSenha(String senha);
	public boolean checarNumeroCartaoCredito(String numeroCartaoCredito);
	public void cadastrarConta(Conta novaConta);
	public Conta getContas(String  login);
	public boolean atualizar(Conta usuario,String informacao,String novoDado);
}
