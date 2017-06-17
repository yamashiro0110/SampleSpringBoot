package com.example.springbootsessionredis.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yamashiro-r on 2017/06/08.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u.* from users u where u.user_name = :name", nativeQuery = true)
    User findByName(@Param("name") String name);

}
