/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Contas.Conta;
import Contas.ContaCliente;
import Contas.Pessoa;
import Trabalho.Trabalho;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Rodemarck Jr
 */
public class CentralController implements Initializable {
    //////////
    @FXML    private JFXTextField txtfProfileEmail;
    @FXML    private JFXTextField txtfProfileLogin;
    ////////////////////
        
    ///////////////////////////
    @FXML    private JFXListView<Trabalho> listaTrabalho;
    @FXML    private JFXListView<Conta> listaConta;
    ////////////////////
    
    
    
    ///////////////////////////
    @FXML    private AnchorPane anpaneTrabalhoLista;
    @FXML    private AnchorPane anpaneTrabalhoExistente;
    @FXML    private Label lblTrabalhoNomeCliente;
    @FXML    private Label lblTrabalhoNomeFuncionario;
    @FXML    private Label lblTrabalhoEspecialidadeFuncionario;
    @FXML    private Label lblTrabalho;
    @FXML    private Label lblTrabalhoInicio;
    @FXML    private Label lblTrabalahoFim;
    @FXML    private Label lblTrabalhoSituacao;
    @FXML    private JFXTextArea txtaTrabalhoDescricao;
    ////////////////////
    
    
    
    ///////////////////////////
    @FXML    private JFXComboBox<String> cbTealaNovoTrabalhoEpecialidade;
    @FXML    private TextArea txtaTrabalhoNovo;
    ////////////////////
    
    
    
    ///////////////////////////
   @FXML    private JFXPasswordField pswfTelaConfirmarSenhaSenha;
   @FXML    private JFXPasswordField pswfTelaConfirmarSenhaConfirma;
    
    
    private String LOCALNovoEmail="";
    private String LOCALNovoLogin="";
    private String LOCALSenha="";
    private String LOCALConfirmaSenha="";
    int uso;
    Controle controle = new Controle();
    GUI gui = new GUI();
    /**
     * Initializes the controller class.
     */

    @FXML private void desloga(){
        this.gui.trocaCena(0);
    }
    @FXML    private void notification(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("notificações");
        alert.setHeaderText("Voce possui 0 notificações");
        alert.setContentText("");
        alert.show();
    }
    @FXML    private void meusTravalhos(){
        this.gui.segundaTelaAbre(5);
    }
    @FXML    private void historico(){
        this.scriptHistorico();
        this.gui.segundaTelaAbre(3);
    }
    @FXML    private void suporte(){
        this.gui.segundaTelaAbre(4);
    }
    @FXML    private void profile(){
        this.gui.segundaTelaAbre(6);
    }    
    @FXML    private void sair(){
        this.gui.segundaTelaFecha();
    }
    @FXML    private void fechar(){
        this.gui.terceiraTelaFecha();
    }
    private void checarSenha(int i){
        this.uso=i;
        this.gui.terceiraTelaFecha();
        this.gui.terceiraTelaAbre(7);
        
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
    






















////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    @FXML    public void alterar(){
        this.LOCALNovoEmail = this.txtfProfileEmail.getText();
        this.LOCALNovoLogin = this.txtfProfileLogin.getText();
        this.checarSenha(1);
        this.gui.terceiraTelaAbre(7);
    }
    
    private void atualiza(){
        Conta conta;
        Conta novaConta;
        conta = this.controle.getUsuario();
        Pessoa dados = conta.getDadosPessoais();
        if(this.LOCALNovoEmail.length()!=0 && 
                this.checaEmail()){
            dados.setEmail(LOCALNovoEmail);
            System.out.println("");
        }
        if(this.LOCALNovoEmail.length()!=0 
                
                && this.checaLogin()){
            dados.setLogin(LOCALNovoEmail);
            System.out.println("");
        }
        this.controle.atualizaConta(dados);
    }
    
    private boolean checaLogin(){
        return ((this.LOCALNovoLogin.length()>=7 || !this.LOCALNovoLogin.contains("@") ||
                !this.LOCALNovoLogin.contains(" ") || !this.LOCALNovoLogin.contains(".")) &&
                !this.controle.checarLogin(this.LOCALNovoLogin));
    }
    
    private boolean checaSenha(){
        return this.pswfTelaConfirmarSenhaSenha.getText().length() >=7 && 
                this.controle.getUsuario().getDadosPessoais().getSenha().equals(this.pswfTelaConfirmarSenhaSenha.getText()) &&
                this.pswfTelaConfirmarSenhaSenha.getText().equals(this.pswfTelaConfirmarSenhaConfirma.getText());
    }
    
    private boolean checaEmail(){
        return !((LOCALNovoEmail.indexOf(".") >= LOCALNovoEmail.length()-3 || 
                !LOCALNovoEmail.contains("@") || !LOCALNovoEmail.contains(".") ||
                LOCALNovoEmail.indexOf(".")-3 < LOCALNovoEmail.indexOf("@") || 
                LOCALNovoEmail.indexOf("@")<3) && this.controle.checarEmail(LOCALNovoEmail));
    }
    
    @FXML   private void confirmarSenha(){
        switch(uso){
            case 1:
                this.alertaConfirmacaoPerfil();
                break;
            case 2:
                this.alertaCriaTrabalho();
                break;
        }
    }
    @FXML    private void alertaConfirmacaoPerfil(){
        if(this.checaSenha()){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("confirmação");
            alert.setHeaderText(null);
            alert.setContentText("deseja alterar esses dados?");
            Optional<ButtonType> escolha= alert.showAndWait();
            if(escolha.get() == ButtonType.OK){
                this.atualiza();
                this.gui.terceiraTelaFecha();
                this.gui.segundaTelaFecha();
            }
            else
                this.gui.terceiraTelaFecha();
        }
         else
            this.alertaConfirmacaoPerfilErro();
    }
    
     private void alertaConfirmacaoPerfilErro(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("senha incorreta");
        alert.setHeaderText(null);
        alert.setContentText("as senhas fornecidas não coincidem com o banco de dados");
        alert.show();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
   
     
     
     
     
     
     
     
     
     
     
     
     
     
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
     
     
     
    private void scriptHistorico(){
        ObservableList<Trabalho> trabalho = FXCollections.observableArrayList(controle.getTodosTrabalhos());
        this.listaTrabalho.setItems(trabalho);
    }
    private void scriptTelaTrabalho(){
       
    }
    @FXML    private void abreTelaTrabalho(){
        Trabalho t = this.listaTrabalho.getSelectionModel().getSelectedItem();
        if(t != null){
            LocalDate d = t.getFimDasObras();
            this.lblTrabalahoFim.setText(this.data(d));
            this.lblTrabalho.setText(t.getEspecialidade());
            d = t.getInicioDasObras();
            this.lblTrabalhoInicio.setText(this.data(d));
            this.lblTrabalhoEspecialidadeFuncionario.setText(t.getEmpregado().getEspecialidade());
            this.lblTrabalhoNomeFuncionario.setText(t.getEmpregado().getDadosPessoais().getLogin());
            this.lblTrabalhoNomeCliente.setText(t.getEmpregador().getDadosPessoais().getLogin());
            this.txtaTrabalhoDescricao.setText(t.getDescricao());
            if(t.isAtivo())
                this.lblTrabalhoSituacao.setText("em operação");
            else
                this.lblTrabalhoSituacao.setText("fora de operação");
            this.anpaneTrabalhoLista.setVisible(false);
            this.anpaneTrabalhoExistente.setVisible(true);
        }
        else
            System.out.println("exception");
    }
    
    private String data(LocalDate d){
        if(d==null)
            return "???";  
        return +d.getDayOfMonth()+" / "+d.getMonthValue()+" / "+d.getYear();
    }
    
    @FXML   private void alertaCriaTrabalho(){
        if(this.checaSenha()){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("confirmação");
            alert.setHeaderText(null);
            alert.setContentText("deseja criar esse trabalho?");
            Optional<ButtonType> escolha= alert.showAndWait();
            if(escolha.get() == ButtonType.OK)
                if(this.criaTrabalho()){
                    this.alertaCriaTrabalhoErro(1);
                    
                }
            this.gui.terceiraTelaFecha();
            this.gui.segundaTelaFecha();
        }
         else
            this.alertaCriaTrabalhoErro(0);
    }
    
     private void alertaCriaTrabalhoErro(int i){
        Alert alert = new Alert(AlertType.ERROR);
        switch(i){
            case 0: 
                alert.setTitle("senha incorreta");
                alert.setContentText("as senhas fornecidas não coincidem com o banco de dados");
                break;
            case 1:
                alert.setTitle("falha ao criar");
                alert.setContentText("o trabalho já está criado");
                break;
            case 2:
                alert.setTitle("falha ao criar");
                alert.setContentText("algum dos campos está em branco");
                break;
        }
        
        alert.setHeaderText(null);
        alert.show();
    }
    @FXML    private void trabalho(){
        this.checarSenha(2);
    }
    private boolean criaTrabalho(){
       if(cbTealaNovoTrabalhoEpecialidade.getSelectionModel().getSelectedItem() != null){
            this.checarSenha(3);
            Trabalho t = new Trabalho((ContaCliente) this.controle.getUsuario(),txtaTrabalhoNovo.getText(),cbTealaNovoTrabalhoEpecialidade.getSelectionModel().getSelectedItem());
            this.controle.criaTrabalho(t); 
            return this.controle.criaTrabalho(t);
        }
       return false;
    }   
    
    
    
    
    
    
    
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    
    
    
    
    
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
        
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void carregaCB(){
          ObservableList<String> listaEspecialidades= FXCollections.observableArrayList(this.controle.getTodasEspecialidades());
          cbTealaNovoTrabalhoEpecialidade.setItems(listaEspecialidades);
    }
    private void carregaConta(){
        ObservableList<Conta> conta = FXCollections.observableArrayList(this.controle.getRepositorioContas());
        this.listaConta.setItems(conta);
    }
    private void load(){
        this.carregaCB();
        this.carregaConta();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.load();
    }    
    
}
