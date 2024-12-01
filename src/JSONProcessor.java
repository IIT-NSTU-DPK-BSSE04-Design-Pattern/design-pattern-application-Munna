import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

class JSONProcessor implements DataProcessor {
    @Override
    public String read(String filePath) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }

    @Override
    public Object parse(String data) {
        return new JSONObject(data);
    }

    @Override
    public void save(Object parsedData, String outputPath) throws Exception {
        try (PrintWriter pw = new PrintWriter(new FileWriter(outputPath))) {
            JSONObject jsonObject = (JSONObject) parsedData;
            pw.write(jsonObject.toString(4));
        }
    }
}