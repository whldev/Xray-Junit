package com.planittesting.jupitertoys.support;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.planittesting.jupitertoys.model.data.ContactDetails;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvDataProvider {

    public static Object[][] feedData(String dataPath) throws IOException {
        List<Object[]> records = new ArrayList<Object[]>();
        String record;
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(dataPath), "UTF-8"));
        //read the first line
        file.readLine();
        while ((record = file.readLine()) != null) {
            String fields[] = record.split(",");
            records.add(fields);
        }
        file.close();

        Object[][] results = new Object[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            results[i] = records.get(i);
        }
        return results;
    }

    //using jackson
    public static List<ContactDetails> readCsv(String filePath) throws IOException {
        List<ContactDetails> records = new ArrayList<>();

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(',');
        ObjectReader objectReader = csvMapper.reader(schema);

        Reader reader = new FileReader(filePath);
        MappingIterator<ContactDetails> mappingIterator = objectReader.forType(ContactDetails.class).readValues(reader);
        while (mappingIterator.hasNext()) {
            ContactDetails contactDetails = mappingIterator.next();
            if (contactDetails != null) {
                records.add(contactDetails);
            }
        }

        return records;
    }
}

