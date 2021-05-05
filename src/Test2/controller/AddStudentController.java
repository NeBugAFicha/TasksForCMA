package Test2.controller;

import Test2.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddStudentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField patronymicField;

    @FXML
    private DatePicker birthDayField;

    @FXML
    private TextField groupField;

    @FXML
    private TextField uniqNumberField;

    @FXML
    private Button addStudent;
    @FXML
    private Button returnToMain;

    @FXML
    private Text someFail;

    @FXML
    private Text uniqStudentFail;

    @FXML
    void initialize() {
        addStudent.setOnAction(actionEvent -> {
            try {
                DatabaseHandler dbHandler = new DatabaseHandler();
                if(!uniqNumberField.getText().matches("[0-9]*")
                        || !dbHandler.checkUniqness(uniqNumberField.getText())
                        || firstNameField.getText().trim().equals("")
                        || lastNameField.getText().trim().equals("")
                        || patronymicField.getText().trim().equals("")
                        || groupField.getText().trim().equals("")
                ) throw new Exception();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Calendar birthDay = Calendar.getInstance();
                birthDay.set(birthDayField.getValue().getYear(),birthDayField.getValue().getMonth().getValue()-1,birthDayField.getValue().getDayOfMonth());
                Student student = new Student(firstNameField.getText().trim(), lastNameField.getText().trim(), patronymicField.getText().trim(), simpleDateFormat.format(birthDay.getTime()), groupField.getText(), Integer.parseInt(uniqNumberField.getText().trim()));
                dbHandler.addStudent(student);
                addStudent.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("../view/main.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                DatabaseHandler dbHandler = new DatabaseHandler();
                if(!dbHandler.checkUniqness(uniqNumberField.getText())){
                    someFail.setVisible(false);
                    uniqStudentFail.setVisible(true);
                }else {
                    someFail.setVisible(true);
                    uniqStudentFail.setVisible(false);
                }
                e.printStackTrace();
            }
        });
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
    }
}
