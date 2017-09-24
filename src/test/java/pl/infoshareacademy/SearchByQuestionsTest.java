package pl.infoshareacademy;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SearchByQuestionsTest {

    @Test
    public void returnLinkWhenChosenHasNoSubcategories() {
        //given
        int categoryId = 1000;
        String expectedCategoryName = "Nazwa";
        Catalog mockCatalog = mock(Catalog.class);
        when(mockCatalog.hasSubcategories(categoryId)).thenReturn(false);
        when(mockCatalog.findCategoryById(categoryId)).thenReturn(new AllegroCategory(categoryId, "Nazwa", 0, 0));

        SearchByQuestions searchByQuestions = new SearchByQuestions();
        searchByQuestions.setCatalog(mockCatalog);

        //when
        SearchResult result = searchByQuestions.chooseCategory(categoryId);

        //then
        assertThat(result.isLink(), is(true)); //Link
        assertThat(result.getCategoryId(), is(categoryId));
        assertThat(result.getCategoryName(), is("Nazwa"));
    }

    @Ignore("Not implemented yet")
    @Test
    public void returnFirstSubCategoryWhenChosenHasSubcategories() {
    }

    @Ignore("Not implemented yet")
    @Test
    public void returnEmptyResultWhenOmmitedHasNoNextOrParent() {
    }

    @Ignore("Not implemented yet")
    @Test
    public void returnNextParentCategoryWhenOmmitedHasNoNextOrParent() {
    }

    @Ignore("Not implemented yet")
    @Test
    public void returnNextSameLevelCategoryWhenOmmited() {
    }

    @Ignore("Not implemented yet")
    @Test
    public void returnCategoryWhenChosenHasSubcategories() {
    }
}
