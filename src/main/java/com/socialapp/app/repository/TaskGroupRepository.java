package com.socialapp.app.repository;

import com.socialapp.app.model.TaskGroup;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskGroupRepository extends MongoRepository<TaskGroup, String> {

    @Query("{ id: ObjectId(?0) }")
    Optional<TaskGroup> findById(String id);

    @Query(value = "{id: ObjectId(?0)}", delete = true)
    void deleteById(String id);
}
