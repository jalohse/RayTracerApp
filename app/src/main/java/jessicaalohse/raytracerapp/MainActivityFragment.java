package jessicaalohse.raytracerapp;

import android.app.Fragment;
import android.graphics.Bitmap;
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

import images.TestImage;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        final ProgressBar spinner = (ProgressBar)view.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        ListView list = (ListView) view.findViewById(R.id.listView);
        ArrayList<PictureCell> cells = new ArrayList<PictureCell>();
        cells.add(new PictureCell(R.drawable.chapter3image, "Test"));
        final ArrayList<PictureCell> finalCells = cells;
        final PictureCellAdapter adapter = new PictureCellAdapter(this.getActivity(), finalCells);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PictureCell cell = finalCells.get(position);
                TestImage image = new TestImage(getOutputMediaFile("test.png"));
                while (image.getTime() == 0) {
                    spinner.setVisibility(View.VISIBLE);
                }
                String time = image.getTime() + " ms.";
                Toast.makeText(view.getContext(),
                        "Generating " + ((PictureCell) parent.getAdapter().getItem(position)).getName() + " took " + time,
                        Toast.LENGTH_LONG).show();
                File bitmap = image.getPath();
                cell.setPath(bitmap);
                cell.setTime(time);
                adapter.notifyDataSetChanged();
            }
        });
        return view;
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
