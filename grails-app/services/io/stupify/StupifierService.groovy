package io.stupify

import grails.transaction.Transactional
import org.codehaus.groovy.grails.io.support.ClassPathResource

@Transactional
class StupifierService {
    Thesaurus t = new Thesaurus(new ClassPathResource("resources/mthesaur.txt"));
    DaleChall dc = new DaleChall(new ClassPathResource("resources/dalechall.txt"));

    def stupify(def input) {
        def text = new Text(input)
        text.getSentences().collect { sentence ->
            new Sentence(sentence.getTokens().collect { token ->
                Token.TokenCategory category = token.getCategory()
                if (category == Token.TokenCategory.ADVERB_ADJECTIVE_VERB ||
                        token.getCategory() == Token.TokenCategory.NOUN) {
                    def newWord = findEasierSynonym(token.getValue());
                    println "Stupified " + token + " to " + newWord;
                    new Token(newWord, category)
                } else {
                    token
                }
            }, sentence.getType())
        }.join(". ")
    }

    private String findEasierSynonym(String s) {
        //needs work..
        //for now, compares synonym length, then checks list of dale chall words, then finds shortest synonym in length
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


}
