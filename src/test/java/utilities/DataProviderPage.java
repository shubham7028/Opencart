package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderPage  {

	@DataProvider(name="dp")
	public String [][]Getdata() 							throws IOException
	{	
		String fiPath="./testdata//Opencart.xlsx"; //taking xl file from TestData
		UtilityFile ufile=new UtilityFile(fiPath); //creating an object for Utility file
		int rows=ufile.getRowCount("Sheet1");
		int cols=ufile.GetCellCount("Sheet1", 1);
		String data[][]=new String[rows][cols];	  //creating 2dimensional Array which can store 
		for(int r=1;r<=rows;r++)				 //read the data from XlSheet & 											
		{										//storing in 2dimensional Array
			for(int c=0;c<cols;c++)
			{
				data[r-1][c]=ufile.GetCellData("Sheet1", r, c);//Array index start from 0...
			}
		}
		return data;	//returning two dimensional array...		
	}
}
