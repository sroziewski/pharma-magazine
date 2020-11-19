package pharma.magazine.adapters.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pharma.magazine.adapters.magazinedb.entity.Staff;
import pharma.magazine.adapters.magazinedb.repository.implementation.StaffRepository;
import pharma.magazine.domain.model.StaffModel;

import java.util.Optional;

@Service
public class SessionService implements UserDetailsService {

    private final StaffRepository staffRepository;

    public SessionService(@Autowired StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Staff getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return findUserByEmail(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByEmail(username);
    }

    private Staff findUserByEmail(String email) {
        Optional<StaffModel> user = staffRepository.findByEmail(email);
        StaffModel staffModel = user.orElseThrow(() -> new UsernameNotFoundException("Cannot find user with name:" + email));
        return Staff.builder()
            .id(staffModel.getId())
            .email(staffModel.getEmail())
            .firstName(staffModel.getFirstName())
            .lastName(staffModel.getLastName())
            .password(staffModel.getPassword())
            .phone(staffModel.getPhone())
            .active(staffModel.getActive())
            .build();
    }
}
