# 문화생활 티켓 아카이빙 서비스 티캣츠(Ticats)
![로고](https://github.com/potenday-23/tickets_backend/assets/82714785/583637a8-0796-4abc-949c-faea3d87e4b0)

## Table of Contents
- [개요](#개요)
- [Skils](#skils)
- [Installation](#Installation)
- [Running Tests](#running-tests)
- [API Reference](#api-reference)
- [프로젝트 진행 및 이슈 관리](#프로젝트-진행-및-이슈-관리)
- [구현과정(설계 및 의도)](#구현과정(설계-및-의도))
- [TIL 및 회고](#til-및-회고)
- [Authors](#authors)

## 개요
본 서비스는 문화생활 기록을 티켓 형식으로 저장하고 조회할 수 있는 아카이빙 서비스 입니다. <br/>

## Skils
언어 및 프레임워크: ![Static Badge](https://img.shields.io/badge/Java-17-Green) ![Static Badge](https://img.shields.io/badge/Spring_boot-REST-Green)<br/>
데이터베이스 및 테스트: ![Static Badge](https://img.shields.io/badge/MySQL-8-blue) ![Static Badge](https://img.shields.io/badge/JUnit-Green) <br/>
배포 : ![Static Badge](https://img.shields.io/badge/LINUX-039BC6) ![Static Badge](https://img.shields.io/badge/AWS-Orange) <br/>

## Installation
1. 환경변수 설정
   - TICKETS_DB_URL : MySQL 데이터베이스 URL
   - TICKETS_DB_USER : MySQL 데이터베이스 유저
   - TICKETS_DB_PW : MySQL 데이터베이스 패스워드
   - TICKETS_S3_ACCESS : S3 Access key 정보 
   - TICKETS_S3_SECRET : S3 Secret key 정보
   - TICKETS_SECRET : JWT Secret 키 정보
   - TICKETS_CLIENT : KAKAO Client 정보(현재 비활성화)
   - TICATS_SSL_PW : ssl 인증서의 pw 정보
2. Ticats 어플리케이션 백엔드 build
    ```bash
    ./gradlew bootjar
    ./gradlew bootrun
    ```

## Running Tests
Ticats 어플리케이션 백엔드 Test(미구현)
```bash
  ./gradlew test
```
> Coverage ScreenShot ![Static Badge](https://img.shields.io/badge/Test_Passed-7/7-green)<br/>

## API 문서(Swagger)
[티캣츠 Swagger 문서 바로가기👈](https://www.ticats.r-e.kr/swagger-ui/)

## 프로젝트 진행 및 이슈 관리
[![Github Project](https://img.shields.io/badge/Github-%23000000.svg?style=for-the-badge&logo=Github&logoColor=white)](https://github.com/potenday-23/tickets_backend/issues)

## 구현과정(설계 및 의도)
- E-R Diargram
![ERD Cloud](https://github.com/potenday-23/tickets_backend/assets/82714785/5b4e207d-35ef-4153-8ce5-a25844bc17cf)
- Architecture
![Architecture](https://github.com/potenday-23/tickets_backend/assets/82714785/021b3e23-66bc-4724-9c31-4e550342f38d)


## TIL 및 회고
### 회고

### TIL
- [QueryDsl에서의 월별 조회를 위한 GroupBy](https://www.notion.so/gabang2/QueryDsl-3b8f3bc3ba30457b8b59371b0654df1d)
- [LocalDateTime 클라이언트 에러 처리](https://www.notion.so/gabang2/LocalDateTime-b69aeadeb78a46349474cb395ffe1e80?pvs=25)
- [JWT Filter에서의 에러 처리 - 미완료](https://www.notion.so/gabang2/JWT-Filter-Error-8c488e856bbb48b9a554146b29c96a5d)
- [LocalDateTime 100% 활용하기 - 미완료](https://www.notion.so/gabang2/LocalDateTime-100-51a54847578f45a88af44eb533390285)
- [테스트 코드 작성법 - 미완료](https://www.notion.so/gabang2/4d89c76418c04972a165966258fe4ce0)

## Authors
- [gabang2](https://github.com/gabang2)