package Test2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.flywaydb.core.Flyway;

import java.io.IOException;

public class Test2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
        primaryStage.setTitle("Приложение для студентов");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) {
        var flyway = Flyway.configure()
                .dataSource(
                        "jdbc:mysql://localhost:3306/users?useUnicode=true&serverTimezone=UTC",
                        "root",
                        "12345678"
                        )
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        launch(args);
    }
}

