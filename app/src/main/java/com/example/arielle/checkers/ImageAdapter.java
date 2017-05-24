package com.example.arielle.checkers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by arielle on 13/04/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private Integer[] thumbIds;

    public ImageAdapter(Context c, GameBoard currBoard, int numRows, int numCols) {
        context = c;
        thumbIds = new Integer[numRows * numCols];
        for (int i = 0; i < numRows * numCols; i++) {
            int r = numRows - 1 - i / numCols, col = i % numCols;
            thumbIds[i] = currBoard.getImage(r, col);
        }
    }

    public int getCount() {
        return thumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            //imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(thumbIds[position]);
        return imageView;
    }

}
