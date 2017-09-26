package pl.infoshareacademy;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchByQuestionsTest {
    private static final AllegroCategory MAIN_CATEGORY = new AllegroCategory(1000, "Main Category", 0, 0);
    private static final AllegroCategory MAIN_CATEGORY_2 = new AllegroCategory(1001, "Main Category 2", 0, 1);
    private static final AllegroCategory MAIN_SUBCATEGORY = new AllegroCategory(2000, "Main Subcategory", MAIN_CATEGORY.getCatID(), 0);
    private static final SearchResult RESULT_MAIN_SUBCATEGORY_LINK = new SearchResult(MAIN_SUBCATEGORY.getName(), MAIN_SUBCATEGORY.getCatID(), true);
    private static final SearchResult RESULT_MAIN_SUBCATEGORY = new SearchResult(MAIN_SUBCATEGORY.getName(), MAIN_SUBCATEGORY.getCatID(), false);
    private static final SearchResult RESULT_MAIN_CATEGORY_2 = new SearchResult(MAIN_CATEGORY_2.getName(), MAIN_CATEGORY_2.getCatID(), false);

    @Test
    public void returnLinkWhenChosenHasNoSubcategories() {
        //given
        int categoryId = MAIN_SUBCATEGORY.getCatID();
        Catalog mockCatalog = mock(Catalog.class);
        when(mockCatalog.hasSubcategories(categoryId)).thenReturn(false);
        when(mockCatalog.findCategoryById(categoryId)).thenReturn(MAIN_SUBCATEGORY);

        SearchByQuestions searchByQuestions = new SearchByQuestions(mockCatalog);
        SearchResult expected = RESULT_MAIN_SUBCATEGORY_LINK;

        //when
        SearchResult actual = searchByQuestions.chooseCategory(categoryId);

        //then
        assertThat(actual, is(expected));
    }

    @Test
    public void returnFirstSubCategoryWhenChosenHasSubcategories() {
        //given
        int categoryId = MAIN_CATEGORY.getCatID();
        Catalog mockCatalog = mock(Catalog.class);
        when(mockCatalog.hasSubcategories(categoryId)).thenReturn(true);
        when(mockCatalog.findCategoryById(categoryId)).thenReturn(MAIN_CATEGORY);
        when(mockCatalog.getSubcategories(categoryId)).thenReturn(Arrays.asList(MAIN_SUBCATEGORY));

        SearchByQuestions searchByQuestions = new SearchByQuestions(mockCatalog);
        SearchResult expected = RESULT_MAIN_SUBCATEGORY;

        //when
        SearchResult actual = searchByQuestions.chooseCategory(categoryId);

        //then
        assertThat(actual, is(expected));
    }

    @Test
    public void returnEmptyResultWhenOmittedHasNoNextOrParent() {
        //given
        int categoryId = MAIN_CATEGORY.getCatID();
        Catalog mockCatalog = mock(Catalog.class);
        when(mockCatalog.findSibling(categoryId)).thenReturn(null);
        when(mockCatalog.findCategoryById(categoryId)).thenReturn(MAIN_CATEGORY);

        SearchByQuestions searchByQuestions = new SearchByQuestions(mockCatalog);
        SearchResult expected = null;

        //when
        SearchResult actual = searchByQuestions.omitCategory(categoryId);

        //then
        assertThat(actual, is(expected));
    }

    @Test
    public void returnNextParentCategoryWhenOmittedHasNoNextHasParent() {
        //given
        int categoryId = MAIN_SUBCATEGORY.getCatID();
        Catalog mockCatalog = mock(Catalog.class);
        when(mockCatalog.findSibling(categoryId)).thenReturn(null);
        when(mockCatalog.findCategoryById(categoryId)).thenReturn(MAIN_SUBCATEGORY);
        when(mockCatalog.findSibling(MAIN_CATEGORY.getCatID())).thenReturn(MAIN_CATEGORY_2);

        SearchByQuestions searchByQuestions = new SearchByQuestions(mockCatalog);
        SearchResult expected = RESULT_MAIN_CATEGORY_2;

        //when
        SearchResult actual = searchByQuestions.omitCategory(categoryId);

        //then
        assertThat(actual, is(expected));
    }

    @Test
    public void returnNextSameLevelCategoryWhenOmittedHasNext() {
        int categoryId = MAIN_CATEGORY.getCatID();
        Catalog mockCatalog = mock(Catalog.class);
        when(mockCatalog.findSibling(categoryId)).thenReturn(MAIN_CATEGORY_2);

        SearchByQuestions searchByQuestions = new SearchByQuestions(mockCatalog);
        SearchResult expected = RESULT_MAIN_CATEGORY_2;

        //when
        SearchResult actual = searchByQuestions.omitCategory(categoryId);

        //then
        assertThat(actual, is(expected));
    }
}