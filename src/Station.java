public class Station {

        //Location adr ;
        private String name;
        double x , y ;
        //Location rep ;
        int SeatCapcity = 10  ;
        private int vertex ;
        Station(String name , double d , double e )
        {
            this.name = name ;
            this.x = d ;
            this.y = e ;
         //   rep = new Location(d, e);
        }
        public int getVertex() {
            return vertex;
        }
        public void setVertex(int vertex) {
            this.vertex = vertex;
        }

        public String getName()
        {
            return name;
        }
        /*public Location getLocation()
        {
            return rep;
        }*/
        public int getSeatCapcity()
        {
            return getSeatCapcity();
        }

    }

