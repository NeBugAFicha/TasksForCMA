package Test2.controller;

import Test2.DAO.StudentDAO;
import Test2.DAO.StudentDAOImpl;
import Test2.Test2;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField studentUniqNumberField;

        @FXML
        private Button addStudentButton;

        @FXML
        private Button deleteStudentButton;

        @FXML
        private Button showStudentsButton;

        @FXML
        private Text successDelete;

        @FXML
        private Text failDelete;



        @FXML
        void initialize() throws IOException {
                addStudentButton.setOnAction(actionEvent -> {
                        addStudentButton.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/addStudent.fxml"));
                        try {
                                loader.load();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                });
                showStudentsButton.setOnAction(actionEvent -> {
                        try {
                                showStudentsButton.getScene().getWindow().hide();
                                Parent root = FXMLLoader.load(getClass().getResource("../view/showStudents.fxml"));
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.show();
                        }catch (IOException e){
                                e.printStackTrace();
                        }
                });
                deleteStudentButton.setOnAction(actionEvent -> {
                        StudentDAO dbHandler = new StudentDAOImpl();
                        if(dbHandler.deleteStudentByUniqNumber(studentUniqNumberField.getText())){
                                successDelete.setVisible(true);
                                failDelete.setVisible(false);
                        }
                        else {
                                failDelete.setVisible(true);
                                successDelete.setVisible(false);
                        }
                        dbHandler.close();
                });

        }


}
