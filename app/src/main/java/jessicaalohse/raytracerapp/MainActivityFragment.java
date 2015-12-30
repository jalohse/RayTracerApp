package jessicaalohse.raytracerapp;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import images.GenericImage;
import images.object.tests.ElevenObjectImage;
import images.object.tests.OneObjectImage;
import images.object.tests.SixteenObjectImage;
import images.object.tests.SnowImage;
import images.object.tests.ThreeObjectImage;
import images.object.tests.TwentyOneObjectImage;
import images.size.tests.LargeImage;
import images.size.tests.MediumImage;
import images.size.tests.SmallImage;
import images.surface.tests.SphereImage;
import images.surface.tests.TriangleImage;
import images.surface.tests.TwoSphereImage;
import images.surface.tests.TwoTriangleImage;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    PictureCellAdapter adapter;
    ArrayList<PictureCell> cells;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        ListView list = (ListView) view.findViewById(R.id.listView);
        cells = new ArrayList<PictureCell>();
        // obj tests
        cells.add(new PictureCell(R.drawable.otoneobject, "One Object"));
        cells.add(new PictureCell(R.drawable.otthreeobject, "Three Objects"));
        cells.add(new PictureCell(R.drawable.otelevenobject, "Eleven Objects"));
        cells.add(new PictureCell(R.drawable.otsixteenobject, "Sixteen Objects"));
        cells.add(new PictureCell(R.drawable.ottwentyoneobject, "Twenty One Objects"));
        cells.add(new PictureCell(R.drawable.otsnowimage, "Snowman"));

        //size tests
        cells.add(new PictureCell(R.drawable.stsmallimage, "Small Image"));
        cells.add(new PictureCell(R.drawable.stmediumimage, "Medium Image"));
        cells.add(new PictureCell(R.drawable.stlargeimage, "Large Image"));

        // surface tests
        cells.add(new PictureCell(R.drawable.sphereimage, "Sphere Image"));
        cells.add(new PictureCell(R.drawable.triangleimage, "Triangle Image"));
        cells.add(new PictureCell(R.drawable.twosphereimage, "Two Sphere Image"));
        cells.add(new PictureCell(R.drawable.twotriangleimage, "Two Triangle Image"));

        adapter = new PictureCellAdapter(this.getActivity(), cells);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PictureCell cell = cells.get(position);
                GenericImage img = null;
                switch (position) {
                    case 0:
                        img = new OneObjectImage(getOutputMediaFile(cell.getFileName()));
                        break;
                    case 1:
                        img = new ThreeObjectImage(getOutputMediaFile(cell.getFileName()));
                        break;
                    case 2:
                        img = new ElevenObjectImage(getOutputMediaFile(cell.getFileName()));
                        break;
                    case 3:
                        img = new SixteenObjectImage(getOutputMediaFile(cell.getFileName()));
                        break;
                    case 4:
                        img = new TwentyOneObjectImage(getOutputMediaFile(cell.getFileName()));
                        break;
                    case 5:
                        img = new SnowImage(getOutputMediaFile(cell.getFileName()));
                        break;
                    case 6:
                        img = new SmallImage(getOutputMediaFile(cell.getFileName()));
                        break;
                    case 7:
                        img = new MediumImage(getOutputMediaFile(cell.getFileName()));
                        break;
                    case 8:
                        img = new LargeImage(getOutputMediaFile(cell.getFileName()));
                        break;
                    case 9:
                        img = new SphereImage(getOutputMediaFile(cell.getFileName()));
                        break;
                    case 10:
                        img = new TriangleImage(getOutputMediaFile(cell.getFileName()));
                        break;
                    case 11:
                        img = new TwoSphereImage(getOutputMediaFile(cell.getFileName()));
                        break;
                    case 12:
                        img = new TwoTriangleImage(getOutputMediaFile(cell.getFileName()));
                        break;
                }
                generateInfo(img, view, cell);
            }
        });
        return view;
    }

    private void generateInfo(GenericImage image, View view, PictureCell cell){
        String time = image.getTime() + " ms.";
        Toast.makeText(view.getContext(),
                "Generating " + cell.getName() + " took " + time,
                Toast.LENGTH_LONG).show();
        cell.setPath(image.getPath());
        cell.setTime(time);
        adapter.notifyDataSetChanged();
    }

    private File getOutputMediaFile(String name) {
        File mediaStorageDirectory = new File(Environment.getExternalStorageDirectory() +
                "/Android/data/" + getActivity().getApplicationContext().getPackageName() + "/Files");
        if(!mediaStorageDirectory.exists()){
            mediaStorageDirectory.mkdirs();
        }
        return new File(mediaStorageDirectory.getPath() + File.separator + name);
    }
}
