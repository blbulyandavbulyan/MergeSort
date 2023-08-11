import org.blbulyandavbulyan.mergesort.files.iterator.LineIterator;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineIteratorTest {
    @Test
    public void testLineIterator() throws Exception{
        List<Integer> expected = TestUtils.generateRandomSortedList(0, 1000, 1000);
        String fileName = "specialtestfile.txt";
        File file = new File(fileName);
        //пишем данные в файл для тестов
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for(int i : expected){
                bufferedWriter.write(Integer.toString(i));
                bufferedWriter.newLine();
            }
        }
        List<Integer> actual = new ArrayList<>();
        try(LineIterator lineIterator = new LineIterator(fileName)){
            Iterable<String> stringIterable = ()->lineIterator;
            for(String line : stringIterable){
                actual.add(Integer.parseInt(line));
            }
        }
        file.delete();
        assertEquals(expected, actual);
    }
}
