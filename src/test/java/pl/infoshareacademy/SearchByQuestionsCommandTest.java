package pl.infoshareacademy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SearchByQuestionsCommandTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void run() throws Exception {
        String path = "";
        SearchByQuestionsCommand searchByQuestionsCommand = new SearchByQuestionsCommand(path);
        searchByQuestionsCommand.run();
    }

}