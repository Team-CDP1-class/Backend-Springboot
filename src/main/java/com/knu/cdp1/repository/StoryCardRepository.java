package com.knu.cdp1.repository;

import com.knu.cdp1.vo.StoryCardVO;
import com.knu.cdp1.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryCardRepository extends JpaRepository<StoryCardVO, Long> {
    List<StoryCardVO> findAllByUserEmail(String email);
}
