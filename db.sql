#권한 부여

#최초
##root 접속 후 sbsst 계정 생성 및 권한 부여를 해야한다
##root 계정 접속 정보는 : root/패스워드 없음
##GRANT ALL PRIVILEGES ON *.* TO sbsst@`%` IDENTIFIED BY 'sbs123414';

##이후 부터는 [sbsst/sbs123414]접속

#데이터베이스 생성
DROP DATABASE IF EXISTS untact;
CREATE DATABASE untact;
USE untact;

#게시물 테이블 생성
CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

#게시물, 테스트 데이터 생성
INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = "제목1 입니다.",
    `body` = "내용1 입니다.";
INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = "제목2 입니다.",
    `body` = "내용2 입니다.";
INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = "제목3 입니다.",
    `body` = "내용3 입니다.";


#회원  테이블 생성
CREATE TABLE `member`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(30) NOT NULL,
    loginPw CHAR(100) NOT NULL,
    `name` CHAR(30) NOT NULL,
    `nickname` CHAR(30) NOT NULL,
    `email` CHAR(100) NOT NULL,
    `cellphoneNo` CHAR(20) NOT NULL
);

#로그인 Id로 검색했을 때
ALTER TABLE `member` ADD UNIQUE INDEX (`loginId`); 

#회원, 테스트 데이터 생성
INSERT INTO `member`
SET 
regDate = NOW(),
updateDate = NOW(),
loginId = "user1",
loginPw = "1234",
`name` = "사자",
`nickname` = "호랑이",
`cellphoneNo` = "01012344321",
`email` = "saja@go.com";

#칼럼 추가하기
ALTER TABLE article ADD COLUMN memberID int(10) UNSIGNED NOT NULL AFTER updateDate;

#기존 게시물의 작성자를 회원1로 저장
UPDATE article
SET memberId = 1
WHERE memberId = 0;