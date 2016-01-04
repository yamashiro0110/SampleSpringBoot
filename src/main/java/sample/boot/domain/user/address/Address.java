package sample.boot.domain.user.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import sample.boot.domain.user.User;

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

}