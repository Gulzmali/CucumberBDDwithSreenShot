package util;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;

public class ExcelUtils {


    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("/Users/gulzhanamalikova/Desktop/StudentsName.xls");
        Workbook workbook = new HSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet2");

        int rowCount = sheet.getPhysicalNumberOfRows();

//to get only first
//          for(int i=0; i<rowCount;i++){
//              System.out.println(sheet.getRow(i).getCell(0).toString());
//
//          }
//            System.out.println(cell.toString());

//        for (int i = 0; i < rowCount; i++) {
//            String name = sheet.getRow(i).getCell(0).toString();
//            String groups = sheet.getRow(i).getCell(1).toString();
//            System.out.println(name + " | " + groups);
//
//        }
//
//        System.out.println(cell.toString());



//        System.out.println(" =================Which can be devided by 5%=======================================");
//
//        for (int i = 1; i < rowCount; i++) {
//            String name = sheet.getRow(i).getCell(0).toString();
//            String groups = sheet.getRow(i).getCell(1).toString();
//            double groupNum= Double.parseDouble(groups);
//
//            if(groupNum%5==0)
//            System.out.println(name);
//
//        }

        System.out.println("========SecondSheet======");


        for (int i = 1; i < rowCount; i++) {
            String model = sheet.getRow(i).getCell(0).toString();
            String year = sheet.getRow(i).getCell(1).toString();
            String color= sheet.getRow(i).getCell(2).toString();
            double numYear= Double.parseDouble(year);

            if(model.equalsIgnoreCase("bmw"))
                model+="\t";

            if(numYear>2018 &&color.equalsIgnoreCase("black"))
                System.out.println(model + "\t | \t" +year.substring(0,year.length()-2)+"\t | \t"+ color);

        }











    }
}




