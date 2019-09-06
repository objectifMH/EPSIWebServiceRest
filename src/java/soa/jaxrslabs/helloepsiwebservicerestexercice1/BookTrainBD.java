package soa.jaxrslabs.helloepsiwebservicerestexercice1;

import soa.jaxrslabs.helloepsiwebservicerestexercice1.BookTrain;
import java.util.ArrayList;
import java.util.List;

public class BookTrainBD {

    private static List<Train> trains = new ArrayList<Train>();
    private static List<BookTrain> bookTrains = new ArrayList<BookTrain>();

    static {
        trains.add(new Train("TR123", "Poitiers", "Paris", 1250));
        trains.add(new Train("TR127", "Poitiers", "Paris", 1420));
        trains.add(new Train("TR129", "Poitiers", "Paris", 1710));
        trains.add(new Train("TR129", "Montpellier", "Paris", 1710));
        trains.add(new Train("TR129", "Lille", "Paris", 1710));
        trains.add(new Train("TR129", "Lyon", "Paris", 1710));
        trains.add(new Train("TR129", "Montpellier", "Paris", 1710));
        
        
    }

    public static List<Train> getTrains() {
        System.out.println(" les trains : > " + trains);
        for ( Train t : trains )
        {
            System.out.println(t.toString());
        }
        return trains;
    }

    public static List<BookTrain> getBookTrains() {
        return bookTrains;
    }
}
