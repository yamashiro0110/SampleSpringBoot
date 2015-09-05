package sample.repository.user.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.domain.user.address.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
