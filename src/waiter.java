import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class waiter {
    String csv = "src\\waitlist.csv";
    CSVWriter writerw ;
    CSVReader readerw ;
    waiter()
    {
        try {
            writerw = new CSVWriter(new FileWriter(csv, true));
            readerw = new CSVReader(new FileReader(csv));

        }catch(IOException e)
        {

        }
    }
    public void addEntries(Entry e)
    {
        String word = e.toString();
        String words[] = word.split("#");
        writerw.writeNext(words);
    }
    public ArrayList<Entry> readEntries()
    {
        String[] row;
        ArrayList<Entry> ent = new ArrayList<Entry>();
        try
        {
            List entries = readerw.readAll();
            for(Object o : entries)
            {
                row = (String[]) o ;
                // System.out.println(row.length);
                ent.add(new Entry(row[0],row[1],row[2],row[3],Integer.parseInt(row[4]),Integer.parseInt(row[5])));
            }
        }catch(IOException e)
        {

        }
        return ent ;
    }
}

