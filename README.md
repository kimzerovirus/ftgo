- 카테고리는 depth 가 얼마나 깊어질지 모르므로 별도의 테이블을 두지 않고  parentId로 카테고리 테이블이 다시 카테고리 테이블을 참조하는 방식으로 설계
- 리뷰는 제품 구매자가 리뷰를 남기고 그 리뷰를 그룹으로 그 안에서 댓글만 작성 가능하므로 별도의 답글 테이블을 만들어 리뷰테이블을 참조하게 설계
```
# 백그라운드 실행, 강제 재생성
db-up:
	docker-compose up -d --force-recreate

# volume 삭제
db-down:
	docker-compose down -v

```