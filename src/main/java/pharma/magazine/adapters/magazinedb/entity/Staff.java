package pharma.magazine.adapters.magazinedb.entity;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pharma.magazine.domain.model.StaffModel;

import javax.persistence.*;
import java.util.Collection;
import java.util.Optional;

@Entity(name = "staff")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Staff implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Boolean active;

    @OneToOne()
    @JoinColumn(name = "store_id")
    private Store store;

    public StaffModel toModel() {
        return StaffModel.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phone(phone)
                .password(password)
                .active(active)
                .build();
    }


    public static Staff of(StaffModel staffModel) {
        return staffModel==null ? null: // we can have null here, when updatedBy is null
                Staff.builder()
                .id(Optional.ofNullable(staffModel.getId()).orElse(null))
                .firstName(staffModel.getFirstName())
                .lastName(staffModel.getLastName())
                .email(staffModel.getEmail())
                .phone(staffModel.getPhone())
                .password(staffModel.getPassword())
                .active(staffModel.getActive())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.id == 1L) {
            return Lists.newArrayList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            return Lists.newArrayList(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
