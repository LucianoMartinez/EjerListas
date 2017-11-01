package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SimpleList implements Initializable {
    @FXML
    Button insertarbtn,buscarbtn,eliminarbtn,terminarbtn;
    @FXML
    TextField InsertarTxt,BuscarTxt,EliminarTxt;
    @FXML
    Label ContarLbl;
    @FXML
    FlowPane RepresentacionGraficaFlpn;
    private Nodo Cabeza;// Nodo princial para poder guardar la lista
    private int Index = 0;// Esta variable calcula el tamaño de la lista
    boolean actionResult = false;// Verifica si el valor ingresado esta en la lista, de lo contrario dara falso
    Stage primaryStage;// Esta instancia cierra la ventana principal
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Boton para insertar o meter objetos a la lista
        this.insertarbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InserrtarNodo();
            }
        });
        // Boton para buscar un valor en la lista
        this.buscarbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BucarNodo();
            }
        });
        // Boton para borrar un valor de la lista
        this.eliminarbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EliminarNodo();
            }
        });
        // Boton que cierra la interfaz
        this.terminarbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage = (Stage) terminarbtn.getScene().getWindow();
                primaryStage.close();
            }
        });

    }
    // Metodo que inserta los valores dados por el usuario en la lista
    public void InserrtarNodo(){
        if (Cabeza == null){
            Cabeza = new Nodo(InsertarTxt.getText());
        }else{
            Nodo tempo = Cabeza;
            Nodo nuevo = new Nodo(InsertarTxt.getText());
            nuevo.Unir(tempo);
            Cabeza = nuevo;
        }
        Index++;
        LlenarFlowPane();
    }
    // Metodo que elimina el valor ingresado por el usuario
    public void EliminarNodo() {
        Nodo tempo = Cabeza;
        while (tempo != null) {
            tempo = tempo.ObtenerSiuiente();
            if (tempo.ObtenerValor().toString().equals(EliminarTxt.getText())) {
                tempo.Unir(tempo.ObtenerSiuiente().ObtenerSiuiente());
                Index--;
                LlenarFlowPane();
            }

        }
    }
    // Metodo para introducir los modos en forma de cadena dentro de un contenedor
    public void LlenarFlowPane(){
        RepresentacionGraficaFlpn.getChildren().clear();
        Nodo Temporal = Cabeza;
        for (int i=0;i<Index;i++){
            System.out.println(Temporal.ObtenerValor().toString());
            HBox NF = new HBox();
            ImageView Flecha = null;
            try {
                File archivo = new File("src/sample/Derecha.png");
                System.out.println(archivo.toURI().toURL().toString());
                Image imagen = new Image(archivo.toURI().toURL().toString());
                Flecha = new ImageView(imagen);

            }catch (Exception e){
            }
            Label nodo = new Label(Temporal.ObtenerValor().toString());
            nodo.setStyle("-fx-font-size:18px;");
            NF.getChildren().add(nodo);
            if (Flecha!=null)  NF.getChildren().add(Flecha);
            RepresentacionGraficaFlpn.getChildren().add(NF);
            Temporal = Temporal.ObtenerSiuiente();
        }
        ContarLbl.setText(Index + "");
    }
    // Metodo que busca y resalta con un color diferente el valor intregsado por el usuario
    public void BucarNodo(){
        RepresentacionGraficaFlpn.getChildren().clear();
        Nodo Temporal = Cabeza;
        for (int i=0;i<Index;i++){
            System.out.println(Temporal.ObtenerValor().toString());
            HBox NF = new HBox();
            ImageView Flecha = null;
            try {
                File archivo = new File("src/sample/Derecha.png");
                //System.out.println(archivo.toURI().toURL().toString());
                Image imagen = new Image(archivo.toURI().toURL().toString());
                Flecha = new ImageView(imagen);

            }catch (Exception e){
            }
            Label nodo = new Label(Temporal.ObtenerValor().toString());
            nodo.setStyle("-fx-font-size:18px;");
            if (Temporal.ObtenerValor().toString().equals(BuscarTxt.getText())) {
                nodo.setStyle("-fx-background-color: #0ff");
                System.out.println("-fx-background-color: #0ff");
            }
            NF.getChildren().add(nodo);
            if (Flecha!=null)  NF.getChildren().add(Flecha);
            RepresentacionGraficaFlpn.getChildren().add(NF);
            Temporal = Temporal.ObtenerSiuiente();
        }
    }
}
