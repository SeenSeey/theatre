module com.example.theatre {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires org.hibernate.commons.annotations;


    opens com.example.theatre to javafx.fxml;
    opens com.example.theatre.Models to org.hibernate.orm.core;
    opens com.example.theatre.Controllers to javafx.fxml;
    exports com.example.theatre.Models;
    exports com.example.theatre.Controllers;
}

