/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agencia;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ruben
 */
public class RutasController implements Initializable {

    @FXML
    private ComboBox cbb_origenes;
    
    @FXML
    private ComboBox cbb_destinos;
    
    @FXML
    private ListView listV_rutas;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String json1 = listaOrigenes();
        JsonParser parser1 = new JsonParser();
        // Obtain Array
        JsonArray gsonArr1 = parser1.parse(json1).getAsJsonArray();
        // for each element of array
        for (JsonElement obj : gsonArr1) {
            // Object of array
            JsonObject gsonObj1 = obj.getAsJsonObject();
            // Primitives elements of object
            String origenes = gsonObj1.get("origenC").getAsString();
            System.out.println(origenes);
            cbb_origenes.getItems().addAll(origenes);
        }    
        
        String json2 = listaDestinos();
        JsonParser parser2 = new JsonParser();
        // Obtain Array
        JsonArray gsonArr2 = parser2.parse(json2).getAsJsonArray();
        // for each element of array
        for (JsonElement obj : gsonArr2) {
            // Object of array
            JsonObject gsonObj2 = obj.getAsJsonObject();
            // Primitives elements of object
            String destinos = gsonObj2.get("destinoC").getAsString();
            System.out.println(destinos);
            cbb_destinos.getItems().addAll(destinos);
        }        
    }    
    
    @FXML
    public void buscarRutas(ActionEvent event){
        
        listV_rutas.getItems().clear();
        
        String origenComb = cbb_origenes.getValue().toString();
        String destinoComb = cbb_destinos.getValue().toString();
        System.out.println(origenComb+" "+destinoComb);
        
        String jsonCamion = buscarCamion(origenComb,destinoComb);
        JsonParser parser2 = new JsonParser();
        // Obtain Array
        JsonArray gsonArr2 = parser2.parse(jsonCamion).getAsJsonArray();
        // for each element of array
        for (JsonElement obj : gsonArr2) {
            // Object of array
            JsonObject gsonObj2 = obj.getAsJsonObject();
            // Primitives elements of object
            String id = gsonObj2.get("id").getAsString();
            String origen = gsonObj2.get("origen").getAsString();
            String destino = gsonObj2.get("destino").getAsString();
            String fecha = gsonObj2.get("fecha").getAsString();
            String hora = gsonObj2.get("hora").getAsString();
            String num_asientos = gsonObj2.get("num_asientos").getAsString();
            int precio = gsonObj2.get("precio").getAsInt();
            String precioConv = Integer.toString(precio);
            String ruta = id+"      "+origen+"      "+destino+"     "+fecha+"       "+hora+"        "+num_asientos+"        "+precioConv;
            System.out.println(id+origen+destino+fecha+hora+num_asientos+precio);
            listV_rutas.getItems().addAll(ruta);
            listV_rutas.getItems().addAll("");
        }    
    }
    
    @FXML
    public void comprarBoleto(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Comprar.fxml"));
            Parent root1= (Parent)fxmlLoader.load();
            Stage stage= new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Comprar boleto");
            stage.show();
    }

    private static String listaOrigenes() {
        controlador.SWTransporteAdministrador service = new controlador.SWTransporteAdministrador();
        controlador.SWTransporte port = service.getSWTransportePort();
        return port.listaOrigenes();
    }

    private static String listaDestinos() {
        controlador.SWTransporteAdministrador service = new controlador.SWTransporteAdministrador();
        controlador.SWTransporte port = service.getSWTransportePort();
        return port.listaDestinos();
    }

    private static String buscarCamion(java.lang.String origen, java.lang.String destino) {
        controlador.SWTransporteAdministrador service = new controlador.SWTransporteAdministrador();
        controlador.SWTransporte port = service.getSWTransportePort();
        return port.buscarCamion(origen, destino);
    }
}
