/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agencia;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ruben
 */
public class RegistrarController implements Initializable {
    
    @FXML
    private TextField txf_id;
    
    @FXML
    private TextField txf_nombre;
    
    @FXML
    private TextField txf_usuario;

    @FXML
    private TextField txf_contrasena;
    
    @FXML
    private void btn_crearCuenta(ActionEvent event) {
        int id = Integer.parseInt(txf_id.getText().toString());
        String nombre = txf_nombre.getText().toString();
        String usuario = txf_usuario.getText().toString();
        String contrasena = txf_contrasena.getText().toString();
        register(id,nombre,usuario,contrasena);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private static int register(int id, java.lang.String name, java.lang.String password, java.lang.String username) {
        mx.agencia.ClientesServer_Service service = new mx.agencia.ClientesServer_Service();
        mx.agencia.ClientesServer port = service.getClientesServerPort();
        return port.register(id, name, password, username);
    }
    
}
