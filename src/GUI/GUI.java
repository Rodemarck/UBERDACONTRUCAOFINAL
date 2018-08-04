/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.



        testandu
 */
package GUI;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Rodemarck Jr
 */
public class GUI extends Application {
    
    private static ArrayList<Stage> tela = new ArrayList<Stage>();
    private static ArrayList<Scene> cenas = new ArrayList<Scene>();
    private Controle controle = new Controle();
    private boolean segundaTela=false;
    @Override
    public void start(Stage primaryStage) throws Exception {
        for(int x=0;x<3;x++){
            tela.add(new Stage());
            tela.get(x).setTitle("UBER DA CONSTRUCAO");
            tela.get(x).setResizable(false);
        }
        criaCena("Logon.fxml");
        criaCena("Cadastro.fxml");
        criaCena("Central.fxml");
        criaCena("Historico.fxml");
        criaCena("Suporte.fxml");
        criaCena("MeusTrabalhos.fxml");
        criaCena("Profile.fxml");
        criaCena("TelaConfirmar.fxml");
        criaCena("TelaNovoTrabalho.fxml");
//        criaCena("TelaTrabalhoNovo.fxml");
        tela.get(0).setScene(cenas.get(0));
        tela.get(0).show();
    }
    
    private void criaCena(String cena) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource(cena));
        Scene scene= new Scene(root);
        cenas.add(scene);
    }
    protected  void trocaCena(int i){
        if(i<cenas.size() )
            tela.get(0).setScene(cenas.get(i));
    }
    protected  void segundaTelaAbre(int i){
        if(i < cenas.size()){
            if(this.segundaTela)
                tela.get(1).hide();
            tela.get(1).setScene(cenas.get(i));
            tela.get(1).show();
        }
    }
    protected  void terceiraTelaAbre(int i){
        if(i < cenas.size()){
            tela.get(2).hide();
            tela.get(2).setScene(cenas.get(i));
            tela.get(2).show();
        }
    }
    protected void segundaTelaFecha(){
        tela.get(1).hide();
    }
    protected void terceiraTelaFecha(){
        tela.get(2).hide();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
