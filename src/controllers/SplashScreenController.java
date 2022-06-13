/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author aavhimovich
 */
public class SplashScreenController implements Initializable
{

    @FXML
    private StackPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        new SplashScreen().start();
    }

    class SplashScreen extends Thread
    {
        
        @Override
        public void run()
        {
            try
            {
                Thread.sleep(5000);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Platform.runLater(() ->
            {

                Parent root = null;
                try
                {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("views/MainView.fxml"));
                }
                catch (IOException ex)
                {
                    Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("resources/icon.png")));
                stage.setScene(scene);
                scene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
                stage.show();

                stage.setOnCloseRequest(new EventHandler<WindowEvent>()
                {
                    @Override
                    public void handle(WindowEvent event)
                    {
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
                        if (result.get() == buttonTypeYes)
                        {
                            System.exit(0);
                        }
                    }
                });

                rootPane.getScene().getWindow().hide();

            });

        }

    }
}
