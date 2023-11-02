package com.knu.cdp1.service;

import com.knu.cdp1.DTO.Authentication.AuthReq;
import com.knu.cdp1.DTO.Authentication.AuthRes;
import com.knu.cdp1.DTO.User.UserReqDTO;
import com.knu.cdp1.repository.UserRepository;
import com.knu.cdp1.vo.UserVO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
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

    public String register(UserReqDTO reqDTO) {
        if (userRepository.findByEmail(reqDTO.getEmail()).isEmpty()) {
            var user = UserVO.builder()
                    .email(reqDTO.getEmail())
                    .password(passwordEncoder.encode(reqDTO.getPassword()))
                    .name(reqDTO.getName())
                    .nickname(reqDTO.getNickname())
                    .birth(reqDTO.getBirth())
                    .build();
            userRepository.save(user);
            return jwtService.generateToken(user);
        } else return null;
    }

    public AuthRes logIn(UserReqDTO reqDTO) {
        System.out.println(reqDTO.getEmail() + reqDTO.getPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        reqDTO.getEmail(),
                        reqDTO.getPassword()
                )
        );
        var user = userRepository.findByEmail(reqDTO.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthRes.builder()
                .accessToken(jwtToken)
                .build();
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
