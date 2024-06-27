package com.example.edukate.service;

import com.example.edukate.dtos.authdtos.RegisterDto;
import com.example.edukate.dtos.userdtos.UserAddRoleDto;
import com.example.edukate.dtos.userdtos.UserDashboardListDto;
import com.example.edukate.dtos.userdtos.UserDto;
import com.example.edukate.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.management.relation.RoleInfoNotFoundException;
import java.util.List;
import java.util.Optional;

public interface UserService {
        boolean register(RegisterDto register);
        boolean confirmEmail(String email, String token);
        List<UserDashboardListDto> getDashboardUsers();
        UserDto getUserById(Long id);
        void addRole(UserAddRoleDto userAddRole);
        public User findUserByEmail(String email);
        public void savePasswordResetToken(String email, String token);
        public boolean verifyToken(String token);
        public void updatePassword(String token, String password);
        boolean checkPassword(User user, String rawPassword);
        public List<User> findAllUsers();
        public void save(User user);
        public Optional<User> findById(Long id);
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

}