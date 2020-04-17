package ch.hearc.todont;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hearc.todont.models.ToDont;
import ch.hearc.todont.models.User;
import ch.hearc.todont.repositories.UserRepository;

@Service
public class UserDetailsImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        // Should we keep this, and try to use it to manage access ?
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (ToDont toDont : user.getOwnedToDonts()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(toDont.getId() + "_admin"));
        }
        for (ToDont toDont : user.getModeratedToDonts()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(toDont.getId() + "_moderator"));
        }

        return new org.springframework.security.core.userdetails.User(
            user.getName(),
            user.getPassword(),
            grantedAuthorities
        );
	}
}