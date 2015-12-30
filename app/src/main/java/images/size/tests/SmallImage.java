package images.size.tests;

import java.io.File;
import java.io.IOException;

import images.GenericImage;
import raytracer.utilities.*;
import raytracer.shapes.*;

/**
 * Created by jessicalohse on 12/29/15.
 */
public class SmallImage implements GenericImage {

    int rowsColumns = 125;
    long milliseconds;
    private File path;

    public SmallImage(File path){
        this.path = path;
        long start = System.currentTimeMillis();
        Image image = new Image(rowsColumns, rowsColumns, path);
        Triangle triangle = new Triangle(new double[] { 125, 0, 0 }, new double[] { 125, 125, 0 },
                new double[] { 0, 125, 0 }, new RGB(255, 0, 255), 0);
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
