package pl.infoshareacademy;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CatalogTest {

    @Test
    public void noCategoryWhenEmptyCatalog() {
        //given
        int categoryId = 1;
        Catalog catalog = new Catalog();
        AllegroCategory expected = null;

        //when
        AllegroCategory actual = catalog.findCategoryById(categoryId);

        //then
        assertThat(actual, is(expected));
    }

}