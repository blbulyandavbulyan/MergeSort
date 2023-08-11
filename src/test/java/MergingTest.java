import org.blbulyandavbulyan.mergesort.Merging;
import org.blbulyandavbulyan.mergesort.startupparamters.SortMode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergingTest {
    private final Random random = new Random();
    private List<Integer> generateRandomSortedList(int min, int max, int count, Comparator<Integer> comparator){
        return Stream.generate(()->random.nextInt(min, max)).limit(count).sorted(comparator).toList();
    }
    private List<Integer> generateRandomSortedList(int min, int max, int count){
        return generateRandomSortedList(min, max, count, Integer::compareTo);
    }
    @Test
    public void randomDataTestAscendingSort(){
        List<Integer> a = generateRandomSortedList(-100, 200, 2000);
        List<Integer> b = generateRandomSortedList(-100, 200, 2000);
        List<Integer> c = generateRandomSortedList(100, 150, 200);
        List<Integer> actual = new ArrayList<>();
        Merging.merge(actual::add, List.of(a.iterator(), b.iterator(), c.iterator()), SortMode.ASCENDED);
        List<Integer> expected = new ArrayList<>();
        expected.addAll(a);
        expected.addAll(b);
        expected.addAll(c);
        expected.sort(Integer::compareTo);
        assertEquals(expected, actual);
    }
    @Test
    public void twoIteratorsForMergeAscendingSort(){
        List<Integer> a = generateRandomSortedList(-100, 200, 2000);
        List<Integer> b = generateRandomSortedList(-100, 200, 2000);
        List<Integer> actual = new ArrayList<>();
        Merging.merge(actual::add, List.of(a.iterator(), b.iterator()), SortMode.ASCENDED);
        List<Integer> expected = new ArrayList<>();
        expected.addAll(a);
        expected.addAll(b);
        expected.sort(Integer::compareTo);
        assertEquals(expected, actual);
    }
    @Test
    public void randomDataTestDescending(){
        List<Integer> a = generateRandomSortedList(-100, 200, 2000, Comparator.reverseOrder());
        List<Integer> b = generateRandomSortedList(-100, 200, 2000, Comparator.reverseOrder());
        List<Integer> c = generateRandomSortedList(100, 150, 200, Comparator.reverseOrder());
        List<Integer> actual = new ArrayList<>();
        Merging.merge(actual::add, List.of(a.iterator(), b.iterator(), c.iterator()), SortMode.DESCENDED);
        List<Integer> expected = new ArrayList<>();
        expected.addAll(a);
        expected.addAll(b);
        expected.addAll(c);
        expected.sort(Comparator.reverseOrder());
        assertEquals(expected, actual);
    }
    @Test
    public void twoIteratorsForMergeDescending(){
        List<Integer> a = generateRandomSortedList(-100, 200, 2000, Comparator.reverseOrder());
        List<Integer> b = generateRandomSortedList(-100, 200, 2000, Comparator.reverseOrder());
        List<Integer> actual = new ArrayList<>();
        Merging.merge(actual::add, List.of(a.iterator(), b.iterator()), SortMode.DESCENDED);
        List<Integer> expected = new ArrayList<>();
        expected.addAll(a);
        expected.addAll(b);
        expected.sort(Comparator.reverseOrder());
        assertEquals(expected, actual);
    }
}
