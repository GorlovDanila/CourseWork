package org.example.util;

import java.util.ArrayList;
import java.util.List;
import org.example.entity.Color;

public class ColorRounder {

    public static List<Color> round(List<String> colors) {
        List<Color> colorsRounded = new ArrayList<>();
        List<Color> colorList = createColorList();
        for (String color : colors) {

            int targetRed = Integer.parseInt(color.split(", ")[0]);
            int targetGreen = Integer.parseInt(color.split(", ")[1]);
            int targetBlue = Integer.parseInt(color.split(", ")[2]);
            colorsRounded.add(findNearestColor(targetRed, targetGreen, targetBlue, colorList));
        }
        return colorsRounded;
    }

    public static List<Color> createColorList() {
        List<Color> colorList = new ArrayList<>();
        colorList.add(new Color("red", 244, 67, 54));
        colorList.add(new Color("pink", 233, 30, 99));
        colorList.add(new Color("purple", 156, 39, 176));
        colorList.add(new Color("deep-purple", 103, 58, 183));
        colorList.add(new Color("indigo", 63, 81, 181));
        colorList.add(new Color("blue", 33, 150, 243));
        colorList.add(new Color("light-blue", 3, 169, 244));
        colorList.add(new Color("cyan", 0, 188, 212));
        colorList.add(new Color("teal", 0, 150, 136));
        colorList.add(new Color("green", 76, 175, 80));
        colorList.add(new Color("light-green", 139, 195, 74));
        colorList.add(new Color("lime", 205, 220, 57));
        colorList.add(new Color("yellow", 255, 235, 59));
        colorList.add(new Color("amber", 255, 193, 7));
        colorList.add(new Color("orange", 255, 152, 0));
        colorList.add(new Color("deep-orange", 255, 87, 34));
        colorList.add(new Color("brown", 121, 85, 72));
        colorList.add(new Color("grey", 158, 158, 158));
        colorList.add(new Color("blue-grey", 96, 125, 139));
        return colorList;
    }

    public static Color findNearestColor(int targetRed, int targetGreen, int targetBlue, List<Color> colorList) {
        Color nearestColor = null;
        double minDistance = Double.MAX_VALUE;

        for (Color color : colorList) {
            double distance = calculateDistance(targetRed, targetGreen, targetBlue, color.getRed(), color.getGreen(), color.getBlue());
            if (distance < minDistance) {
                minDistance = distance;
                nearestColor = color;
            }
        }

        return nearestColor;
    }

    public static double calculateDistance(int x1, int y1, int z1, int x2, int y2, int z2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
    }
}
