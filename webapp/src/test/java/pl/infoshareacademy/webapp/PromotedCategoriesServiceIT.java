package pl.infoshareacademy.webapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.infoshareacademy.webapp.promotedCategories.PromotedCategoriesData;
import pl.infoshareacademy.webapp.promotedCategories.PromotedCategoriesService;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class PromotedCategoriesServiceIT extends BaseTest {

    @Inject
    private PromotedCategoriesService promotedCategoriesService;

    @Inject
    private AllegroCategoryService allegroCategoryService;

    @Test
    public void testInjectionInTestWorks() throws Exception {
        Assert.assertNotNull(promotedCategoriesService);
    }

    @Before
    public void setUp() throws Exception {
        allegroCategoryService.saveAllegroCategoryFile(PromotedCategoriesServiceIT.class.getResourceAsStream("/categories.xml"));
    }

    @Test
    public void testPromotedCategoriesAreSaved() throws Exception {
        List<Integer> catIds = Arrays.asList(26014, 26098, 26271);
        promotedCategoriesService.savePromotedCategories(26013, catIds);

        PromotedCategoriesData result = promotedCategoriesService.getPromotedCategoriesData(26013);
        List<Integer> categoriesIds = result.getPromotedCategoriesIds();

        Assert.assertEquals(catIds, categoriesIds);
    }

    @Test
    public void testPromotedCategoriesRemoveOldValues() throws Exception {
        List<Integer> catIds = Arrays.asList(26014, 26098, 26271);
        List<Integer> catId = Arrays.asList(26014);

        promotedCategoriesService.savePromotedCategories(26013, catIds);
        promotedCategoriesService.savePromotedCategories(26013, catId);
        List<Integer> result = promotedCategoriesService
                .getPromotedCategoriesData(26013)
                .getPromotedCategoriesIds();

        Assert.assertEquals(catId, result);
    }
}