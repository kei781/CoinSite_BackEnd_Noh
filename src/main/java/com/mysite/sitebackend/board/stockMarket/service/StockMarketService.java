package com.mysite.sitebackend.board.stockMarket.service;

import com.mysite.sitebackend.board.Inform.domain.InformBoard;
import com.mysite.sitebackend.board.Inform.dto.InformBoardDto;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.coin.dto.CoinBoardDto;
import com.mysite.sitebackend.board.stockMarket.dao.StockMarketBoardRepository;
import com.mysite.sitebackend.board.stockMarket.domain.StockMarketBoard;
import com.mysite.sitebackend.board.stockMarket.dto.StockMarketBoardDto;
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
    private final StockMarketBoardRepository boardRepository;
    private final ModelMapper modelMapper;
    //게시글 작성하기
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
        this.boardRepository.save(c1);
    }
    //게시글 리스트 불러오기
    public List<StockMarketBoardListDto> findAll(){
        List<StockMarketBoard> stockMarketBoards = boardRepository.findAll();
        List<StockMarketBoardListDto> stockMarketBoardListDtos =
                stockMarketBoards.stream()
                .map(stockMarketBoard -> modelMapper.map(stockMarketBoard, StockMarketBoardListDto.class))
                .collect(Collectors.toList());
        return stockMarketBoardListDtos;
    }
    //게시글 불러오기
    public StockMarketBoardDto findById(Integer id){
        StockMarketBoard board = boardRepository.findById(id).get();
        StockMarketBoardDto boardDto = modelMapper.map(board, StockMarketBoardDto.class);
        return boardDto;
    }

    //게시글 수정
    public StockMarketBoardDto findByIdToPatch(Integer id, String subject, String contents, String author){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        Optional<StockMarketBoard> opBoard = boardRepository.findById(id);
        if(opBoard.isPresent()){
            StockMarketBoard board = opBoard.get();
            board.setSubject(subject);
            board.setSubject(contents);
            board.setSubject(author);
            board.setDate(formatedNow);
            boardRepository.save(board);
        }
        StockMarketBoard board = boardRepository.findById(id).get();
        StockMarketBoardDto boardDto = modelMapper.map(board, StockMarketBoardDto.class);
        return boardDto;
    }
    //게시글 삭제
    public String findByIdToDelete(Integer id){
        this.boardRepository.deleteById(id);
        return id + "번 게시판의 삭제가 완료되었습니다.";
    }

}
