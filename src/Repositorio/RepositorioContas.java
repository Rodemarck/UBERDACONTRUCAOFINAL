/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorio;

import java.util.ArrayList;
import Conta.ContaCliente;
import Conta.ContaFuncionarioBurocratico;
import Conta.ContaFuncionarioCampo;
import Conta.Conta;
import Conta.Pessoa;
import interfaces.IRepositorioContas;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

/**
 *
 * @author Rodemarck Jr
 */
public class RepositorioContas implements IRepositorioContas {
  	private static ArrayList<Conta> contas = new  ArrayList<Conta>();
        private static RepositorioContas instance;
        private static Conta usuario=null;
        private LocalDate agr=LocalDate.now();
	
        
        private RepositorioContas() {
        }
        
        
        public static IRepositorioContas getInstance() {
        if (instance == null) {
            instance = lerDoArquivo();
        }

        return instance;
    }

    private static RepositorioContas lerDoArquivo() {
        RepositorioContas instanciaLocal = null;
        File in = new File("contas.dat");
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(in);
            ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            instanciaLocal = (RepositorioContas)o;
        } catch (Exception var13) {
            instanciaLocal = new RepositorioContas();
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
			if(contas.get(i).getDados().getLogin().equals(login))
				return true;
		return false;
            }
            else
                return true;
	}
	public boolean checarEmail(String email) {
            if(email!=null){
		for(int i=0;i<contas.size();i++)
			if(contas.get(i).getDados().getEmail().equals(email))
				return true;
		return false;
            }
            else
                return true;
	}
	public boolean checarNumeroCartaoCredito(String numeroCartaoCredito) {
            if(numeroCartaoCredito != null){
		for(int i=0;i<contas.size();i++)
			if(contas.get(i).getDados().getNumeroCartao().equals(numeroCartaoCredito))
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
                    if(contas.get(i).getDados().getLogin().equals(login))
                            return contas.get(i);
                return null;
            }
            else
                return null;
	}
          @Override
        public Conta getContasPorEmail(String  email) {
            if(email != null){
                for(int i=0;i<contas.size();i++)
                    if(contas.get(i).getDados().getEmail().equals(email))
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
                usuario.setDados(dados);
                System.out.println(usuario);
                contas.set(x, usuario);
            }
        }
	public boolean login(String login,String senha) {
            if(login != null && senha != null){
                for(int i=0;i<contas.size();i++)
                    if(contas.get(i).getDados().getLogin().equals(login)) 
                        if(contas.get(i).getDados().getSenha().equals(senha)){
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
                    if(contas.get(i).getDados().getEmail().equals(email)) 
                        if(contas.get(i).getDados().getSenha().equals(senha)){
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
                contas.get(x).getDados().setUltimaSessao(agr);
            }
        }
        public void atualiar(Conta conta,Pessoa dados){
            if(conta != null && dados != null && contas.contains(conta)){
                int x = contas.indexOf(conta);
                conta.setDados(dados);
                contas.set(x, conta);
            }
        }
        public void deletarConta(Conta conta){
            if(conta != null && contas.contains(conta))
                contas.remove(conta);
        }

    @Override
    public void cadastrar(Conta var1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Conta procurarConta(String var1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizarConta(Conta var1, Conta var2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(Conta var1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}
