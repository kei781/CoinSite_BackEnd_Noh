package com.mysite.sitebackend.board.service;


import com.mysite.sitebackend.account.dao.AccountRepository;
import com.mysite.sitebackend.account.domain.Account;
import com.mysite.sitebackend.board.dao.BoardCommentRepository;
import com.mysite.sitebackend.board.dao.BoardRepository;
import com.mysite.sitebackend.board.domain.Board;
import com.mysite.sitebackend.board.domain.BoardComment;
import com.mysite.sitebackend.board.dto.BoardDto;
import com.mysite.sitebackend.board.dto.BoardListDto;
import com.mysite.sitebackend.board.dto.CommentListDto;
import com.mysite.sitebackend.board.vo.BoardInput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final AccountRepository accountRepository;
    private final BoardCommentRepository commentRepository;
    private final ModelMapper modelMapper;
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    String formatedNow = now.format(formatter);

    //전체검색
    public List<BoardListDto> searchAll(String value){
        List<Board> boardList = this.boardRepository.searchAll(value);
        List<BoardListDto> boardListDto = boardList.stream()
                .map(BoardListDto1 -> modelMapper.map(BoardListDto1, BoardListDto.class))
                .collect(Collectors.toList());
        return boardListDto;
    }
    //게시판별검색
    public List<BoardListDto> search(String value, String lcategory, String mcategory){
        List<Board> boardList = this.boardRepository.search(value, lcategory, mcategory);
        List<BoardListDto> boardListDto = boardList.stream()
                .map(BoardListDto1 -> modelMapper.map(BoardListDto1, BoardListDto.class))
                .collect(Collectors.toList());
        return boardListDto;
    }
    //게시글 작성하기
    public boolean boardPost(String lcategory, String mcategory, BoardInput boardInput, MultipartFile file) throws SQLException {
        //공지카테고리안의 공지사항 게시판이나 이벤트 게시판일 경우
        if(lcategory.equals("notice") && (mcategory.equals("n") || mcategory.equals("e"))){
            Account account = accountRepository.findByUserId(boardInput.getAuthor());
            // 어드민만 게시글 작성가능
            if(account.getRole().equals("ADMIN")){
                Board b1 = new Board();
                b1.setSubject(boardInput.getSubject());
                b1.setContents(boardInput.getContents());
                // 관리자는 글 작성시 반드시 어드민
                b1.setAuthor("관리자");
                b1.setViews(0);
                b1.setDate(formatedNow);
                b1.setLcategory(lcategory);
                b1.setMcategory(mcategory);
                this.boardRepository.save(b1);
                return true;
            }
            else return false;
        }
        // 그외 카테고리, 게시판일경우
        else {
            // 자유롭게 게시글 작성가능
            Board b1 = new Board();
            b1.setSubject(boardInput.getSubject());
            b1.setContents(boardInput.getContents());
            b1.setAuthor(boardInput.getAuthor());
            b1.setViews(0);
            b1.setDate(formatedNow);
            b1.setLcategory(lcategory);
            b1.setMcategory(mcategory);
            this.boardRepository.save(b1);
            return true;
        }
    }
    //댓글 작성하기
    public boolean commentPost(BoardInput boardInput){
        Optional<Board> optionalBoard = this.boardRepository.findById(boardInput.getId());
        // 댓글을 작성하려는 게시글이 옳바르게 존재하면
        if(optionalBoard.isPresent()) {
            // 해당게시글이 문의 게시판인지 체크
            if (optionalBoard.get().getLcategory().equals("notice") && optionalBoard.get().getMcategory().equals("i")) {
                System.out.println("문의 게시판!!!!");
                //문의 게시판의 게시글에 댓글은, 어드민만 작성가능
                if (accountRepository.findByUserId(boardInput.getAuthor()).getRole().equals("ADMIN")) {
                    BoardComment a = new BoardComment();
                    a.setContents(boardInput.getContents());
                    a.setDate(formatedNow);
                    a.setAuthor("ADMIN");
                    a.setBoardIndex(boardInput.getId());
                    this.commentRepository.save(a);
                    return true;
                }
                // 문의 게시판의 게시글에 댓글은, 어드민이 아니면 작성 불가
                else return false;
            } else {
                // 해당게시판이 문의 게시판이 아니라면
                // 자유롭게 댓글 작성가능
                BoardComment a = new BoardComment();
                a.setContents(boardInput.getContents());
                a.setDate(formatedNow);
                a.setAuthor(boardInput.getAuthor());
                a.setBoardIndex(boardInput.getId());
                this.commentRepository.save(a);
                return true;
            }
        }
        // 댓글을 작성하려는 게시글이 옳바르게 존재하지않으면 실패
        else return false;
    }

    //게시글 전체목록 불러오기
    public List<BoardListDto> findAll(String lcategory, String mcategory){
        List<Board> board = boardRepository.findAllByLcategoryAndMcategory(lcategory, mcategory);
        List<BoardListDto> boardListDto = board.stream()
                        .map(BoardListDto1 -> modelMapper.map(BoardListDto1, BoardListDto.class))
                        .collect(Collectors.toList());
        return boardListDto;
    }
    //게시글 3개만 불러오기
    public List<BoardListDto> findThree(String lcategory, String mcategory){
        List<Board> board = boardRepository.findThree(lcategory, mcategory, PageRequest.of(0, 3));
        List<BoardListDto> boardListDto = board.stream()
                .map(BoardListDto1 -> modelMapper.map(BoardListDto1, BoardListDto.class))
                .collect(Collectors.toList());
        return boardListDto;
    }

    //게시글 1개불러오기
    public BoardDto findByIdToBoard(String lcategory, String mcategory, Integer id){
        Board board = this.boardRepository.findByIdAndLcategoryAndMcategory(id, lcategory, mcategory);
        board.setViews(board.getViews() +1);
        this.boardRepository.save(board);
        BoardDto boardDto = modelMapper.map(board, BoardDto.class);
        return boardDto;
    }
    //게시글 1개의 댓글 전체목록 불러오기
    public List<CommentListDto> findByIdToComment(String lcategory, String mcategory, Integer id){
        Optional<Board> opboard = Optional.of(this.boardRepository.findByIdAndLcategoryAndMcategory(id, lcategory, mcategory));
        if (opboard.isPresent()){
            List<BoardComment> comment = commentRepository.findAllByBoardIndex(id);
            List<CommentListDto> boardListDto = comment.stream()
                    .map(comments1 -> modelMapper.map(comments1, CommentListDto.class))
                    .collect(Collectors.toList());
            return boardListDto;
        }
        else return null;
    }


    //게시글 수정
    public boolean boardPatch(String lcategory, String mcategory,  BoardInput boardInput){
        Optional<Board> opBoard = Optional.of(this.boardRepository.findByIdAndLcategoryAndMcategory(boardInput.getId(), lcategory, mcategory));
        if(opBoard.isPresent()){
            if(boardInput.getAuthor().equals(opBoard.get().getAuthor())){
                Board board = opBoard.get();
                board.setSubject(boardInput.getSubject());
                board.setContents(boardInput.getContents());
                board.setAuthor(boardInput.getAuthor());
                board.setDate(formatedNow);
                boardRepository.save(board);
                return true;
            }
            else return false;
        }
        else return false;

    }
    //댓글 수정
    public boolean commentPatch(BoardInput boardInput){
        Optional<BoardComment> opComment = commentRepository.findById(boardInput.getId());
        if(opComment.isPresent()){
            if(boardInput.getAuthor().equals(opComment.get().getAuthor())){
                BoardComment comment = opComment.get();
                comment.setContents(boardInput.getContents());
                comment.setAuthor(boardInput.getAuthor());
                comment.setDate(formatedNow);
                comment.setBoardIndex(comment.getBoardIndex());
                commentRepository.save(comment);
                return true;
            }
            else return false;
        }
        else return false;
    }

    //게시글 삭제
    public boolean boardDelete(String lcategory, String mcategory,  BoardInput boardInput){
        Account account = this.accountRepository.findByUserId(boardInput.getAuthor());
        // 사용자가 단순 유저일경우
        if(account.getRole().equals("USER")){
            Optional<Board> optionalBoard = Optional.of(this.boardRepository.findByIdAndLcategoryAndMcategory(boardInput.getId(), lcategory, mcategory));
            if(optionalBoard.isPresent()){
                // 해당작성자가 맞는지 체크 후 삭제
                if(boardInput.getAuthor().equals(optionalBoard.get().getAuthor())){
                    this.boardRepository.deleteById(boardInput.getId());
                    this.commentRepository.deleteAllByBoardIndex(boardInput.getId());
                    return true;
                }
                else return false;
            }
            else return false;
        }
        // 사용자가 관리자 일경우
        else if (account.getRole().equals("ADMIN")) {
            Optional<Board> optionalBoard = Optional.of(this.boardRepository.findByIdAndLcategoryAndMcategory(boardInput.getId(), lcategory, mcategory));
            // 작성자유무를 체크하지않고 바로 삭제
            if(optionalBoard.isPresent()){
                this.boardRepository.deleteById(boardInput.getId());
                this.commentRepository.deleteAllByBoardIndex(boardInput.getId());
                return true;
            }
            else return false;
        }
            else return false;
    }
    // 댓글삭제
    public boolean commnetDelete(BoardInput boardInput){
        Account account = this.accountRepository.findByUserId(boardInput.getAuthor());
        Optional<BoardComment> optionalCoinBoardComment = this.commentRepository.findById(boardInput.getId());
        //상용자가 유저일경우
        if (account.getRole().equals("USER")){
            if(optionalCoinBoardComment.isPresent()){
                // 작성자가 맞는지 체크 후 삭제
                if (boardInput.getAuthor().equals(optionalCoinBoardComment.get().getAuthor())) {
                    this.commentRepository.deleteById(boardInput.getId());
                    return true;
                }
                else return false;
            }
        }
        //사용자가 관리자일경우
        else if (account.getRole().equals("ADMIN")) {
            if(optionalCoinBoardComment.isPresent()){
                //작성자가 맞는지 체크하지않고 삭제
                    this.commentRepository.deleteById(boardInput.getId());
                    return true;
            }
        } else return false;
        return false;
    }
}
