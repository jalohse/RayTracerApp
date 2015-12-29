package images;

import java.io.File;
import java.io.IOException;

import raytracer.shapes.Sphere;
import raytracer.shapes.Triangle;
import raytracer.utilities.Image;
import raytracer.utilities.RGB;


/**
 * Created by jessicalohse on 9/28/15.
 */
public class Chapter2Image implements GenericImage{

        int rowsColumns = 101;
        long milliseconds;
        private File path;

        public Chapter2Image(File path) {
            this.path = path;
            long start = System.currentTimeMillis();
            Image image = new Image(rowsColumns, rowsColumns, path);
            Sphere sphere = new Sphere(0, 0, -150, 100.1f, new RGB(215, 215, 215),
                    0);
            Triangle triangle = new Triangle(new double[] { 0, 0, -100 },
                    new double[] { 100.1, 0, -100 },
                    new double[] { 50, 100.1, -100 }, new RGB(112, 112, 112), 0);
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

    public long getTime(){
        return milliseconds;
    }

    public File getPath() {
        return path;
    }
}
