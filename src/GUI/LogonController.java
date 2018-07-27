/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Rodemarck Jr
 */
public class LogonController implements Initializable {
    GUI gui = new GUI();
    Controle controle = new Controle();
    @FXML    public JFXTextField txtfLogin;
    @FXML    public JFXPasswordField pswfSenha;
    private int x=15;
    /**
     * Initializes the controller class.
     */
    @FXML    private void logar(){
       fazerLogin();
       //this.controle.script(0);
       //this.script();
    }
    @FXML    private void cadastrar(){
        this.limparBuffer();
        this.troca(1);
    }
    private void troca(int i){
        gui.trocaCena(i);
    }

    public void fazerLogin(){
        boolean check=true;
        String aviso="";
        if(this.txtfLogin.getText().length()==0){
            check=false;
            aviso+="Campo obrigatorio:login\n";
        }
        if(this.pswfSenha.getText().length()==0){
            check=false;
            aviso+="Campo obrigatorio:senha\n";
        }
        if(check){
            if(this.txtfLogin.getText().contains("@")){
                if(controle.fazerLoginPorEmail(this.txtfLogin.getText(),this.pswfSenha.getText())){
                    this.limparBuffer();
                    this.troca(2);
                }
                else
                    chamaAlert("senha ou e-mail invalidos",false);
            }
            else if(controle.fazerLogin(this.txtfLogin.getText(),this.pswfSenha.getText()))
                this.troca(2);
            else
                chamaAlert("senha ou login invalidos",false);
        }
        else
            chamaAlert(aviso,true);
    }
    private void chamaAlert(String aviso,boolean check){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("falha no prcesso de acesso  ");
        if(check)
            alert.setHeaderText("Campos obrigatorios em branco");
        else
            alert.setHeaderText("Não foi possivel acessar");
        alert.setContentText(aviso);
        alert.show();
    }
    public void teclar(KeyEvent e){
        
    }
    public void limparBuffer(){
        this.pswfSenha.setText("");
        this.txtfLogin.setText("");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.limparBuffer();
    }    
    
}
