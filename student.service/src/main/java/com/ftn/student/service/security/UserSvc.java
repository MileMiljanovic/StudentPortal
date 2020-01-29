package com.ftn.student.service.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.student.service.models.Korisnik;
import com.ftn.student.service.repository.KorisniciRepository;

@Service
public class UserSvc implements UserDetailsService {
	
	@Autowired
	private KorisniciRepository repoKorisnici;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Korisnik k = repoKorisnici.findById(username).get();
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(k.getUloga().toString()));
		User u = new User(k.getUsername(), k.getPassword(), authorities);
		return u;
	}

}
