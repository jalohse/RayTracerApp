package jessicaalohse.raytracerapp;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jessicalohse on 9/28/15.
 */
public class PictureCellAdapter extends ArrayAdapter<PictureCell> {

    private final Context context;
    private final ArrayList<PictureCell> cells;

    public PictureCellAdapter(Context context, ArrayList<PictureCell> cells){
        super(context, R.layout.picture_cell, cells);
        this.context = context;
        this.cells = cells;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cell = inflater.inflate(R.layout.picture_cell, parent, false);
        ImageView image = (ImageView) cell.findViewById(R.id.imageView);
        PictureCell currentCell = cells.get(position);
        image.setImageResource(currentCell.getImage());
        TextView text = (TextView) cell.findViewById(R.id.textView);
        text.setText(currentCell.getName());
        return cell;
    }
}
