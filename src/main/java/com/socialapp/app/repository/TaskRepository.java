package com.socialapp.app.repository;

import com.socialapp.app.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    @Query("{ user: ObjectId(?0) }")
    List<Task> findAllByUserId(String id);

    @Query("{ id: ObjectId(?0) }")
    Optional<Task> findById(String id);

    @Query("{ done: ?0 }")
    List<Task> findByDoneStatus(boolean done);

    @Query("{ description: ?0 }")
    Optional<Task> findByDescription(String description);

    @Query(value = "{id: ObjectId(?0)}", delete = true)
    void deleteById(String id);
}
