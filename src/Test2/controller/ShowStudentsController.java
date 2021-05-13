package Test2.controller;


import Test2.DAO.StudentDAO;
import Test2.DAO.StudentDAOImpl;
import Test2.Student;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowStudentsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button returnToMain;

    @FXML
    private TableView<Student> studentsTable;

    @FXML
    private TableColumn<Student,String> firstNameColumn;

    @FXML
    private TableColumn<Student,String> lastNameColumn;

    @FXML
    private TableColumn<Student,String> patronymicColumn;

    @FXML
    private TableColumn<Student,String> birthDayColumn;

    @FXML
    private TableColumn<Student,String> groupColumn;

    @FXML
    private TableColumn<Student,Integer> uniqNumberColumn;

    @FXML
    void initialize() throws ClassNotFoundException{
        returnToMain.setOnAction(actionEvent -> {
            try{
                returnToMain.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("../view/main.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }

        });
        StudentDAO<Student, String> dbHandler = new StudentDAOImpl();
        ObservableList<Student> students = FXCollections.observableArrayList(
                dbHandler.findAllStudents()
        );
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("lastName"));
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("patronymic"));
        birthDayColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("birthDay"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("group"));
        uniqNumberColumn.setCellValueFactory(new PropertyValueFactory<Student,Integer>("uniqNumber"));
        studentsTable.setItems(students);

    }
}