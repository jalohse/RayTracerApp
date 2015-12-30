package jessicaalohse.raytracerapp;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import images.Chapter2Image;
import images.Chapter3Image;
import images.Chapter3SecEdImage;
import images.Chapter4Image;
import images.Chapter5Image;
import images.GenericImage;
import images.GlobeImage;

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
        cells.add(new PictureCell(R.drawable.chapter2image, "Chapter 2 Image"));
        cells.add(new PictureCell(R.drawable.chapter3image, "Chapter 3 Image"));
        cells.add(new PictureCell(R.drawable.chapter3secondedimage, "Chapter 3 Second Edition Image"));
        cells.add(new PictureCell(R.drawable.chapter4image, "Chapter 4 Image"));
        cells.add(new PictureCell(R.drawable.chapter5image, "Chapter 5 Image"));
        cells.add(new PictureCell(R.drawable.globeimage, "Globe Image"));
        adapter = new PictureCellAdapter(this.getActivity(), cells);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PictureCell cell = cells.get(position);
                switch (position) {
                    case 0:
                        Chapter2Image image = new Chapter2Image(getOutputMediaFile(cell.getFileName()));
                        generateInfo(image, view, cell);
                        break;
                    case 1:
                        Chapter3Image ch3image = new Chapter3Image(getOutputMediaFile(cell.getFileName()));
                        generateInfo(ch3image, view, cell);
                        break;
                    case 2:
                        Chapter3SecEdImage ch3secimage = new Chapter3SecEdImage(getOutputMediaFile(cell.getFileName()));
                        generateInfo(ch3secimage, view, cell);
                        break;
                    case 3:
                        Chapter4Image ch4 = new Chapter4Image(getOutputMediaFile(cell.getFileName()));
                        generateInfo(ch4, view, cell);
                        break;
                    case 4:
                        Chapter5Image ch5 = new Chapter5Image(getOutputMediaFile(cell.getFileName()));
                        generateInfo(ch5, view, cell);
                        break;
                    case 5:
                        GlobeImage globe = new GlobeImage(getActivity().getApplicationContext().getResources(), getOutputMediaFile(cell.getFileName()), getOutputMediaFile("texture.png"));
                        generateInfo(globe, view, cell);
                        break;
                }
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
