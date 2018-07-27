/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorio;

import java.util.ArrayList;
import Contas.ContaCliente;
import Contas.ContaFuncionarioBurocratico;
import Contas.ContaFuncionarioCampo;
import Contas.Conta;
import Contas.Pessoa;
import Interfaces.IRepositorioContas;
import java.time.LocalDate;

/**
 *
 * @author Rodemarck Jr
 */
public class RepositorioContas  {
  	private static ArrayList<Conta> contas = new  ArrayList<Conta>();
        private static Conta usuario=null;
        private LocalDate agr=LocalDate.now();
	
        public ArrayList<Conta> getRepositorioContas(){
            return contas;
        }
        public boolean isLoagado(){
            return usuario!=null;
        }
        public Conta getUsuario(){
            return usuario;
        }
        public boolean checarLogin(String login) {
            if(login!=null){
		for(int i=0;i<contas.size();i++)
			if(contas.get(i).getDadosPessoais().getLogin().equals(login))
				return true;
		return false;
            }
            else
                return true;
	}
	public boolean checarEmail(String email) {
            if(email!=null){
		for(int i=0;i<contas.size();i++)
			if(contas.get(i).getDadosPessoais().getEmail().equals(email))
				return true;
		return false;
            }
            else
                return true;
	}
	public boolean checarNumeroCartaoCredito(String numeroCartaoCredito) {
            if(numeroCartaoCredito != null){
		for(int i=0;i<contas.size();i++)
			if(contas.get(i).getDadosPessoais().getNumeroCartao().equals(numeroCartaoCredito))
				return true;
		return false;
            }
            else
                return true;
	}
	public void cadastrarConta(Pessoa dados,String tipo,String especialidade) {
            if(dados != null && tipo!=null && especialidade!=null){
                Conta novaConta=null;
                switch(tipo){
                    case "cliente":
                        novaConta = new ContaCliente(dados);
                        break;
                    case "campo":
                        novaConta = new ContaFuncionarioBurocratico(dados);
                        break;
                    case "burocratico":
                        novaConta = new ContaFuncionarioCampo(dados,especialidade);
                        break;
                }
                dados.setDataDeCriacao(agr);
                dados.setUltimaSessao(agr);
                contas.add(novaConta);
            }
		
	}
	public Conta getContas(String  login) {
            if(login != null){
                for(int i=0;i<contas.size();i++)
                    if(contas.get(i).getDadosPessoais().getLogin().equals(login))
                            return contas.get(i);
                return null;
            }
            else
                return null;
	}
        public Conta getContasPorEmail(String  email) {
            if(email != null){
                for(int i=0;i<contas.size();i++)
                    if(contas.get(i).getDadosPessoais().getEmail().equals(email))
                            return contas.get(i);
                return null;
            }
            else
                return null;
	}
        public void atualizar(Pessoa dados){
            if(usuario!=null && dados!=null && contas.contains(usuario)){
                int x = contas.indexOf(usuario);
                System.out.println(usuario);
                usuario.setDadosPessoais(dados);
                System.out.println(usuario);
                contas.set(x, usuario);
            }
        }
	public boolean login(String login,String senha) {
            if(login != null && senha != null){
                for(int i=0;i<contas.size();i++)
                    if(contas.get(i).getDadosPessoais().getLogin().equals(login)) 
                        if(contas.get(i).getDadosPessoais().getSenha().equals(senha)){
                           usuario=contas.get(i);
                            return true;
                        }
                return false;
            }
            else 
                return false;
	}
	public boolean loginPorEmail(String email,String senha) {
            if(senha != null && email != null){
                for(int i=0;i<contas.size();i++)
                    if(contas.get(i).getDadosPessoais().getEmail().equals(email)) 
                        if(contas.get(i).getDadosPessoais().getSenha().equals(senha)){
                            usuario=contas.get(i);
                            return true;
                        }
                return false;
            }
            else 
                return false;
	}
        public void desloga(){
            if(usuario!=null && contas.contains(usuario)){
                int x = contas.indexOf(usuario);
                usuario=null;
                contas.get(x).getDadosPessoais().setUltimaSessao(agr);
            }
        }
        public void atualiar(Conta conta,Pessoa dados){
            if(conta != null && dados != null && contas.contains(conta)){
                int x = contas.indexOf(conta);
                conta.setDadosPessoais(dados);
                contas.set(x, conta);
            }
        }
        public void deletarConta(Conta conta){
            if(conta != null && contas.contains(conta))
                contas.remove(conta);
        }
	
}
