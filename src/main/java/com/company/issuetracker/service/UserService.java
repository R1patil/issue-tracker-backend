// package com.company.issuetracker.service;

// import com.company.issuetracker.dto.UserRequestDto;
// import com.company.issuetracker.entity.User;
// import com.company.issuetracker.repository.UserRepository;
// import org.springframework.stereotype.Service;
// import java.util.List;

// @Service
// public class UserService {
//     private final UserRepository repo;

//     public UserService(UserRepository repo) {
//         this.repo = repo;
//     }

//     // without using DTO
//     // public User save(User user) {
//     //     return repo.save(user);
//     // }

//     // using DTO

// public User save(UserRequestDto dto) {

//     User user = new User();
//     user.setName(dto.getName());
//     user.setEmail(dto.getEmail());

//     return repo.save(user);
// }

//     public List<User> findAll() {
//         return repo.findAll();
//     }

// }
package com.company.issuetracker.service;

import java.util.List;

import com.company.issuetracker.dto.UserRequestDto;
import com.company.issuetracker.entity.User;
import com.company.issuetracker.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    // ✅ Constructor injection (BEST PRACTICE)
    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(UserRequestDto dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        // ✅ Safe: passwordEncoder is guaranteed initialized
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return repo.save(user);
    }

    public User createUser(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());

        // 🔥 THIS IS THE KEY LINE
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return repo.save(user);
    }

    public List<User> findAll() {
        return repo.findAll();
    }
}
