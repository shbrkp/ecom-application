package com.app.ecom.controller;

import com.app.ecom.model.User;
import com.app.ecom.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private List<User> userList = new ArrayList<>();

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers()
    {
        System.out.println("getAllUsers");
        userList = userService.getAllusers();
        if(userList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(userList);

    }

    @PostMapping("/api/users")
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.adduser(user);
        return ResponseEntity.ok("User added successfully");
    }

   @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){

        /*Optional<User> user = userService.fetchUser(id);
        if(user.isEmpty()) sample
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userService.fetchUser(id));*/


        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User updatedUser,
                                             @PathVariable Long id){
        System.out.println("updatedUser----"+updatedUser.getFirstName());
        boolean updatedUserBool = userService.updateUser(updatedUser,id);
        System.out.println("updatedUserBool----"+updatedUserBool);
        if(updatedUserBool)
            return ResponseEntity.ok("User updated successfully");
        return ResponseEntity.notFound().build();
    }
}
