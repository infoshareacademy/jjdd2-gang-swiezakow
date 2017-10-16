package pl.infoshareacademy.webapp;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.infoshareacademy.webapp.promotedCategories.PromotedCategoriesData;
import pl.infoshareacademy.webapp.promotedCategories.PromotedCategoriesService;

import javax.inject.Inject;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@RunWith(Arquillian.class)
public class PromotedCategoriesServiceIT {

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "it.war")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsResource("categories.xml", "categories.xml")
                .addPackages(true, "pl.infoshareacademy.webapp")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        resolveDependencies(archive);
        return archive;
    }

    private static void resolveDependencies(WebArchive archive) {
        File file = new File("webapp/pom.xml");
        try {
            File[] dependencies = Maven.resolver().loadPomFromFile(file)
                    .importRuntimeDependencies()
                    .resolve()
                    .withTransitivity().asFile();
            for (File dependency : dependencies) {
                archive.addAsLibrary(dependency);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

