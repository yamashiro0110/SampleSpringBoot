package sample.boot.repository.user.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.boot.domain.user.address.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
