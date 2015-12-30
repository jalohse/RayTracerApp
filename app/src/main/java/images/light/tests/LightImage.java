package images.light.tests;

import java.io.File;
import java.io.IOException;

import raytracer.shapes.*;
import raytracer.utilities.*;
import images.GenericImage;


/**
 * Created by jessicalohse on 9/28/15.
 */
public class LightImage implements GenericImage{

    int rowsColumns = 500;
    long milliseconds;
    private File path;

    public LightImage(File path) {
        this.path = path;
        long start = System.currentTimeMillis();
        Image image = new Image(rowsColumns, rowsColumns, path);
        RGB lightGray = new RGB(215, 215, 215);
        Sphere largeSphere = new Sphere(250, 120, -1000, 100, lightGray, 0);
        Sphere smallSphere = new Sphere(250, 250, -1000, 30, lightGray, 0);
        image.addSurface(largeSphere);
        image.addSurface(smallSphere);
        Light light = new Light(new Vector3D(0, 1, 0), new RGB(255, 255, 255));
        image.addLight(light);
        image.createImage();
        try {
            image.printImage();
            milliseconds = System.currentTimeMillis() - start;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getTime(){
        return milliseconds;
    }

    public File getPath() {
        return path;
    }
}
