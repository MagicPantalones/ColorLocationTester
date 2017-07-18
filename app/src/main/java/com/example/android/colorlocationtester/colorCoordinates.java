package com.example.android.colorlocationtester;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

/**
 * Copied from user Andrew87 at https://stackoverflow.com/questions/25275524/android-finding-x-and-y-positions-with-bitmap
 * Modified by MagicPantalones on 14.07.2017.
 */

public class colorCoordinates extends android.support.v7.widget.AppCompatImageView {

    Canvas mainCanvas;
    Drawable dColorMap;
    int colorCooX;
    int colorCooY;

    public colorCoordinates(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        BitmapFactory.Options mNoScale = new BitmapFactory.Options();
        mNoScale.inScaled = false;

        Bitmap dColorMap = BitmapFactory.decodeResource(getResources(), R.drawable.newcolormap, mNoScale);

        analyzeColors(dColorMap);

    }

    public void analyzeColors(Bitmap myBitmap) {
        int[] mapColorArray;
        int x = 0;
        int y = 0;
        mapColorArray = new int[myBitmap.getHeight() * myBitmap.getWidth()];
        myBitmap.getPixels(mapColorArray, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(), myBitmap.getHeight());

        for (int i = 0; i < mapColorArray.length; i++) {
            int colorHash = mapColorArray[i];
            String testing = String.format("#%06X", (0xFFFFFF & colorHash));
            int blue = Color.blue(colorHash);
            int red = Color.red(colorHash);
            int green = Color.green(colorHash);

            if (red <= 197 && green >= 200 && blue >= 62) {
                y = i / myBitmap.getWidth();
                x = i % myBitmap.getWidth();
                Log.v("FARGETEST", "x: " + Integer.toString(x) + " y: " + Integer.toString(y) + " AND Red: " + Integer.toString(red) + " Green: " + Integer.toString(green) + " Blue: " + Integer.toString(blue));
            }
        }
        colorCooX = x;
        colorCooY = y;
    }

    public int getViewResource(View v) {
        return Integer.parseInt(this.getTag().toString());
    }

}