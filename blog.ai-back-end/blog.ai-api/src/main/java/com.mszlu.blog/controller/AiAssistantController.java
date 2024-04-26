package com.mszlu.blog.controller;

import com.google.cloud.vertexai.generativeai.ChatSession;
import com.mszlu.blog.service.impl.GeminiAPIServiceImpl;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.PromptParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Configuration
@RestController
public class AiAssistantController {
    @Autowired
    GeminiAPIServiceImpl geminiAPIService;


    @Value("${gemini.projectId}")
    private String projectId;

    @Value("${gemini.location}")
    private String location;

    @Value("gemini-1.0-pro-001")
    private String modelVersion;

    private ChatSession chatSession;

    @PostConstruct
    public void init() {
        geminiAPIService.setModel(projectId, location, modelVersion);
        chatSession = geminiAPIService.chatSession;
    }

    @PostMapping("prompt")
    public Result getResponse(@RequestBody PromptParam promptParam){
        return geminiAPIService.processPrompt(promptParam.getContent(), promptParam.getTraining(), chatSession);
    }

}
