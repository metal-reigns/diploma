/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Specification;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 *
 * @author Unlimited
 */
public class Variables
{

    static String type;
    static int idElement;
    static String saveXls = "c:\\нормы";

    static long startTime;
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;
    static Statement statement = null;
    static ResultSet resultSet = null;
    static ResultSetMetaData resultSetMetaData = null;

    static NumberFormat nf = new DecimalFormat("#.######");

    static final byte Элементы_1_IDЭлементов = 0;
    static final byte Дерево_Поз = 1;
    static final byte Элементы_1_Обозначение = 2;
    static final byte Элементы_1_Наименование = 3;
    static final byte Элементы_1_Примечание = 4;
    static final byte Дерево_Кол = 5;
    static final byte Материалы_IDМатериалов = 6;
    static final byte Материалы_Материал = 7;
    static final byte Формулы_IDФормул = 8;
    static final byte Элементы_Обозначение = 9;
    static final byte Элементы_1_а = 10;
    static final byte Элементы_1_б = 11;
    static final byte Элементы_1_в = 12;
    static final byte Элементы_1_г = 13;
    static final byte Элементы_1_д = 14;
    static final byte Элементы_1_е = 15;
    static final byte Элементы_1_НРМ = 16;
    static final byte Материалы_ЕИМ = 17;
    static final byte Формулы_ФормулаРасчета = 18;
    static final byte Формулы_а = 19;
    static final byte Формулы_б = 20;
    static final byte Формулы_в = 21;
    static final byte Формулы_г = 22;
    static final byte Формулы_д = 23;
    static final byte Формулы_е = 24;
    static final byte Цех_НазваниеЦеха = 25;
    static final byte Дерево_IDДерева = 26;
    static final byte Цех_1_НазваниеЦеха = 27;
    static final byte Элементы_1_КоэффициентЭ = 28;
    static final byte Материалы_КоэффициентМ = 29;
    static final byte Элементы_1_КТДЭ = 30;
    static final byte Элементы_1_ЕИЭ = 31;
    static final byte Материалы_КТДМ = 32;
    static final byte Элементы_1_Узел = 33;
    static final byte Выписано = 34;

    static final byte В_Дерево_Обозначение = 0;
    static final byte В_ЭлементыВспомогательные_IDЭлементовВспомогательных = 1;
    static final byte В_Вспомогательные_Вспомогательный = 2;
    static final byte В_ЭлементыВспомогательные_НРМ = 3;
    static final byte В_Вспомогательные_ЕИВ = 4;
    static final byte В_Цех_НазваниеЦеха = 5;
    static final byte В_Формулы_ФормулаРасчета = 6;
    static final byte В_Формулы_а = 7;
    static final byte В_ЭлементыВспомогательные_вспа = 8;
    static final byte В_Формулы_б = 9;
    static final byte В_ЭлементыВспомогательные_вспб = 10;
    static final byte В_Формулы_в = 11;
    static final byte В_ЭлементыВспомогательные_вспв = 12;
    static final byte В_Формулы_г = 13;
    static final byte В_ЭлементыВспомогательные_вспг = 14;
    static final byte В_Формулы_д = 15;
    static final byte В_ЭлементыВспомогательные_вспд = 16;
    static final byte В_Формулы_е = 17;
    static final byte В_ЭлементыВспомогательные_вспе = 18;
    static final byte В_Вспомогательные_КоэффициентВМ = 19;
    static final byte В_ЭлементыВспомогательные_КоэфВЭ = 20;
    static final byte В_Вспомогательные_IDВспомогательных = 21;
    static final byte В_Вспомогательные_КТДВ = 22;
}
