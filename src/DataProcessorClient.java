

public class DataProcessorClient {
    public static void main(String[] args) {
        try {
            DataProcessor processor;

            // Example with CSV
            processor = new CSVProcessor();
            String csvData = processor.read("data.csv");
            Object parsedCsvData = processor.parse(csvData);
            processor.save(parsedCsvData, "output.csv");

            // Example with JSON
            processor = new JSONProcessor();
            String jsonData = processor.read("data.json");
            Object parsedJsonData = processor.parse(jsonData);
            processor.save(parsedJsonData, "output.json");

            // Example with XML
            processor = new XMLProcessor();
            String xmlData = processor.read("data.xml");
            Object parsedXmlData = processor.parse(xmlData);
            processor.save(parsedXmlData, "output.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
