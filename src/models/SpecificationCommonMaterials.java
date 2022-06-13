/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Unlimited
 */
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SpecificationCommonMaterials
{ 
    private SimpleIntegerProperty В_Дерево_Обозначение;
    private SimpleIntegerProperty В_ЭлементыВспомогательные_IDЭлементовВспомогательных;
    private SimpleStringProperty В_Вспомогательные_Вспомогательный;
    private SimpleStringProperty В_ЭлементыВспомогательные_НРМ;
    private SimpleStringProperty В_Вспомогательные_ЕИВ;
    private SimpleStringProperty В_Цех_НазваниеЦеха;
    private SimpleStringProperty В_Формулы_ФормулаРасчета;
    private SimpleStringProperty В_Формулы_а;
    private SimpleStringProperty В_ЭлементыВспомогательные_вспа;
    private SimpleStringProperty В_Формулы_б;
    private SimpleStringProperty В_ЭлементыВспомогательные_вспб;
    private SimpleStringProperty В_Формулы_в;
    private SimpleStringProperty В_ЭлементыВспомогательные_вспв;
    private SimpleStringProperty В_Формулы_г;
    private SimpleStringProperty В_ЭлементыВспомогательные_вспг;
    private SimpleStringProperty В_Формулы_д;
    private SimpleStringProperty В_ЭлементыВспомогательные_вспд;
    private SimpleStringProperty В_Формулы_е;
    private SimpleStringProperty В_ЭлементыВспомогательные_вспе;
    private SimpleStringProperty В_Вспомогательные_КоэффициентВМ;
    private SimpleStringProperty В_ЭлементыВспомогательные_КоэфВЭ;
    private SimpleIntegerProperty В_Вспомогательные_IDВспомогательных;
    private SimpleStringProperty В_Вспомогательные_КТДВ;

    public SpecificationCommonMaterials(int В_Дерево_Обозначение, int В_ЭлементыВспомогательные_IDЭлементовВспомогательных, String В_Вспомогательные_Вспомогательный, String В_ЭлементыВспомогательные_НРМ, String В_Вспомогательные_ЕИВ, String В_Цех_НазваниеЦеха, String В_Формулы_ФормулаРасчета, String В_Формулы_а, String В_ЭлементыВспомогательные_вспа, String В_Формулы_б, String В_ЭлементыВспомогательные_вспб, String В_Формулы_в, String В_ЭлементыВспомогательные_вспв, String В_Формулы_г, String В_ЭлементыВспомогательные_вспг, String В_Формулы_д, String В_ЭлементыВспомогательные_вспд, String В_Формулы_е, String В_ЭлементыВспомогательные_вспе, String В_Вспомогательные_КоэффициентВМ, String В_ЭлементыВспомогательные_КоэфВЭ, int В_Вспомогательные_IDВспомогательных, String В_Вспомогательные_КТДВ)
    {
        this.В_Дерево_Обозначение = new SimpleIntegerProperty(В_Дерево_Обозначение);
        this.В_ЭлементыВспомогательные_IDЭлементовВспомогательных = new SimpleIntegerProperty(В_ЭлементыВспомогательные_IDЭлементовВспомогательных);
        this.В_Вспомогательные_Вспомогательный = new SimpleStringProperty(В_Вспомогательные_Вспомогательный);
        this.В_ЭлементыВспомогательные_НРМ = new SimpleStringProperty(В_ЭлементыВспомогательные_НРМ);
        this.В_Вспомогательные_ЕИВ = new SimpleStringProperty(В_Вспомогательные_ЕИВ);
        this.В_Цех_НазваниеЦеха = new SimpleStringProperty(В_Цех_НазваниеЦеха);
        this.В_Формулы_ФормулаРасчета = new SimpleStringProperty(В_Формулы_ФормулаРасчета);
        this.В_Формулы_а = new SimpleStringProperty(В_Формулы_а);
        this.В_ЭлементыВспомогательные_вспа = new SimpleStringProperty(В_ЭлементыВспомогательные_вспа);
        this.В_Формулы_б = new SimpleStringProperty(В_Формулы_б);
        this.В_ЭлементыВспомогательные_вспб = new SimpleStringProperty(В_ЭлементыВспомогательные_вспб);
        this.В_Формулы_в = new SimpleStringProperty(В_Формулы_в);
        this.В_ЭлементыВспомогательные_вспв = new SimpleStringProperty(В_ЭлементыВспомогательные_вспв);
        this.В_Формулы_г = new SimpleStringProperty(В_Формулы_г);
        this.В_ЭлементыВспомогательные_вспг = new SimpleStringProperty(В_ЭлементыВспомогательные_вспг);
        this.В_Формулы_д = new SimpleStringProperty(В_Формулы_д);
        this.В_ЭлементыВспомогательные_вспд = new SimpleStringProperty(В_ЭлементыВспомогательные_вспд);
        this.В_Формулы_е = new SimpleStringProperty(В_Формулы_е);
        this.В_ЭлементыВспомогательные_вспе = new SimpleStringProperty(В_ЭлементыВспомогательные_вспе);
        this.В_Вспомогательные_КоэффициентВМ = new SimpleStringProperty(В_Вспомогательные_КоэффициентВМ);
        this.В_ЭлементыВспомогательные_КоэфВЭ = new SimpleStringProperty(В_ЭлементыВспомогательные_КоэфВЭ);
        this.В_Вспомогательные_IDВспомогательных = new SimpleIntegerProperty(В_Вспомогательные_IDВспомогательных);
        this.В_Вспомогательные_КТДВ = new SimpleStringProperty(В_Вспомогательные_КТДВ);
    }


    public int getВ_Дерево_Обозначение()
    {
        return В_Дерево_Обозначение.get();
    }

    public void setВ_Дерево_Обозначение(int value)
    {
        В_Дерево_Обозначение.set(value);
    }

    public SimpleIntegerProperty В_Дерево_ОбозначениеProperty()
    {
        return В_Дерево_Обозначение;
    }

    public int getВ_ЭлементыВспомогательные_IDЭлементовВспомогательных()
    {
        return В_ЭлементыВспомогательные_IDЭлементовВспомогательных.get();
    }

    public void setВ_ЭлементыВспомогательные_IDЭлементовВспомогательных(int value)
    {
        В_ЭлементыВспомогательные_IDЭлементовВспомогательных.set(value);
    }

    public SimpleIntegerProperty В_ЭлементыВспомогательные_IDЭлементовВспомогательныхProperty()
    {
        return В_ЭлементыВспомогательные_IDЭлементовВспомогательных;
    }

    public String getВ_Вспомогательные_Вспомогательный()
    {
        return В_Вспомогательные_Вспомогательный.get();
    }

    public void setВ_Вспомогательные_Вспомогательный(String value)
    {
        В_Вспомогательные_Вспомогательный.set(value);
    }

    public SimpleStringProperty В_Вспомогательные_ВспомогательныйProperty()
    {
        return В_Вспомогательные_Вспомогательный;
    }

    public String getВ_ЭлементыВспомогательные_НРМ()
    {
        return В_ЭлементыВспомогательные_НРМ.get();
    }

    public void setВ_ЭлементыВспомогательные_НРМ(String value)
    {
        В_ЭлементыВспомогательные_НРМ.set(value);
    }

    public SimpleStringProperty В_ЭлементыВспомогательные_НРМProperty()
    {
        return В_ЭлементыВспомогательные_НРМ;
    }

    public String getВ_Вспомогательные_ЕИВ()
    {
        return В_Вспомогательные_ЕИВ.get();
    }

    public void setВ_Вспомогательные_ЕИВ(String value)
    {
        В_Вспомогательные_ЕИВ.set(value);
    }

    public SimpleStringProperty В_Вспомогательные_ЕИВProperty()
    {
        return В_Вспомогательные_ЕИВ;
    }

    public String getВ_Цех_НазваниеЦеха()
    {
        return В_Цех_НазваниеЦеха.get();
    }

    public void setВ_Цех_НазваниеЦеха(String value)
    {
        В_Цех_НазваниеЦеха.set(value);
    }

    public SimpleStringProperty В_Цех_НазваниеЦехаProperty()
    {
        return В_Цех_НазваниеЦеха;
    }

    public String getВ_Формулы_ФормулаРасчета()
    {
        return В_Формулы_ФормулаРасчета.get();
    }

    public void setВ_Формулы_ФормулаРасчета(String value)
    {
        В_Формулы_ФормулаРасчета.set(value);
    }

    public SimpleStringProperty В_Формулы_ФормулаРасчетаProperty()
    {
        return В_Формулы_ФормулаРасчета;
    }

    public String getВ_Формулы_а()
    {
        return В_Формулы_а.get();
    }

    public void setВ_Формулы_а(String value)
    {
        В_Формулы_а.set(value);
    }

    public SimpleStringProperty В_Формулы_аProperty()
    {
        return В_Формулы_а;
    }

    public String getВ_ЭлементыВспомогательные_вспа()
    {
        return В_ЭлементыВспомогательные_вспа.get();
    }

    public void setВ_ЭлементыВспомогательные_вспа(String value)
    {
        В_ЭлементыВспомогательные_вспа.set(value);
    }

    public SimpleStringProperty В_ЭлементыВспомогательные_вспаProperty()
    {
        return В_ЭлементыВспомогательные_вспа;
    }

    public String getВ_Формулы_б()
    {
        return В_Формулы_б.get();
    }

    public void setВ_Формулы_б(String value)
    {
        В_Формулы_б.set(value);
    }

    public SimpleStringProperty В_Формулы_бProperty()
    {
        return В_Формулы_б;
    }

    public String getВ_ЭлементыВспомогательные_вспб()
    {
        return В_ЭлементыВспомогательные_вспб.get();
    }

    public void setВ_ЭлементыВспомогательные_вспб(String value)
    {
        В_ЭлементыВспомогательные_вспб.set(value);
    }

    public SimpleStringProperty В_ЭлементыВспомогательные_вспбProperty()
    {
        return В_ЭлементыВспомогательные_вспб;
    }

    public String getВ_Формулы_в()
    {
        return В_Формулы_в.get();
    }

    public void setВ_Формулы_в(String value)
    {
        В_Формулы_в.set(value);
    }

    public SimpleStringProperty В_Формулы_вProperty()
    {
        return В_Формулы_в;
    }

    public String getВ_ЭлементыВспомогательные_вспв()
    {
        return В_ЭлементыВспомогательные_вспв.get();
    }

    public void setВ_ЭлементыВспомогательные_вспв(String value)
    {
        В_ЭлементыВспомогательные_вспв.set(value);
    }

    public SimpleStringProperty В_ЭлементыВспомогательные_вспвProperty()
    {
        return В_ЭлементыВспомогательные_вспв;
    }

    public String getВ_Формулы_г()
    {
        return В_Формулы_г.get();
    }

    public void setВ_Формулы_г(String value)
    {
        В_Формулы_г.set(value);
    }

    public SimpleStringProperty В_Формулы_гProperty()
    {
        return В_Формулы_г;
    }

    public String getВ_ЭлементыВспомогательные_вспг()
    {
        return В_ЭлементыВспомогательные_вспг.get();
    }

    public void setВ_ЭлементыВспомогательные_вспг(String value)
    {
        В_ЭлементыВспомогательные_вспг.set(value);
    }

    public SimpleStringProperty В_ЭлементыВспомогательные_вспгProperty()
    {
        return В_ЭлементыВспомогательные_вспг;
    }

    public String getВ_Формулы_д()
    {
        return В_Формулы_д.get();
    }

    public void setВ_Формулы_д(String value)
    {
        В_Формулы_д.set(value);
    }

    public SimpleStringProperty В_Формулы_дProperty()
    {
        return В_Формулы_д;
    }

    public String getВ_ЭлементыВспомогательные_вспд()
    {
        return В_ЭлементыВспомогательные_вспд.get();
    }

    public void setВ_ЭлементыВспомогательные_вспд(String value)
    {
        В_ЭлементыВспомогательные_вспд.set(value);
    }

    public SimpleStringProperty В_ЭлементыВспомогательные_вспдProperty()
    {
        return В_ЭлементыВспомогательные_вспд;
    }

    public String getВ_Формулы_е()
    {
        return В_Формулы_е.get();
    }

    public void setВ_Формулы_е(String value)
    {
        В_Формулы_е.set(value);
    }

    public SimpleStringProperty В_Формулы_еProperty()
    {
        return В_Формулы_е;
    }


    public String getВ_ЭлементыВспомогательные_вспе()
    {
        return В_ЭлементыВспомогательные_вспе.get();
    }

    public void setВ_ЭлементыВспомогательные_вспе(String value)
    {
        В_ЭлементыВспомогательные_вспе.set(value);
    }

    public SimpleStringProperty В_ЭлементыВспомогательные_вспеProperty()
    {
        return В_ЭлементыВспомогательные_вспе;
    }

    public String getВ_Вспомогательные_КоэффициентВМ()
    {
        return В_Вспомогательные_КоэффициентВМ.get();
    }

    public void setВ_Вспомогательные_КоэффициентВМ(String value)
    {
        В_Вспомогательные_КоэффициентВМ.set(value);
    }

    public SimpleStringProperty В_Вспомогательные_КоэффициентВМProperty()
    {
        return В_Вспомогательные_КоэффициентВМ;
    }

    public String getВ_ЭлементыВспомогательные_КоэфВЭ()
    {
        return В_ЭлементыВспомогательные_КоэфВЭ.get();
    }

    public void setВ_ЭлементыВспомогательные_КоэфВЭ(String value)
    {
        В_ЭлементыВспомогательные_КоэфВЭ.set(value);
    }

    public SimpleStringProperty В_ЭлементыВспомогательные_КоэфВЭProperty()
    {
        return В_ЭлементыВспомогательные_КоэфВЭ;
    }

    public int getВ_Вспомогательные_IDВспомогательных()
    {
        return В_Вспомогательные_IDВспомогательных.get();
    }

    public void setВ_Вспомогательные_IDВспомогательных(int value)
    {
        В_Вспомогательные_IDВспомогательных.set(value);
    }

    public SimpleIntegerProperty В_Вспомогательные_IDВспомогательныхProperty()
    {
        return В_Вспомогательные_IDВспомогательных;
    }

    public String getВ_Вспомогательные_КТДВ()
    {
        return В_Вспомогательные_КТДВ.get();
    }

    public void setВ_Вспомогательные_КТДВ(String value)
    {
        В_Вспомогательные_КТДВ.set(value);
    }

    public SimpleStringProperty В_Вспомогательные_КТДВProperty()
    {
        return В_Вспомогательные_КТДВ;
    }   
}
