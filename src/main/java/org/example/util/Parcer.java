package org.example.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parcer {
    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream fileOut = new FileOutputStream("output.txt");
        PrintStream printOut = new PrintStream(fileOut);
        System.setOut(printOut);

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\danil\\Downloads\\chrome-win64\\chrome-win64\\chrome.exe");

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\danil\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.materialpalette.com/light-blue/cyan");

        driver.getTitle();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        List<WebElement> elements = driver.findElements(By.className("palette-color-wrapper"));

        for (WebElement element : elements) {
            WebElement childElement = element.findElement(By.cssSelector("div[style*='background']"));
            String styleAttributeValue = childElement.getAttribute("style");
            String[] rgbValues = styleAttributeValue.split("rgb\\(")[1].split("\\)")[0].split(",");

            int red = Integer.parseInt(rgbValues[0].trim());
            int green = Integer.parseInt(rgbValues[1].trim());
            int blue = Integer.parseInt(rgbValues[2].trim());
            String title = childElement.getText().replace("check", "").replace("\n", "");
            if (title.isEmpty()) {
                System.out.println("WHITE" + " " + "(" + red + ", " + green + ", " + blue + ")");
            } else {
                System.out.println(title + " " + "(" + red + ", " + green + ", " + blue + ")");
            }
        }

        System.out.println("-----------------------------------");
        WebElement element = driver.findElement(By.cssSelector(".theme-palette-colors.clearfix"));

        Map<String, String> map = new HashMap<>();

        String[] keyValuePairs = element.getText().split("\n");

        for (int i = 0; i < keyValuePairs.length; i += 2) {
            String key = keyValuePairs[i];
            String value = keyValuePairs[i + 1];
            map.put(key, value);
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        driver.quit();
        printOut.close();
    }
}
