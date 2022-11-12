# Transactional Propagation
caller -> first Transactional -> second Transactional
second transactional의 propagation 옵션이 REQUIRED, REQUIRED_NEW이고
second transactional에서 예외가 발생했을때 first transactional이 rollback되는지 확인하는 테스트
