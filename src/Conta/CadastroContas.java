//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Conta;


import Repositorio.RepositorioContas;
import interfaces.IRepositorioContas;

public class CadastroContas {
    private IRepositorioContas repositorio = RepositorioContas.getInstance();

    public CadastroContas() {
    }

    public void cadastrar(Conta novaConta) {
        if (novaConta != null && !this.repositorio.existe(novaConta)) {
            this.repositorio.cadastrar(novaConta);
        }

    }

    public void deletarConta(Conta conta) {
        if (conta != null && this.repositorio.existe(conta)) {
            this.repositorio.deletarConta(conta);
        }

    }

    public void atualizarConta(Conta contaAntiga, Conta contaNova) {
        if (contaAntiga != null && contaNova != null && this.repositorio.existe(contaAntiga)) {
            this.repositorio.atualizarConta(contaAntiga, contaNova);
        }

    }

    public Conta encontrarConta(Conta conta) {
        return conta != null && this.repositorio.existe(conta) ? this.repositorio.procurarConta(conta.getDados().getNome()) : null;
    }
}
