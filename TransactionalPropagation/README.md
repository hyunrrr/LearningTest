# Transactional Propagation
caller -> first Transactional -> second Transactional

second transactional의 propagation 옵션이 REQUIRED, REQUIRED_NEW이고

second transactional에서 예외가 발생했을때 first transactional이 rollback되는지 확인하는 테스트

## REQUIRED
- second transactional에서 예외가 발생하면 first transactional이 rollback됨
- second transactional에서 예외가 발생해도 catch해서 예외를 먹으면 first transactional은 rollback되지 않음

## REQUIRED_NEW
- second transactional에서 예외가 발생해도 first transactional이 rollback되지 않음



## 정리
[보러가기](https://velog.io/@hyunrrr/%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98-%EC%A0%84%ED%8C%8C-%EA%B3%B5%EC%8B%9D%EB%AC%B8%EC%84%9C-%ED%86%B5%ED%95%B4-%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0-feat.-%ED%95%99%EC%8A%B5%ED%85%8C%EC%8A%A4%ED%8A%B8)
