package com.knu.cdp1.repository;

import com.knu.cdp1.vo.LogVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogVO, Integer> {

}
