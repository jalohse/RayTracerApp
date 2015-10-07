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
        final ProgressBar spinner = (ProgressBar)view.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
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
                String time;
                switch (position) {
                    case 0:
                        Chapter2Image image = new Chapter2Image(getOutputMediaFile("chapter2image.png"));
                        while (image.getTime() == 0) {
                            spinner.setVisibility(View.VISIBLE);
                        }
                        time = image.getTime() + " ms.";
                        generateInfo(view, cell, time, image.getPath());
                        break;
                    case 1:
                        Chapter3Image ch3image = new Chapter3Image(getOutputMediaFile("chapter3image.png"));
                        while (ch3image.getTime() == 0) {
                            spinner.setVisibility(View.VISIBLE);
                        }
                        time = ch3image.getTime() + " ms.";
                        generateInfo(view, cell, time, ch3image.getPath());
                        break;
                    case 2:
                        Chapter3SecEdImage ch3secimage = new Chapter3SecEdImage(getOutputMediaFile("chapter3image.png"));
                        while (ch3secimage.getTime() == 0) {
                            spinner.setVisibility(View.VISIBLE);
                        }
                        time = ch3secimage.getTime() + " ms.";
                        generateInfo(view, cell, time, ch3secimage.getPath());
                        break;
                    case 3:
                        Chapter4Image ch4 = new Chapter4Image(getOutputMediaFile("chapter4image.png"));
                        while (ch4.getTime() == 0) {
                            spinner.setVisibility(View.VISIBLE);
                        }
                        time = ch4.getTime() + " ms.";
                        generateInfo(view, cell, time, ch4.getPath());
                        break;
                    case 4:
                        Chapter5Image ch5 = new Chapter5Image(getOutputMediaFile("chapter4image.png"));
                        while (ch5.getTime() == 0) {
                            spinner.setVisibility(View.VISIBLE);
                        }
                        time = ch5.getTime() + " ms.";
                        generateInfo(view, cell, time, ch5.getPath());
                        break;
                    case 5:
                        GlobeImage globe = new GlobeImage(getActivity().getApplicationContext().getResources(), getOutputMediaFile("chapter4image.png"), getOutputMediaFile("texture.png"));
                        while (globe.getTime() == 0) {
                            spinner.setVisibility(View.VISIBLE);
                        }
                        time = globe.getTime() + " ms.";
                        generateInfo(view, cell, time, globe.getPath());
                        break;
                }
            }
        });
        return view;
    }

    private void generateInfo(View view, PictureCell cell, String time, File bitmap){
        Toast.makeText(view.getContext(),
                "Generating " + cell.getName() + " took " + time,
                Toast.LENGTH_LONG).show();
        cell.setPath(bitmap);
        cell.setTime(time);
        adapter.notifyDataSetChanged();
    }

    private File getOutputMediaFile(String name) {
        File mediaStorageDirectory = new File(Environment.getExternalStorageDirectory() +
                "/Android/data/" + getActivity().getApplicationContext().getPackageName() + "/Files");
        if(!mediaStorageDirectory.exists()){
            mediaStorageDirectory.mkdirs();
        }
        return new File(mediaStorageDirectory.getPath() + File.separator +name);
    }
}
