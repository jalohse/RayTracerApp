package images.surface.tests;

import java.io.File;
import java.io.IOException;

import images.GenericImage;
import raytracer.utilities.*;
import raytracer.shapes.*;

/**
 * Created by jessicalohse on 12/29/15.
 */
public class TriangleImage implements GenericImage {

    int rowsColumns = 500;
    long milliseconds;
    private File path;

    public TriangleImage(File path){
        this.path = path;
        long start = System.currentTimeMillis();
        Image image = new Image(rowsColumns, rowsColumns, path);
        Triangle triangle = new Triangle(new double[] { 100, 100, 0 }, new double[] { 350, 100, 0 },
                new double[] { 100, 351.33, 0 }, new RGB(255, 255, 255), 0);
        image.addSurface(triangle);
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
