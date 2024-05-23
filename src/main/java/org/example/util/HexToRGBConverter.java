package org.example.util;

import java.util.ArrayList;
import java.util.List;

public class HexToRGBConverter {

    public static List<String> convert(List<String> colorsInHex) {
        List<String> colors = new ArrayList<>();
        for (String hexColor : colorsInHex) {
            colors.add(hexToRGB(hexColor));
        }
        return colors;
    }

    public static String hexToRGB(String hexColor) {
        // Убираем решётку, если она есть
        if (hexColor.startsWith("#")) {
            hexColor = hexColor.substring(1);
        }

        // Извлекаем составляющие цвета
        int red = Integer.parseInt(hexColor.substring(0, 2), 16);
        int green = Integer.parseInt(hexColor.substring(2, 4), 16);
        int blue = Integer.parseInt(hexColor.substring(4, 6), 16);

        // Формируем строку в формате RGB
        return red + ", " + green + ", " + blue;
    }
}

