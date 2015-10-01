package jessicaalohse.raytracerapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    public PictureCell getItem(int position) {
        return cells.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cell = inflater.inflate(R.layout.picture_cell, parent, false);
        ImageView image = (ImageView) cell.findViewById(R.id.imageView);
        final PictureCell currentCell = cells.get(position);
        image.setImageResource(currentCell.getImage());
        TextView text = (TextView) cell.findViewById(R.id.name);
        text.setText(currentCell.getName());
        final TextView time = (TextView) cell.findViewById(R.id.time);
        if(currentCell.getTime() != null){
            time.setText(currentCell.getTime());
        } else {
            time.setVisibility(View.INVISIBLE);
        }
        final Button getFile = (Button) cell.findViewById(R.id.file_open);
        final Button redo = (Button) cell.findViewById(R.id.button);
        if(currentCell.getPath() == null){
            getFile.setVisibility(View.INVISIBLE);
            redo.setVisibility(View.INVISIBLE);
        } else {
            getFile.setVisibility(View.VISIBLE);
            redo.setVisibility(View.VISIBLE);
        }
        getFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GeneratedImage.class);
                intent.putExtra("PATH", currentCell.getPath().getAbsolutePath());
                v.getContext().startActivity(intent);
            }
        });
        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setVisibility(View.INVISIBLE);
                getFile.setVisibility(View.INVISIBLE);
                redo.setVisibility(View.INVISIBLE);
            }
        });
        return cell;
    }
}
