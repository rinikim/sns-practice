package com.fastcampus.sns.controller;

import com.fastcampus.sns.controller.request.PostCreateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest   {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser   // 인증된 유저
    void 포스터작성() throws Exception {
        String title = "title";
        String body = "body";

        mockMvc.perform(post("/api/vi/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new PostCreateRequest(title, body)))
                ).andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @WithAnonymousUser // 익명의 유저
    void 포스트작성시_로그인하지않은경우() throws Exception {
        String title = "title";
        String body = "body";

        // 로그인을 하지 않은 경우

        mockMvc.perform(post("/api/vi/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new PostCreateRequest(title, body)))
                ).andDo(print())
                .andExpect(status().isOk());

    }
}
