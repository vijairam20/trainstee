import com.opencsv.*;

import java.io.FileReader;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
public class hancsv {
    String csv = "src\\wait.csv";
    CSVWriter writerl;

    public void addEntries(User u, String origin, String destination, String pnr , int seats , int cost ) {
        try {
            writerl = new CSVWriter(new FileWriter(csv, true));
            String line = u.name;
            line += "#";
            line += origin;
            line += "#";
            line += destination;
            line += "#";
            line += pnr;
            line += "#";
            line += seats;
            line += "#";
            line += cost ;
            line += "#";

            String[] entries = line.split("#");
            writerl.writeNext(entries);

            writerl.close();
        } catch (IOException e) {

        }
    }

    public ArrayList<Entry> readEntries() {
        ArrayList<Entry> ent = new ArrayList<Entry>();
        try {
            String[] row;
            CSVReader readerl = new CSVReader(new FileReader(csv), ',');
            List entries = readerl.readAll();
            for (Object o : entries) {
                row = (String[]) o;
                ent.add(new Entry(row[0],row[1],row[2],row[3],Integer.parseInt(row[4]),Integer.parseInt(row[5])));
            }
        } catch (IOException e) {

        }
        catch (IndexOutOfBoundsException e)
        {
            return  ent ;
        }
        return ent;
    }

    public void reUpdate(List<String[]> q) {
        try {
            writerl = new CSVWriter(new FileWriter(csv, false));
            writerl.writeAll(q);
            writerl.close();
        } catch (IOException e) {

        }
    }

}
