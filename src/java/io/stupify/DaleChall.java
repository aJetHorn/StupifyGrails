package io.stupify;

import org.codehaus.groovy.grails.io.support.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DaleChall {
    private Set<String> words;

    public DaleChall(Resource resource) {
        words = new HashSet<>();
        readList(resource);
    }

    private void readList(Resource resource) {
        try {
            System.out.println(resource.getFile().getAbsoluteFile());
            BufferedReader in = new BufferedReader(new FileReader(resource.getFile()));
            String line;
            while ((line = in.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException ex) {
            System.out.println("There was an error in StringBuilder, check input file(s)");
        }
    }

    public boolean isWordOnList(String word) {
        return words.contains(word);
    }
}