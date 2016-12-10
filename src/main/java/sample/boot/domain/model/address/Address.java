package sample.boot.domain.model.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import sample.boot.domain.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 7, max = 7)
    @Pattern(regexp = "[0-9]{7}")
    private String postalCode;

    @NotNull
    private String prefectures;

    @NotNull
    private String city;

    @NotNull
    private String address;

    @NotNull
    private String building;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private User user;

    public boolean hasPostalCode() {
        return StringUtils.isNotEmpty(this.postalCode);
    }

    public boolean hasPrefectures() {
        return StringUtils.isNotEmpty(this.prefectures);
    }

    public boolean hasCity() {
        return StringUtils.isNotEmpty(this.city);
    }

    public boolean hasAddress() {
        return StringUtils.isNotEmpty(this.address);
    }

    public boolean hasBuilding() {
        return StringUtils.isNotEmpty(this.building);
    }

}
