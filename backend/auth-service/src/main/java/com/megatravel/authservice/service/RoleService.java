package com.megatravel.authservice.service;

import com.megatravel.authservice.model.Role;
import com.megatravel.authservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    public RoleRepository roleRepo;

    public Role findOneById(Long id){
        return roleRepo.findOneById(id);
    }

}
