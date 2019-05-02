/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agencia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mx.agencia.Usuario;

/**
 *
 * @author ruben
 */
public class LoginController implements Initializable {
    
    @FXML
    private TextField txf_usuario;
    
    @FXML
    private TextField txf_contrasena;
    
    @FXML
    private void btn_iniciarSesion(ActionEvent event) throws IOException {
        
        String usuario = txf_usuario.getText().toString();
        String contrasena = txf_contrasena.getText().toString();
        
        if(login(usuario,contrasena)!=null){
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Rutas.fxml"));
            Parent root1= (Parent)fxmlLoader.load();
            Stage stage= new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Lista de rutas");
            stage.show();
        }else{
            Alert dialogo = new Alert(AlertType.INFORMATION);
            dialogo.setTitle("Usuario o Contraseña incorrectos");
            dialogo.setHeaderText(null);
            dialogo.setContentText("Usuario o Contraseña inccorectos");
            dialogo.initStyle(StageStyle.UTILITY);
            dialogo.showAndWait();
        };
    }
    
    @FXML
    private void btn_crearCuenta(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Registrar.fxml"));
        Parent root1= (Parent)fxmlLoader.load();
        Stage stage= new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Crear una cuenta");
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private static Usuario login(java.lang.String username, java.lang.String password) {
        mx.agencia.ClientesServer_Service service = new mx.agencia.ClientesServer_Service();
        mx.agencia.ClientesServer port = service.getClientesServerPort();
        return port.login(username, password);
    }
    
}
