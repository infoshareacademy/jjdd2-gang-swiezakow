package pl.infoshareacademy;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class SearchByQuestionsTest {

    @Test
    public void returnLinkWhenChosenHasNoSubcategories() {
        //given
        int categoryId = 1000;
        String expectedCategoryName = "Nazwa";

        //when
        SearchByQuestions searchByQuestions = new SearchByQuestions();
        SearchResult result = searchByQuestions.chooseCategory(categoryId);

        //then
        assertThat(result.isLink(), is(true));
        assertThat(result.getCategoryId(), is(categoryId));
        assertThat(result.getCategoryName(), is("Nazwa"));
    }
}
