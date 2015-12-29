package images;

import java.io.File;
import java.io.IOException;

import raytracer.textures.*;
import raytracer.utilities.*;
import raytracer.shapes.*;

/**
 * Created by jessicalohse on 10/1/15.
 */
public class Chapter5Image implements GenericImage{

    int rowsColumns = 101;
    long milliseconds;
    private File path;

    public Chapter5Image(File path) {
        this.path = path;
        long start = System.currentTimeMillis();
        Image image = new Image(rowsColumns, rowsColumns, path);
        Sphere sphere = new Sphere(50, 50, -150, 50f, new RGB(215, 215, 215),
                0);
        sphere.addTexture(new MarbleTexture(0.15f, 5, 8));
        image.addSurface(sphere);
        image.createImage();
        try {
            image.printImage();
            this.milliseconds = System.currentTimeMillis() - start;
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
