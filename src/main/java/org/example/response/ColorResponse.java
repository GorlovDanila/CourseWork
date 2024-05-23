package org.example.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Data
public class ColorResponse {
    Map<String, String> colors;
}
