/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import static controllers.Variables.*;
import java.sql.SQLException;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aavhimovich
 */
public class ParametersController implements Initializable
{

    @FXML
    private JFXTextField jfxtextfieldLength;
    @FXML
    private JFXTextField jfxtextfieldWidth;
    @FXML
    private JFXTextField jfxtextfieldNomberOfLayers;

    double a;
    double b;
    double c;
    double result1;
    double result2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        jfxtextfieldLength.setOnKeyPressed((event) ->
        {

            if (event.getCode() == KeyCode.ENTER)
            {
                jfxtextfieldWidth.requestFocus();
            }
        });
        jfxtextfieldWidth.setOnKeyPressed((event) ->
        {
            if (event.getCode() == KeyCode.ENTER)
            {
                jfxtextfieldNomberOfLayers.requestFocus();
            }
        });
        jfxtextfieldNomberOfLayers.setOnKeyPressed((event) ->
        {
            if (event.getCode() == KeyCode.ENTER)
            {
                try
                {
                    a = Double.parseDouble(jfxtextfieldLength.getText().replace(",", "."));
                    b = Double.parseDouble(jfxtextfieldWidth.getText().replace(",", "."));
                    c = Double.parseDouble(jfxtextfieldNomberOfLayers.getText().replace(",", "."));
                    result1 = (a * b * 0.5 * c) / 1000000;
                    result2 = (a * b * 0.1) / 1000000;
                    if (type == "Bar")
                    {
                        insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа,вспб,вспв, НРМ)\n"
                                + "VALUES(?,414,4," + a + "," + b + "," + c + "," + result1 + ");", idElement);
                        insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                + "VALUES(?,333,4," + result1 + "," + result1 / 5 + ");", idElement);
                    }
                    else
                    {
                        insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа,вспб,вспв, НРМ)\n"
                                + "VALUES(?,414,4," + a + "," + b + "," + c + "," + result1 + ");", idElement);
                        insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                + "VALUES(?,333,4," + result1 + "," + result1 / 5 + ");", idElement);
                        insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                + "VALUES(?,78,4, " + a + "," + (a / 1000) * 2 + ");", idElement);
                        if ((a * b) / 1000000 > 6.25)
                        {
                            insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                    + "VALUES(?,63,4," + 1 + "," + 1 + ");", idElement);
                            insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                    + "VALUES(?,415,4," + 0.5 + "," + 0.5 + ");", idElement);
                            insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                    + "VALUES(?,358,4," + 2 + "," + (0.3 * 2) + ");", idElement);
                            insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа,вспб,вспв, НРМ)\n"
                                    + "VALUES(?,1511421945,4," + a + "," + b + "," + 1 + "," + result2 + ");", idElement);
                            insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа,вспб,вспв, НРМ)\n"
                                    + "VALUES(?,1511421945,8," + a + "," + b + "," + 1 + "," + result2 + ");", idElement);

                        }
                        else
                        {
                            insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                    + "VALUES(?,63,4," + 0.5 + "," + 0.5 + ");", idElement);
                            insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                    + "VALUES(?,415,4," + 0.25 + "," + 0.25 + ");", idElement);
                            insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                    + "VALUES(?,358,4," + 1 + "," + (0.3 * 1) + ");", idElement);
                            insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа,вспб,вспв, НРМ)\n"
                                    + "VALUES(?,1511421945,4," + a + "," + b + "," + 1 + "," + result2 + ");", idElement);
                            insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа,вспб,вспв, НРМ)\n"
                                    + "VALUES(?,1511421945,8," + a + "," + b + "," + 1 + "," + result2 + ");", idElement);
                        }
                    }
                }
                catch (java.lang.NumberFormatException exception)
                {
                    Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                    stage.close();
                }

                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.close();

            }
        });
    }

    private void insertCommonElementToDb(String sql, int id)
    {

        try
        {
            if (connection == null)
            {
                System.out.println("Нет соединения с БД!");
                System.exit(0);
            }
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

}
