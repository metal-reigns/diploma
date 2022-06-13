/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alex
 */
public class DBConnection
{

    private static Connection connection;

    public static boolean connect()
    {/*
        Scanner fileIn = null;
        try
        {
            fileIn = new Scanner(new File(System.getProperty("user.dir") + File.separator + "connection.txt"));
        }
        catch (FileNotFoundException ex)
        {
            
        }*/
        Boolean isConnected = false;
        try
        {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/snm", "root3", "root3");

            if (!connection.isClosed())
            {
                //System.out.println("Connection established");
                isConnected = true;
            }
        }
        catch (SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return isConnected;
    }

    public static Connection getConnection()
    {
        return connection;
    }

}
