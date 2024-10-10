package net.engineeringdigest.journalApp.controller;

import java.util.List;
import java.util.Optional;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok( userService.getAll());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        userService.saveUser(user);
        return ResponseEntity.accepted().body(user);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getUserName(@PathVariable("userName") String userName){
        Optional<User> byUserName = userService.findByUserName(userName);
        if(byUserName.isPresent()) return ResponseEntity.ok(byUserName.get());
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userName") String userName){
       userService.deleteByUserName(userName);
       return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateJournalById(@RequestBody User user,@PathVariable String userName){
        Optional<User> userInDbOp = userService.findByUserName(userName);
        if(!userInDbOp.isPresent()) return ResponseEntity.notFound().build();
        User userInDb = userInDbOp.get();
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveUser(userInDb);
        return ResponseEntity.noContent().build();
    }

}
