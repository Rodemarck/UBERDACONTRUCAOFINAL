/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho;

import Contas.ContaCliente;
import Contas.ContaFuncionarioCampo;
import Interfaces.IRepositrorioTrabalhos;
import java.time.*;

/**
 *
 * @author Rodemarck Jr
 */
public class Trabalho implements IRepositrorioTrabalhos{
	private LocalDate inicioDasObras;
	private LocalDate fimDasObras;
	private ContaCliente empregador;
	private ContaFuncionarioCampo empregado;
	private String obra;
        private String especialidade;
	private boolean ativo;
        private String descricao;
	
	public Trabalho(ContaCliente cliente, ContaFuncionarioCampo funcionario,String obra, String especialidade) {
            if(cliente!= null && funcionario != null && obra!= null && especialidade != null){
		this.empregador= cliente;
		this.empregado= funcionario;
		this.obra= obra;
		this.ativo=true;
                this.especialidade=especialidade;
            }
	}
	
	
	public Trabalho(ContaCliente cliente,String obra, String especialidade) {
            if(cliente!= null && obra!= null & especialidade != null){
		this.empregador= cliente;
		this.empregado=null;
		this.obra= obra;
		this.ativo= false;
                this.especialidade=especialidade;
                this.descricao="";
                this.inicioDasObras= null;
                this.fimDasObras=null;
            }
	}
        
        public Trabalho(ContaFuncionarioCampo conta){
            if(conta != null){
                this.ativo=false;
                this.descricao="";
                this.empregado=conta;
                this.empregador=null;
                this.especialidade=conta.getEspecialidade();
                this.fimDasObras=null;
                this.inicioDasObras=null;
                this.obra="";
            }            
        }

        public String getEspecialidade() {
            return especialidade;
        }

        public void setEspecialidade(String especialidade) {
            this.especialidade = especialidade;
        }

        public boolean isAtivo() {
            return ativo;
        }

        public void setAtivo(boolean ativo) {
            this.ativo = ativo;
        }
                
	public ContaCliente getEmpregador() {
		return empregador;
	}

	public ContaFuncionarioCampo getEmpregado() {
		return empregado;
	}

	public boolean mudarEmpregado(ContaFuncionarioCampo novoEmpregado) {
		if(this.empregado!=null) {
			this.empregado = novoEmpregado;
			return true;
		}
		return false;
	}
	
	public boolean demitirEmpregado() {
		if(this.empregado!=null) {
			this.empregado=null;
			this.ativo=false;
			return true;
		}
		return false;
	}
	@Override
	public boolean contratarEmpregado(ContaFuncionarioCampo contratado) {
		if(this.empregado==null) {
			this.empregado= contratado;
			this.ativo=true;
			return true;
		}
		return false;
	}

	public String getObra() {
		return obra;
	}

	public boolean EstadoObra() {
		return ativo;
	}

	public void MudarEstadoObra(boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDate getInicioDasObras() {
		return inicioDasObras;
	}

	public void setInicioDasObras(LocalDate inicioDasObras) {
		this.inicioDasObras = inicioDasObras;
	}

	public LocalDate getFimDasObras() {
		return fimDasObras;
	}

	public void setFimDasObras(LocalDate fimDasObras) {
		this.fimDasObras = fimDasObras;
	}


	public void setEmpregador(ContaCliente empregador) {
		this.empregador = empregador;
	}
	
	public void setEmpregado(ContaFuncionarioCampo empregado) {
		this.empregado = empregado;
	}

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

	public boolean equals(Trabalho trabalho) {
		if(trabalho==null)
			return false;
		return (this.inicioDasObras.equals(trabalho.getInicioDasObras()) && this.empregado.equals(trabalho.getEmpregado()) &&
				this.empregador.equals(trabalho.getEmpregador()) && this.obra.equals(trabalho.getObra()));
	}
	@Override
	public String toString() {
                return "Trabalho:"+this.ativo+ "Requisito:"+ this.especialidade+
                    "Empregador:"+this.empregador+"Empregado:"+this.empregado;
        }

}
