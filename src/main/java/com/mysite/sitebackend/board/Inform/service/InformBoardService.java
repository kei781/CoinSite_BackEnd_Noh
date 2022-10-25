package com.mysite.sitebackend.board.Inform.service;

import com.mysite.sitebackend.board.Inform.dao.InformBoardCommentRepository;
import com.mysite.sitebackend.board.Inform.dao.InformBoardRepository;
import com.mysite.sitebackend.board.Inform.domain.InformBoard;
import com.mysite.sitebackend.board.Inform.domain.InformBoardComment;
import com.mysite.sitebackend.board.Inform.dto.InformBoardDto;
import com.mysite.sitebackend.board.Inform.dto.InfromBoardListDto;
import com.mysite.sitebackend.board.coin.dao.CoinBoardCommentRepository;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.coin.domain.CoinBoardComment;
import com.mysite.sitebackend.board.coin.dto.CoinBoardDto;
import com.mysite.sitebackend.board.coin.dto.CoinBoardListDto;
import com.mysite.sitebackend.board.dto.BoardInput;
import lombok.Data;
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
public class InformBoardService {
    private final InformBoardRepository boardRepository;
    private final InformBoardCommentRepository commentRepository;
    private final ModelMapper modelMapper;
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    String formatedNow = now.format(formatter);
    //게시글 작성하기
    public void save(BoardInput boardInput){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        InformBoard c1 = new InformBoard();
        c1.setSubject(boardInput.getSubject());
        c1.setContents(boardInput.getContents());
        c1.setAuthor(boardInput.getAuthor());
        c1.setViews(0);
        c1.setDate(formatedNow);
        this.boardRepository.save(c1);
        System.out.println("성공적으로 저장되었습니다.");
    }
    //댓글 작성하기
    public boolean commentPost(BoardInput boardInput){
        Optional<InformBoard> optionalCoinBoard = this.boardRepository.findById(boardInput.getId());
        if(optionalCoinBoard.isPresent()){
            InformBoard q = optionalCoinBoard.get();
            InformBoardComment a = new InformBoardComment();
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
    public List<InfromBoardListDto> findAll(){
        List<InformBoard> informBoards = boardRepository.findAll();
        List<InfromBoardListDto> infromBoardListDto =
                informBoards.stream()
                .map(informBoard1 -> modelMapper.map(informBoard1, InfromBoardListDto.class))
                .collect(Collectors.toList());
        return infromBoardListDto;
    }
    //게시글 불러오기
    public InformBoardDto findById(BoardInput boardInput){
        InformBoard board = boardRepository.findById(boardInput.getId()).get();
        InformBoardDto boardDto = modelMapper.map(board, InformBoardDto.class);

        return boardDto;
    }

    //게시글 1개의 댓글 전체목록 불러오기
    public List<InformBoardComment> findByIdToComment(BoardInput boardInput){
        Optional<InformBoard> optionalCoinBoard = this.boardRepository.findById(boardInput.getId());
        optionalCoinBoard.isPresent() ;
        List<InformBoardComment> informBoardComments = commentRepository.findAllByBoardIndex(boardInput.getId());

        return informBoardComments;
    }

    //게시글 수정
    public InformBoardDto findByIdToPatch(BoardInput boardInput){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        Optional<InformBoard> opBoard = boardRepository.findById(boardInput.getId());
        if(opBoard.isPresent()){
            InformBoard board = opBoard.get();
            board.setSubject(boardInput.getSubject());
            board.setContents(boardInput.getContents());
            board.setAuthor(boardInput.getAuthor());
            board.setDate(formatedNow);
            boardRepository.save(board);
        }
        InformBoard board = boardRepository.findById(boardInput.getId()).get();
        InformBoardDto boardDto = modelMapper.map(board, InformBoardDto.class);
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
