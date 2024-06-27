package com.example.edukate.service.impl;
import org.modelmapper.ModelMapper;
import com.example.edukate.dtos.roledtos.RoleDto;
import com.example.edukate.models.Role;
import com.example.edukate.repositories.RoleRepository;
import com.example.edukate.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RoleDto> getRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> result=roles.stream().map(role -> modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Role> findAllRoles() {
        List<Role> roles=roleRepository.findAll();

        return roles;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.empty();
    }
}
