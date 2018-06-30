package guillaume.spyWeb.security.service.impl;

import guillaume.spyWeb.dto.UserDto;
import guillaume.spyWeb.entity.User;
import guillaume.spyWeb.security.repository.UserRepository;
import guillaume.spyWeb.security.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(userName);

        if (user.isPresent()) {
            return user.get();
        }else{
            throw new UsernameNotFoundException(String.format("the user %s can't be found.", userName));
        }
    }

    @Override
    public User create(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserDto> findAll() {

        return Stream.of(userRepository.findAll())
                .map(u -> new ModelMapper().map(u, UserDto.class))
                .collect(Collectors.toList());

    }
}
