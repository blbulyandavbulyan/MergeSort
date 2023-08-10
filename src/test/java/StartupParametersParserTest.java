import org.blbulyandavbulyan.mergesort.startupparamters.InputDataType;
import org.blbulyandavbulyan.mergesort.startupparamters.SortMode;
import org.blbulyandavbulyan.mergesort.startupparamters.StartupParameterParser;
import org.blbulyandavbulyan.mergesort.startupparamters.StartupParameters;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartupParametersParserTest {
    @Test
    public void inputTypeIntegerTwoInputFiles() {
        StartupParameterParser startupParameterParser = new StartupParameterParser();
        StartupParameters startupParameters = startupParameterParser.getStartupParameters(new String[]{"-i", "out.txt", "in1.txt", "in2.txt"});
        assertEquals(InputDataType.INTEGER, startupParameters.getInputDataType());
        assertEquals("out.txt", startupParameters.getOutputFileName());
        assertEquals(List.of("in1.txt", "in2.txt"), startupParameters.getInputFilesNames());
    }
    @Test
    public void inputTypeStringTwoInputFiles(){
        StartupParameterParser startupParameterParser = new StartupParameterParser();
        StartupParameters startupParameters = startupParameterParser.getStartupParameters(new String[]{"-s", "out.txt", "in1.txt", "in2.txt"});
        assertEquals(InputDataType.STRING, startupParameters.getInputDataType());
        assertEquals("out.txt", startupParameters.getOutputFileName());
        assertEquals(List.of("in1.txt", "in2.txt"), startupParameters.getInputFilesNames());
    }
    @Test
    public void inputTypeIntegerAscendedOrder(){
        StartupParameterParser startupParameterParser = new StartupParameterParser();
        StartupParameters startupParameters = startupParameterParser.getStartupParameters(new String[]{"-s", "-a", "out.txt", "in1.txt", "in2.txt"});
        assertEquals(InputDataType.STRING, startupParameters.getInputDataType());
        assertEquals(SortMode.ASCENDED, startupParameters.getSortMode());
        assertEquals("out.txt", startupParameters.getOutputFileName());
        assertEquals(List.of("in1.txt", "in2.txt"), startupParameters.getInputFilesNames());
    }
    @Test
    public void inputTypeIntegerDescendedOrder(){
        StartupParameterParser startupParameterParser = new StartupParameterParser();
        StartupParameters startupParameters = startupParameterParser.getStartupParameters(new String[]{"-s", "-d", "out.txt", "in1.txt", "in2.txt"});
        assertEquals(InputDataType.STRING, startupParameters.getInputDataType());
        assertEquals(SortMode.DESCENDED, startupParameters.getSortMode());
        assertEquals("out.txt", startupParameters.getOutputFileName());
        assertEquals(List.of("in1.txt", "in2.txt"), startupParameters.getInputFilesNames());
    }
}
