package com.example.edukate.service;
import com.example.edukate.dtos.roledtos.RoleDto;
import com.example.edukate.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDto> getRoles();
    public List<Role> findAllRoles();
    public Optional<Role> findById(Long id);


}
