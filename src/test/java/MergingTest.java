import org.blbulyandavbulyan.mergesort.Merging;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergingTest {
    private final Random random = new Random();
    private List<Integer> generateRandomSortedList(int min, int max, int count){
        return Stream.generate(()->random.nextInt(min, max)).limit(count).sorted().toList();
    }
    @Test
    public void randomDataTest(){
        List<Integer> a = generateRandomSortedList(-100, 200, 2000);
        List<Integer> b = generateRandomSortedList(-100, 200, 2000);
        List<Integer> c = generateRandomSortedList(100, 150, 200);
        List<Integer> actual = new ArrayList<>();
        Merging.merge(actual::add, List.of(a.iterator(), b.iterator(), c.iterator()));
        List<Integer> expected = new ArrayList<>();
        expected.addAll(a);
        expected.addAll(b);
        expected.addAll(c);
        expected.sort(Integer::compareTo);
        assertEquals(expected, actual);
    }
    @Test
    public void twoIteratorsForMerge(){
        List<Integer> a = generateRandomSortedList(-100, 200, 2000);
        List<Integer> b = generateRandomSortedList(-100, 200, 2000);
        List<Integer> actual = new ArrayList<>();
        Merging.merge(actual::add, List.of(a.iterator(), b.iterator()));
        List<Integer> expected = new ArrayList<>();
        expected.addAll(a);
        expected.addAll(b);
        expected.sort(Integer::compareTo);
        assertEquals(expected, actual);
    }
}
