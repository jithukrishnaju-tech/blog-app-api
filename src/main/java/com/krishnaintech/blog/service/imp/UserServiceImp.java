package com.krishnaintech.blog.service.imp;

import com.krishnaintech.blog.entity.User;
import com.krishnaintech.blog.exceptions.ResourceNotFoundException;
import com.krishnaintech.blog.payload.UserDto;
import com.krishnaintech.blog.repository.UserRepo;
import com.krishnaintech.blog.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImp implements UserService {
    private final UserRepo userRepo;

    public UserServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userDto.dtoToUser();
        User userCreated = userRepo.save(user);
        return userDto.userToDto(userCreated);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User userExisting = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id", userId));
        userExisting.setName(userDto.getName());
        userExisting.setEmail(userDto.getEmail());
        userExisting.setPassword(userDto.getPassword());
        userExisting.setAbout(userDto.getAbout());
        User user = userRepo.save(userExisting);
        return userDto.userToDto(user);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User existingUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        UserDto userDto = new UserDto();
        return userDto.userToDto(existingUser);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepo.findAll();
        return users.stream().map((user) -> new UserDto().userToDto(user)).toList();
    }

    @Override
    public void deleteUser(Integer userId) {
        User existingUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        userRepo.delete(existingUser);
    }
}
