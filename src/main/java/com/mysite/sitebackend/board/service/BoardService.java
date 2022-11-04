package com.mysite.sitebackend.board.service;


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
    private final BoardCommentRepository commentRepository;
    private final ModelMapper modelMapper;
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    String formatedNow = now.format(formatter);

    //전체검색
    public List<BoardListDto> searchAll(String value){
        List<Board> boardList = this.boardRepository.findBySubjectContaining(value);
        List<BoardListDto> boardListDto = boardList.stream()
                .map(BoardListDto1 -> modelMapper.map(BoardListDto1, BoardListDto.class))
                .collect(Collectors.toList());
        return boardListDto;
    }
    //게시판별검색
    public List<BoardListDto> search(String value, String lcategory, String mcategory){
        List<Board> boardList = this.boardRepository.findBySubjectContainingAndLcategoryAndMcategory(value, lcategory, mcategory);
        List<BoardListDto> boardListDto = boardList.stream()
                .map(BoardListDto1 -> modelMapper.map(BoardListDto1, BoardListDto.class))
                .collect(Collectors.toList());
        return boardListDto;
    }
    //게시글 작성하기
    public boolean boardPost(String lcategory, String mcategory, BoardInput boardInput, MultipartFile file) throws SQLException {
            Board b1 = new Board();
            b1.setSubject(boardInput.getSubject());
            b1.setContents(boardInput.getContents());
            b1.setAuthor(boardInput.getAuthor());
            b1.setViews(0);
            b1.setDate(formatedNow);
            b1.setLcategory(lcategory);
            b1.setMcategory(mcategory);
//            b1.setImage(this.imageSave(file));
            this.boardRepository.save(b1);
            return true;
    }
    //댓글 작성하기
    public boolean commentPost(BoardInput boardInput){
        Optional<Board> optionalCoinBoard = this.boardRepository.findById(boardInput.getId());
        if(optionalCoinBoard.isPresent()){
            BoardComment a = new BoardComment();
            a.setContents(boardInput.getContents());
            a.setDate(formatedNow);
            a.setAuthor(boardInput.getAuthor());
            a.setBoardIndex(boardInput.getId());
            this.commentRepository.save(a);
            return true;
        }
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
        Optional<Board> optionalBoard = Optional.of(this.boardRepository.findByIdAndLcategoryAndMcategory(boardInput.getId(), lcategory, mcategory));
        if(optionalBoard.isPresent()){
            if(boardInput.getAuthor().equals(optionalBoard.get().getAuthor())){
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
        Optional<BoardComment> optionalCoinBoardComment = this.commentRepository.findById(boardInput.getId());
        if(optionalCoinBoardComment.isPresent()){
            this.commentRepository.deleteById(boardInput.getId());
            return true;
        }
        else return false;
    }
}
