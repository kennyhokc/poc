package com.example.api;

import com.example.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserApi implements UsersApiDelegate {

    @Override
    public ResponseEntity<User> getUsers(Long userId) {
        var user = new User();
        user.setId(1L);
        user.setName("Kenny Ho");
        return ResponseEntity.ok(user);
    }
}
