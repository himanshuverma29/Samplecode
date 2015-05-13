/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.himanshu.qa.Util.FileUtil;

public class ExcelUtil {
	
	
	public void readData(String file,int sheet) throws IOException
	{
	
	File myFile = new File(file);
    FileInputStream fis = new FileInputStream(myFile);
    HSSFWorkbook myWorkBook = new HSSFWorkbook (fis);
    HSSFSheet mySheet = myWorkBook.getSheetAt(0);
    Iterator<Row> rowIterator = mySheet.iterator();
    while (rowIterator.hasNext()) {
        Row row = rowIterator.next();
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                System.out.print(cell.getStringCellValue() + "\t");
                break;
            case Cell.CELL_TYPE_NUMERIC:
                System.out.print(cell.getNumericCellValue() + "\t");
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                System.out.print(cell.getBooleanCellValue() + "\t");
                break;
            default :
            }
        }
    }
	}

	
	
	public static List<String> getPackagesAndClasses(String file, String testArray[]) throws IOException
	{
		
	File myFile = new File(file);
    FileInputStream fis = new FileInputStream(myFile);

    HSSFWorkbook myWorkBook = new HSSFWorkbook (fis);
    HSSFSheet mySheet = myWorkBook.getSheet(FileUtil.getConstantValue("ExcelTestMappingSheet"));
   int rows	= mySheet.getLastRowNum();
   List<String> packageClass = new ArrayList<String>();
   for(int i=0;i<testArray.length;i++)
   {
   for(int k=1;k<=rows;k++)
   {
	   Cell cell	=	mySheet.getRow(k).getCell(0);
	   String cellval = cell.toString();
	   if(cellval.equals(testArray[i]))
			   {
		           packageClass.add(mySheet.getRow(k).getCell(1).toString());
		           break;
			   }
   }
   }
   return packageClass;
	}
	}
