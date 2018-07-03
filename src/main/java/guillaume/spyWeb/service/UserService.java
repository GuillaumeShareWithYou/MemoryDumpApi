package guillaume.spyWeb.service;

import guillaume.spyWeb.dto.UserDto;
import guillaume.spyWeb.entity.User;
import guillaume.spyWeb.exception.UserNotFoundException;
import guillaume.spyWeb.repository.UserRepository;
import guillaume.spyWeb.tools.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(userName);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("the user %s can't be found.", userName));
        }
        return user.get();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("the user with id %s cannot be found.", userId)));
    }

    @Transactional
    public User create(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public List<UserDto> findAll() {
        return Converter.map(userRepository.findAll(), UserDto.class);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

}
