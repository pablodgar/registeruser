package com.company.registeruser.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.registeruser.dto.MessageResponseDTO;
import com.company.registeruser.dto.UserInDTO;
import com.company.registeruser.repositories.UserRepository;
import com.company.registeruser.security.AutenticacionLogin;
import com.company.registeruser.security.JwtUtil;
import com.company.registeruser.services.MiUserDetailsService;
import com.company.registeruser.services.RegisterService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
public class RegisterController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
    private MiUserDetailsService miUserDetailsService;
		
    @Autowired
    private JwtUtil jwtUtil;
	
	
	@PostMapping("/registrarse")
	  public ResponseEntity<?> registerUser(@RequestBody UserInDTO userIn) {
				
		Pattern patternEmail = Pattern.compile("[a-z]{7}@?dominio\\.cl");		
		Pattern patternPassword = Pattern.compile("[A-Z]{1}[a-z]+[0-9][0-9]");		
		Matcher matherEmail = patternEmail.matcher(userIn.getEmail());
		Matcher matherPassword = patternPassword.matcher(userIn.getPassword());
		
		if(!matherEmail.find()) {
			return ResponseEntity.badRequest().body(new MessageResponseDTO("Error: El Formato del EMail No es Correcto!"));
		}		
		if(!matherPassword.find()) {
			return ResponseEntity.badRequest().body(new MessageResponseDTO("Error: El Formato de la Password No es Correcto!"));
		}		
		if (userRepository.existsByName(userIn.getName())) {
		      return ResponseEntity.badRequest().body(new MessageResponseDTO("Error: El nombre del Usuario ya Existe!"));
		}
		if (userRepository.existsByEmail(userIn.getEmail())) {
		      return ResponseEntity.badRequest().body(new MessageResponseDTO("Error: El Email del Usuario ya Existe!"));
		    }		
		return ResponseEntity.ok(registerService.saveUsuario(userIn));
		
	}
	
	 /**
	  * Se Hace el login, y se edita, para agregar token.
	  * @param userIn
	  * @return
	  * @throws Exception
	  */
    @PostMapping("/iniciar")
    public ResponseEntity<?> iniciarSesion(@RequestBody AutenticacionLogin userIn) throws Exception{
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userIn.getUsername(), userIn.getPassword())
            );

        }catch (BadCredentialsException ex){
            throw new Exception("Error en el username o contraseña " + ex.getMessage());
        } // fin de try~catch

        // Obtenemos los datos del usuario de la BD para construir el token
        final UserDetails userDetails = miUserDetailsService.loadUserByUsername(userIn.getUsername());
        final String token = jwtUtil.creatToken(userDetails);

        // Edito User para actualizar fecha de lastLogin y agregarle el token
        return ResponseEntity.ok(registerService.editUsuario(userIn, token));
    } 
	
	
    @DeleteMapping("/deleteUser")
    public void  deleteUser(@RequestBody AutenticacionLogin usuario){
    	registerService.deleteUsuario(usuario);
    }

}
