package io.stupify;

public class Token {
    //WORD was broken into parts of speech excluding interjections
    //Word -> article, preposition, conjunction, pronoun, adv_adj_noun_verb
    //public enum TokenCategory {WORD, TERMINAL, SYMBOL, NUMERAL, INVALID}
    public enum TokenCategory {
        ARTICLE, PREPOSITION, CONJUNCTION, PRONOUN,
        PROPER_NOUN, NOUN, ADVERB_ADJECTIVE_VERB, TERMINAL, SYMBOL, NUMERAL, INVALID
    }

    ;

    private String value;
    private TokenCategory category; //category

    public String getValue() {
        return value;
    }

    public TokenCategory getCategory() {
        return category;
    }

    public void setValue(String val) {
        this.value = val;
    }

    public void setCategory(TokenCategory cat) {
        this.category = cat;
    }

    public Token(String val, TokenCategory cat) {
        this.value = val;
        this.category = cat;
    }

    public Token() { //default
    }

    public String toString() {
        return this.value + " (" + this.category + ")";
    }

    public static String categoryToString(TokenCategory cat) { //turns expected enum into string
        //this could use some work
        switch (cat) {
            case ARTICLE:
                return "Article";
            case PREPOSITION:
                return "Preposition";
            case CONJUNCTION:
                return "Conjunction";
            case PRONOUN:
                return "Pronoun";
            case PROPER_NOUN:
                return "Proper_Noun";
            case ADVERB_ADJECTIVE_VERB:
                return "Adverb_Adjective_Verb";
            case NOUN:
                return "Noun";
            case TERMINAL:
                return "Terminal";
            case SYMBOL:
                return "Symbol";
            case NUMERAL:
                return "Numeral";
            case INVALID:
                return "Invalid";
            default:
                return "";
        }
    }
}
