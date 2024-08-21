package com.krishnaintech.blog.service.imp;

import com.krishnaintech.blog.entity.User;
import com.krishnaintech.blog.exceptions.ResourceNotFoundException;
import com.krishnaintech.blog.payload.UserDto;
import com.krishnaintech.blog.repository.UserRepo;
import com.krishnaintech.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public UserServiceImp(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User userCreated = userRepo.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User userExisting = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id", userId));
        userExisting.setName(userDto.getName());
        userExisting.setEmail(userDto.getEmail());
        userExisting.setPassword(userDto.getPassword());
        userExisting.setAbout(userDto.getAbout());
        User user = userRepo.save(userExisting);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User existingUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return modelMapper.map(existingUser, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepo.findAll();
        return users.stream().map((user) -> modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    public void deleteUser(Integer userId) {
        User existingUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        userRepo.delete(existingUser);
    }

    public UserDto userToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
    public User DtoToUser(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }
}
