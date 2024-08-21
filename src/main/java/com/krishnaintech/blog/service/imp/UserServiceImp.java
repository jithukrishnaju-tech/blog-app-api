package com.krishnaintech.blog.service.imp;

import com.krishnaintech.blog.entity.User;
import com.krishnaintech.blog.exceptions.ResourceNotFoundException;
import com.krishnaintech.blog.payload.UserDto;
import com.krishnaintech.blog.repository.UserRepo;
import com.krishnaintech.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
        User user = this.DtoToUser(userDto);
        User userCreated = userRepo.save(user);
        return this.userToDto(userCreated);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User userExisting = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id", userId));
        userExisting.setName(userDto.getName());
        userExisting.setEmail(userDto.getEmail());
        userExisting.setPassword(userDto.getPassword());
        userExisting.setAbout(userDto.getAbout());
        User user = userRepo.save(userExisting);
        return this.userToDto(user);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User existingUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return this.userToDto(existingUser);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepo.findAll();
        return users.stream().map(this::userToDto).toList();
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
