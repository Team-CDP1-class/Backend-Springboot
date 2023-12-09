package com.knu.cdp1.repository;

import com.knu.cdp1.vo.StoryCardVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryCardRepository extends JpaRepository<StoryCardVO, Long> {
    List<StoryCardVO> findAllByUserEmail(String email);
}
