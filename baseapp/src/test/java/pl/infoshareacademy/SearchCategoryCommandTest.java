package pl.infoshareacademy;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchCategoryCommandTest {

    private SearchCategoryCommand searchCategoryCommand;
    private AllegroCategoryLoader loader;
    private AllegroCategorySearcher searcher;

    @Before
    public void setUp() throws Exception {
        ConfigurationLoader.loadConfiguration();
        loader = Mockito.mock(AllegroCategoryLoader.class);
        searcher = Mockito.mock(AllegroCategorySearcher.class);
        searchCategoryCommand = new SearchCategoryCommand("cokolwiek", loader, searcher);
    }

    @Test
    public void testGeneratingLinkWithParent() {
        AllegroCategory allegroCategory = new AllegroCategory(1, "name", 2, 3);
        AllegroCategory allegroCategoryParent = new AllegroCategory(2, "parent Name", 0, 2);
        String phrase = "przykładowa fraza";

        String link = searchCategoryCommand.generateLink(allegroCategory, allegroCategoryParent, phrase);

        assertEquals("https://allegro.pl/kategoria/parent-Name-2?string=przykładowa-fraza", link);
    }

    @Test
    public void testGeneratingLinkWithoutParent() {
        AllegroCategory allegroCategory = new AllegroCategory(1, "Category Name", 0, 2);
        AllegroCategory parent = null;
        String phrase = "przykładowa fraza";

        String link = searchCategoryCommand.generateLink(allegroCategory, parent, phrase);

        assertEquals("https://allegro.pl/kategoria/category-name?string=przykładowa-fraza", link);
    }

    @Test
    public void testReadingAnswerForYes() {
        UserInput userInput = new UserInput(new Scanner(new StringReader("tAk\n")));

        boolean yesAnswer = searchCategoryCommand.readYesNoAnswer(userInput);

        assertTrue(yesAnswer);
    }

    @Test
    public void testReadingAnswerForNo() {
        UserInput userInput = new UserInput(new Scanner(new StringReader("nIe\n")));

        boolean noAnswer = searchCategoryCommand.readYesNoAnswer(userInput);

        assertFalse(noAnswer);
    }

    @Test
    public void testReadingWrongAnswer() {
        UserInput userInput = Mockito.mock(UserInput.class);
        when(userInput.line()).thenReturn("błędna odpowiedź", "nie");

        boolean wrongAnswer = searchCategoryCommand.readYesNoAnswer(userInput);

        assertFalse(wrongAnswer);
        verify(userInput, times(2)).line();
    }

    @Test
    public void testHandlingCommandForBadNumber() {
        String searchPhrase = "fgfgfgf";
        Scanner scanner = new Scanner(new StringReader(searchPhrase + "\nnie\n"));
        List<AllegroCategory> list = Arrays.asList(new AllegroCategory(1, "", 2, 3));
        when(loader.loadAllCategories(anyString())).thenReturn(list);
        when(searcher.searchCategory(searchPhrase, list)).thenReturn(list);
        when(searcher.printCategoriesAndLetUserChoose(scanner, list, list)).thenReturn(null);

        searchCategoryCommand.handleCommand(scanner);

        verify(loader, times(1)).loadAllCategories(anyString());
        verify(searcher, times(1)).searchCategory(searchPhrase, list);
        verify(searcher, times(1)).printCategoriesAndLetUserChoose(scanner, list, list);
    }

    @Test
    public void testHandlingCommandForNoResults() {
        String searchPhrase = "blablabla";
        List<AllegroCategory> list = Arrays.asList(new AllegroCategory(1, "", 2, 3));
        Scanner scanner = new Scanner(new StringReader(searchPhrase + "\nnie\n"));
        when(loader.loadAllCategories(anyString())).thenReturn(list);
        when(searcher.searchCategory(searchPhrase, list)).thenReturn(Collections.EMPTY_LIST);

        searchCategoryCommand.handleCommand(scanner);

        verify(loader, times(1)).loadAllCategories(anyString());
        verify(searcher, times(1)).searchCategory(searchPhrase, list);
        verify(searcher, never()).printCategoriesAndLetUserChoose(scanner, list, list);
    }

    @Test
    public void testHandlingCommandForFatalError() {
        String searchPhrase = "balab";
        List<AllegroCategory> list = Arrays.asList(new AllegroCategory(1, "", 2, 3));
        Scanner scanner = new Scanner(new StringReader(searchPhrase));
        when(loader.loadAllCategories(anyString())).thenReturn(Collections.EMPTY_LIST);

        searchCategoryCommand.handleCommand(scanner);

        verify(loader, times(1)).loadAllCategories(anyString());
        verify(searcher, never()).searchCategory(searchPhrase,list);
        verify(searcher, never()).printCategoriesAndLetUserChoose(scanner, list, list);
    }
}
