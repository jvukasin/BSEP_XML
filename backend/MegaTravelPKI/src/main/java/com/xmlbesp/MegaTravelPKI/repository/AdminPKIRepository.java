package com.xmlbesp.MegaTravelPKI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xmlbesp.MegaTravelPKI.model.AdminPKI;
import com.xmlbesp.MegaTravelPKI.model.Certificate;

public interface AdminPKIRepository extends JpaRepository<AdminPKI, Long>, JpaSpecificationExecutor<AdminPKI>{

}
