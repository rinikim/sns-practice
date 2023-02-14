package com.fastcampus.sns.controller;

import com.fastcampus.sns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
}
