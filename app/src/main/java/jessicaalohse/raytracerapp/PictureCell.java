package jessicaalohse.raytracerapp;

import android.media.Image;

/**
 * Created by jessicalohse on 9/28/15.
 */
public class PictureCell {

    public PictureCell(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int image;
    private String name;


}
