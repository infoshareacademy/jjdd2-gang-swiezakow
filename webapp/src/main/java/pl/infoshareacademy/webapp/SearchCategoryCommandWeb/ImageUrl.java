package pl.infoshareacademy.webapp.SearchCategoryCommandWeb;

import java.util.List;

public class ImageUrl {
    public String getImageUrl(List<CategoryPicture> picturesList, int catID) {
        for (CategoryPicture picture : picturesList) {
            if (picture.getId() == catID) {
                return picture.getUrl();
            }
        }
        return "";
    }
}
