package com.meena.vocablearner.configuration;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatModelConfiguration {

  //  String apiKey = System.getenv("OPENAI_API_KEY");
  String apiKey = "demo";


  @Bean
  public OpenAiChatModel openAiChatModel() {
    return OpenAiChatModel.withApiKey(apiKey);
  }

}
