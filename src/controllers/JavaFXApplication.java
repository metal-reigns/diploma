package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static controllers.Variables.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @midivisana
 */
public class JavaFXApplication extends Application {

    private Locale locale = new Locale("ru");

    @Override
    public void start(Stage stage) throws Exception {
     /*   
        File file = new File("D:\\logo.png");
        if (file.exists())
        {
            Parent root = null;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("views/SplashScreenView.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("style/style.css");
            stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("resources/logo.png")));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }
        else
        {*/
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getClassLoader().getResource("views/MainView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        //Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("resources/icon.png")));
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Подтверждение");
                alert.setHeaderText(null);
                alert.setContentText("Вы действительно хотите выйти?");
                alert.initStyle(StageStyle.UTILITY);
                alert.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("resources/icon.png").toString()));

                ButtonType buttonTypeYes = new ButtonType("Да");
                ButtonType buttonTypeNo = new ButtonType("Нет");
                ButtonType buttonTypeClose = new ButtonType("Закрыть", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeClose);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes) {
                    System.exit(0);
                }
            }
        });
        /*}*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        DBConnection.connect();
        connection = DBConnection.getConnection();
        launch(args);
    }
}
