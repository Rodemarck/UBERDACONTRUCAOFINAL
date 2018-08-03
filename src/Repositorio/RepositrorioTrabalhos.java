/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorio;

import java.util.*;
import Trabalho.Trabalho;
import Interfaces.IRepositrorioTrabalhos;
import Contas.Conta;
import Contas.ContaCliente;
import Contas.ContaFuncionario;
import Contas.ContaFuncionarioBurocratico;
import Contas.ContaFuncionarioCampo;

/**
 *
 * @author Rodemarck Jr
 */
public class RepositrorioTrabalhos implements IRepositrorioTrabalhos{
        private ArrayList<Trabalho> trabalhos = new ArrayList<Trabalho>();
        private ArrayList<String> especialidades = new ArrayList<String>();
        
        
        
	public ArrayList<Trabalho> getRepositrorioTrabalhos(){
            return this.trabalhos;
        }
        public ArrayList<Trabalho> getTodosTrabalhos(Conta conta){
            if(conta instanceof ContaFuncionarioBurocratico)
                return this.getTodosTrabalhos(conta);
            return new ArrayList<Trabalho>();
        }
        public ArrayList<Trabalho> getTodosTrabalhos(ContaFuncionarioCampo conta){
            ArrayList<Trabalho> todosTrabalhos = new ArrayList<Trabalho>();
            for(Trabalho t : trabalhos)
                if(t.getEmpregado().equals(conta))
                    todosTrabalhos.add(t);
            return todosTrabalhos;
        }
        public ArrayList<Trabalho> getTodosTrabalhos(ContaCliente conta){
            ArrayList<Trabalho> todosTrabalhos = new ArrayList<Trabalho>();
            for(Trabalho t : trabalhos)
                if(t.getEmpregador().equals(conta))
                    todosTrabalhos.add(t);
            return todosTrabalhos;
        }
        public boolean verificarDisponibilidade(Conta conta){
            if(conta instanceof ContaCliente)
                return verificarDisponibilidadeCliente((ContaCliente) conta);
            return verificarDisponibilidadeFuncionario((ContaFuncionario) conta);
        }
	private boolean verificarDisponibilidadeCliente(ContaCliente cliente) {
            if(cliente != null){
                for(int i=0; i < this.trabalhos.size();i++)
			if(this.trabalhos.get(i).getEmpregador().equals(cliente))
				return false;
		return true;
            }
            return true;
	}
	private boolean verificarDisponibilidadeFuncionario(ContaFuncionario funcionario) {
		for(int i=0; i < this.trabalhos.size();i++)
			if(this.trabalhos.get(i).getEmpregado().equals(funcionario))
				return false;
		return true;
	}
        public boolean cadastrar(Trabalho novoTrabalho) {
		if(novoTrabalho!=null && !trabalhos.contains(novoTrabalho)) {
			this.trabalhos.add(novoTrabalho);
		}
                return false;
	}
        
        public Trabalho getTrabalho(Conta conta){
            if(conta instanceof ContaCliente)
                return this.getTrabalhoCliente(((ContaCliente)conta));
            return this.getTrabalhoFuncionarioCampo((ContaFuncionarioCampo) conta);
        }
        public void deletar(Trabalho trabalho) {
            if(trabalho!=null && this.trabalhos.contains(trabalho)){
                this.trabalhos.remove(trabalho);
            }
	}
        public void atualizarTrabalho(Trabalho trabalhoAntigo, Trabalho trabalhoNovo) {
		if(trabalhoAntigo!=null && trabalhoNovo!=null && this.trabalhos.contains(trabalhoAntigo)) {
                    int x;
                    x=this.trabalhos.indexOf(trabalhoAntigo);
                    this.trabalhos.get(x).setEmpregador(trabalhoNovo.getEmpregador());
                    this.trabalhos.get(x).setEmpregado(trabalhoNovo.getEmpregado());
                    this.trabalhos.get(x).setEspecialidade(trabalhoNovo.getEspecialidade());
		}
	}
	private Trabalho getTrabalhoFuncionarioCampo(ContaFuncionarioCampo empregado) {
		if(empregado!=null) {
			for(int i=0;i<this.trabalhos.size();i++) {
				if(this.trabalhos.get(i).getEmpregado().equals(empregado)) {
					return this.trabalhos.get(i);
				}
			}	
		}
		return null;//exception
	}	
	private Trabalho getTrabalhoCliente(ContaCliente empregador) {
		if(empregador!=null) {
			for(int i=0;i<this.trabalhos.size();i++) {
				if(this.trabalhos.get(i).getEmpregador().equals(empregador)) {
					return this.trabalhos.get(i);
				}
			}	
		}
		return null;//exception
	}	
	public void terminarTrabalho(Trabalho trabalho) {
            if(trabalho!=null && this.trabalhos.contains(trabalho)) {
                int x=this.trabalhos.indexOf(trabalho);
                this.trabalhos.get(x).setAtivo(false);
                this.trabalhos.get(x).getEmpregado().setDisponibilidade(true);
                this.trabalhos.get(x).getEmpregador().setDisponibilidade(true);
            }
        }
        public boolean existeTrabalho(Trabalho trabalho) {
		return (trabalho!=null && this.trabalhos.contains(trabalho));
	}
        @Override
	public boolean demitirEmpregado() {
		return false;
	}
        @Override
	public boolean contratarEmpregado(ContaFuncionarioCampo contratado) {
		return false;
	}
	public boolean solicitarTrabalho() {
		return false;
	}
        public ArrayList<String> getTodasEspecialidades(){
            if(this.especialidades.size()==0){
                especialidades.add("pedreiro");
                especialidades.add("encanador");
                especialidades.add("pintor");
                especialidades.add("eletricista");
                especialidades.add("engenheiro");
                especialidades.add("mestre de obras");
            }
            return this.especialidades;
        }
}

