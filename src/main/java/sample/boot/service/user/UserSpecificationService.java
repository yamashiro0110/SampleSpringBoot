package sample.boot.service.user;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import sample.boot.domain.user.address.Address;
import sample.boot.domain.user.User;

@AllArgsConstructor
public class UserSpecificationService {

    private Address address;

    public Specification<User> specification() {
        if (address == null) return null;
        return Specifications
                .where(postalCode())
                .and(prefectures())
                .and(city())
                .and(address())
                .and(building());
    }

    private Specification<User> postalCode() {
        if (StringUtils.isEmpty(this.address.getPostalCode())) return null;
        return ((root, query, cb) -> cb.equal(root.get("address").get("postalCode"), this.address.getPostalCode()));
    }

    private Specification<User> prefectures() {
        if (StringUtils.isEmpty(this.address.getPrefectures())) return null;
        return ((root, query, cb) -> cb.equal(root.get("address").get("prefectures"), this.address.getPrefectures()));
    }

    private Specification<User> address() {
        if (StringUtils.isEmpty(this.address.getAddress())) return null;
        return ((root, query, cb) -> cb.equal(root.get("address").get("address"), this.address.getAddress()));
    }

    private Specification<User> city() {
        if (StringUtils.isEmpty(this.address.getCity())) return null;
        return ((root, query, cb) -> cb.equal(root.get("address").get("city"), this.address.getCity()));
    }

    private Specification<User> building() {
        if (StringUtils.isEmpty(this.address.getBuilding())) return null;
        return ((root, query, cb) -> cb.equal(root.get("address").get("building"), this.address.getBuilding()));
    }

}
