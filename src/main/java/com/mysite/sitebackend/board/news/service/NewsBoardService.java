package com.mysite.sitebackend.board.news.service;

import com.mysite.sitebackend.board.Inform.dao.InformBoardRepository;
import com.mysite.sitebackend.board.Inform.domain.InformBoard;
import com.mysite.sitebackend.board.Inform.dto.InformBoardDto;
import com.mysite.sitebackend.board.Inform.dto.InfromBoardListDto;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.coin.dto.CoinBoardListDto;
import com.mysite.sitebackend.board.dto.BoardInput;
import com.mysite.sitebackend.board.news.dao.NewsBoardRepository;
import com.mysite.sitebackend.board.news.domain.NewsBoard;
import com.mysite.sitebackend.board.news.dto.NewsBoardDto;
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
    private final NewsBoardRepository boardRepository;
    private final ModelMapper modelMapper;
    //게시글 작성하기
    public void save(BoardInput boardInput){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        NewsBoard c1 = new NewsBoard();
        c1.setSubject(boardInput.getSubject());
        c1.setContents(boardInput.getContents());
        c1.setAuthor(boardInput.getAuthor());
        c1.setViews(0);
        c1.setDate(formatedNow);
        this.boardRepository.save(c1);
    }
    //게시글 리스트 불러오기
    public List<NewsBoardListDto> findAll(){
        List<NewsBoard> newsBoards = boardRepository.findAll();
        List<NewsBoardListDto> newsBoardListDtos = newsBoards.stream()
                .map(newsBoard -> modelMapper.map(newsBoard, NewsBoardListDto.class))
                .collect(Collectors.toList());
        return newsBoardListDtos;
    }
    //게시글 불러오기
    public NewsBoardDto findById(BoardInput boardInput){
        NewsBoard board = boardRepository.findById(boardInput.getId()).get();
        NewsBoardDto boardDto = modelMapper.map(board, NewsBoardDto.class);

        return boardDto;
    }
    //게시글 수정
    public NewsBoardDto findByIdToPatch(BoardInput boardInput){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        Optional<NewsBoard> opBoard = boardRepository.findById(boardInput.getId());
        if(opBoard.isPresent()){
            NewsBoard board = opBoard.get();
            board.setSubject(boardInput.getSubject());
            board.setContents(boardInput.getContents());
            board.setAuthor(boardInput.getAuthor());
            board.setDate(formatedNow);
            boardRepository.save(board);
        }
        NewsBoard board = boardRepository.findById(boardInput.getId()).get();
        NewsBoardDto boardDto = modelMapper.map(board, NewsBoardDto.class);
        return boardDto;
    }
    //게시글 삭제
    public String findByIdToDelete(BoardInput boardInput){
        this.boardRepository.deleteById(boardInput.getId());
        return boardInput.getId() + "번 게시판의 삭제가 완료되었습니다.";
    }

}
