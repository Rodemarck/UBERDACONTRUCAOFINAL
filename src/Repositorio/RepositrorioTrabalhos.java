/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorio;

import java.util.*;
import Conta.Trabalho;
import interfaces.IRepositorioTrabalhos;
import Conta.Conta;
import Conta.ContaCliente;
import Conta.ContaFuncionario;
import Conta.ContaFuncionarioBurocratico;
import Conta.ContaFuncionarioCampo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Rodemarck Jr
 */
public class RepositrorioTrabalhos implements IRepositorioTrabalhos{
        private ArrayList<Trabalho> trabalhos = new ArrayList<Trabalho>();
        private static IRepositorioTrabalhos instance;
        private ArrayList<String> especialidades = new ArrayList<String>();
        
        
        
        
        private RepositrorioTrabalhos() {
    }

    public static IRepositorioTrabalhos getInstance() {
        if (instance == null) {
            instance = lerDoArquivo();
        }

        return instance;
    }

    private static RepositrorioTrabalhos lerDoArquivo() {
        RepositrorioTrabalhos instanciaLocal = null;
        File in = new File("trabalhos.dat");
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(in);
            ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            instanciaLocal = (RepositrorioTrabalhos)o;
        } catch (Exception var13) {
            instanciaLocal = new RepositrorioTrabalhos();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException var12) {
                    ;
                }
            }

        }

        return instanciaLocal;
    }

    public void salvarArquivo() {
        if (instance != null) {
            File out = new File("contas.dat");
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;

            try {
                fos = new FileOutputStream(out);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(instance);
            } catch (Exception var13) {
                var13.printStackTrace();
            } finally {
                if (oos != null) {
                    try {
                        oos.close();
                    } catch (IOException var12) {
                        ;
                    }
                }

            }

        }
    }
        
        
        
        
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
      public boolean cadastrar(Trabalho novoTrabalho) {
        if (novoTrabalho != null && !this.existeTrabalho(novoTrabalho)) {
            this.trabalhos.add(novoTrabalho);
            return true;
        }
        return false;
    }

    public Trabalho procurarTrabalho(ContaCliente empregador) {
        if (empregador != null) {
            for(int i = 0; i < this.trabalhos.size(); ++i) {
                if (((Trabalho)this.trabalhos.get(i)).getEmpregador().equals(empregador)) {
                    return (Trabalho)this.trabalhos.get(i);
                }
            }
        }

        return null;
    }

    public Trabalho[] listarTrabalhos(String especialidade) {
        ArrayList<Trabalho> trabalhos = new ArrayList();

        for(int i = 0; i < this.trabalhos.size(); ++i) {
            if (((Trabalho)this.trabalhos.get(i)).getEspecialidade().equals(especialidade)) {
                trabalhos.add((Trabalho)this.trabalhos.get(i));
            }
        }

        Trabalho[] listaTrabalho = (Trabalho[])trabalhos.toArray(new Trabalho[trabalhos.size()]);
        return listaTrabalho;
    }

    public Trabalho procurarTrabalho(ContaFuncionarioCampo empregado) {
        if (empregado != null) {
            for(int i = 0; i < this.trabalhos.size(); ++i) {
                if (((Trabalho)this.trabalhos.get(i)).getEmpregado().equals(empregado)) {
                    return (Trabalho)this.trabalhos.get(i);
                }
            }
        }

        return null;
    }

    public void deletar(Trabalho trabalho) {
        if (trabalho != null && this.trabalhos.contains(trabalho)) {
            this.trabalhos.remove(trabalho);
        }

    }

    public void atualizarTrabalho(Trabalho trabalhoAntigo, Trabalho trabalhoNovo) {
        if (trabalhoAntigo != null && trabalhoNovo != null && this.trabalhos.contains(trabalhoAntigo)) {
            int x = this.trabalhos.indexOf(trabalhoAntigo);
            ((Trabalho)this.trabalhos.get(x)).setEmpregador(trabalhoNovo.getEmpregador());
            ((Trabalho)this.trabalhos.get(x)).setEmpregado(trabalhoNovo.getEmpregado());
            ((Trabalho)this.trabalhos.get(x)).setEspecialidade(trabalhoNovo.getEspecialidade());
        }

    }

    public boolean existeTrabalho(Trabalho trabalho) {
        return trabalho != null && this.trabalhos.contains(trabalho);
    }

    public void encerrarTrabalho(Trabalho trabalho) {
        if (this.trabalhos.contains(trabalho)) {
            int x = this.trabalhos.indexOf(trabalho);
            ((Trabalho)this.trabalhos.get(x)).setAtivo(false);
            ((Trabalho)this.trabalhos.get(x)).getEmpregado().setDisponibilidade(true);
        }

    }

    public void demitirEmpregado(ContaCliente conta) {
        if (this.trabalhos.contains(this.procurarTrabalho(conta))) {
            this.procurarTrabalho(conta).setEmpregado((ContaFuncionarioCampo)null);
            this.procurarTrabalho(conta).setAtivo(false);
        }

    }

    public void sairDoTrabalho(ContaFuncionarioCampo conta) {
        if (!conta.isDisponibilidade()) {
            conta.setDisponibilidade(true);
            this.procurarTrabalho(conta).setAtivo(false);
            this.procurarTrabalho(conta).setEmpregado((ContaFuncionarioCampo)null);
        }

    }

    public ArrayList<Trabalho> trabalhosAtivos() {
        ArrayList<Trabalho> trabalhos = new ArrayList();

        for(int i = 0; i < this.trabalhos.size(); ++i) {
            if (((Trabalho)this.trabalhos.get(i)).isAtivo()) {
                trabalhos.add((Trabalho)this.trabalhos.get(i));
            }
        }

        return trabalhos;
    }

    public ArrayList<Trabalho> trabalhosNaoAtivos() {
        ArrayList<Trabalho> trabalhos = new ArrayList();

        for(int i = 0; i < this.trabalhos.size(); ++i) {
            if (!((Trabalho)this.trabalhos.get(i)).isAtivo()) {
                trabalhos.add((Trabalho)this.trabalhos.get(i));
            }
        }

        return trabalhos;
    }

    @Override
    public ArrayList<Trabalho> trabalhosnaoAtivos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verificarDisponibilidade(Conta usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Trabalho getTrabalho(Conta usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

