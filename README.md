# aks-auto-deploy-sample

AKS(Azure Kubernetes Service) Auto Deploy 실험을 위한 Spring Boot 게시판 애플리케이션입니다.

## 기술 스택

- **Java 17**
- **Spring Boot 3.2.3**
- **Spring MVC** - MVC 패턴 적용
- **Thymeleaf** - 서버사이드 템플릿 엔진
- **Spring Data JPA** - 데이터 접근 계층
- **H2 Database** - 인메모리 DB (개발/테스트용)
- **Maven** - 빌드 도구

## 기능

- 게시글 목록 조회
- 게시글 상세 조회
- 게시글 작성
- 게시글 수정
- 게시글 삭제

## 프로젝트 구조

```
src/main/java/com/example/board/
├── BoardApplication.java          # 진입점
├── controller/
│   ├── HomeController.java        # 루트 리다이렉트
│   └── PostController.java        # 게시글 CRUD 컨트롤러
├── service/
│   └── PostService.java           # 비즈니스 로직
├── repository/
│   └── PostRepository.java        # 데이터 접근 (Spring Data JPA)
└── entity/
    └── Post.java                  # 게시글 엔티티

src/main/resources/
├── templates/posts/
│   ├── list.html                  # 목록 페이지
│   ├── form.html                  # 작성/수정 폼
│   └── view.html                  # 상세 페이지
├── static/css/
│   └── style.css                  # 스타일시트
└── application.properties         # 설정
```

## 로컬 실행 방법

### 사전 요구사항
- Java 17 이상
- Maven 3.6 이상

### 빌드 및 실행

```bash
# 빌드
mvn clean package

# 실행
java -jar target/board-0.0.1-SNAPSHOT.jar
```

또는 Maven으로 직접 실행:

```bash
mvn spring-boot:run
```

### 접속

- 애플리케이션: http://localhost:8080
- H2 콘솔: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:boarddb`
  - Username: `sa`
  - Password: (없음)

## 테스트 실행

```bash
mvn test
```
