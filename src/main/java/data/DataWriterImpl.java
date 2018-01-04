package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

public class DataWriterImpl implements DataWriter {
    public void writeCsv(List<BigDecimal> toWrite, String name, String kind) {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new File( name + "_" + kind + ".csv"));
        } catch (FileNotFoundException e) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (BigDecimal t : toWrite) {
            sb.append(t.toString());
            sb.append("\n");
        }
        pw.write(sb.toString());
        pw.close();
    }
}
