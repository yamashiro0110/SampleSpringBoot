package sample.bean;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * userテーブルのリポジトリ
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
