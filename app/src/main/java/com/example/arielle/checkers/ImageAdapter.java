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
    public ImageAdapter(Context c, CheckersBoard currBoard, int player) {
        mContext = c;
        mThumbIds = new Integer[64];
        for(int i=0; i<64; i++){
            int r = 7-i/8, col=i%8;
            if (currBoard.isValidIndex(r, col)){
                if (currBoard.getPlayerAt(r, col)==0){
                    mThumbIds[i] = R.drawable.blacksquare;
                }
                else if ((currBoard.getPlayerAt(r, col)==1 && player==0)|| (currBoard.getPlayerAt(r, col)==2 && player==1)){
                    if (currBoard.getTypeOfPieceAt(r,col)==1) {
                        mThumbIds[i] = R.drawable.redpiece;
                    }
                    else{
                        mThumbIds[i] = R.drawable.redking;
                    }
                }
                else{
                    if (currBoard.getTypeOfPieceAt(r,col)==1) {
                        mThumbIds[i] = R.drawable.blackpiece;
                    }
                    else{
                        mThumbIds[i] = R.drawable.blackking;
                    }
                }
            }
            else{
                mThumbIds[i] = R.drawable.whitesquare;
            }

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
