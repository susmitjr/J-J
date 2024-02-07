package StepDefinitions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class Reader {
	
	public static List<Map<String, String>> readData(String filePath ,String testCaseName) throws CsvValidationException {
        
		List<Map<String, String>> records = new ArrayList<>();
//        File file = new File(filePath);
        
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath))
//                .withCSVParser(new CSVParserBuilder().withSeparator('|').build())
//                .build()
        		) {
        	
        	String hLine = csvReader.readLine();
        	String[] headers = hLine.split("\\|");
        	String line;
        	
        	while ((line = csvReader.readLine())!= null)
        	{
        		String [] values = line.split("\\|");
        		Map<String,String> testData = new HashMap<>();
        		if (values.length > 0 && values[0].trim().equalsIgnoreCase(testCaseName)) {
                  for (int i = 0; i < headers.length; i++) {
                	  String header = headers[i];
//      			String value = nextRecord[i];
//      			record.put(header, value);
                      testData.put(headers[i].trim(), values[i].trim());
                  }
                  records.add(testData);
                  break;  // Break the loop once the row is found
              }
//        		
//        		for (int i=0; i<headers.length; i++) {
////        			String header = headers[i];
////        			String value = nextRecord[i];
////        			record.put(header, value);
//        			testData.put(headers[i].trim(), values[i].trim());
//        		}
//        		records.add(testData);
        	}
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
	
//	@SuppressWarnings("unchecked")
//	public static List<Map<String, String>> readCsv(String filePath, String testCaseName) {
//        List<Map<String, String>> testData = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            // Read header
//            String headerLine = reader.readLine();
//            String[] headers = headerLine.split("\\|");
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] values = line.split("\\|");
//
//                // Find the row for the specified test case name
//                if (values.length > 0 && values[0].trim().equalsIgnoreCase(testCaseName)) {
//                    for (int i = 0; i < headers.length; i++) {
//                        testData.put(headers[i].trim(), values[i].trim());
//                    }
//                    break;  // Break the loop once the row is found
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return testData;
//    }
//	

}
