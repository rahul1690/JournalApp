package net.engineeringdigest.journalApp.service;

import java.util.List;
import java.util.Optional;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteByUserName(String userName){
        User user = userRepository.findByUserName(userName).get();
        userRepository.deleteById(user.getId());
    }

    public Optional<User> findByUserName(String username){
        return userRepository.findByUserName(username);
    }

}
