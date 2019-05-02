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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ruben
 */
public class ComprarController implements Initializable {
    
    @FXML
    private TextField id_compra;
    @FXML
    private TextField origen_compra;
    @FXML
    private TextField destino_compra;
    @FXML
    private TextField fecha_compra;
    @FXML
    private TextField hora_compra;
    @FXML
    private TextField num_asientos_compra;
    @FXML
    private TextField precio_compra;
    
    @FXML
    public void comprar(ActionEvent event) throws IOException{
        
        String cuentaOrigen = "6555820782511769";
        String cuentaDestino = "9266261271333690";
        
        String id = id_compra.getText().toString();
        String origen = origen_compra.getText().toString();
        String destino = destino_compra.getText().toString();
        String fecha = fecha_compra.getText().toString();
        String hora = hora_compra.getText().toString();
        int asientos = Integer.parseInt(num_asientos_compra.getText().toString());
        int precio = Integer.parseInt(precio_compra.getText().toString());
        
        comprarBoleto_1(id,origen,destino,fecha,hora,asientos,precio);
        transaccion(cuentaOrigen, cuentaDestino,precio_compra.getText().toString());
        
            Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
            dialogo.setTitle("Boleto comprado");
            dialogo.setHeaderText(null);
            dialogo.setContentText("Boleto comprado cerrar ventana");
            dialogo.initStyle(StageStyle.UTILITY);
            dialogo.showAndWait();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private static boolean comprarBoleto_1(java.lang.String id, java.lang.String origen, java.lang.String destino, java.lang.String fecha, java.lang.String hora, int numAsientos, int precio) {
        controlador.SWTransporteAdministrador service = new controlador.SWTransporteAdministrador();
        controlador.SWTransporte port = service.getSWTransportePort();
        return port.comprarBoleto(id, origen, destino, fecha, hora, numAsientos, precio);
    }

    private static String transaccion(java.lang.String noCuentaOrigen, java.lang.String noCuentaDestino, java.lang.String monto) {
        registro.pasarela.Pasarela_Service service = new registro.pasarela.Pasarela_Service();
        registro.pasarela.Pasarela port = service.getPasarelaPort();
        return port.transaccion(noCuentaOrigen, noCuentaDestino, monto);
    }
    
}
