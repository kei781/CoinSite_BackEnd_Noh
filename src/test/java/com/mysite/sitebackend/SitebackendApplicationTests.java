package com.mysite.sitebackend;

import com.mysite.sitebackend.board.Inform.dao.InformBoardRepository;
import com.mysite.sitebackend.board.Inform.domain.InformBoard;
import com.mysite.sitebackend.board.coin.dao.CoinBoardRepository;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.news.dao.NewsBoardRepository;
import com.mysite.sitebackend.board.news.domain.NewsBoard;
import com.mysite.sitebackend.board.stockMarket.dao.StockMarketBoardRepository;
import com.mysite.sitebackend.board.stockMarket.domain.StockMarketBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@SpringBootTest
class SitebackendApplicationTests {
	@Autowired
	private CoinBoardRepository coinBoardRepository;
	@Autowired
	private InformBoardRepository informBoardRepository;
	@Autowired
	private NewsBoardRepository newsBoardRepository;
	@Autowired
	private StockMarketBoardRepository stockMarketBoardRepository;
	LocalDate now = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	String formatedNow = now.format(formatter);

	@Test
	void contextLoads() {
		for(int i = 1; i<=10; i++){
			CoinBoard c1 = new CoinBoard();
			InformBoard i1 = new InformBoard();
			NewsBoard n1 = new NewsBoard();
			StockMarketBoard sm1 = new StockMarketBoard();
			String subject;
			String contents;
			String author;
			Integer views;

			// Coin Board (코인게시판) 테스트 케이스 추가 코드
			subject = i + "번째 코인 게시글 제목";
			contents = i + "번째 코인 게시글 내용";
			author = i + "번째_익명작성자";
			views = 0;
			c1.setSubject(subject);
			c1.setContents(contents);
			c1.setAuthor(author);
			c1.setViews(views);
			c1.setDate(formatedNow);
			this.coinBoardRepository.save(c1);
			System.out.println(i + "번째 코인 게시판 테스트 데이터가 성공적으로 저장되었습니다.");

			// Inform Board (공지사항 게시판) 테스트 케이스 추가 코드
			subject = i + "번째 공지사항 게시글 제목";
			contents = i + "번째 공지사항 게시글 내용";
			author = i + "번째_익명작성자";
			views = 0;
			i1.setSubject(subject);
			i1.setContents(contents);
			i1.setAuthor(author);
			i1.setDate(formatedNow);
			i1.setViews(views);
			this.informBoardRepository.save(i1);
			System.out.println(i + "번째 공지사항 게시판 테스트 데이터가 성공적으로 저장되었습니다.");

			// News Board (뉴스 게시판) 테스트 케이스 추가 코드
			subject = i + "번째 뉴스 게시글 제목";
			contents = i + "번째 뉴스 게시글 내용";
			author = i + "번째_익명작성자";
			views = 0;
			n1.setSubject(subject);
			n1.setContents(contents);
			n1.setAuthor(author);
			n1.setDate(formatedNow);
			n1.setViews(views);
			this.newsBoardRepository.save(n1);
			System.out.println(i + "번째 뉴스 게시판 테스트 데이터가 성공적으로 저장되었습니다.");

			// StockMarket Board (주식 게시판) 테스트 케이스 추가 코드
			subject = i + "번째 주식 게시글 제목";
			contents = i + "번째 주식 게시글 내용";
			author = i + "번째_익명작성자";
			views = 0;
			sm1.setSubject(subject);
			sm1.setContents(contents);
			sm1.setAuthor(author);
			sm1.setDate(formatedNow);
			sm1.setViews(views);
			this.stockMarketBoardRepository.save(sm1);
			System.out.println(i + "번째 주식 게시판 테스트 데이터가 성공적으로 저장되었습니다.");
		}
	}
}
