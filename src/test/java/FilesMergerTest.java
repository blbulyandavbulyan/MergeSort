import org.blbulyandavbulyan.mergesort.files.FilesMerger;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilesMergerTest {
    @Test
    public void testMergeFiles() throws FileNotFoundException {
        Function<String, String> fileNameResolver = (s) -> this.getClass().getResource(s).getFile();
        List<String> fileNames = List.of(
                fileNameResolver.apply("/1in.txt"),
                fileNameResolver.apply("/2in.txt"),
                fileNameResolver.apply("/3in.txt")
        );
        FilesMerger filesMerger = new FilesMerger(fileNames);
        List<Integer> expected = fileNames.stream().flatMap(fn-> {
            try {
                return Files.readAllLines(Path.of(fn)).stream().map(Integer::parseInt);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).sorted().toList();
        List<Integer> actual = new ArrayList<>();
        filesMerger.mergeFiles(Integer::parseInt, actual::add);
        assertEquals(expected, actual);
    }
}
