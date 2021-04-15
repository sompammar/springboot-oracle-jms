package com.example.springboot.businesslogicservice;

import com.example.springboot.JsonUtil;
import com.example.springboot.dbservice.UserDBService;
import com.example.springboot.dto.PostDTO;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.entity.BatchRequest;
import com.example.springboot.entity.Post;
import com.example.springboot.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchRequestService implements IBatchRequestService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDBService userDBService;


    @Override
    public void executeReq(BatchRequest req) {
        System.out.println("Executing req");
        System.out.println(JsonUtil.toJson(req));
    }

    public void registerUserAndSavePosts(UserDTO user, List<PostDTO> posts) {
        List<Post> postEntities = posts.stream().map(this::convertToEntity).collect(Collectors.toList());
        User userEntity = convert(user, User.class);
        userDBService.saveUser(userEntity);
    }

    private PostDTO convertToDto(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

    private Post convertToEntity(PostDTO dto) {
        return modelMapper.map(dto, Post.class);
    }


    private <T> T convertToDto(Object post, Class<T> dtoClass) {
        return modelMapper.map(post, dtoClass);
    }

    private <T> T convert(Object dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
}
