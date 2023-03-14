package com.company.registeruser.controllers;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import com.company.registeruser.dto.UserInDTO;
import com.company.registeruser.dto.UserOutDTO;
import com.company.registeruser.repositories.UserRepository;
import com.company.registeruser.security.JwtUtil;
import com.company.registeruser.security.MiUserDetails;
import com.company.registeruser.services.RegisterService;
import junit.framework.Assert;


@ExtendWith(MockitoExtension.class)
public class RegisterControllerTest {
	
	@InjectMocks
	 private RegisterController registerController = new RegisterController();
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
    private JwtUtil jwtUtil;
	
	@Mock
	private RegisterService registerService;
	 	 
	private static final String TOKEN ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJQZXBlIExPTyIsImV4cCI6MTY3ODg0Mzk1NywiaWF0IjoxNjc4ODA3OTU3fQ.1uz_tsZa35-r3IIZSOMlFiHSJ0evu6yvi8sVIHc5bn2kbwi1GErlqPNP2k4chziMLt0bPgzGF6qyb_jo2FALjQ";
	

	@SuppressWarnings("deprecation")
	@Test
	 public void registerUser_ResponseEntity_ok_Test() {
		 
		 UserInDTO userIn = new UserInDTO();
		 userIn.setName("Pepe Lo");
		 //formato email correcto
		 userIn.setEmail("qwertyu@dominio.cl");
		 //formato password correcto
		 userIn.setPassword("Mnnn65");
		 
		 UserOutDTO userOutDTO = new UserOutDTO();
		 userOutDTO.setCreated(null);
		 userOutDTO.setId("e41376ed-3633-423e-a37c-ef6422724f95");
		 userOutDTO.setIsactive(true);
		 userOutDTO.setLast_login(new Date());
		 userOutDTO.setModified(new Date());
		 userOutDTO.setToken(TOKEN);		 
		 Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);		 
		 Mockito.when(jwtUtil.creatToken(Mockito.any(MiUserDetails.class))).thenReturn(TOKEN);
		 Mockito.when(registerService.saveUsuario(Mockito.any(UserInDTO.class),Mockito.anyString())).thenReturn(userOutDTO);
		 		 
		 ResponseEntity<?> respRegisterUser = registerController.registerUser(userIn);
		 Assert.assertNotNull(respRegisterUser);
		 Assert.assertEquals(respRegisterUser.getStatusCode().value(),200);
		 
		 
	 }
	 
	@SuppressWarnings("deprecation")
	@Test
	 public void registerUser_ResponseEntity_Error_Email_Test() {
		 
		 UserInDTO userIn = new UserInDTO();
		 userIn.setName("Pepe Lo");
		 //formato email incorrecto
		 userIn.setEmail("qweru@dgmail");
		 //formato password correcto
		 userIn.setPassword("Mnnn65");
		 		 		 
		 ResponseEntity<?> respRegisterUser = registerController.registerUser(userIn);
		 Assert.assertNotNull(respRegisterUser);
		 Assert.assertEquals(respRegisterUser.getStatusCode().value(),400);	 
	 }
	
	
	@SuppressWarnings("deprecation")
	@Test
	 public void registerUser_ResponseEntity_Error_Passsword_Test() {
		 
		 UserInDTO userIn = new UserInDTO();
		 userIn.setName("Pepe Lo");
		 //formato correo correcto
		 userIn.setEmail("qwertyu@dominio.cl");
		 //formato email incorrecto
		 userIn.setPassword("MALnnn675");
		 		 		 
		 ResponseEntity<?> respRegisterUser = registerController.registerUser(userIn);
		 Assert.assertNotNull(respRegisterUser);
		 Assert.assertEquals(respRegisterUser.getStatusCode().value(),400);	 
	 }
	
	
	@SuppressWarnings("deprecation")
	@Test
	 public void registerUser_ResponseEntity_Error_Ya_Existe_Mail_Test() {
		 
		 UserInDTO userIn = new UserInDTO();
		 userIn.setName("Pepe Lo");
		 //formato correo correcto
		 userIn.setEmail("qwertyu@dominio.cl");
		 //formato password correcto
		 userIn.setPassword("Mnnn65");
		 		
		 Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
		 
		 ResponseEntity<?> respRegisterUser = registerController.registerUser(userIn);
		 Assert.assertNotNull(respRegisterUser);
		 Assert.assertEquals(respRegisterUser.getStatusCode().value(),400);	 
	 }
	 
}
