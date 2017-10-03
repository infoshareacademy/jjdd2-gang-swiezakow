package pl.infoshareacademy.beans;


import pl.infoshareacademy.AllegroCategory;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CategoryPickerCommandLocal {

    public List<AllegroCategory> showChildrenCategory(Integer selectedCategory);

    public String generateLink(AllegroCategory allegroCategory);

    public String testDependencyInjection();
}


