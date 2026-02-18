package com.app.ecom.service;

import com.app.ecom.model.User;
import com.app.ecom.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private List<User> userList = new ArrayList();

    public List<User> getAllusers()
    {
        return userRepository.findAll();
    }
    public void adduser(User user){
        userRepository.save(user);
    }

    public boolean updateUser(User updatedUser, Long id){

        userList = userRepository.findAll();
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    existingUser.setId(id);
                    userRepository.save(existingUser);
                    return true;
                })
                .orElse(false);



        /*Optional<User> userfromDB = Optional.of(new User());
        userfromDB = userRepository.findById(id);
        user.setAddress();
        userRepository.save(user);*/
    }

    public Optional<User> fetchUser(Long id)
    {

        /*return userList.stream()
                .filter(user -> user.getId().eq*/

       return userRepository.findById(id);
    }
}
