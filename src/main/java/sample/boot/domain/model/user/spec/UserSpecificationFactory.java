package sample.boot.domain.model.user.spec;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;
import sample.boot.domain.model.address.Address;
import sample.boot.domain.model.user.User;

import javax.validation.constraints.NotNull;

@Component
public class UserSpecificationFactory {

    public Specification<User> create(@NotNull final Address address) {
        return Specifications
                .where(postalCode(address))
                .and(prefectures(address))
                .and(city(address))
                .and(address(address))
                .and(building(address));
    }

    private Specification<User> postalCode(final Address address) {
        if (StringUtils.isEmpty(address.getPostalCode())) return null;
        return ((root, query, cb) -> cb.equal(root.get("address").get("postalCode"), address.getPostalCode()));
    }

    private Specification<User> prefectures(final Address address) {
        if (StringUtils.isEmpty(address.getPrefectures())) return null;
        return ((root, query, cb) -> cb.equal(root.get("address").get("prefectures"), address.getPrefectures()));
    }

    private Specification<User> address(final Address address) {
        if (StringUtils.isEmpty(address.getAddress())) return null;
        return ((root, query, cb) -> cb.equal(root.get("address").get("address"), address.getAddress()));
    }

    private Specification<User> city(final Address address) {
        if (StringUtils.isEmpty(address.getCity())) return null;
        return ((root, query, cb) -> cb.equal(root.get("address").get("city"), address.getCity()));
    }

    private Specification<User> building(final Address address) {
        if (StringUtils.isEmpty(address.getBuilding())) return null;
        return ((root, query, cb) -> cb.equal(root.get("address").get("building"), address.getBuilding()));
    }

}
