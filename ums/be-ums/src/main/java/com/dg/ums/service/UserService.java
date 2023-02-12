package com.dg.ums.service;

import com.dg.ums.entities.DGUserEntity;
import com.dg.ums.model.APIStatusResponse;
import com.dg.ums.model.DGUser;
import com.dg.ums.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<DGUser> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertFromEntity).collect(Collectors.toList());
    }

    public DGUser addUser(DGUser inputUser) {
        DGUserEntity entity = convertToEntity(inputUser);
        return convertFromEntity(userRepository.save(entity));
    }

    private DGUser convertFromEntity(DGUserEntity dbUser) {
        DGUser user = new DGUser();
        user.setId(dbUser.getId());
        user.setFirstName(dbUser.getFirstName());
        user.setLastName(dbUser.getLastName());
        user.setDob(dbUser.getDob());
        user.setEmail(dbUser.getEmail());
        user.setUsername(dbUser.getUsername());
        user.setProfilePic(dbUser.getProfilePic());
        return user;
    }

    private DGUserEntity convertToEntity(DGUser inputUser) {
        DGUserEntity userEntity = new DGUserEntity();
        userEntity.setFirstName(inputUser.getFirstName());
        userEntity.setLastName(inputUser.getLastName());
        userEntity.setDob(inputUser.getDob());
        userEntity.setEmail(inputUser.getEmail());
        userEntity.setUsername(inputUser.getUsername());
        return userEntity;
    }

    public DGUser getUserById(int userId) {
        Optional<DGUserEntity> entityOptional = userRepository.findById(userId);
        if (entityOptional.isPresent()) {
            return convertFromEntity(entityOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User Not Found with userId:%s", userId));
        }
    }

    public DGUser updateUser(int userId, DGUser user) {
        if(userRepository.existsById(userId)){
            DGUserEntity userEntity = convertToEntity(user);
            userEntity.setId(userId);
            return convertFromEntity(userRepository.save(userEntity));
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User Not Found with userId:%s", userId));
        }
    }

    public APIStatusResponse deleteUser(int userId) {
        userRepository.deleteById(userId);
        return new APIStatusResponse(HttpStatus.OK,"User is Deleted Successfully.");
    }

    public DGUser getUserByUsername(String username) {
        Optional<DGUserEntity> entityOptional = userRepository.findByUsername(username);
        if (entityOptional.isPresent()) {
            return convertFromEntity(entityOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User Not Found with given username:%s", username));
        }
    }
}
