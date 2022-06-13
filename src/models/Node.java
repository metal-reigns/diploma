/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author aavhimovich
 */
public class Node
{
    private final SimpleIntegerProperty заказы_idЗаказа;
    private final SimpleStringProperty заказы_Заказ;
    private final SimpleStringProperty заказы_Узел;

    public Node(int заказы_idЗаказа, String заказы_Заказ, String заказы_Узел)
    {
        this.заказы_idЗаказа = new SimpleIntegerProperty(заказы_idЗаказа);
        this.заказы_Заказ = new SimpleStringProperty(заказы_Заказ);
        this.заказы_Узел = new SimpleStringProperty(заказы_Узел);
    }

    public int getЗаказы_idЗаказа()
    {
        return заказы_idЗаказа.get();
    }

    public void setЗаказы_idЗаказа(int value)
    {
        заказы_idЗаказа.set(value);
    }

    public SimpleIntegerProperty заказы_idЗаказаProperty()
    {
        return заказы_idЗаказа;
    }

    public String getЗаказы_Заказ()
    {
        return заказы_Заказ.get();
    }

    public void setЗаказы_Заказ(String value)
    {
        заказы_Заказ.set(value);
    }

    public SimpleStringProperty заказы_ЗаказProperty()
    {
        return заказы_Заказ;
    }

    public String getЗаказы_Узел()
    {
        return заказы_Узел.get();
    }

    public void setЗаказы_Узел(String value)
    {
        заказы_Узел.set(value);
    }

    public SimpleStringProperty заказы_УзелProperty()
    {
        return заказы_Узел;
    }
    
    
}
