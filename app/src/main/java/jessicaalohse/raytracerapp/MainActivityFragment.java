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
                        Toast.makeText(view.getContext(),
                                "Generating " + ((PictureCell) parent.getAdapter().getItem(position)).getName() + " took " + time,
                                Toast.LENGTH_LONG).show();
                        generateInfo(cell, time, image.getPath());
                        break;
                    case 1:
                        Chapter3Image ch3image = new Chapter3Image(getOutputMediaFile("chapter3image.png"));
                        while (ch3image.getTime() == 0) {
                            spinner.setVisibility(View.VISIBLE);
                        }
                        time = ch3image.getTime() + " ms.";
                        Toast.makeText(view.getContext(),
                                "Generating " + ((PictureCell) parent.getAdapter().getItem(position)).getName() + " took " + time,
                                Toast.LENGTH_LONG).show();
                        generateInfo(cell, time, ch3image.getPath());
                        break;
                }
            }
        });
        return view;
    }

    private void generateInfo(PictureCell cell, String time, File bitmap){
        cell.setPath(bitmap);
        cell.setTime(time);
        adapter.notifyDataSetChanged();
    }

    private File getOutputMediaFile(String name) {
        File mediaStorageDirectory = new File(Environment.getExternalStorageDirectory() +
                "/Android/data" + getActivity().getApplicationContext().getPackageName() + "/Files");
        if(!mediaStorageDirectory.exists()){
            mediaStorageDirectory.mkdirs();
        }
        return new File(mediaStorageDirectory.getPath() + File.separator +name);
    }
}
