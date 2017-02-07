package com.tigerspike.chrisnevin.movies;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by chris.nevin on 11/01/2017.
 */

public class MoviesGridView extends GridView {
    public MoviesGridView(Context context) {
        super(context);
    }

    public MoviesGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MoviesGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public int computeVerticalScrollOffset() {
        return super.computeVerticalScrollOffset();
    }
}
