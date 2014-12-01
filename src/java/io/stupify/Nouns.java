package io.stupify;

//copy of the DaleChall class, can simplify to just DaleChall but made just in case we need to add
//more to this specific class

import org.codehaus.groovy.grails.io.support.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Nouns {
    private String[] nouns;

    public Nouns(Resource resource) {;
        nouns = new String[91000];
        readList(resource);
    }

    private void readList(Resource resource) {
        int numWords = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(resource.getFile()));
            String line;
            while ((line = in.readLine()) != null) {
                numWords++;
                nouns[numWords - 1] = line.trim();
            }
        } catch (IOException ex) {
            System.out.println("There was an error in StringBuilder, check input file(s)");
        }
    }

    public boolean isWordOnList(String word) {
        for (String noun : nouns) {
            if (word.equalsIgnoreCase(noun)) {
                return true;
            }
        }
        return false;
    }
}