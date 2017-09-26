package pl.infoshareacademy;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AllegroCategorySearcherTest {

    private AllegroCategorySearcher allegroCategorySearcher = new AllegroCategorySearcher();

    @Test
    public void testCutingLastLetter() {
        String[] searchPhrases = {"szukana", "fraza"};
        String[] expected = {"szukan", "fraz"};

        String[] result = allegroCategorySearcher.cutLastLetter(searchPhrases);

        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void testCutingLastLetterWithShortWords() {
        String[] searchPhrases = {"kot", "i", "pies"};
        String[] expected = {"pie"};

        String[] result = allegroCategorySearcher.cutLastLetter(searchPhrases);

        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void testCutingLastLetterWithEmptyArray() {
        String[] searchPhrases = {};
        String[] expected = {};

        String[] result = allegroCategorySearcher.cutLastLetter(searchPhrases);

        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void testSearchingCategory() {
        List<AllegroCategory> list = getTestCategories();
        String phrase = "Rosomaczek";

        List<AllegroCategory> result = allegroCategorySearcher.searchCategory(phrase, list);

        Assert.assertEquals(list.get(2), result.get(0));
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testSearchingCategoryWithLongPhrase() {
        List<AllegroCategory> list = getTestCategories();
        String phrase = "foka je kurczaka";

        List<AllegroCategory> result = allegroCategorySearcher.searchCategory(phrase, list);

        Assert.assertEquals(list.get(1), result.get(0));
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testSearchingCategoryWithStupidPhrase() {
        List<AllegroCategory> list = getTestCategories();
        String phrase = "hfjfkdlkiojdfcjfnd";

        List<AllegroCategory> result = allegroCategorySearcher.searchCategory(phrase, list);

        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testFindingById() {
        List<AllegroCategory> list = getTestCategories();
        int id = 1;

        AllegroCategory result = allegroCategorySearcher.findById(list, id);

        Assert.assertEquals(list.get(0), result);
    }

    @Test
    public void testFindingByIdWithWrongId() {
        List<AllegroCategory> list = getTestCategories();
        int id = 69;

        AllegroCategory result = allegroCategorySearcher.findById(list, id);

        Assert.assertNull(result);
    }

    @Test
    public void testSearchingCategoryWithEmptyString() {
        List<AllegroCategory> list = getTestCategories();
        String phrase = "";

        List<AllegroCategory> result = allegroCategorySearcher.searchCategory(phrase, list);

        Assert.assertTrue(result.isEmpty());
    }

    private List<AllegroCategory> getTestCategories() {
        List<AllegroCategory> list = new ArrayList<>();
        list.add(new AllegroCategory(1, "krowa", 2, 2));
        list.add(new AllegroCategory(3, "kurczak", 4, 2));
        list.add(new AllegroCategory(5, "rosomak", 6, 3));

        return list;
    }
}
