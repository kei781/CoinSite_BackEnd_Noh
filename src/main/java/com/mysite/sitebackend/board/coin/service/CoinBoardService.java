package com.mysite.sitebackend.board.coin.service;


import antlr.StringUtils;
import com.mysite.sitebackend.board.coin.dao.CoinBoardCommentRepository;
import com.mysite.sitebackend.board.coin.dao.CoinBoardRepository;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.coin.domain.CoinBoardComment;
import com.mysite.sitebackend.board.coin.dto.CoinBoardDto;
import com.mysite.sitebackend.board.coin.dto.CoinBoardListDto;
import com.mysite.sitebackend.board.dto.BoardInput;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public void boardPost(BoardInput boardInput){
        CoinBoard c1 = new CoinBoard();
        c1.setSubject(boardInput.getSubject());
        c1.setContents(boardInput.getContents());
        c1.setAuthor(boardInput.getAuthor());
        c1.setViews(0);
        c1.setDate(formatedNow);
        this.boardRepository.save(c1);
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
    public List<CoinBoardListDto> findAll(){
        List<CoinBoard> coinBoard = boardRepository.findAll();
        List<CoinBoardListDto> coinBoardListDto = coinBoard.stream()
                        .map(coinBoard1 -> modelMapper.map(coinBoard1, CoinBoardListDto.class))
                        .collect(Collectors.toList());
        return coinBoardListDto;
    }

    //게시글 1개불러오기
    public CoinBoardDto findByIdToBoard(BoardInput boardInput){
        CoinBoard board = boardRepository.findById(boardInput.getId()).get();
        board.setViews(board.getViews().intValue() +1);
        CoinBoardDto CoinboardDto = modelMapper.map(board, CoinBoardDto.class);
        return CoinboardDto;
    }

    //게시글 1개의 댓글 전체목록 불러오기
    public List<CoinBoardComment> findByIdToComment(BoardInput boardInput){
        Optional<CoinBoard> optionalCoinBoard = this.boardRepository.findById(boardInput.getId());
        optionalCoinBoard.isPresent() ;
            List<CoinBoardComment> coinBoardComments = commentRepository.findAllByBoardIndex(boardInput.getId());

        return coinBoardComments;
    }

    //게시글 수정
    public CoinBoardDto findByIdToPatch(BoardInput boardInput){
        Optional<CoinBoard> opBoard = boardRepository.findById(boardInput.getId());
        if(opBoard.isPresent()){
            CoinBoard board = opBoard.get();
            board.setSubject(boardInput.getSubject());
            board.setContents(boardInput.getContents());
            board.setAuthor(boardInput.getAuthor());
            board.setDate(formatedNow);
            boardRepository.save(board);
        }
        CoinBoard board = boardRepository.findById(boardInput.getId()).get();
        CoinBoardDto coinboardDto = modelMapper.map(board, CoinBoardDto.class);
        return coinboardDto;
    }

    //게시글 삭제
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
