package jessicaalohse.raytracerapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

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
        ListView list = (ListView) view.findViewById(R.id.listView);
        ArrayList<PictureCell> cells = new ArrayList<PictureCell>();
        cells.add(new PictureCell(R.drawable.chapter3image, "Test"));
        PictureCellAdapter adapter = new PictureCellAdapter(this.getActivity(), cells);
        list.setAdapter(adapter);
        return view;
    }
}
