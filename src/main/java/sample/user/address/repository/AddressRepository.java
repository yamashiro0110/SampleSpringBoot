package sample.user.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.user.address.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
