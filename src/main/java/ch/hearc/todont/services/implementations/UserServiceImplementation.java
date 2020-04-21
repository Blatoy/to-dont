package ch.hearc.todont.services.implementations;

import ch.hearc.todont.datatransfer.UserDto;
import ch.hearc.todont.models.User;
import ch.hearc.todont.repositories.UserRepository;
import ch.hearc.todont.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserDto userDto) throws Exception {
        User existing = userRepo.findByEmail(userDto.getEmail());
        if (existing != null) {
            throw new Exception();
        }

        User user = new User();
        user.setName(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(
            passwordEncoder.encode(userDto.getPassword())
        );

        userRepo.save(user);

        return user;
    }

}