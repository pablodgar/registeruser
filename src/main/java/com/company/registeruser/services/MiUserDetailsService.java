package com.company.registeruser.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.registeruser.models.User;
import com.company.registeruser.repositories.UserRepository;
import com.company.registeruser.security.MiUserDetails;

@Service
public class MiUserDetailsService implements UserDetailsService {
	
	   @Autowired
	    private UserRepository userRepository;
	   

	    /**
	     * Cargo los datos obtenidos de la consulta a la BD</b>.
	     * @param username nombre del usuario a buscar
	     * @return UserDetails que poblara por medio de <b>Usuario</b>
	     * @throws UsernameNotFoundException Si no encuentra el registro en la BD.
	     */
	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Optional<User> usuario = userRepository.buscarUsuarioPorName(username);
	        usuario.orElseThrow(() -> new UsernameNotFoundException("No se encontro el usuario "+ username
	                +" en la BD"));

	        return usuario.map(MiUserDetails::new).get();

	    }
}
