package raytracer.utilities;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import raytracer.shapes.Sphere;
import raytracer.shapes.Surface;
import raytracer.shapes.SurfaceList;
import raytracer.shapes.Triangle;


/**
 * Created by jessicalohse on 9/28/15.
 */
public class Image {

        int rows;
        int columns;
        public RGB[][] image;
        private SurfaceList surfaces = new SurfaceList();
        private Light light;
        private Camera camera;
        private float ambience;
        private File path;

        public Image(int rows, int columns, File path) {
            this.rows = rows;
            this.columns = columns;
            this.image = new RGB[columns][rows];
            this.path = path;
        }

        public void addSurface(Surface surface) {
            surfaces.add(surface);
        }

        public void addLight(Light light) {
            this.light = light;
        }

        public void addCamera(Camera camera) {
            this.camera = camera;
        }

        public void addAmbientLight(float ambience) {
            this.ambience = ambience;
        }

        public void createImage() {
            RGB[][] pixels = new RGB[this.columns][this.rows];
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.columns; j++) {
                    Ray ray = this.createRay(i, j);
                    if (this.surfaces.hit(ray, 0, Integer.MAX_VALUE, 0)) {
                        pixels[i][j] = getHitColor(ray);
                    } else {
                        pixels[i][j] = getAmbientBlack();
                    }
                }
            }
            populateImage(pixels);
        }

        private RGB getAmbientBlack() {
            int ambientBlack = (int) (0 + (this.ambience * 255));
            return new RGB(ambientBlack, ambientBlack, ambientBlack);
        }

        private Ray createRay(int i, int j) {
            if (this.camera == null) {
                Vector3D origin = new Vector3D(i, j, 0);
                return new Ray(origin, new Vector3D(0, 0, -1));
            } else {
                return this.camera.getRay(i, j, rows, columns);
            }
        }

        public RGB getHitColor(Ray ray) {
            Surface hitSurface = this.surfaces.getPrim();
            Vector3D hitPoint = (Vector3D) ray.pointAtParameter(this.surfaces
                    .getT());
            if (this.light != null) {
                if (isHitByShadowRay(ray, hitSurface)) {
                    return getAmbientBlack();
                } else {
                    if (hitSurface instanceof Sphere) {
                        Sphere sphere = (Sphere) hitSurface;
                        return sphere.getLitColor(light, hitPoint, ambience);
                    } else if (hitSurface instanceof Triangle) {
                        Triangle tri = (Triangle) hitSurface;
                        return tri.getLitColor(light, ambience);
                    } else {
                        return getAmbientBlack();
                    }
                }
            } else {
                return hitSurface.getAmbientColor(ambience, hitPoint);
            }
        }

        private boolean isHitByShadowRay(Ray ray, Surface hitSurface) {
            Vector3D originOfShadowRay = (Vector3D) ray.pointAtParameter(hitSurface
                    .getT());
            Ray shadowRay = new Ray(originOfShadowRay, light.getLightVector());
            return this.surfaces.hit(shadowRay, 0.001, Integer.MAX_VALUE, 0);
        }

        public void populateImage(RGB[][] pixels) {
            image = pixels;
        }

        public void printImage() throws IOException {
            Bitmap img = Bitmap.createBitmap(columns, rows, Bitmap.Config.ARGB_8888);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    RGB rgb = image[i][j];
                    int color = android.graphics.Color.rgb(rgb.red, rgb.green, rgb.blue);
                    img.setPixel(j, i, color);
                }
            }
            FileOutputStream fos = new FileOutputStream(path);
            img.compress(Bitmap.CompressFormat.PNG, 90, fos);
        }

        public int getRows() {
            return rows;
        }

        public int getColumns() {
            return columns;
        }

    }


