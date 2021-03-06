package sample.boot.domain.model.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TestDomainRepository extends JpaRepository<TestDomain, Long>, JpaSpecificationExecutor<TestDomain> {
}
