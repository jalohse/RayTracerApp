package jessicaalohse.raytracerapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

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
        PictureCellAdapter adapter = new PictureCellAdapter(this.getActivity(), cells);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TestImage image = new TestImage();
                while (image.getTime() == 0) {
                    spinner.setVisibility(View.VISIBLE);
                }
                Toast.makeText(view.getContext(),
                        "Generating " + ((PictureCell) parent.getAdapter().getItem(position)).getName() + " took " + image.getTime() + " ms.",
                        Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
