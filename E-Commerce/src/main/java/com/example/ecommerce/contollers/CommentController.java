package com.example.ecommerce.contollers;

import com.example.ecommerce.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController @RequiredArgsConstructor
@RequestMapping("api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/")
    public ResponseEntity getComment(){
        return ResponseEntity.status(200).body(commentService.getComments());
    }



}
