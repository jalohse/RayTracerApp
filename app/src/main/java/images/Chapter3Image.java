package images;

import me.jessicaalohse.raytracer.utilities.*;
import java.io.File;
import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.*;

/**
 * Created by jessicalohse on 10/1/15.
 */
public class Chapter3Image {
    int rowsColumns = 101;
    long milliseconds;
    private File path;

    public Chapter3Image(File path) {
        this.path = path;
        long start = System.currentTimeMillis();
        Image image = new Image(rowsColumns, rowsColumns, path);
        RGB lightGray = new RGB(215, 215, 215);
        Sphere largeSphere = new Sphere(50, -80, -1000, 100, lightGray, 0);
        Sphere smallSphere = new Sphere(50, 50, -1000, 30, lightGray, 0);
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