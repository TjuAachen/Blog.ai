package com.mszlu.blog.service.impl;
import com.google.cloud.vertexai.api.GenerationConfig;
import com.google.cloud.vertexai.api.HarmCategory;
import com.google.cloud.vertexai.api.SafetySetting;
import com.google.cloud.vertexai.generativeai.ChatSession;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import com.mszlu.blog.service.GeminiAPIService;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.mszlu.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class GeminiAPIServiceImpl implements GeminiAPIService {
    private GenerativeModel geminiModel;
    public ChatSession chatSession;
    @Override
    public void setModel(String projectId, String location, String modelVersion) {
        try {
            VertexAI vertexAI = new VertexAI(projectId, location);
            GenerationConfig generationConfig =
                    GenerationConfig.newBuilder()
                            .setMaxOutputTokens(2048)
                            .setTemperature(0.9F)
                            .setTopP(1F)
                            .build();
            List<SafetySetting> safetySettings = Arrays.asList(
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_HATE_SPEECH)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build(),
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build(),
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_SEXUALLY_EXPLICIT)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build(),
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_HARASSMENT)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build()
            );
            GenerativeModel model = new  GenerativeModel.Builder().setModelName(modelVersion).setGenerationConfig(generationConfig).setSafetySettings(safetySettings).setVertexAi(vertexAI).build();
            geminiModel =  model;
            chatSession = model.startChat();
        }catch (Exception e) {
            log.error("Fail to set the Gemini Model ", e);
        }

    }

    public Result processPrompt(String prompt, Boolean isTraining, ChatSession chatSession) {

        if (!isTraining) {
            //String contraintPrompt = "Based on the previous prompts, ";
            prompt = prompt;
        }else {
            String trainingPrompt = "Please remember this prompt for later use as a context: ";
            prompt = trainingPrompt + prompt;
        }
        System.out.println(prompt);
        try {
           //GenerateContentResponse response = geminiModel.generateContent(ContentMaker.fromString(prompt));

            GenerateContentResponse response = chatSession.sendMessage(ContentMaker.fromMultiModalData(prompt));
            return Result.success(ResponseHandler.getText(response));
          //  return Result.success(response.getCandidates(0).getContent().getParts(0).getText());
        }catch (Exception e) {
            log.error("Prompt Processing ERROR ", e);
        }

        return Result.fail( 500, "No Reponse due to error");
    }

}
