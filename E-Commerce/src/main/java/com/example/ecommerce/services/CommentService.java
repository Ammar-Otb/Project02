package com.example.ecommerce.services;

import com.example.ecommerce.models.Comment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentService {
    private ArrayList<Comment> comments = new ArrayList<>();

    public ArrayList<Comment> getComments(){
        return comments;
    }

    public void addComment(Comment p){
        comments.add(p);
    }

    public Boolean updateComment(Integer index, Comment comment){
        if(index > comments.size()-1){
            return false;
        }
        comments.set(index, comment);
        return true;
    }

    public Boolean deleteComment(String commentId){
        for(Comment p: comments){
            if (commentId.equals(p.getId())){
                comments.remove(p);
                return true;
            }
        }
        return false;
    }

}
