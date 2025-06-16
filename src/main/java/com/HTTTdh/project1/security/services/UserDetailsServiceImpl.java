package com.HTTTdh.project1.security.services;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.HTTTdh.project1.models.User;
import com.HTTTdh.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }
  public User findByUsername(String username) {
    Optional<User> users =  userRepository.findByUsername(username);
    return users.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
  }
  public User findById(Long id) {
    Optional<User> users =  userRepository.findById(id);
    return users.orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));
  }
  public User updateUser(User user) {
    Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());

    if (optionalUser.isPresent()) {
      User existingUser = optionalUser.get();
      existingUser.setEmail(user.getEmail());
      existingUser.setPassword(passwordEncoder.encode(user.getPassword()));

      return userRepository.save(existingUser);
    } else {
      throw new RuntimeException("User not found with username: " + user.getUsername());
    }
  }


}
