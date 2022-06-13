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
 * @author Unlimited
 */
public class Orders
{

    private final SimpleIntegerProperty Заказы_idЗаказа;
    private final SimpleStringProperty Заказы_Заказ;


    public Orders(int Заказы_idЗаказа, String Заказы_Заказ)
    {
        this.Заказы_idЗаказа = new SimpleIntegerProperty(Заказы_idЗаказа);
        this.Заказы_Заказ = new SimpleStringProperty(Заказы_Заказ);
    }

    public int getЗаказы_idЗаказа()
    {
        return Заказы_idЗаказа.get();
    }

    public void setЗаказы_idЗаказа(int value)
    {
        Заказы_idЗаказа.set(value);
    }

    public SimpleIntegerProperty Заказы_idЗаказаProperty()
    {
        return Заказы_idЗаказа;
    }

    public String getЗаказы_Заказ()
    {
        return Заказы_Заказ.get();
    }

    public void setЗаказы_Заказ(String value)
    {
        Заказы_Заказ.set(value);
    }

    public SimpleStringProperty Заказы_ЗаказProperty()
    {
        return Заказы_Заказ;
    }
}
