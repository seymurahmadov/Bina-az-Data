package com.bina.az.binaazdata.controllers.securityController;

import com.bina.az.binaazdata.config.security.JwtTokenUtil;
import com.bina.az.binaazdata.dto.security.AfterSignInResponseDto;
import com.bina.az.binaazdata.dto.security.SignUpDto;
import com.bina.az.binaazdata.entity.UserEntity;
import com.bina.az.binaazdata.model.JwtRequest;
import com.bina.az.binaazdata.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	private final PasswordEncoder passwordEncoder;

	private final UserRepo userRepo;

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public AfterSignInResponseDto  signIn( @RequestBody JwtRequest request)
			throws Exception {

		authenticate(request.getEmail(), request.getPassword());
		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(request.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		AfterSignInResponseDto signInResponseDto = AfterSignInResponseDto.builder()
				.token(token)
				.email(request.getEmail())
				.role(request.getRole())
				.build();

		return  signInResponseDto;
	}

	@RequestMapping(value = "/signup",method = RequestMethod.POST)
	public ResponseEntity signUp ( @RequestBody SignUpDto dto){

		UserEntity entity = userRepo.findUsersEntityByEmail(dto.getEmail());
		if (entity == null) {
			UserEntity userEntity = UserEntity.builder()
					.email(dto.getEmail())
					.password(passwordEncoder.encode(dto.getPassword()))
					.role(dto.getRole())
					.build();
			userRepo.save(userEntity);
			return ResponseEntity.ok("You signed!");
		}else
			return ResponseEntity.ok("This account already exist in our DB!");

	}



	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
