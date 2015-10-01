package images;

import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import me.jessicaalohse.raytracer.utilities.*;
import me.jessicaalohse.raytracer.shapes.*;



/**
 * Created by jessicalohse on 9/28/15.
 */
public class TestImage {

        int rowsColumns = 101;
        long milliseconds;
        private File path;

        public TestImage(File path) {
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
