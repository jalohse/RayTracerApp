package images.surface.tests;

import java.io.File;
import java.io.IOException;

import images.GenericImage;
import raytracer.utilities.*;
import raytracer.shapes.*;

/**
 * Created by jessicalohse on 12/29/15.
 */
public class TwoSphereImage implements GenericImage {

    int rowsColumns = 500;
    long milliseconds;
    private File path;

    public TwoSphereImage(File path){
        this.path = path;
        long start = System.currentTimeMillis();
        Image image = new Image(rowsColumns, rowsColumns, path);
        Sphere sphereOne = new Sphere(150, 150, 0, 100.0f, new RGB(255, 255, 255), 0);
        Sphere sphereTwo = new Sphere(300, 300, 0, 100.0f, new RGB(255, 255, 255), 0);
        image.addSurface(sphereOne);
        image.addSurface(sphereTwo);
        image.createImage();
        try {
            image.printImage();
            milliseconds = System.currentTimeMillis() - start;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getTime() {
        return milliseconds;
    }

    @Override
    public File getPath() {
        return path;
    }
}
