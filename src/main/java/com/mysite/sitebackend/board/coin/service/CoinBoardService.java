package com.mysite.sitebackend.board.coin.service;


import antlr.StringUtils;
import com.mysite.sitebackend.board.coin.dao.CoinBoardRepository;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.coin.dto.CoinBoardDto;
import com.mysite.sitebackend.board.coin.dto.CoinBoardListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoinBoardService {
    private final CoinBoardRepository boardRepository;
    private final ModelMapper modelMapper;

    //게시글 작성하기
    public void save(String subject, String contents, String author){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        CoinBoard c1 = new CoinBoard();
        c1.setSubject(subject);
        c1.setContents(contents);
        c1.setAuthor(author);
        c1.setViews(0);
        c1.setDate(formatedNow);
        this.boardRepository.save(c1);
        System.out.println("성공적으로 저장되었습니다.");
    }
    //게시글 전체목록 불러오기
    public List<CoinBoardListDto> findAll(){
        List<CoinBoard> coinBoard = boardRepository.findAll();
        List<CoinBoardListDto> coinBoardListDto = coinBoard.stream().
                map(coinBoard1 -> modelMapper.map(coinBoard1, CoinBoardListDto.class)).collect(Collectors.toList());

        return coinBoardListDto;
    }
    //게시글 1개불러오기
    public CoinBoardDto findById(Integer id){
        CoinBoard board = boardRepository.findById(id).get();
        CoinBoardDto CoinboardDto = modelMapper.map(board, CoinBoardDto.class);
        return CoinboardDto;
    }
    //게시글 수정
    public CoinBoardDto findByIdToPatch(Integer id, String subject, String contents, String author){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        Optional<CoinBoard> opBoard = boardRepository.findById(id);
        if(opBoard.isPresent()){
            CoinBoard board = opBoard.get();
            board.setSubject(subject);
            board.setContents(contents);
            board.setAuthor(author);
            board.setDate(formatedNow);
            boardRepository.save(board);
        }
        CoinBoard board = boardRepository.findById(id).get();
        CoinBoardDto coinboardDto = modelMapper.map(board, CoinBoardDto.class);
        return coinboardDto;
    }
    //게시글 삭제
    public String findByIdToDelete(Integer id){
        this.boardRepository.deleteById(id);
        return id + "번 게시판의 삭제가 완료되었습니다.";
    }




}
