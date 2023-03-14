package com.company.registeruser.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.registeruser.dto.PhoneDTO;
import com.company.registeruser.dto.UserInDTO;
import com.company.registeruser.dto.UserOutDTO;
import com.company.registeruser.models.Phone;
import com.company.registeruser.models.User;
import com.company.registeruser.repositories.PhoneRepository;
import com.company.registeruser.repositories.UserRepository;
import com.company.registeruser.security.AutenticacionLogin;

@Service
public class RegisterService implements UserDetailsService {
	
	 @Autowired
	 private UserRepository userRepository;
	    
	 @Autowired
	 private PhoneRepository phoneRepository;
	 
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;
	 

	 
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User user =  userRepository.findByName(name);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with name: " + name);
		}
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
				new ArrayList<>());
		
	}
	 
	
	public UserOutDTO saveUsuario(UserInDTO userInDTO){
			   User user = new User();
			    user.setName(userInDTO.getName());
			    user.setEmail(userInDTO.getEmail());
			    user.setPassword(passwordEncoder.encode(userInDTO.getPassword()));
			    user.setCreated(new Date());
			    user.setModified(new Date());
			    user.setLast_login(new Date());				    		    
			    user.setToken(null);
			    user.setIsactive(true);
			        
		        List<Phone> phonesAux = new ArrayList<Phone>();
		        for( PhoneDTO  phoneAux :userInDTO.getPhones()) {
			    	 Phone phone = new Phone();
			    	 phone.setNumber(phoneAux.getNumber());
			    	 phone.setCitycode(phoneAux.getContrycode());
			    	 phone.setContrycode(phoneAux.getContrycode());
			    	 phonesAux.add(phone);
			     }	        
		        user.setPhones(phonesAux);
		        userRepository.save(user);
		        
		        for(Phone phone :user.getPhones()) {
		        	phone.setUser(user);
		        	phoneRepository.save(phone);
		        }
	        return user.toDTO();          
	    }
	 
	 
	public UserOutDTO editUsuario(AutenticacionLogin userInDTO, String token){
		 Optional<User> usuario =  userRepository.buscarUsuarioPorName(userInDTO.getUsername());
	   	 User usuarioAux= (usuario.isPresent())? usuario.get() : new User();
	   	 usuarioAux.setToken(token);
	   	 userRepository.save(usuarioAux);
	   	 return usuarioAux.toDTO();
	}
	
	
	public void deleteUsuario(AutenticacionLogin userInDTO){
		Optional<User> usuario =  userRepository.buscarUsuarioPorName(userInDTO.getUsername());
	   	 User usuarioAux= (usuario.isPresent())? usuario.get() : new User();
	   	userRepository.delete(usuarioAux);
   }
	
	
}
