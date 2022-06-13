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
public class CommonMaterials
{

    private final SimpleIntegerProperty Вспомогательные_IDВспомогательных;
    private final SimpleStringProperty Вспомогательные_КТДВ;
    private final SimpleStringProperty Вспомогательные_Вспомогательный;
    private final SimpleStringProperty Вспомогательные_ЕИВ;
    private final SimpleStringProperty Формулы_Формула;

    public CommonMaterials(int Вспомогательные_IDВспомогательных, String Вспомогательные_КТДВ, String Вспомогательные_Вспомогательный, String Вспомогательные_ЕИВ, String Формулы_Формула)
    {
        this.Вспомогательные_IDВспомогательных = new SimpleIntegerProperty(Вспомогательные_IDВспомогательных);
        this.Вспомогательные_КТДВ = new SimpleStringProperty(Вспомогательные_КТДВ);
        this.Вспомогательные_Вспомогательный = new SimpleStringProperty(Вспомогательные_Вспомогательный);
        this.Вспомогательные_ЕИВ = new SimpleStringProperty(Вспомогательные_ЕИВ);
        this.Формулы_Формула = new SimpleStringProperty(Формулы_Формула);
    }

    public int getВспомогательные_IDВспомогательных()
    {
        return Вспомогательные_IDВспомогательных.get();
    }

    public void setВспомогательные_IDВспомогательных(int value)
    {
        Вспомогательные_IDВспомогательных.set(value);
    }

    public SimpleIntegerProperty Вспомогательные_IDВспомогательныхProperty()
    {
        return Вспомогательные_IDВспомогательных;
    }

    public String getВспомогательные_КТДВ()
    {
        return Вспомогательные_КТДВ.get();
    }

    public void setВспомогательные_КТДВ(String value)
    {
        Вспомогательные_КТДВ.set(value);
    }

    public SimpleStringProperty Вспомогательные_КТДВProperty()
    {
        return Вспомогательные_КТДВ;
    }

    public String getВспомогательные_Вспомогательный()
    {
        return Вспомогательные_Вспомогательный.get();
    }

    public void setВспомогательные_Вспомогательный(String value)
    {
        Вспомогательные_Вспомогательный.set(value);
    }

    public SimpleStringProperty Вспомогательные_ВспомогательныйProperty()
    {
        return Вспомогательные_Вспомогательный;
    }

    public String getВспомогательные_ЕИВ()
    {
        return Вспомогательные_ЕИВ.get();
    }

    public void setВспомогательные_ЕИВ(String value)
    {
        Вспомогательные_ЕИВ.set(value);
    }

    public SimpleStringProperty Вспомогательные_ЕИВProperty()
    {
        return Вспомогательные_ЕИВ;
    }

    public String getФормулы_Формула()
    {
        return Формулы_Формула.get();
    }

    public void setФормулы_Формула(String value)
    {
        Формулы_Формула.set(value);
    }

    public SimpleStringProperty Формулы_ФормулаProperty()
    {
        return Формулы_Формула;
    }
 
}
