package com.meena.vocablearner.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import net.sf.extjwnl.data.POS;

@Data
public class SearchResponse {

  private Map<POS, List<Sense>> indexWordMap;
  //  private Set<POS> validPOSSet;


  public SearchResponse() {
    this.indexWordMap = new HashMap<>();
    //    this.validPOSSet = new HashSet<>();
  }


  @Data
  @Builder
  public static class Sense {

    private Object key;
    //    private DictionaryElementType type;
    private String lexFileName;
    //    private long lexFileNum;
    private String[] verbFrames;
    //    private int index;
    private String gloss;
    private List<Word> words;

  }

  @Data
  @Builder
  public static class Word {

    private String lemma;
    //    private int lexId;
    private int senseNumber;


  }

}
