package com.meena.vocablearner.service;

import com.meena.vocablearner.model.SearchResponse;
import com.meena.vocablearner.model.SearchResponse.Sense;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.IndexWordSet;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.dictionary.Dictionary;
import org.springframework.stereotype.Service;

@Service
public class WordnetService {

  Dictionary dictionary;


  public WordnetService() {
    try {
      dictionary = Dictionary.getDefaultResourceInstance();
    } catch (JWNLException e) {
      throw new RuntimeException("Exception Occurred while instantiating Wordnet Dictionary!", e);
    }
  }


  public SearchResponse search(String searchWord) {
    try {
      Map<POS, List<Sense>> indexWordMap = new HashMap<>();
      IndexWordSet indexWordSet = dictionary.lookupAllIndexWords(searchWord);
      Set<POS> validPOSSet = indexWordSet.getValidPOSSet();
      for (POS pos : validPOSSet) {
        System.out.println("POS: " + pos);
        IndexWord indexWord = indexWordSet.getIndexWord(pos);
        List<Synset> senses = indexWord.getSenses();
        List<Sense> glosses = senses.stream()
          .map(sense -> SearchResponse.Sense.builder()
            .key(sense.getKey())
            //            .type(sense.getType())
            .lexFileName(sense.getLexFileName())
            //            .index(sense.getIndex())
            //            .lexFileNum(sense.getLexFileNum())
            .verbFrames(pos.equals(POS.VERB) ? sense.getVerbFrames() : new String[0])
            .gloss(sense.getGloss())
            .words(sense.getWords().stream()
              .map(word -> {
                try {
                  return SearchResponse.Word.builder()
                    .lemma(word.getLemma())
                    //                    .lexId(word.getLexId())
                    .senseNumber(word.getSenseNumber())
                    .build();
                } catch (JWNLException e) {
                  throw new RuntimeException("Exception while getting the Sense Number!", e);
                }
              })
              .collect(Collectors.toList())
            ).build())
          .collect(Collectors.toList());

        indexWordMap.put(pos, glosses);
      }
      SearchResponse searchResponse = new SearchResponse();
//      searchResponse.setValidPOSSet(validPOSSet);
      searchResponse.setIndexWordMap(indexWordMap);
      return searchResponse;

    } catch (JWNLException e) {
      throw new RuntimeException("Exception Occurred while looking-up all Index Words", e);
    }

  }

}
