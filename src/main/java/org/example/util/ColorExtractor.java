package org.example.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorExtractor {

    public static List<String> extract() throws IOException {
        String filePath = "C:\\Users\\danil\\IdeaProjects\\CourseWork\\src\\main\\resources\\file";
        return extractHexColors(filePath);
    }

    public static List<String> extractHexColors(String filePath) throws IOException {
        List<String> colors = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        Pattern pattern = Pattern.compile("#([0-9a-fA-F]{6}|[0-9a-fA-F]{3})");

        while ((line = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                colors.add(matcher.group());
            }
        }

        reader.close();
        return colors;
    }
}