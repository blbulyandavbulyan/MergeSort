import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class TestUtils {
    private static final Random random = new Random();
    public static List<Integer> generateRandomSortedList(int min, int max, int count, Comparator<Integer> comparator){
        return Stream.generate(()->random.nextInt(min, max)).limit(count).sorted(comparator).toList();
    }
    public static List<Integer> generateRandomSortedList(int min, int max, int count){
        return generateRandomSortedList(min, max, count, Integer::compareTo);
    }
}
