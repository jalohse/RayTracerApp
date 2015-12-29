package images;

import android.content.res.Resources;

import java.io.File;
import java.io.IOException;

import jessicaalohse.raytracerapp.R;

import raytracer.textures.*;
import raytracer.utilities.*;
import raytracer.shapes.*;

/**
 * Created by jessicalohse on 10/1/15.
 */
public class GlobeImage implements GenericImage{

    int rowsColumns = 1001;
    long milliseconds;
    private File path;

    public GlobeImage(Resources res,File path, File texturepath) {
        this.path = path;
        long start = System.currentTimeMillis();
        Image image = new Image(rowsColumns, rowsColumns, path);
        Sphere sphere = new Sphere(500, 500, -1500, 500f, new RGB(215, 215, 215),
                0);
        sphere.addTexture(new ImageTexture(res, R.drawable.map2, texturepath));
        image.addSurface(sphere);
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
