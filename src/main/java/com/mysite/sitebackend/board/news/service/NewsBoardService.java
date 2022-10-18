package com.mysite.sitebackend.board.news.service;

import com.mysite.sitebackend.board.Inform.dao.InformBoardRepository;
import com.mysite.sitebackend.board.Inform.domain.InformBoard;
import com.mysite.sitebackend.board.Inform.dto.InfromBoardListDto;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.coin.dto.CoinBoardListDto;
import com.mysite.sitebackend.board.news.dao.NewsBoardRepository;
import com.mysite.sitebackend.board.news.domain.NewsBoard;
import com.mysite.sitebackend.board.news.dto.NewsBoardListDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsBoardService {
    private final NewsBoardRepository newsBoardRepository;
    private final ModelMapper modelMapper;

    public void save(String subject, String contents, String author){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        NewsBoard c1 = new NewsBoard();
        c1.setSubject(subject);
        c1.setContents(contents);
        c1.setAuthor(author);
        c1.setViews(0);
        c1.setDate(formatedNow);
        this.newsBoardRepository.save(c1);
    }

    public List<NewsBoardListDto> findAll(){
        List<NewsBoard> newsBoards = newsBoardRepository.findAll();
        List<NewsBoardListDto> newsBoardListDtos = newsBoards.stream()
                .map(newsBoard -> modelMapper.map(newsBoard, NewsBoardListDto.class))
                .collect(Collectors.toList());
        return newsBoardListDtos;
    }
    public NewsBoard findById(Integer id){
        Optional<NewsBoard> boardOptional = newsBoardRepository.findById(id);
        NewsBoard newsBoard = boardOptional.get();
        return newsBoard;
    }

}
