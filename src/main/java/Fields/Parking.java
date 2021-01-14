package Fields;

public class Parking extends Field{
    public Parking (int id){
        super(id);
        setParking(true);
    }

    boolean parking;

    public void setParking(boolean parking) {
        this.parking = parking;
    }
}
