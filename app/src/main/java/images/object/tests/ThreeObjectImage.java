package images.object.tests;

import java.io.File;
import java.io.IOException;

import images.GenericImage;
import raytracer.utilities.*;
import raytracer.shapes.*;

/**
 * Created by jessicalohse on 12/29/15.
 */
public class ThreeObjectImage implements GenericImage {

    int rowsColumns = 500;
    long milliseconds;
    private File path;

    public ThreeObjectImage(File path){
        this.path = path;
        long start = System.currentTimeMillis();
        Image image = new Image(rowsColumns, rowsColumns, path);
        Sphere head = new Sphere(230, 250, 0, 50.0f, new RGB(255, 255, 255), 0);
        Sphere body = new Sphere(330, 250, 0, 60.0f, new RGB(255, 255, 255), 0);
        Sphere bottom = new Sphere(430, 250, 0, 70.0f, new RGB(255, 255, 255), 0);
        image.addSurface(head);
        image.addSurface(body);
        image.addSurface(bottom);
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
