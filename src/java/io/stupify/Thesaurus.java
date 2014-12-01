package io.stupify;

import org.codehaus.groovy.grails.io.support.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Thesaurus {

    private Map<String, ArrayList<String>> synonyms = new HashMap<>();

    public Thesaurus(Resource resource) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(resource.getFile()));
            String line;
            while ((line = input.readLine()) != null) {
                ArrayList<String> synonymList = new ArrayList<>();
                String[] tempWords = line.split(",");
                synonymList.addAll(Arrays.asList(tempWords).subList(1, tempWords.length));
                synonyms.put(tempWords[0], synonymList);
            }
        } catch (IOException ex) {
            System.out.println("There was an error in BufferedReader, check input file(s)");
        }
    }

    public List<String> getSynonyms(String lookupWord) {
        return synonyms.get(lookupWord);
    }
}