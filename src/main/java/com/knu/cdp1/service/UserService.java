package com.knu.cdp1.service;

import com.knu.cdp1.DTO.User.UserReqDTO;
import com.knu.cdp1.repository.UserRepository;
import com.knu.cdp1.vo.UserVO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String save(UserReqDTO reqDTO) {
        return userRepository.save(reqDTO.toEntity()).getEmail();
    }

    @Transactional
    public boolean findByEmail(String email) {
        UserVO entity = userRepository.findByEmail(email);

        if (entity == null) return true;
        else return false;
    }

    @Transactional
    public String update(UserReqDTO reqDTO) {
        UserVO entity = userRepository.findByEmail(reqDTO.getEmail());
        entity.update(reqDTO.getNickname(), reqDTO.getPassword());

        return reqDTO.getEmail();
    }
}
