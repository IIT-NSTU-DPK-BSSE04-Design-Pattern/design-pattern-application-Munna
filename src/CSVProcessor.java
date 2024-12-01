import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// CSV Processor
class CSVProcessor implements DataProcessor {
    @Override
    public String read(String filePath) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public Object parse(String data) {
        List<List<String>> parsedData = new ArrayList<>();
        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] values = line.split(",");
            parsedData.add(Arrays.asList(values));
        }
        return parsedData;
    }

    @Override
    public void save(Object parsedData, String outputPath) throws Exception {
        try (PrintWriter pw = new PrintWriter(new FileWriter(outputPath))) {
            List<List<String>> rows = (List<List<String>>) parsedData;
            for (List<String> row : rows) {
                pw.println(String.join(",", row));
            }
        }
    }
}
