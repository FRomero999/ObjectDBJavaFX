module com.paco.javafxobjectdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires javax.persistence;
    requires java.sql;

    opens com.paco.javafxobjectdb to javafx.fxml, java.sql;
    exports com.paco.javafxobjectdb;
    
    opens models;
}
