package com.meena.vocablearner.controller;

import com.meena.vocablearner.model.SearchResponse;
import com.meena.vocablearner.service.WordnetService;
import net.sf.extjwnl.data.IndexWordSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordnetController {

  private final WordnetService wordnetService;


  public WordnetController(WordnetService wordnetService) {
    this.wordnetService = wordnetService;
  }


  @GetMapping("/search/{word}")
  public SearchResponse search(@PathVariable String word) {
    return wordnetService.search(word);
  }

}
