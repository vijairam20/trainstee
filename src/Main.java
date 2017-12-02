import java.util.Scanner;
import java.io.*;
import java.util.*;
import com.opencsv.*;
import org.apache.commons.lang3.*;
public class Main {
    Scanner in;
    CSVWriter writer;
    CSVReader reader;
    private ArrayList<String> stations;
    final String csv = "src\\inp.csv";
    final String csvl = "src\\list.csv";

    Main() {
        in = new Scanner(System.in);
        try {
            writer = new CSVWriter(new FileWriter(csv, true), '#');
            reader = new CSVReader(new FileReader(csv), '#');


            stations = new ArrayList<String>();
            stations.add("madrid");
            stations.add("cordoba");
            stations.add("seville");
            stations.add("malaga");
            stations.add("valencia");
            stations.add("alicante");
            stations.add("cartagena");
            stations.add("barcelona");
            stations.add("irun");
            stations.add("miranda");
            stations.add("bilbao");
            stations.add("valladolid");
            stations.add("santiago");
            stations.add("coimbra");
            stations.add("lisbon");
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public void input() {

        String inp;
        int c = 0;
        do {
            System.out.println("1:Sign In");
            System.out.println("2:Create new user");
            System.out.println("3:SearchByPNR");
            System.out.println("4:Exit");
            inp = in.next();
            c = Integer.parseInt(inp);
        } while (c > 4 || c < 1);
        if (c == 1) {
            String[] row;
            try {
                List content = reader.readAll();
                boolean flag = false;
                int l = 0;
                do {
                    if (l > 0) {
                        System.out.println("Please Try Again");
                    }
                    System.out.println("Enter Username");
                    String us = in.next();
                    System.out.println("Enter Password");
                    String ps = in.next();
                    for (Object object : content) {
                        row = (String[]) object;
                        if (row[0].equals(us) && row[1].equals(ps.trim())) {
                            User u = new User(row[3], row[0], row[1], row[2], Integer.parseInt(row[4]));

                            ask(u);

                            flag = true;
                        }
                    }
                    l++;
                } while (!flag);

            } catch (IOException e) {
                System.out.println("LOOOL");
            }
        } else if (c == 2) {
            String add = "";
            System.out.println("Enter Username :");
            String us = in.next();
            add += us;
            add += "#";
            System.out.println("Enter Password");
            String ps = in.next();
            add += ps;
            add += "#";
            System.out.println("Enter Email-id");
            String email = in.next();
            add += email;
            add += "#";
            System.out.println("Enter First Name");
            String name = in.next();
            System.out.println("Enter Last Name ");
            name += " ";
            name += in.next();
            System.out.println("");
            add += name;
            add += "#";
            add += "0";
            add += "#";
            try {


                String[] credential = add.split("#");

                writer.writeNext(credential);

                writer.close();
                input();
            } catch (IOException e) {
                System.out.println("LOOOOOOL");
            }
        } else if (c == 3) {
            System.out.println("Enter PNR : ");
            String pnr = in.next();
            hancsv h = new hancsv();
            ArrayList<Entry> ent = h.readEntries();
            for (Entry e : ent) {
                if (e.pnr.equalsIgnoreCase(pnr)) {
                    Graph g = new Graph();
                    System.out.println("Name: " + e.name);
                    boolean flag = g.process(stations.indexOf(e.origin), stations.indexOf(e.destination), 0);
                    System.out.println("No Seats :" + e.seats);
                }
            }
        } else if (c == 4) {
            System.exit(0);
        }
    }

    public void ask(User u) {
        u.id++;
        if (u.id == 1) {
            System.out.println("Welcome " + u.name);
        } else {
            System.out.println("Welcome back , " + u.name);
        }
        int c = 0;
        do {


            System.out.println("1:Book");
            System.out.println("2:Review");
            System.out.println("3:Cancel");
            System.out.println("4:View Details");
            System.out.println("5:Logout");
            String inp = in.next();
            c = Integer.parseInt(inp);
        } while (c < 0 && c > 5);
        if (c == 1) {
            String origin;
            String destination;
            System.out.println("         Santiago     Bilbao                       ");
            System.out.println("        ---|- Miranda  -| --------------Irun               ");
            System.out.println("        |         |                       |                ");
            System.out.println("     Coimbra      Valldolid               |                ");
            System.out.println("        |             |                   |                  ");
            System.out.println("     Lisbon         Madrid       ---- Barcelona                     ");
            System.out.println("                       |                   |                ");
            System.out.println("                       |________________Valencia               ");
            System.out.println("                       |                               ");
            System.out.println("                       |_________________Alicante             ");
            System.out.println("                       |                               ");
            System.out.println("                       |_________________Cartagena                ");
            System.out.println("     Seville ------ Cordoba                               ");
            System.out.println("                       |                              ");
            System.out.println("                     Malaga                                    ");

            int e = 0;
            do {
                if (e >= 1) {
                    System.out.println("Please Do Again");
                }
                System.out.println("Enter Origin");
                origin = in.next();
                e++;
            } while (stations.indexOf(origin.toLowerCase().trim()) == -1);
            e = 0;
            do {
                if (e >= 1) {
                    System.out.println("Please Do Again");
                }
                System.out.println("Enter Destination");
                destination = in.next();
                e++;
            } while (stations.indexOf(destination.toLowerCase().trim()) == -1);
            int seats = 0;
            do {
                System.out.println("Enter No of seats : ");
                seats = in.nextInt();
            }
            while (seats > 10);
            Graph g = new Graph();
            int o = 0;
            int d = 0;
            for (int i = 0; i < stations.size(); i++) {
                if (stations.get(i).equalsIgnoreCase(origin.trim())) {
                    o = i;
                    break;
                }
            }
            for (int i = 0; i < stations.size(); i++) {
                if (stations.get(i).equalsIgnoreCase(destination.trim())) {
                    d = i;
                    break;
                }
            }
            boolean checker = g.process(o, d, seats);
            if (checker) {
                String k = "";
                do {
                    System.out.println("Do you want to Purchase ? Yes/No");
                    k = in.next();
                } while (!(k.equalsIgnoreCase("yes")) && !(k.equalsIgnoreCase("no")));
                if (k.equalsIgnoreCase("Yes")) {
                    boolean flag = true;
                    if (flag) {
                        hancsv h = new hancsv();
                        String pnr = RandomStringUtils.random(2, true, false).toUpperCase();
                        pnr += RandomStringUtils.random(2, false, true);
                        h.addEntries(u, stations.get(o), stations.get(d), pnr, seats, 100);
                        System.out.println("PNR :" + pnr);

                        System.out.println();
                    }
                    ask(u);
                } else {
                    ask(u);
                }
            }
            {
                ask(u);
            }

        }


        else if(c == 2 )
        {
                hancsv h = new hancsv();
                ArrayList<Entry> ent = h.readEntries();
                int counter = 0 ;

                 if(ent.size() > 0 ) {
                     for (Entry e : ent) {
                         if (e.name.equalsIgnoreCase(u.name)) {
                             System.out.print(++counter);
                             System.out.print(":");
                             Graph temp = new Graph();
                             temp.process(stations.indexOf(e.origin), stations.indexOf(e.destination),0);

                         }
                     }
                 }
                 if(counter == 0 )
                 {
                     System.out.println("No Tickets have been booked ");
                 }
            System.out.println("Press B to Go Back");
            String check = in.next();
            ask(u);
        }
        else if(c == 5 )
        {
            input();
        }
        else if(c == 4)
        {
            u.print();
            System.out.println("Press y to Go Back");
            String check = in.next();
            ask(u);
        }
        else if(c == 3)
        {
            System.out.println("Which ticket do you want to cancel ?");
            hancsv han = new hancsv();
            ArrayList<Entry> ent = han.readEntries();
            ArrayList<Entry> cancels = new ArrayList<Entry>();
            if(ent.size()!=0) {
                int i = ent.size() - 1;
                while (i >= 0) {
                    if (ent.get(i).name.equalsIgnoreCase(u.name)) {
                        cancels.add(ent.get(i));
                    }
                    i--;
                }
                int counter = 0;
                for (Entry e : cancels) {
                    if (e.name.equalsIgnoreCase(u.name)) {
                        System.out.print(++counter);
                        System.out.print(":");
                        Graph temp = new Graph();
                temp.process(stations.indexOf(e.origin), stations.indexOf(e.destination),0);

                    }
                }
                String k = in.next();
                ent.remove(cancels.get(Integer.parseInt(k) - 1));
                counter = 0;
                if (ent.size() == 0) {
                    String blank[] = {"           "};
                    List<String[]> q = new ArrayList<String[]>();
                    han.reUpdate(q);
                } else {
                    List<String[]> q = new ArrayList<String[]>();

                    for (int p = 0; p < ent.size(); p++) {
                        Entry e = ent.get(p);
                        /*String[] entry = new String[4];
                        entry[0] = u.name;
                        entry[1] = e.origin ;
                        entry[2] = e.destination ;
                        entry[3] = ""+e.time;*/
                        q.add(e.toString().split("#"));
                    }
                    hancsv h = new hancsv();
                    if (!q.isEmpty())
                        h.reUpdate(q);
                }
            }
           else {
                System.out.println("No tickets to cancel");
            }
                ask(u);
        }
    }
    public boolean purchase()
    {
        boolean flag = false ;
        System.out.println("Enter last 4 digits : ");
        String inp = in.next();
        System.out.println("Enter CSV code");
        inp = in.next();
        do {
            System.out.println("Do you want to proceed : Y/N");
            inp = in.next();
        }while(!(inp.equalsIgnoreCase("y"))&&!(inp.equalsIgnoreCase("n")));
        if(inp.equalsIgnoreCase("y"))
        {
            System.out.println("Purchase completed");
            flag = true ;
        }
        else
        {
            System.out.println("Booking canceled");
        }
        return flag ;
    }
    public void clear(int n )
    {
        for(int i = 0 ; i < n ; i++ )
        {
            System.out.println();
        }
    }
    public void sleep( )
    {
        for(int i = 90000; i < Integer.MAX_VALUE;++i)
        {

        }
    }
public static void main(String[]args)
{
    System.out.println("                                     Trainster [Version 4.2]             ");
    System.out.println("                       (c) 2017 Trainster Corporation. All rights reserved.   ");
    System.out.println("                                      GoTo Free Edition                   ");
    Main m = new Main();
      m.input();


}

}
