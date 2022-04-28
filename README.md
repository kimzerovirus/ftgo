# CRUD
> 위지윅에디터(summernote)를 적용한 게시판

|   분야        | 사용기술       | 비고 |
|--------------|------------|-----|
|  Database  | postgresql |   |

```
spring.jpa.hibernate.ddl-auto=update
```
어플리케이션 실행시 ddl문을 자동으로 실행한다. 값으로는 create, update, create-drop, validate가 있다.
```
spring.jpa.properties.hibernate.format_sql = true
```
SQL을 포맷팅하여 출력해준다.
```
spring.jpa.show-sql = true
```
jpa 처리시 sql을 보여준다.
