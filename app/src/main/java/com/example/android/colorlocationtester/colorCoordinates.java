import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Copied from user Andrew87 at https://stackoverflow.com/questions/25275524/android-finding-x-and-y-positions-with-bitmap
 * Modified by MagicPantalones on 14.07.2017.
 */

public class colorCoordinates extends android.support.v7.widget.AppCompatImageView {

    Canvas mainCanvas;
    Drawable dColorMap;

    public colorCoordinates(Context context) {
        super(context);
    }


    protected void onDraw(int colorMapId, Canvas canvas) {
        super.onDraw(canvas);
        BitmapFactory.Options mNoscale = new BitmapFactory.Options();
        mNoscale.inScaled = false;
        ImageView img = (ImageView) findViewById(colorMapId);
        img.setDrawingCacheEnabled(true);
        Bitmap dColorMap = BitmapFactory.decodeResource(getResources(), colorMapId);

        analyzeColors(dColorMap);

    }

    public int[] analyzeColors(Bitmap myBitmap) {
        int[] mapColorArray;
        int x = 0;
        int y = 0;
        mapColorArray = new int[myBitmap.getHeight() * myBitmap.getWidth()];
        myBitmap.getPixels(mapColorArray, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(), myBitmap.getHeight());

        for (int i = 0; i < mapColorArray.length; i++) {
            int colorHash = mapColorArray[i];

            int blue = Color.blue(colorHash);
            int red = Color.red(colorHash);
            int green = Color.green(colorHash);
            y = i / myBitmap.getWidth();
            x = i % myBitmap.getWidth();
        }
        return mapColorArray;
    }

}