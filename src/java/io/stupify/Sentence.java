package io.stupify;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
    public enum SentenceType {IMPERATIVE_DECLARATIVE, EXCLAMATORY, INTERROGATIVE, UNKNOWN}

    private ArrayList<Token> tokens = new ArrayList<>();
    private SentenceType type; //type

    public Sentence(ArrayList<Token> tokens, SentenceType type) {
        this.tokens = tokens;
        this.type = type;
    }

    public Sentence(String raw, SentenceType type) {
        this.type = type;
        System.out.println("sentence crated with type: " + type);
        System.out.println("time to tokenize");
        Tokenizer tokenizer = new Tokenizer(raw);
        System.out.println("tokenizer created");

        while (tokenizer.hasNext()) {
            Token nextToken = tokenizer.nextToken();
            System.out.println("Token in sentence: " + nextToken);
            tokens.add(nextToken);
        }
    }

    public static String typeToString(SentenceType type) {
        switch (type) {
            case IMPERATIVE_DECLARATIVE:
                return "Imperative or Declarative";
            case EXCLAMATORY:
                return "Exclamatory";
            case INTERROGATIVE:
                return "Interrogative";
            case UNKNOWN:
                return "Unknown";
            default:
                return "No Value";
        }
    }


    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public SentenceType getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Token t : tokens) {
            sb.append(t.getValue());
            sb.append(" ");
        }
        return sb.toString();
    }
}
