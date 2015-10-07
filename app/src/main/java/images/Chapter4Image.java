package images;

import java.io.File;
import java.io.IOException;

import raytracer.utilities.*;
import raytracer.shapes.*;

/**
 * Created by jessicalohse on 10/1/15.
 */
public class Chapter4Image {

    int rowsColumns = 101;
    long milliseconds;
    private File path;

    public Chapter4Image(File path) {
        this.path = path;
        long start = System.currentTimeMillis();
        Image image = new Image(rowsColumns, rowsColumns, path);

        Vector3D eyePoint = new Vector3D(0, 0, 2);
        Vector3D gazeVector = new Vector3D(0, 0, -2);
        Vector3D viewUpVector = new Vector3D(0, 1, 0);
        Vector2D bottomLeft = new Vector2D(-2f, -2f);
        Vector2D topRight = new Vector2D(2, 2);
        PinholeCamera camera = new PinholeCamera(eyePoint, gazeVector,
                viewUpVector, bottomLeft, topRight, 2.0f);
        image.addCamera(camera);

        Sphere sphere = new Sphere(0, 0, 0, (float) Math.sqrt(2), new RGB(215,
                215, 215), 0.9f);
        image.addSurface(sphere);

        Light light = new Light(new Vector3D(0, 1, 0), new RGB(255, 255, 255));
        image.addLight(light);
        image.addAmbientLight(0.1f);

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
