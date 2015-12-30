package images.texture.tests;

import android.content.res.Resources;

import java.io.File;
import java.io.IOException;

import jessicaalohse.raytracerapp.R;

import raytracer.textures.*;
import raytracer.utilities.*;
import raytracer.shapes.*;
import images.*;

/**
 * Created by jessicalohse on 10/1/15.
 */
public class XSmallTextureImage implements GenericImage{

    int rowsColumns = 500;
    long milliseconds;
    private File path;

    public XSmallTextureImage(Resources res, File path, File texturepath) {
        this.path = path;
        long start = System.currentTimeMillis();
        Image image = new Image(rowsColumns, rowsColumns, path);
        Sphere sphere = new Sphere(250, 250, -1500, 250f, new RGB(215, 215, 215), 0);
        sphere.addTexture(new ImageTexture(res, R.drawable.textxsmallmap, texturepath));
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
