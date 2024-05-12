package com.meena.vocablearner.service;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service
public class ChatModelService {

  private final OpenAiChatModel chatModel;


  public ChatModelService(OpenAiChatModel chatModel) {
    this.chatModel = chatModel;

    // Test
    String answer = chatModel.generate("Say 'Hello World'");
    System.out.println(answer); // Hello World
  }


}
