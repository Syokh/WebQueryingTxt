package education.read;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProceedData {

    public ArrayList<String> find(String fileName, String name, Integer limit, Integer length) {

        ArrayList<String> arrayList = new ArrayList<String>();
        File file = new File(fileName);
        Matcher matcher = null;
        String currentLine = "";
        String selection = "";


        try {
            Scanner scanner = new Scanner(file);
            Pattern pattern = Pattern.compile("\\w*(" + name + ")\\w*");
            pattern.flags();
            scanner.useDelimiter("");
            while (scanner.hasNext() && currentLine.length() < limit) {
                currentLine += scanner.next();
            }
            if (name.equals("")) {
                arrayList.add(currentLine);
                return arrayList;
            }
            matcher = pattern.matcher(currentLine);
            while (matcher.find()) {
                String value = matcher.group();
                if (length != null && value.length() >= length) {
                    selection += (value.substring(0, length) + ", ");
                } else {
                    selection += (value + ", ");
                }
            }
            arrayList.add(selection);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<String> metaData(String fileName) throws IOException {

        File file = new File(fileName);
        String nameFile = file.getName();
        String stringName = "File name: " + nameFile;

        int size = (int) file.length();
        String stringSize = "Size: " + size + " bit";

        Path path = file.toPath();
        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
        FileTime creationTime = attributes.creationTime();
        String stringData = "Data create: " + creationTime;

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(stringSize);
        arrayList.add(stringName);
        arrayList.add(stringData);

        return arrayList;
    }


}
