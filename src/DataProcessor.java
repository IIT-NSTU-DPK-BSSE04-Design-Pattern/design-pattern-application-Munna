// Abstract Processor Class
interface DataProcessor {
    String read(String filePath) throws Exception;
    Object parse(String data) throws Exception;
    void save(Object parsedData, String outputPath) throws Exception;
}