package images.object.tests;

import java.io.File;
import java.io.IOException;

import images.GenericImage;
import raytracer.utilities.*;
import raytracer.shapes.*;

/**
 * Created by jessicalohse on 12/29/15.
 */
public class ElevenObjectImage implements GenericImage {

    int rowsColumns = 500;
    long milliseconds;
    private File path;

    public ElevenObjectImage(File path){
        this.path = path;
        long start = System.currentTimeMillis();
        Image image = new Image(rowsColumns, rowsColumns, path);
        Sphere head = new Sphere(230, 250, 0, 50.0f, new RGB(255, 255, 255), 0);
        Sphere body = new Sphere(330, 250, 0, 60.0f, new RGB(255, 255, 255), 0);
        Sphere bottom = new Sphere(430, 250, 0, 70.0f, new RGB(255, 255, 255), 0);
        image.addSurface(head);
        image.addSurface(body);
        image.addSurface(bottom);
        Sphere leftEye = new Sphere(220, 230, 0, 10.0f, new RGB(50, 50, 50), 0);
        Sphere rightEye = new Sphere(220, 270, 0, 10.0f, new RGB(50, 50, 50), 0);
        image.addSurface(leftEye);
        image.addSurface(rightEye);
        Triangle nose = new Triangle(new double[] { 230, 250, 0 }, new double[] { 250, 250, 0 },
                new double[] { 235, 310, 0 }, new RGB(255, 127, 0), 0);
        image.addSurface(nose);
        Sphere button1 = new Sphere(300, 250, 0, 5.0f, new RGB(50, 50, 50), 0);
        Sphere button2 = new Sphere(320, 250, 0, 5.0f, new RGB(50, 50, 50), 0);
        Sphere button3 = new Sphere(340, 250, 0, 5.0f, new RGB(50, 50, 50), 0);
        image.addSurface(button1);
        image.addSurface(button2);
        image.addSurface(button3);
        Triangle leftArm = new Triangle(new double[] { 260, 150, 0 }, new double[] { 270, 180, 0 },
                new double[] { 335, 210, -100 }, new RGB(153, 102, 51), 0);
        Triangle rightArm = new Triangle(new double[] { 260, 350, 0 }, new double[] { 270, 320, 0 },
                new double[] { 335, 280, -100 }, new RGB(153, 102, 51), 0);
        image.addSurface(leftArm);
        image.addSurface(rightArm);
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
