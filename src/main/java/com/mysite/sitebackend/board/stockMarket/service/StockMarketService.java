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

import java.sql.SQLException;
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
    public boolean boardPost(BoardInput boardInput) throws SQLException {
            StockMarketBoard c1 = new StockMarketBoard();
            c1.setSubject(boardInput.getSubject());
            c1.setContents(boardInput.getContents());
            c1.setAuthor(boardInput.getAuthor());
            c1.setViews(0);
            c1.setDate(formatedNow);
            this.boardRepository.save(c1);
            return true;
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
    public boolean boardPatch(BoardInput boardInput){
        Optional<StockMarketBoard> opBoard = boardRepository.findById(boardInput.getId());
        if(opBoard.isPresent()){
            if(boardInput.getAuthor() == opBoard.get().getAuthor()){
                StockMarketBoard board = opBoard.get();
                board.setSubject(boardInput.getSubject());
                board.setContents(boardInput.getContents());
                board.setAuthor(boardInput.getAuthor());
                board.setDate(formatedNow);
                boardRepository.save(board);
                return true;
            }
            else {
                return false;
            }
        }
        else{
            return false;
        }
    }
    //댓글 수정
    public boolean commentPatch(BoardInput boardInput){
        Optional<StockMarketBoardComment> opComment = commentRepository.findById(boardInput.getId());
        if(opComment.isPresent()){
            if(boardInput.getAuthor() == opComment.get().getAuthor()){
                StockMarketBoardComment comment = opComment.get();
                comment.setContents(boardInput.getContents());
                comment.setAuthor(boardInput.getAuthor());
                comment.setDate(formatedNow);
                comment.setBoardIndex(comment.getBoardIndex());
                commentRepository.save(comment);
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    //게시글 삭제
    public boolean boardDelete(BoardInput boardInput){
        Optional<StockMarketBoard> opBoard = boardRepository.findById(boardInput.getId());
        if(opBoard.isPresent()){
            if(boardInput.getAuthor() == opBoard.get().getAuthor()){
                this.boardRepository.deleteById(boardInput.getId());
                this.commentRepository.deleteAllByBoardIndex(boardInput.getId());
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    // 댓글삭제
    public boolean commnetDelete(BoardInput boardInput){
        Optional<StockMarketBoardComment> opComment= commentRepository.findById(boardInput.getId());
        if(opComment.isPresent()){
            if(boardInput.getAuthor() == opComment.get().getAuthor()){
                this.commentRepository.deleteById(boardInput.getId());
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }

}
