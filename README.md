# 쇼핑 서버 Rest API Server


## 프로젝트 소개 
* **프로젝트 명** : 간단 쇼핑 서버 Rest API Server
* **제공 기능** : 회원관련 기능(회원가입, 로그인), 상품 및 카테고리 등록, 주문 기능
---------------------------------------

## 기술 스택

* **Backend** : Spring Boot 3.2.5
* **Libraries** : Spring data JPA, Spring Security , JWT
* **Database** : MySql
* **Language** : Java

---------------------------------------

## 엔티티 분석

<img width="779" alt="Screenshot 2024-07-09 at 3 19 33 PM" src="https://github.com/jaeilssss/ShoppingServer/assets/59818827/672e103b-27b9-4126-8934-3e85951a7951">

---------------------------------------


## 주요 코드 분석

### Token 생성 매소드
<pre><code>
  
      public JWTDto createToken(String email, Long userId) {
        Claims claims = Jwts.claims();

        claims.put(AUTHORITIES_KEY,email);

        long now = new Date().getTime();
        Date accessExpiration = new Date(now + this.expirationTime);
        Date refreshExpiration = new Date(now + this.refreshExpirationTime);

        String accessToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(accessExpiration)
                .setSubject(email)
                .claim("userId",userId)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        String refreshToken = Jwts.builder()
                .setExpiration(refreshExpiration)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        return JWTDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
  
</code></pre>

Access Token 과 RefreshToken 을 생성하는 메소드 

Access Token에는 email과 userId를 넣어주고 RefreshToken에는 앞선 두개의 개인 정보를 담지 않고 토큰을 생성합니다.

RefreshToken은 말 그대로 AcessToken을 갱신 받기 위해서 쓰는 토큰 임으로 개인 정보를 담지 않고 생성합니다.

