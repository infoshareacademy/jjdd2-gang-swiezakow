package pl.infoshareacademy;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CatalogTest {
    public static final AllegroCategory CATEGORY_1 = new AllegroCategory(1, "1", 0, 0);
    public static final AllegroCategory CATEGORY_2 = new AllegroCategory(2, "2", 0, 0);
    public static final AllegroCategory CATEGORY_11 = new AllegroCategory(11, "11", 1, 0);
    public static final AllegroCategory CATEGORY_22 = new AllegroCategory(22, "22", 2, 0);
    public static final AllegroCategory CATEGORY_222 = new AllegroCategory(222, "222", 22, 0);
    Catalog catalog;

    @Before
    public void setUp() {
        Map<Integer, List<AllegroCategory>> idToSubcategories = new HashMap<>();
        // 0
        // +-- 1
        // |   +-- 11
        // |
        // +-- 2
        //     +-- 22
        //         +-- 222

        //0 -> 1,2
        idToSubcategories.put(0, asList(CATEGORY_1, CATEGORY_2));
        //1 -> 11
        idToSubcategories.put(1, asList(CATEGORY_11));
        //2 -> 22
        idToSubcategories.put(2, asList(CATEGORY_22));
        //22 -> 222
        idToSubcategories.put(22, asList(CATEGORY_222));

        catalog = Catalog.catalogForMap(idToSubcategories);
    }

    @Test
    public void noCategoryWhenEmptyCatalog() {
        //given
        int categoryId = 1;
        AllegroCategory expected = null;

        //when
        AllegroCategory actual = Catalog.catalogForMap(emptyMap()).findCategoryById(categoryId);

        //then
        assertThat(actual, is(expected));
    }


}