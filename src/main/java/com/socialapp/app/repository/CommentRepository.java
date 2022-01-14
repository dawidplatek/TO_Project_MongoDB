package com.socialapp.app.repository;

import com.socialapp.app.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    @Query("{ id: ObjectId(?0) }")
    Optional<Comment> findById(String id);
    @Query(value = "{id: ObjectId(?0)}", delete = true)
    void deleteById(String id);

    @Query("{task: ObjectId(?0)}")
    List<Comment> findAllByTaskId(String id);
}
