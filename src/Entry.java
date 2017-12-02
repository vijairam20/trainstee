public class Entry {
    String name  ;
    String destination ;
    String origin ;
    String pnr ;
    int seats ;
    
    int cost ;
    /*Entry(String name , String destination , String origin , int seats )
    {
        this.name = name ;
        this.destination = destination ;
        this.origin = origin ;
        this.seats = seats ;
    }*/
    Entry(String name , String origin , String destination ,String pnr ,int seats , int cost )
    {
        this.name = name ;
        this.origin = origin;
        this.destination = destination;
        this.pnr = pnr ;
        this.seats = seats ;
        this.cost = cost ;
    }
    public String toString()
    {

        return name+"#"+origin+"#"+destination+"#"+pnr+"#"+seats+"#"+cost+"#";
    }
}
