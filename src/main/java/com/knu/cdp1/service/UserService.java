package com.knu.cdp1.service;

import com.knu.cdp1.DTO.User.UserReqDTO;
import com.knu.cdp1.repository.UserRepository;
import com.knu.cdp1.vo.UserVO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public String register(UserReqDTO reqDTO) {
        // 비밀번호 인코더 안함
        var user = reqDTO.toEntity();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return jwtToken;
    }

    @Transactional
    public String logIn(UserReqDTO reqDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        reqDTO.getEmail(),
                        reqDTO.getPassword()
                )
        );
        var user = userRepository.findByEmail(reqDTO.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return jwtToken;
    }

    @Transactional
    public boolean findByEmail(String email) {
        Optional<UserVO> entity = userRepository.findByEmail(email);

        if (entity == null) return true;
        else return false;
    }

    @Transactional
    public String update(UserReqDTO reqDTO) {
        Optional<UserVO> entity = userRepository.findByEmail(reqDTO.getEmail());
        //entity.update(reqDTO.getNickname(), reqDTO.getPassword());

        return reqDTO.getEmail();
    }
}
