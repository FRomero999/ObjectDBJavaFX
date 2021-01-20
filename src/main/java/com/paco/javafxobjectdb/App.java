package com.paco.javafxobjectdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * JavaFX App 1.0
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 300, 480);
        stage.setScene(scene);
        stage.setTitle("Agenda personal");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
    
        //EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("p.odb");
        //EntityManager em = entityManagerFactory.createEntityManager();
        //EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        /*TypedQuery<models.Contacto> query = em.createQuery("SELECT c FROM Contacto c", models.Contacto.class);
        List<models.Contacto> results = query.getResultList();
        System.out.println(results);
        */
        launch();
    }

}
