package io.stupify;

import org.codehaus.groovy.grails.io.support.ClassPathResource;

import java.util.ArrayList;
import java.util.List;

public class Stupifier {
    private Text inputText;
    private Text outputText;

    Thesaurus t = new Thesaurus(new ClassPathResource("resources/mthesaur.txt"));
    DaleChall dc = new DaleChall(new ClassPathResource("resources/dalechall.txt"));

    public Stupifier(Text in) {
        this.inputText = in;
    }

    public void stupify() {
        outputText = inputText; //preserve input
        //first pass
        ArrayList<Sentence> sentences = outputText.getSentences();
        for (Sentence sentence : sentences) {
            ArrayList<Token> tokens = sentence.getTokens();
            for (Token currentToken : tokens) {
                if (Token.categoryToString(currentToken.getCategory()).equalsIgnoreCase("Adverb_Adjective_Verb") ||
                        Token.categoryToString(currentToken.getCategory()).equalsIgnoreCase("Noun")) {
                    String newWord = findEasierSynonym(currentToken.getValue());
                    System.out.println("Stupified " + currentToken + " to " + newWord);
                    currentToken.setValue(newWord);
                }
            }
        }
    }

    //has easier synonym, common words as well

    public String findEasierSynonym(String s) {
        //needs work..
        //for now, compares synonym length, then checks list of dale chall words, then finds shortest synonym in length
        System.out.println(s);
        if (t.getSynonyms(s.toLowerCase()) != null) {
            List<String> syn = t.getSynonyms(s.toLowerCase());
            String small = syn.get(0);
            for (String aSyn : syn) {
                if (s.length() > aSyn.length()) {
                    if (dc.isWordOnList(aSyn)) {
                        small = aSyn;
                        break;
                    } else {
                        if (aSyn.length() < small.length()) {
                            small = aSyn;
                        }
                    }
                }
            }
            s = small;
        } else {
            System.out.println(s + " couldn't be stupified any more");
        }
        return s;
    }

    public void newInputText(Text in) {
        this.inputText = in;
    }

    public Text getOutputText() {
        return outputText;
    }

    public Text getInputText() {
        return inputText;
    }
}