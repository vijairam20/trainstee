import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
public class Graph {
    final int V = 15;
    final int seatCap = 100;
    ArrayList<Station> Stations = new ArrayList<Station>();
    int d[][] = new int[15][15];
    int t[][] = new int[15][15];
    CSVWriter writer;
    CSVReader reader;
    private final String csvw = "src\\waiter.csv";

    Graph() {
        //Madrid
        d[0][7] = 625;
        d[0][1] = 394;
        d[0][5] = 421;
        d[0][6] = 446;
        d[0][11] = 196;
        d[0][4] = 358;
        d[0][8] = 472;
        Stations.add(new Station("Madrid", 40.437616, -3.9597497));
        t[0][7] = 356;
        t[0][1] = 234;
        t[0][5] = 236;
        t[0][6] = 254;
        t[0][11] = 134;
        t[0][4] = 206;
        t[0][8] = 276;

        //Corodoba
        Stations.add(new Station("Cordoba", 37.8915652, -4.8545272));
        d[1][0] = 394;
        d[1][2] = 145;
        d[1][3] = 163;

        t[1][0] = 234;
        t[1][2] = 98;
        t[1][3] = 112;

        //Seville
        Stations.add(new Station("Seville", 37.3752881, -6.0951501));
        d[2][1] = 145;

        t[2][1] = 98;

        //Malaga
        Stations.add(new Station("Malaga", 36.7645035, -4.7044057));
        d[3][1] = 163;

        t[1][3] = 112;

        //Valencia
        Stations.add(new Station("Valencia", 39.4077012, -0.5016027));
        d[4][0] = 358;
        d[4][7] = 350;

        t[4][0] = 206;
        t[4][7] = 223;

        //Alicante
        Stations.add(new Station("Alicante", 38.35782, -0.542566));
        d[5][0] = 421;
        t[5][0] = 236;

        // Cartagena
        Stations.add(new Station("Cartagena", 37.6174065, -1.0235273));
        d[6][0] = 446;
        t[6][0] = 254;

        //Barcelona
        Stations.add(new Station("Barcelona", 41.3947051, 2.0086769));
        d[7][0] = 625;
        d[7][4] = 350;
        d[7][8] = 565;

        t[7][0] = 356;
        t[7][4] = 223;
        t[7][8] = 346;

        //Irun
        Stations.add(new Station("Irun", 43.3198578, -1.8608425));
        d[8][7] = 565;
        d[8][0] = 472;
        d[8][13] = 831;
        d[8][9] = 151;

        t[8][7] = 346;
        t[8][0] = 276;
        t[8][13] = 491;
        t[8][9] = 106;

        //Miranda de Ebro
        Stations.add(new Station("Miranda", 42.6849665, -2.9746307));
        d[9][8] = 151;
        d[9][10] = 82;
        d[9][11] = 207;
        d[9][12] = 584;

        t[9][8] = 106;
        t[9][10] = 61;
        t[9][11] = 129;
        t[9][12] = 353;

        //Bilbao
        Stations.add(new Station("Bilbao", 43.2633021, -3.0036059));
        d[10][9] = 82;

        t[10][9] = 61;

        //valladoid
        Stations.add(new Station("Valladolid", 41.7029131, -5.0890239));
        d[11][9] = 207;
        d[11][0] = 196;

        t[11][9] = 129;
        t[11][0] = 134;

        //Santiago de Comsetelo
        Stations.add(new Station("Santiago", 42.8801836, -8.6148112));
        d[12][9] = 584;

        t[12][9] = 353;

        //Coimbra
        Stations.add(new Station("Coimbra", 40.2286755, -8.5563655));
        d[13][8] = 831;
        d[13][14] = 203;
        t[13][8] = 491;
        t[13][14] = 121;
        //Libson
        Stations.add(new Station("Lisbon", 38.7436057, -9.2302451));

        d[14][13] = 203;
        t[14][13] = 121;

    }

    void initCSV() {
        try {
            writer = new CSVWriter(new FileWriter(csvw, false));

            for (int i = 0; i < V; i++) {
                String t = i + 1 + "#" + seatCap;
                writer.writeNext(t.split("#"));
            }
            writer.close();

        } catch (IOException e) {

        }
    }

    int minDistance(int dist[], Boolean sptSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    ArrayList<Station> printPath(int parent[], int src, int j) {
        ArrayList<Station> sts = new ArrayList<Station>();

        while (parent[j] != -1) {
            sts.add(Stations.get(j));
            //System.out.println(j);
            j = parent[j];
        }
        sts.add(Stations.get(src));
        return sts;
    }

    ArrayList<Station> dijkstra(int Stations[][], int src, int end, boolean flag) {
        int dist[] = new int[V];
        int parent[] = new int[V];

        Boolean visted[] = new Boolean[V];


        for (int i = 0; i < V; i++) {
            parent[i] = -1;
            dist[i] = Integer.MAX_VALUE;
            visted[i] = false;
        }


        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, visted);

            visted[u] = true;


            for (int v = 0; v < V; v++)


                if (!visted[v] && Stations[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + Stations[u][v] < dist[v]) {
                    dist[v] = dist[u] + Stations[u][v];
                    parent[v] = u;
                }
        }

        ArrayList<Station> sts = printPath(parent, src, end);
        if (flag) {
            System.out.println("Time taken : " + (int) (dist[end] / 60) + ":" + dist[end] % 60);
        }

        return sts;
    }

    boolean process(int start, int end, int seats) {
        if (checkAvailability()) {
          //   if(true){
            ArrayList<Station> route = dijkstra(d, start, end, false);
            System.out.println();
            int dist = 0;
            System.out.print(route.get(0).getName());
            System.out.print("->");

            for (int i = 1; i < route.size() - 1; i++) {
                System.out.print(route.get(i).getName());
                System.out.print("->");

            }
            System.out.println(route.get(route.size() - 1).getName());
            ArrayList<Station> time = dijkstra(t, start, end, true);
            return true;
        } else {
            System.out.println("TICKET not available");
            System.out.println("DO you want to be added to the waiting list ?");
            System.out.println("Yes or NO");
            Scanner in = new Scanner(System.in);
            String inp = in.next();
            if (inp.equalsIgnoreCase("yes")) {
                System.out.println("ADDED to the waitlist");
            } else if (inp.equalsIgnoreCase("no")) {
                System.out.println("NOT Added to the waitlist ");
            }
            return false;
        }
    }

    int dijkstra(int Stations[][], int src, int end) {
        int dist[] = new int[V];
        int parent[] = new int[V];

        Boolean visted[] = new Boolean[V];


        for (int i = 0; i < V; i++) {
            parent[i] = -1;
            dist[i] = Integer.MAX_VALUE;
            visted[i] = false;
        }


        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, visted);

            visted[u] = true;


            for (int v = 0; v < V; v++)


                if (!visted[v] && Stations[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + Stations[u][v] < dist[v]) {
                    dist[v] = dist[u] + Stations[u][v];
                    parent[v] = u;
                }
        }


        //  System.out.println("Time taken : " + (int)(dist[end] / 60) + ":" + dist[end] % 60);


        return (int) (dist[end] / 60) + dist[end] % 60;
    }


    boolean checkAvailability() {
        ArrayList<String> stations = new ArrayList<String>();
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
        hancsv h = new hancsv();
        ArrayList<Entry> ent = h.readEntries();
        ArrayList<Station> ws = new ArrayList<Station>();
        for (Entry e : ent) {

            ArrayList<Station> sts = dijkstra(d, stations.indexOf(e.origin), stations.indexOf(e.destination), false);
            for (Station t : sts) {
                for (int i = 0; i < e.seats; i++) {
                    ws.add(t);
                }
            }
        }
        int avail[] = new int[V];
        for (int i = 0; i < V; i++) {
            avail[i] = 10;
        }
        for (Station q : ws) {
            avail[stations.indexOf(q.getName().toLowerCase())] -= 1;
        }
        for (int i = 0; i < stations.size(); i++) {
            //      System.out.println(stations.get(i)+":"+avail[i]);
        }
        for (int i = 0; i < V; i++) {
            if (avail[i] <= 0) {
                return false;
            }
        }
        return true;
    }

}