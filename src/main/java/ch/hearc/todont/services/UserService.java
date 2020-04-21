package ch.hearc.todont.services;

import ch.hearc.todont.datatransfer.UserDto;
import ch.hearc.todont.models.User;

public interface UserService {
    User registerUser(UserDto userDto) throws Exception;
}