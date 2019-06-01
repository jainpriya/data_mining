/********************************DATA MINING ASSIGNMENT -2|||||||K-NEAREST NEIGHBOUR CLASSIFIER***************************************************
 Submitted by:Priya Jain
 RollNo:22

PreRequisites: Apache poi library installed in system
			   Java compliance level=1.7
			   
Input:Training data set of 49 records and 5 attributes along with the classes in excel file
 	   Test data
Output:Category in which the test data is segregated

Approach:Follows K-Nearest neighbour algorith with K=10;
				Hamming distance is calcualted to gather near data points. 
				Majority vote is calculated using weight of the distance i.e less the distance more the weight along with the probability of occurence of a particular category
		
Data Structures Used: 1) Priority queue to hold the category distance pair in ascending order
					  2) Hash Map to calculate the probablity of occurence of each category in 10 nearest data points
					  3)Hash map to calculate the majority vote of each category
*/

//necessary imports
import java.util.Scanner;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Input_Data 
{
	public static final String SAMPLE_XLSX_FILE_PATH = "DM_data.xlsx";

	public static void main(String[] args) throws IOException, InvalidFormatException {

	        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
	        System.out.println("Setting up training data set................");
	        System.out.println("Disclaimer:The above classiffication is made on the basis of the training data provided");
	        double[][] training_data=new double[49][6];//subject to change depending upon records and attributes
	        Sheet sheet = workbook.getSheetAt(0);
	        System.out.println("\n\nSetting up values......\n");
	        Iterator<Row> rowIterator = sheet.rowIterator();
	        int i=0;
	        while (rowIterator.hasNext()&&i<49) {
	            Row row = rowIterator.next();
	            int j=0;
	            Iterator<Cell> cellIterator = row.cellIterator();

	            while (cellIterator.hasNext()&&j<=5) {
	                Cell cell = cellIterator.next();
	                double cellValue = cell.getNumericCellValue();
	                training_data[i][j]=cellValue;
	                j++;
	            }
	            i++;
	        }
	        	        
	        for(int array_row=0;array_row<49;array_row++) 
	        { 
	        	for(int array_column=0;array_column<6;array_column++) 
	        	{
	        	System.out.printf("%-16f",training_data[array_row][array_column]);
	       	    } 
	        System.out.println(); 
	       	 }
	        Scanner input=new Scanner(System.in);

	        System.out.println("***************WELCOME TO K-MEANS CLASSIFIER***************************");
	        char option='y';
	        do{
	        	double array_input[]=new double[6];
	        	i=0;
	        	System.out.println("We'll be conducting a survey to let you know your category");
	        	System.out.println("Age:");
	        	array_input[i]=input.nextDouble();
	        	i++;
	        	System.out.println("BMI:");
	        	array_input[i]=input.nextDouble();
	        	i++;
	        	System.out.println("Glucose:");
	        	array_input[i]=input.nextDouble();
	        	i++;
	        	System.out.println("Leptin:");
	        	array_input[i]=input.nextDouble();
	        	i++;
	        	System.out.println("MCP.1:");
	        	array_input[i]=input.nextDouble();
	        	i++;
	        	
	        	System.out.println("Calculating your category.....");
	        	
	        	Classifier C=new Classifier();
	        	array_input[i]=C.k_means(training_data,array_input);
	        	System.out.println("Category:"+array_input[i]);
	        	System.out.println("Do you wish to continue...press y|Y");
	        	option=input.next().charAt(0);
	        }while(option=='y'||option=='Y');
	        input.close();
	        System.out.println("****************Exiting Classifier**************");
	}
}


