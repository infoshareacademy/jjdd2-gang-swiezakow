package pl.infoshareacademy.webapp;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.impl.base.filter.ExcludeRegExpPaths;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.infoshareacademy.webapp.promotedCategories.PromotedCategoriesService;

import javax.inject.Inject;
import java.io.File;

@RunWith(Arquillian.class)
public class PromotedCategoriesServiceIT {

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "it.war")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addPackages(true, new ExcludeRegExpPaths(".*IT.class$"), "pl.infoshareacademy.webapp")
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

    @Test
    public void testInjectionInTestWorks() throws Exception {
        Assert.assertNotNull(promotedCategoriesService);
    }
}
