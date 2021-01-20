package com.paco.javafxobjectdb;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.persistence.*;


public class PrimaryController implements Initializable{

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnInsertar;
    @FXML
    private Button btnRefrescar;
    @FXML
    private TableView<models.Contacto> tabla;
    @FXML
    private TableColumn<models.Contacto, String> cNombre;
    @FXML
    private TableColumn<models.Contacto, String> cEmail;
    @FXML
    private TableColumn<models.Contacto, String> cTelefono;
    
    ObservableList<models.Contacto> agenda;

    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void insertar(ActionEvent event) {
        
        models.Contacto c = new models.Contacto(txtNombre.getText(), txtTelefono.getText(), txtEmail.getText());

        EntityManagerFactory emf = ObjectDBUtil.getEntityManagerFactory();
        System.out.println(emf);
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(c);
        System.out.println("commiting...");
        em.getTransaction().commit();
        
        txtNombre.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        
        cargar();
    }
    
    private void cargar(){
        EntityManagerFactory emf = ObjectDBUtil.getEntityManagerFactory();
        System.out.println(emf);
        EntityManager em = emf.createEntityManager();

        /* Obtengo todos los contactos de la agenda */
        TypedQuery<models.Contacto> query = em.createQuery("SELECT c FROM Contacto c", models.Contacto.class);
        List<models.Contacto> results = query.getResultList();
        
        agenda.clear();
        agenda.addAll(results);
    }

    @FXML
    private void refrescar(ActionEvent event) {
        cargar();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agenda = FXCollections.observableArrayList();

        cNombre.setCellValueFactory((TableColumn.CellDataFeatures<models.Contacto, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getNombre()));
        cTelefono.setCellValueFactory((TableColumn.CellDataFeatures<models.Contacto, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getTelefono()));
        cEmail.setCellValueFactory((TableColumn.CellDataFeatures<models.Contacto, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getEmail()));

        tabla.setItems(agenda);
        
        cargar();
    }
}
