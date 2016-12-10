package sample.boot.domain.model.user;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;
import sample.boot.domain.model.address.Address;

import javax.validation.constraints.NotNull;

@Component
class UserSpecFactory {

    Specification<User> create(@NotNull final Address address) {
        return Specifications
                .where(postalCode(address))
                .and(prefectures(address))
                .and(city(address))
                .and(address(address))
                .and(building(address));
    }

    private Specification<User> postalCode(final Address address) {
        if (!address.hasPostalCode()) return null;
        return ((root, query, cb) -> cb.equal(root.get("address").get("postalCode"), address.getPostalCode()));
    }

    private Specification<User> prefectures(final Address address) {
        if (!address.hasPrefectures()) return null;
        return ((root, query, cb) -> cb.equal(root.get("address").get("prefectures"), address.getPrefectures()));
    }

    private Specification<User> address(final Address address) {
        if (!address.hasAddress()) return null;
        return ((root, query, cb) -> cb.equal(root.get("address").get("address"), address.getAddress()));
    }

    private Specification<User> city(final Address address) {
        if (!address.hasCity()) return null;
        return ((root, query, cb) -> cb.equal(root.get("address").get("city"), address.getCity()));
    }

    private Specification<User> building(final Address address) {
        if (!address.hasBuilding()) return null;
        return ((root, query, cb) -> cb.equal(root.get("address").get("building"), address.getBuilding()));
    }

}
