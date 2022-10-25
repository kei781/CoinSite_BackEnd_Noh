package com.mysite.sitebackend.board.stockMarket.service;

import com.mysite.sitebackend.board.dto.BoardInput;
import com.mysite.sitebackend.board.stockMarket.dao.StockMarketBoardCommentRepository;
import com.mysite.sitebackend.board.stockMarket.dao.StockMarketBoardRepository;
import com.mysite.sitebackend.board.stockMarket.domain.StockMarketBoard;
import com.mysite.sitebackend.board.stockMarket.domain.StockMarketBoardComment;
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
    private final StockMarketBoardCommentRepository commentRepository;
    private final ModelMapper modelMapper;
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    String formatedNow = now.format(formatter);
    //게시글 작성하기
    public void save(BoardInput boardInput){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        StockMarketBoard c1 = new StockMarketBoard();
        c1.setSubject(boardInput.getSubject());
        c1.setContents(boardInput.getContents());
        c1.setAuthor(boardInput.getAuthor());
        c1.setViews(0);
        c1.setDate(formatedNow);
        this.boardRepository.save(c1);
    }

    public boolean commentPost(BoardInput boardInput){
        Optional<StockMarketBoard> optionalStockMarketBoard = this.boardRepository.findById(boardInput.getId());
        if(optionalStockMarketBoard.isPresent()){
            StockMarketBoard q = optionalStockMarketBoard.get();
            StockMarketBoardComment a = new StockMarketBoardComment();
            a.setContents(boardInput.getContents());
            a.setDate(formatedNow);
            a.setAuthor(boardInput.getAuthor());
            a.setBoardIndex(boardInput.getId());
            this.commentRepository.save(a);
            return true;
        }
        else {
            return false;
        }
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
    public StockMarketBoardDto findById(BoardInput boardInput){
        StockMarketBoard board = boardRepository.findById(boardInput.getId()).get();
        StockMarketBoardDto boardDto = modelMapper.map(board, StockMarketBoardDto.class);
        return boardDto;
    }

    //게시글 1개의 댓글 전체목록 불러오기
    public List<StockMarketBoardComment> findByIdToComment(BoardInput boardInput) {
        Optional<StockMarketBoard> optionalCoinBoard = this.boardRepository.findById(boardInput.getId());
        optionalCoinBoard.isPresent();
        List<StockMarketBoardComment> stockMarketBoardComments = commentRepository.findAllByBoardIndex(boardInput.getId());

        return stockMarketBoardComments;
    }

    //게시글 수정
    public StockMarketBoardDto findByIdToPatch(BoardInput boardInput){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        Optional<StockMarketBoard> opBoard = boardRepository.findById(boardInput.getId());
        if(opBoard.isPresent()){
            StockMarketBoard board = opBoard.get();
            board.setSubject(boardInput.getSubject());
            board.setContents(boardInput.getContents());
            board.setAuthor(boardInput.getAuthor());
            board.setDate(formatedNow);
            boardRepository.save(board);
        }
        StockMarketBoard board = boardRepository.findById(boardInput.getId()).get();
        StockMarketBoardDto boardDto = modelMapper.map(board, StockMarketBoardDto.class);
        return boardDto;
    }

    public String boardDelete(BoardInput boardInput){
        this.boardRepository.deleteById(boardInput.getId());
        this.commentRepository.deleteAllByBoardIndex(boardInput.getId());
        return boardInput.getId() + "번 게시판의 삭제가 완료되었습니다.";
    }
    // 댓글삭제
    public String commnetDelete(BoardInput boardInput){
        this.commentRepository.deleteById(boardInput.getId());
        return boardInput.getId() + "번 댓글의 삭제가 완료되었습니다.";
    }

}
