package com.megatravel.reservationservice.security;

import com.megatravel.reservationservice.model.Privilege;
import com.megatravel.reservationservice.model.Role;
import com.megatravel.reservationservice.model.TPerson;
import com.megatravel.reservationservice.model.User;
import com.megatravel.reservationservice.repository.RoleRepository;
import com.megatravel.reservationservice.repository.TPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
    TPersonRepository tPersonRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TPerson user = tPersonRepo.findOneByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return user;
		}
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
		return getGrantedAuthorities(getPrivileges(role));
	}
	
	private Collection<Privilege> getPrivileges(Role role) {
        return role.getPrivileges();
    }
	
	private List<GrantedAuthority> getGrantedAuthorities(Collection<Privilege> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Privilege privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege.getName()));
        }
        return authorities;
    }
}
