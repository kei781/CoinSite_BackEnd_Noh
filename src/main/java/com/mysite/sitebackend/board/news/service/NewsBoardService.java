package com.mysite.sitebackend.board.news.service;

import com.mysite.sitebackend.board.Inform.domain.InformBoard;
import com.mysite.sitebackend.board.Inform.domain.InformBoardComment;
import com.mysite.sitebackend.board.dto.BoardDto;
import com.mysite.sitebackend.board.dto.BoardInput;
import com.mysite.sitebackend.board.dto.BoardListDto;
import com.mysite.sitebackend.board.dto.CommentListDto;
import com.mysite.sitebackend.board.news.dao.NewsBoardCommentRepository;
import com.mysite.sitebackend.board.news.dao.NewsBoardRepository;
import com.mysite.sitebackend.board.news.domain.NewsBoard;
import com.mysite.sitebackend.board.news.domain.NewsBoardComment;
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
public class NewsBoardService {
    private final NewsBoardRepository boardRepository;
    private final NewsBoardCommentRepository commentRepository;
    private final ModelMapper modelMapper;
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    String formatedNow = now.format(formatter);

    //게시글 작성하기
    public boolean boardPost(BoardInput boardInput) throws SQLException {
            NewsBoard c1 = new NewsBoard();
            c1.setSubject(boardInput.getSubject());
            c1.setContents(boardInput.getContents());
            c1.setAuthor(boardInput.getAuthor());
            c1.setViews(0);
            c1.setDate(formatedNow);
            this.boardRepository.save(c1);
            return true;
    }

    public boolean commentPost(BoardInput boardInput){
        Optional<NewsBoard> optionalNewsBoard = this.boardRepository.findById(boardInput.getId());
        if(optionalNewsBoard.isPresent()){
            NewsBoard q = optionalNewsBoard.get();
            NewsBoardComment a = new NewsBoardComment();
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
    public List<BoardListDto> findAll(){
        List<NewsBoard> newsBoard = boardRepository.findAll();
        List<BoardListDto> boardListDto = newsBoard.stream()
                .map(newsBoard1 -> modelMapper.map(newsBoard1, BoardListDto.class))
                .collect(Collectors.toList());
        return boardListDto;
    }
    //게시글 1개불러오기
    public BoardDto findByIdToBoard(BoardInput boardInput){
        NewsBoard board = boardRepository.findById(boardInput.getId()).get();
        board.setViews(board.getViews() +1);
        BoardDto boardDto = modelMapper.map(board, BoardDto.class);
        return boardDto;
    }
    //게시글 1개의 댓글 전체목록 불러오기
    public List<CommentListDto> findByIdToComment(BoardInput boardInput){
        Optional<NewsBoard> opboard = this.boardRepository.findById(boardInput.getId());
        opboard.isPresent() ;
        List<NewsBoardComment> newsComment = commentRepository.findAllByBoardIndex(boardInput.getId());
        List<CommentListDto> boardListDto = newsComment.stream()
                .map(newsComment1 -> modelMapper.map(newsComment1, CommentListDto.class))
                .collect(Collectors.toList());
        return boardListDto;
    }

    //게시글 수정
    public boolean boardPatch(BoardInput boardInput){
        Optional<NewsBoard> opBoard = boardRepository.findById(boardInput.getId());
        if(opBoard.isPresent()){
            if(boardInput.getAuthor() == opBoard.get().getAuthor()){
                NewsBoard board = opBoard.get();
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
        Optional<NewsBoardComment> opComment = commentRepository.findById(boardInput.getId());
        if(opComment.isPresent()){
            if(boardInput.getAuthor() == opComment.get().getAuthor()){
                NewsBoardComment comment = opComment.get();
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
        Optional<NewsBoard> opBoard = boardRepository.findById(boardInput.getId());
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
        Optional<NewsBoardComment> opComment= commentRepository.findById(boardInput.getId());
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
