# triple

### 실행방법
1. dev와 test의 경우 main/resources/data.sql에 원하는 값을 추가한 후 실행
2. dev와 test 환경의 경우 ddl-auto:create로 되어있기에 매번 data.sql에 있는 값으로 리뉴얼됨
3. prod 환경의 경우 main.resources/application-prod.yml에 본인의 로컬 MySQl의 root 사용자의 아이디와 비밀번호를 기입하고 실행하여야함.