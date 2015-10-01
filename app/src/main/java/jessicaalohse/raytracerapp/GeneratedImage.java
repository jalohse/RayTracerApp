package jessicaalohse.raytracerapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class GeneratedImage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_image);
        ImageView image = (ImageView) findViewById(R.id.generated_image);
        Intent intent = getIntent();
        Bitmap bitmap = BitmapFactory.decodeFile(intent.getStringExtra("PATH"));
        image.setImageBitmap(bitmap);
    }
}
