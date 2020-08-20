import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Utils {

    private static final Random random = new Random();

    public static List<Passenger> generateRandomCountOfPassengers(int count,
                                                                  int exclusiveStation,
                                                                  List<Integer> stationList) {
        List<Integer> destinationStationList = new ArrayList<>(stationList);
        if (stationList.contains(exclusiveStation)){
            destinationStationList.remove(stationList.indexOf(exclusiveStation));
        }
        List<Passenger> passengers = new ArrayList<>();
        IntStream.range(0, count).forEach(i -> passengers.add(generateRandomPassenger(destinationStationList)));
        return passengers;
    }

    private static Passenger generateRandomPassenger(List<Integer> stationList){
        int randomIndex = random.nextInt(stationList.size());
        return new Passenger(stationList.get(randomIndex));
    }

}
