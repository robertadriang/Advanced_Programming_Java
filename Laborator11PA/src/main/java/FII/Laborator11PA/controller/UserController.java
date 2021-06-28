package FII.Laborator11PA.controller;

import FII.Laborator11PA.entity.User;
import FII.Laborator11PA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/user")
public class UserController {

    private UserRepository userRepository = new UserRepository();

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        if(user.getUserName() == null || userRepository.findUserByUserName(user.getUserName()).isPresent())
            return ResponseEntity.badRequest().build();

        User createdUser = userRepository.createUser(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userName}").buildAndExpand(createdUser.getUserName()).toUri();
        return ResponseEntity.created(uri).body(createdUser);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> foundUsers = userRepository.getAllUsers();
        if(foundUsers.size() == 0 || foundUsers == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(foundUsers);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName)
    {
        Optional<User> foundUser = userRepository.findUserByUserName(userName);
        if(foundUser.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(foundUser.get());
    }

    @PutMapping("/{userName}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String userName)
    {
        if(user.getUserName() == null)
            return ResponseEntity.badRequest().build();
        if(userRepository.findUserByUserName(userName).isEmpty())
            return ResponseEntity.notFound().build();

        User updatedUser = userRepository.updateUser(user,userName);
        if(updatedUser == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<User> deleteUSer(@PathVariable String userName)
    {
        if(userName == null)
            return ResponseEntity.badRequest().build();
        if(userRepository.findUserByUserName(userName).isEmpty())
            return ResponseEntity.notFound().build();
        userRepository.deleteUser(userName);
        return ResponseEntity.noContent().build();
    }
}
