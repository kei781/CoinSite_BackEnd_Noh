package com.mysite.sitebackend.board.stockMarket.service;

import com.mysite.sitebackend.board.stockMarket.dao.StockMarketBoardRepository;
import com.mysite.sitebackend.board.stockMarket.domain.StockMarketBoard;
import com.mysite.sitebackend.board.stockMarket.dto.StockMarketBoardListDto;
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
public class StockMarketService {
    private final StockMarketBoardRepository stockMarketBoardRepository;
    private final ModelMapper modelMapper;

    public void save(String subject, String contents, String author){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        StockMarketBoard c1 = new StockMarketBoard();
        c1.setSubject(subject);
        c1.setContents(contents);
        c1.setAuthor(author);
        c1.setViews(0);
        c1.setDate(formatedNow);
        this.stockMarketBoardRepository.save(c1);
    }

    public List<StockMarketBoardListDto> findAll(){
        List<StockMarketBoard> stockMarketBoards = stockMarketBoardRepository.findAll();
        List<StockMarketBoardListDto> stockMarketBoardListDtos =
                stockMarketBoards.stream()
                .map(stockMarketBoard -> modelMapper.map(stockMarketBoard, StockMarketBoardListDto.class))
                .collect(Collectors.toList());
        return stockMarketBoardListDtos;
    }
    public StockMarketBoard findById(Integer id){
        Optional<StockMarketBoard> boardOptional = stockMarketBoardRepository.findById(id);
        StockMarketBoard stockMarketBoard = boardOptional.get();
        return stockMarketBoard;
    }

}
