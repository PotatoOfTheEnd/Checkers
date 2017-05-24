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
    private Context mContext;
    private Integer[] mThumbIds;

    public ImageAdapter(Context c, GameBoard currBoard, int numRows, int numCols) {
        mContext = c;
        mThumbIds = new Integer[numRows * numCols];
        for (int i = 0; i < numRows * numCols; i++) {
            int r = numRows - 1 - i / numCols, col = i % numCols;
            mThumbIds[i] = currBoard.getImage(r, col);
        }
    }

    public int getCount() {
        return mThumbIds.length;
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
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

}
