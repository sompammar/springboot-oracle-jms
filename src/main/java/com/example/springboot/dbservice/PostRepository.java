package com.example.springboot.dbservice;

import com.example.springboot.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
