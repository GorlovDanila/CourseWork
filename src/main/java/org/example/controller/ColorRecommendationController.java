package org.example.controller;

import org.example.entity.Color;
import org.example.response.ColorResponse;
import org.example.util.ColorExtractor;
import org.example.util.ColorRounder;
import org.example.util.HexToRGBConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/rec")
public class ColorRecommendationController {

    private static final String PATH = "C:\\Users\\danil\\IdeaProjects\\CourseWork\\src\\main\\resources\\file";

    @PostMapping("/upload")
    public ResponseEntity<ColorResponse> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        saveFile(file, PATH);
        List<String> colorsInHex = ColorExtractor.extract();
        List<String> colorsRgb = HexToRGBConverter.convert(colorsInHex);
        List<Color> colorList = ColorRounder.round(colorsRgb);

        Map<Color, Long> colorCountMap = colorList.stream()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        List<Map.Entry<Color, Long>> sortedEntries = new ArrayList<>(colorCountMap.entrySet());
        sortedEntries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        List<Color> mostFrequentColors = sortedEntries.stream()
                .limit(2)
                .map(Map.Entry::getKey)
                .toList();

        for (Color color : mostFrequentColors) {
            System.out.println(color.getName());
        }

        Map<String, String> map = new HashMap<>();
        map.put("LIGHT PRIMARY COLOR", "#FFF9C4");
        map.put("SECONDARY TEXT", "#757575");
        map.put("DARK PRIMARY COLOR", "#FBC02D");
        map.put("DIVIDER COLOR", "#BDBDBD");
        map.put("PRIMARY COLOR", "#FFEB3B");
        map.put("ACCENT COLOR", "#FFEB3B");
        map.put("PRIMARY TEXT", "#212121");
        map.put("TEXT / ICONS", "#212121");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new ColorResponse(
                                map
                        )
                );
    }

    public void processFile(MultipartFile file) throws IOException {
        byte[] fileBytes = file.getBytes();
        String fileContent = new String(fileBytes);
        System.out.println("File content:");
        System.out.println(fileContent);
    }

    public void saveFile(MultipartFile file, String filePath) throws IOException {
        byte[] fileBytes = file.getBytes();
        Path path = Paths.get(filePath);
        Files.write(path, fileBytes);
        System.out.println("File saved successfully to: " + filePath);
    }
}
