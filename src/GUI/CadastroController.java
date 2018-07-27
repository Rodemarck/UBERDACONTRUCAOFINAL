/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Contas.*;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Rodemarck Jr
 */
public class CadastroController implements Initializable {
    @FXML    private JFXTextField txtfLogin;
    @FXML    private JFXPasswordField pswfSenha;
    @FXML    private JFXRadioButton rbtnContaCliente;
    @FXML    private JFXRadioButton rbtnContaFuncionario;
    @FXML    private JFXRadioButton rbtnFuncionarioCampo;
    @FXML    private JFXRadioButton rbtnFuncionarioBurocratico;
    @FXML    private JFXTextField txtfEMail;
    @FXML    private JFXTextField txtfCartao;
    @FXML    private JFXPasswordField pswfConfirmarSenha;
    @FXML    private Label lbl;
    GUI gui = new GUI();
    Controle controle = new Controle();
    private ObservableList<String> listasEspecialidade;
    @FXML    private JFXComboBox<String> cbEpecialidade;
    /**
     * Initializes the controller class.
     */
    @FXML    private void sair(){
        this.limparBuffer();
        this.troca(0);
    }
    @FXML    private void cadastrar(){
        fazerCadastro();
    }
    private void fazerCadastro(){
        boolean check=true;
        String aviso="",CO="Campo obrigatorio:",CI="Campo invalido:",IJU="Informação já em uso";
        if(this.txtfLogin.getText().length()==0){
            aviso+=CO+"login \n";
            check=false;
        }
        else if(this.txtfLogin.getText().length()<=7 || this.txtfLogin.getText().contains("@") ||
                this.txtfLogin.getText().contains(" ") || this.txtfLogin.getText().contains(".")){
            aviso+=CI+"login \n";
            check=false;
        }
        if(this.txtfEMail.getText().length()==0){
            aviso+=CO+"e-mail \n";
            check=false;
        }
        else if(checaEmail()){
            check=false;
            aviso+=CI+"e-mail \n";
        }
        if(this.txtfCartao.getText().length()==0){
            aviso+=CO+"numero do cartão \n";
            check=false;
        }
        if(this.pswfSenha.getText().length()==0){
            aviso+=CO+"senha \n";
            check=false;
        }
        else if(this.pswfSenha.getText().length()<=7){
            aviso+="tamano insuficiente: "+"senha \n";
            check=false;
        }
        if(this.pswfConfirmarSenha.getText().length()==0){
            aviso+=CO+"confirmar senha \n";
            check=false;
        }
        else if(!this.pswfSenha.getText().equals(this.pswfConfirmarSenha.getText())){
            aviso+=CI+"connfimação de senha\n";
            check=false;
        }
        if(!(this.rbtnContaCliente.isSelected() || this.rbtnContaFuncionario.isSelected())){
            aviso+=CO+"tipo de conta \n";
            check=false;
        }
        
        if(this.rbtnContaFuncionario.isSelected() && (!(this.rbtnFuncionarioBurocratico.isSelected() || this.rbtnFuncionarioCampo.isSelected()))){
            aviso+=CO+"tipo de funcionario \n";
            check=false;
        }
        if(!this.rbtnContaCliente.isSelected() && this.rbtnContaFuncionario.isSelected()&&
                this.cbEpecialidade.getSelectionModel().getSelectedItem()==null){
            aviso+=CO+"especialidade \n";
            check=false;
        }
        if(check){
            if(this.controle.checarLogin(this.txtfLogin.getText())){
                aviso+=IJU+"login \n";
                check=false;
            }
            if(this.controle.checarEmail(this.txtfEMail.getText())){
                aviso+=IJU+"e-mail \n";
                check=false;
            }
            if(this.controle.checarCartaoCredito(this.txtfCartao.getText())){
                aviso+=IJU+"numero do cartão \n";
                check=false;
            }
            if(check)
                this.confirma();
            else
                chamaAlert(aviso,false);
        }
        else
            chamaAlert(aviso,true);
} 
    private boolean checaEmail(){
        return (this.txtfEMail.getText().indexOf(".") >= this.txtfEMail.getText().length()-3 || 
                !this.txtfEMail.getText().contains("@") || !this.txtfEMail.getText().contains(".") ||
                this.txtfEMail.getText().indexOf(".")-3 < this.txtfEMail.getText().indexOf("@") || 
                this.txtfEMail.getText().indexOf("@")<3);
    }
    private void confirma(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("confirmação");
        alert.setHeaderText(null);
        alert.setContentText("deseja criar essa conta?");
        Optional<ButtonType> escolha= alert.showAndWait();
        if(escolha.get() == ButtonType.OK)
            this.criarConta();
    }
  
    private void criarConta(){
        Conta novaConta;
        String tipo="",especialidade="";
        Pessoa dados = new Pessoa();
        dados.setEmail(this.txtfEMail.getText());
        dados.setLogin(this.txtfLogin.getText());
        dados.setSenha(this.pswfSenha.getText());
        dados.setNumeroCartao(this.txtfCartao.getText());
        if(this.rbtnContaCliente.isSelected())
            tipo="cliente";
        
        else if(this.rbtnFuncionarioCampo.isSelected()){
            tipo="campo";
            especialidade = this.cbEpecialidade.getSelectionModel().getSelectedItem();
        }
        else
            tipo="burocratico";
        this.controle.fazerCadastro(dados,tipo,especialidade);
        this.limparBuffer();
        this.troca(0);
    }
    private void chamaAlert(String aviso,boolean check){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("falha no prcesso de cadastro");
        if(check)
            alert.setHeaderText("Campos obrigatorios em branco");
        else
            alert.setHeaderText("Não foi possivel cadastrar");
        alert.setContentText(aviso);
        alert.show();
    }
    private void troca(int i){
        gui.trocaCena(i);
    }
    @FXML    private void hide(){
        this.lbl.setVisible(false);
        this.rbtnFuncionarioBurocratico.setVisible(false);
        this.rbtnFuncionarioCampo.setVisible(false);
        this.hideEspecialidades();
    }
    @FXML    private void show(){
        this.lbl.setVisible(true);
        this.rbtnFuncionarioBurocratico.setVisible(true);
        this.rbtnFuncionarioCampo.setVisible(true);
        
    }
    @FXML    private void showEspecialidades(){
        this.cbEpecialidade.setVisible(true);
    }
    @FXML    private void hideEspecialidades(){
        this.cbEpecialidade.setVisible(false);
    }
    private void limparBuffer(){
        this.hide();
        this.txtfCartao.setText("");
        this.txtfEMail.setText("");
        this.txtfLogin.setText("");
        this.pswfConfirmarSenha.setText("");
        this.pswfSenha.setText("");
        this.rbtnContaCliente.setSelected(false);
        this.rbtnContaFuncionario.setSelected(false);
        this.rbtnFuncionarioBurocratico.setSelected(false);
        this.rbtnFuncionarioCampo.setSelected(false);
    }
    private void carregaCB(){
        this.listasEspecialidade = FXCollections.observableArrayList(this.controle.getTodasEspecialidades());
        this.cbEpecialidade.setItems(listasEspecialidade);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.limparBuffer();
        this.carregaCB();
    }    
    
}
