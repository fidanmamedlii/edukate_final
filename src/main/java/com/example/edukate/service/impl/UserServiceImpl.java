package com.example.edukate.service.impl;

import org.modelmapper.ModelMapper;
import com.example.edukate.controller.PasswordController;
import com.example.edukate.dtos.authdtos.RegisterDto;
import com.example.edukate.dtos.userdtos.UserAddRoleDto;
import com.example.edukate.dtos.userdtos.UserDashboardListDto;
import com.example.edukate.dtos.userdtos.UserDto;
import com.example.edukate.models.Role;
import com.example.edukate.models.User;
import com.example.edukate.repositories.RoleRepository;
import com.example.edukate.repositories.UserRepository;
import com.example.edukate.service.EmailService;
import com.example.edukate.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(PasswordController.class);


    @Override
    public boolean register(RegisterDto register) {

        User user = userRepository.findByEmail(register.getEmail());
        if (user != null){
            return false;
        }
        String hashPassword = bCryptPasswordEncoder.encode(register.getPassword());
        String token = bCryptPasswordEncoder.encode(register.getEmail());
        User newUser = modelMapper.map(register, User.class);
        newUser.setEmailConfirmed(true);
        newUser.setConfirmationToken(token);
        newUser.setPassword(hashPassword);
        // Assign the default role (USER)
        Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow();
        newUser.setRoles(List.of(userRole));
        userRepository.save(newUser);
        emailService.sendConfirmationEmail(register.getEmail(),token);
        return true;
    }

    @Override
    public boolean confirmEmail(String email, String token) {

        User findUser = userRepository.findByEmail(email);
        if (findUser.getConfirmationToken().equals(token) && findUser != null)
        {
            findUser.setEmailConfirmed(true);
            userRepository.saveAndFlush(findUser);
            return true;
        }
        return false;
    }


    @Override
    public List<UserDashboardListDto> getDashboardUsers() {
        List<User> findUsers = userRepository.findAll();
        List<UserDashboardListDto> users = findUsers.stream().map(user -> modelMapper.map(user, UserDashboardListDto.class)).collect(Collectors.toList());
        return users;
    }

    @Override
    public UserDto getUserById(Long id)
    {
        User findUser = userRepository.findById(id).orElseThrow();
        UserDto user = modelMapper.map(findUser, UserDto.class);
        return user;
    }

    @Override
    public void addRole(UserAddRoleDto userAddRole) {
        User findUser = userRepository.findByEmail(userAddRole.getEmail());
        List<Role> roles = roleRepository.findAll().stream().filter(x->x.getId() == userAddRole.getRoleId()).collect(Collectors.toList());
        findUser.setRoles(roles);
        userRepository.save(findUser);

    }

    @Override
    public User findUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public void savePasswordResetToken(String email, String token) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setConfirmationToken(token);
            userRepository.save(user);
        }
    }

    @Override
    public boolean verifyToken(String token) {
        User user = userRepository.findByConfirmationToken(token);
        return user != null;
    }



    @Override
    public void updatePassword(String token, String password) {
        logger.debug("Updating password for token: {}", token);
        User user = userRepository.findByConfirmationToken(token);
        if (user != null) {
            logger.debug("User found with token: {}", token);
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setConfirmationToken(null); // Clear the reset token after successful reset
            userRepository.save(user);
            logger.debug("Password updated successfully for user: {}", user.getEmail());
        } else {
            logger.debug("No user found with token: {}", token);
        }
    }
    @Override
    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Set<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
