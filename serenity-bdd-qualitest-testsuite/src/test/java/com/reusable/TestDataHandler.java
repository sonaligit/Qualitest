
package com.reusable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class TestDataHandler {
	private static Map<String, CSVRecord> testDataRecordsMap = null;
	private static CSVParser csvParser;
	
	
	public static Map<String, CSVRecord> getDataInstance() {

		if (testDataRecordsMap != null) {
			return testDataRecordsMap;
		}

		try {
			String dataFilePath = null;
			dataFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
					+ File.separator + "resources" + File.separator + "testdata" + File.separator + "customer" + ".csv";

			testDataRecordsMap = new HashMap<String, CSVRecord>();
			Reader reader = new FileReader(dataFilePath);
			BufferedReader bufferReader = new BufferedReader(reader);
			StringBuilder builder = new StringBuilder();
			String line;
			while ((line = bufferReader.readLine()) != null) {
				builder.append(line.replace("~", ""));
				builder.append("\n");
			}

			final String csvData = builder.toString();
			csvParser = CSVParser.parse(csvData,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			
			for(CSVRecord csvRow: csvRecords) {
				//Accessing values by Header Name
				String serviceName = csvRow.get("data_id");
				testDataRecordsMap.put(serviceName, csvRow);
			}
			
			bufferReader.close();
			reader.close();
			return testDataRecordsMap;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				csvParser.close();
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

		return testDataRecordsMap;
	}

}
