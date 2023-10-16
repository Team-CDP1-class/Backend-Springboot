package com.knu.cdp1.service;

import com.knu.cdp1.DTO.StoryCard.StoryCardReqDTO;
import com.knu.cdp1.DTO.StoryCard.StoryCardResDTO;
import com.knu.cdp1.repository.StoryCardRepository;
import com.knu.cdp1.vo.StoryCardVO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryCardService {

    private final StoryCardRepository storyCardRepository;

    @Transactional // db 트랜잭션 자동으로 commit 해줌
    public int save(StoryCardReqDTO reqDTO) {
        return storyCardRepository.save(reqDTO.toEntity()).getId();
    }

    @Transactional
    public StoryCardResDTO findById(Long id) {
        StoryCardVO entity = storyCardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 스토리 카드가 없습니다. id=" + id));

        return new StoryCardResDTO(entity);
    }

    @Transactional
    public List<StoryCardResDTO> findAll() {
        List<StoryCardVO> entity = storyCardRepository.findAll();
        List<StoryCardResDTO> reqList = new ArrayList<>();
        for(int i = 0; i < entity.size(); i++)
            reqList.add(new StoryCardResDTO(entity.get(i)));

        return reqList;
    }

    @Transactional
    public Long update(Long id, StoryCardReqDTO reqDTO) {
        StoryCardVO entity = storyCardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 스토리 카드가 없습니다. id=" + id));

        entity.update(
                reqDTO.getPremise(), reqDTO.getSetting(), reqDTO.getCharacters(),
                reqDTO.getOutline(), reqDTO.getStorycard_name());

        return id;
    }

    @Transactional
    public void deleteById(Long id) {
        storyCardRepository.deleteById(id);
    }
}
