package images.color.tests;

import java.io.File;
import java.io.IOException;

import images.GenericImage;
import raytracer.utilities.*;
import raytracer.shapes.*;
import raytracer.textures.*;

/**
 * Created by jessicalohse on 12/29/15.
 */
public class NoiseImage implements GenericImage {

    int rowsColumns = 500;
    long milliseconds;
    private File path;

    public NoiseImage(File path){
        this.path = path;
        long start = System.currentTimeMillis();
        Image image = new Image(rowsColumns, rowsColumns, path);
        Sphere sphere = new Sphere(250, 250, 0, 100.0f, new RGB(0, 255, 0), 0);
        sphere.addTexture(new NoiseTexture());
        image.addSurface(sphere);
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
