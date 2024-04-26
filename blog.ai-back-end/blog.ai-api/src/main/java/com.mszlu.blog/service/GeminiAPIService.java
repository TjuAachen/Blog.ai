package com.mszlu.blog.service;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.generativeai.ChatSession;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.mszlu.blog.vo.Result;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface GeminiAPIService {
    void setModel(String projectId, String location, String modelVersion);
    Result processPrompt(String prompt, Boolean isTraining, ChatSession chatSession);

}
