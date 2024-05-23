package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Color {
    private String name;
    private int red;
    private int green;
    private int blue;
}
