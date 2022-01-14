package com.socialapp.app.repository;

import com.socialapp.app.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{ username: ?0 }")
    Optional<User> findByUsername(String username);
    @Query("{ id: ObjectId (?0) }")
    Optional<User> findById(String id);

    @Query("{ email: ?0 }")
    Optional<User> findByEmail(String email);

    @Query("{}")
    List<User> findAll();
    @Query(value = "{id: ObjectId(?0)}", delete = true)
    void deleteById(String id);
}
