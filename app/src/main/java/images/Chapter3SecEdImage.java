package images;

import java.io.File;
import java.io.IOException;

import raytracer.shapes.*;
import raytracer.utilities.*;

/**
 * Created by jessicalohse on 10/1/15.
 */
public class Chapter3SecEdImage {

    int rowsColumns = 500;
    long milliseconds;
    private File path;

    public Chapter3SecEdImage(File path) {
        this.path = path;
        long start = System.currentTimeMillis();
        Image image = new Image(rowsColumns, rowsColumns, path);
        Sphere sphere = new Sphere(250, 250, -1000, 150, new RGB(0, 0, 255), 0);
        Triangle triangle = new Triangle(new double[] { 300, 600, -800 },
                new double[] { 0, 100, -1000 },
                new double[] { 450, 20, -1000 }, new RGB(255, 0, 0), 0);
        image.addSurface(sphere);
        image.addSurface(triangle);
        image.createImage();
        try {
            image.printImage();
            milliseconds = System.currentTimeMillis() - start;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getTime() {
        return milliseconds;
    }

    public File getPath() {
        return path;
    }
}
