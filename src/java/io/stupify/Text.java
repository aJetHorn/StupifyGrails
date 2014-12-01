package io.stupify;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Text { //a collection of sentences
    //collection of tokens with terminal punctuation
    //consider edge cases
    
    private ArrayList<Sentence> sentences = new ArrayList<>();
    private String name;
    private String rawText;
    private int index;
    
    public Text(String rawText){ //constructor..
        this.index = 0;
        System.out.println("Rawtext: " + rawText);
        //this is rough, splits on period, exclamation, question mark
        String[] rawSentences = rawText.split("(?<=(!+|\\.+|\\?+))");
        for (int i = 0; i < rawSentences.length; i++){
            rawSentences[i] = rawSentences[i].trim(); //trim
            System.out.println(i + ": " + rawSentences[i]);
        }
        //if it is one sentence with no terminals, consider that as well
        Sentencizer sentencizer = new Sentencizer(rawSentences);
        System.out.println("Sentencizer created");

        while (sentencizer.hasNext()){
            System.out.println("adding sentence " + sentencizer.getIndex());
            sentences.add(sentencizer.nextSentence());
        }
    }

    public String getName(){ //get name of text
        return name;
    }
    
    public void setName(String s){
        this.name = s;
    }
    
    public int getIndex(){
        return index;
    }
    
    public void printRawSentences(){
        for (Sentence sentence : sentences) {
            System.out.println(sentence);
        }
    }
    
    public void printTokens(){
        for (Sentence sentence : sentences) {
            List<Token> tokens = sentence.getTokens();
            for (Token token : tokens) {
                System.out.println(token + " ");
            }
        }
    }
    public String getNewSentence(){
        StringBuilder sb = new StringBuilder();
        for (Sentence sentence : sentences) {
            List<Token> tokens = sentence.getTokens();
            for (Token token : tokens) {
                sb.append(token);
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public Sentence getSentence(int i){
        return sentences.get(i);
    }
    
    public ArrayList<Sentence> getSentences(){
        return sentences;
    }
    public Sentence getNextSentence(){
        return sentences.get(index++);
    }
    
    public String getRawText(){
        return rawText;
    } 
}
