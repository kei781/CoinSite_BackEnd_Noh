package com.mysite.sitebackend.board.coin.service;


import com.mysite.sitebackend.board.coin.dao.CoinBoardCommentRepository;
import com.mysite.sitebackend.board.coin.dao.CoinBoardRepository;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.coin.domain.CoinBoardComment;
import com.mysite.sitebackend.board.dto.BoardDto;
import com.mysite.sitebackend.board.vo.BoardInput;
import com.mysite.sitebackend.board.dto.BoardListDto;
import com.mysite.sitebackend.board.dto.CommentListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoinBoardService {
    private final CoinBoardRepository boardRepository;
    private final CoinBoardCommentRepository commentRepository;
    private final ModelMapper modelMapper;
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    String formatedNow = now.format(formatter);

    //게시글 작성하기
    public boolean boardPost(BoardInput boardInput) throws SQLException {
            CoinBoard c1 = new CoinBoard();
            c1.setSubject(boardInput.getSubject());
            c1.setContents(boardInput.getContents());
            c1.setAuthor(boardInput.getAuthor());
            c1.setViews(0);
            c1.setDate(formatedNow);
            this.boardRepository.save(c1);
            return true;
    }
    //댓글 작성하기
    public boolean commentPost(BoardInput boardInput){
        Optional<CoinBoard> optionalCoinBoard = this.boardRepository.findById(boardInput.getId());
        if(optionalCoinBoard.isPresent()){
            CoinBoardComment a = new CoinBoardComment();
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

    //게시글 전체목록 불러오기
    public List<BoardListDto> findAll(){
        List<CoinBoard> coinBoard = boardRepository.findAll();
        List<BoardListDto> boardListDto = coinBoard.stream()
                        .map(BoardListDto1 -> modelMapper.map(BoardListDto1, BoardListDto.class))
                        .collect(Collectors.toList());
        System.out.println(boardListDto);
        return boardListDto;
    }
    //게시글 전체목록 불러오기






    //게시글 1개불러오기
    public BoardDto findByIdToBoard(BoardInput boardInput){
        CoinBoard board = boardRepository.findById(boardInput.getId()).get();
        board.setViews(board.getViews().intValue() +1);
        BoardDto boardDto = modelMapper.map(board, BoardDto.class);
        return boardDto;
    }
    //게시글 1개의 댓글 전체목록 불러오기
    public List<CommentListDto> findByIdToComment(BoardInput boardInput){
        Optional<CoinBoard> opboard = this.boardRepository.findById(boardInput.getId());
        opboard.isPresent() ;
            List<CoinBoardComment> coinComment = commentRepository.findAllByBoardIndex(boardInput.getId());
            List<CommentListDto> boardListDto = coinComment.stream()
                    .map(coinComments1 -> modelMapper.map(coinComments1, CommentListDto.class))
                    .collect(Collectors.toList());
        return boardListDto;
    }

    //게시글 수정
    public boolean boardPatch(BoardInput boardInput){
        Optional<CoinBoard> opBoard = boardRepository.findById(boardInput.getId());
        if(opBoard.isPresent()){
            if(boardInput.getAuthor() == opBoard.get().getAuthor()){
                CoinBoard board = opBoard.get();
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
        Optional<CoinBoardComment> opComment = commentRepository.findById(boardInput.getId());
        if(opComment.isPresent()){
            if(boardInput.getAuthor() == opComment.get().getAuthor()){
                CoinBoardComment comment = opComment.get();
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
        else{
            return false;
        }

    }

    //게시글 삭제
    public boolean boardDelete(BoardInput boardInput){
        Optional<CoinBoard> optionalCoinBoard = this.boardRepository.findById(boardInput.getId());
        if(optionalCoinBoard.isPresent()){
            if(boardInput.getAuthor() == optionalCoinBoard.get().getAuthor()){
                this.boardRepository.deleteById(boardInput.getId());
                this.commentRepository.deleteAllByBoardIndex(boardInput.getId());
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
    // 댓글삭제
    public boolean commnetDelete(BoardInput boardInput){
        Optional<CoinBoardComment> optionalCoinBoardComment = this.commentRepository.findById(boardInput.getId());
        if(optionalCoinBoardComment.isPresent()){
            this.commentRepository.deleteById(boardInput.getId());
            return true;
        }
        else{
            return false;
        }
    }
}
