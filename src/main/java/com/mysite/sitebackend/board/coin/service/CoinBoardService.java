package com.mysite.sitebackend.board.coin.service;


import com.mysite.sitebackend.board.coin.dao.CoinBoardRepository;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.coin.dto.CoinBoardListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoinBoardService {
    private final CoinBoardRepository coinBoardRepository;
    private final ModelMapper modelMapper;


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
        this.coinBoardRepository.save(c1);
        System.out.println("성공적으로 저장되었습니다.");
    }

    public List<CoinBoardListDto> findAll(){
        List<CoinBoard> coinBoard = coinBoardRepository.findAll();
        List<CoinBoardListDto> coinBoardListDto = coinBoard.stream().
                map(coinBoard1 -> modelMapper.map(coinBoard1, CoinBoardListDto.class)).collect(Collectors.toList());

        return coinBoardListDto;
    }
    public CoinBoard findById(Integer id){
        Optional<CoinBoard> boardOptional = coinBoardRepository.findById(id);
        CoinBoard coinBoard = boardOptional.get();

        return coinBoard;
    }

}
