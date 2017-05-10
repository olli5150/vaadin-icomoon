package de.headengine.vaadin.icomoon;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Oliver.Weichert on 10.05.2017.
 */
public class Enumgenerator {

    public static final String SEP_ICON = ".icon-";
    public static final String CONTENT = "content: \"\\";

    public static void main(String[] args) throws IOException {
        List<String> result = new ArrayList<String>();
        File cssFile = new File(Enumgenerator.class.getResource("/style.css").getFile());
        final String cssData = FileUtils.readFileToString(cssFile);
        final String[] rules = cssData.split("}");
        for (int i = 0; i < rules.length; i++) {
            String rule = rules[i];
            if(!StringUtils.isBlank(rule)){
                System.out.println(i+">"+rule);
                String name = rule.substring(rule.indexOf(SEP_ICON)+SEP_ICON.length(),rule.indexOf(":")).toUpperCase().replace("-","_");
                Integer value =Integer.parseInt(rule.substring(rule.indexOf(CONTENT)+CONTENT.length(),rule.lastIndexOf("\"")),16);
                result.add(name + "(" + value + ")");
            }
        }
        result.sort(Comparator.naturalOrder());
        File output = new File(Enumgenerator.class.getResource("/").getFile(),"Enums.txt");
        System.out.println(output.getAbsoluteFile());
        FileUtils.write(output, StringUtils.join(result, ",\n"));
    }
}
