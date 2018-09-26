package com.last.fm.poc.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import com.last.fm.poc.datamodels.Image;
import com.last.fm.poc.datamodels.SearchData;

import java.util.List;

public class DataModelUtils {

    public static String getImageURL(SearchData searchData, Context context) {
        String imageURL = null;
        List<Image> images = searchData.getImage();
        if (images != null && !images.isEmpty()) {
            for (Image image : images) {
                if (image.getText() != null && !image.getText().isEmpty()) {
                    imageURL = image.getText();
                    if (getSizeName(context).equalsIgnoreCase(image.getSize()))
                        break;
                }
            }
        }
        return imageURL;
    }

    private static String getSizeName(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int density = metrics.densityDpi;

        if (density >= DisplayMetrics.DENSITY_XXHIGH)
            return "extralarge";
        else if (density >= DisplayMetrics.DENSITY_XHIGH)
            return "large";
        else if (density >= DisplayMetrics.DENSITY_HIGH)
            return "medium";
        else
            return "small";
    }
}
