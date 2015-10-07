package raytracer.textures;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import java.io.File;
import raytracer.utilities.Image;
import raytracer.utilities.RGB;
import raytracer.utilities.Vector2D;
import raytracer.utilities.Vector3D;

/**
 * Created by jessicalohse on 10/7/15.
 */

public class ImageTexture implements Texture {

    Image image;

    public ImageTexture(Resources res, int fileName, File path) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap imageFile = BitmapFactory.decodeResource(res, fileName, options);
            this.image = new Image(imageFile.getHeight(), imageFile.getWidth(), path);
            this.image.populateImage(getPixelsFromImage(imageFile));
    }

    public RGB[][] getPixelsFromImage(Bitmap image) {
        RGB[][] pixels = new RGB[image.getHeight()][image.getWidth()];
        for(int i = 0; i < image.getWidth(); i++){
            for(int j = 0; j < image.getHeight(); j++){
                int pixel = image.getPixel(i, j);
                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);
                RGB color = new RGB(red, green, blue);
                color.clamp();
                pixels[j][i] = color;
            }
        }
        return pixels;
    }

    public RGB getValue(Vector2D uv, Vector3D p) {
        float u = uv.getX() - (int) uv.getX();
        float v = uv.getY() - (int) uv.getY();
        u *= image.getRows() - 3;
        v *= image.getColumns() - 3;
        int iu = (int) u;
        int iv = (int) v;
        float tu = u - iu;
        float tv = v - iv;
        RGB[][] pixels = image.image;
        RGB c1 = pixels[iu][iv].multiplyByScalar(1 - tu).multiplyByScalar(1 - tv);
        RGB c2 = pixels[iu + 1][iv].multiplyByScalar(tu).multiplyByScalar(1 - tv);
        RGB c3 = pixels[iu][iv + 1].multiplyByScalar(1 - tu).multiplyByScalar(tv);
        RGB c4 = pixels[iu + 1][iv + 1].multiplyByScalar(tu).multiplyByScalar(tv);
        RGB c = c1.add(c2).add(c3).add(c4);
        return c;
    }

}

