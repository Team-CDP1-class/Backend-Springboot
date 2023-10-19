package com.knu.cdp1.repository;

import com.knu.cdp1.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserVO, Integer> {
    Optional<UserVO> findByEmail(String email);

}
