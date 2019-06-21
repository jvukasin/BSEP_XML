package com.xml.MegaTravelAgent.service;

import com.xml.MegaTravelAgent.model.TPerson;
import com.xml.MegaTravelAgent.repository.TPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TPersonService {

    @Autowired
    private TPersonRepository tPersonRepo;

    public TPerson findOneByUsername(String username) {
        return tPersonRepo.findOneByUsername(username);
    }

    public List<TPerson> findAll() {
        return tPersonRepo.findAll();
    }




}
