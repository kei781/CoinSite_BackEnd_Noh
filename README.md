<h1> !!!!!해당버전은 Json 테스트버전입니다!!!!!</h1>

<h1> RestApi url의 대분류</h1>
<h2> 예시 : localhost:포트번호/{value}</h2>

1. value의 자리에 위치할 수 있는 키워드는 다음과 같습니다.
2. board(게시판), chart(미구현), account(계정관리)

<h1> 각 대분류별 세부 키워드 </h1>

<h2> 게시판(/board/)의 경우 총 2개의 카테고리(/board/{Large category}/{Medium category})을 지원합니다.</h2>

    각각의 게시판은 다음의 기능을 공통적으로 지원합니다.

1. 게시글작성(post 메소드, /board/{Large category}/{Medium category}/post) 기능은 다음과 같은 객체를 필요로 합니다.
   ````json
   {
   "subject" : "제목입니다.",
   "contents" : "내용입니다.",
   "author" : "글작성자입니다."
   }
   ````
2. 게시판 리스트 가져오기(get 메소드, /board/{Large category}/{Medium category}/get)
3. 특정 게시글 가져오기(get 메소드, /board/{Large category}/{Medium category}/getid) 기능은 다음과 같은 객체를 필요로 합니다.
   ````json
   {
   "id" : 숫자
   }
   ````
4. 특정 게시글 수정(patch 메소드, /board/{Large category}/{Medium category}/patch) 기능은 다음과 같은 객체를 필요로 합니다.
   ````json
   {
   "id" : 게시글의 고유 아이디값(숫자),
   "subject" : "제목입니다.",
   "contens" : "내용입니다.",
   "author" : "글작성자입니다."
   }
   ````
5. 게시글 삭제(delet메소드, /board/{Large category}/{Medium category}/delete/) 기능은 다음과 같은 객체를 필요로 합니다.
   주의사항 : 해당 게시글에 포함된 댓글까지 일괄삭제됩니다!!
   ````json
   {
   "id" : 숫자,
   "author" : "글작성자입니다."
   }
   ````
6. 댓글 작성(post메소드, /board/{Large category}/{Medium category}/post/comment/) 기능은 다음과 같은 객체를 필요로 합니다.
   ````json
   {
   "contents" : "댓글 내용입니다.",
   "author" : "댓글 작성자입니다.",
   "id" : "게시글의 고유 아이디값(숫자)"
   }
   ````
7. 특정게시글의 댓글리스트 가져오기(get메소드, /board/{Large category}/{Medium category}/getid/comment) 기능은 다음과 같은 객체를 필요로 합니다.
   ````
   {
   "id" : "게시글의 고유 아이디값(숫자)"
   }
   ````
8. 댓글 수정(patch 메소드, /board/{Large category}/{Medium category}/patch/comment) 기능은 다음과 같은 객체를 필요로 합니다.
   ````json
   {
   "id" : 댓글의 고유 아이디값(숫자),
   "contens" : "내용입니다.",
   "author" : "댓글작성자입니다."
   }
   ````
9. 댓글 삭제(delet메소드, /board/{Large category}/{Medium category}/delete/comment) 기능은 다음과 같은 객체를 필요로 합니다.
   ````json
   {
   "id" : "게시글의 고유 아이디값(숫자)",
   "author" : "댓글작성자입니다."
   }
   ````


<h2> chart(미구현) </h2>

<h2> 계정관리(/account/)의 경우 다음의 기능을 제공합니다. </h2>

   1. 회원가입(post메소드, /account/signUp) 기능은 다음과 같은 객체를 필요로 합니다.
````json
{
"userId" : "아이디값을 입력하여 주세요.",
"userName" : "당신의 실명을 입력하여주세요",
"password" : "패스워드를 입력해주세요",
"confirmPassword" : "검증용 패스워드를 입력해주세요"
}
````
   2. 로그인(post메소드, account/signIn)기능은 기능은 다음과 같은 객체를 필요로 합니다.
````json
{
"userId" : "아이디값을 입력하여 주세요.",
"password" : "패스워드를 입력해주세요"
}
````
   3. 패스워드 재설정(get 메소드, account/pwReset) 기능은 다음과 같은 객체를 필요로 합니다.
````json
{
"userId" : "아이디값을 입력하여 주세요.",
"userName" : "당신의 실명을 입력하여주세요",
"password" : "패스워드를 입력해주세요",
"confirmPassword" : "검증용 패스워드를 입력해주세요"
}
````
   4. 가져오기(get 메소드, account/accountGet) 기능은 다음과 같은 객체를 필요로 합니다.
````json
{
"userId" : "아이디값을 입력하여 주세요.",
}
````