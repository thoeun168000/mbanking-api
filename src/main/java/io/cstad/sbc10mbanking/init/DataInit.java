package io.cstad.sbc10mbanking.init;

import io.cstad.sbc10mbanking.domain.AccountType;
import io.cstad.sbc10mbanking.domain.Role;
import io.cstad.sbc10mbanking.domain.User;
import io.cstad.sbc10mbanking.features.accounttype.AccountTypeRepository;
import io.cstad.sbc10mbanking.features.user.RoleRepository;
import io.cstad.sbc10mbanking.features.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void init(){

        if (accountTypeRepository.count() == 0) {
            AccountType payroll = new AccountType();
            payroll.setName("Payroll");
            payroll.setAlias("payroll");
            payroll.setIsDeleted(false);
            payroll.setDescription("Payroll Account of User");

            AccountType saving = new AccountType();
            saving.setName("Saving");
            saving.setAlias("saving");
            saving.setIsDeleted(false);
            saving.setDescription("Saving Account of User");

//            accountTypeRepository.save(payroll);
//            accountTypeRepository.save(saving);

            accountTypeRepository.saveAll(List.of(payroll, saving));
        }

        if (userRepository.count() == 0) {

            Role user = new Role();
            user.setName("USER");

            Role customer = new Role();
            customer.setName("CUSTOMER");

            Role manager = new Role();
            manager.setName("MANAGER");

            Role admin = new Role();
            admin.setName("ADMIN");

            roleRepository.saveAll(List.of(user, customer, manager, admin));

            User user1 = new User();
            user1.setUuid(UUID.randomUUID().toString());
            user1.setName("ko kok");
            user1.setGender("Male");
            user1.setPhoneNumber("0954323223");
            user1.setPin("1234");
            user1.setPassword(passwordEncoder.encode("qwer"));
            user1.setNationalCardId("55556666");
            user1.setProfileImage("user/avatar.png");
            user1.setStudentCardId("CSTAD-1001");
            user1.setIsDeleted(false);
            user1.setIsBlocked(false);
            user1.setRoles(List.of(user, admin));

            User user2 = new User();
            user2.setUuid(UUID.randomUUID().toString());
            user2.setName("kok ca");
            user2.setGender("Female");
            user2.setPhoneNumber("0774323223");
            user2.setPin("2222");
            user2.setPassword(passwordEncoder.encode("qwerqwer"));
            user2.setNationalCardId("11112222");
            user2.setProfileImage("user/avatar.png");
            user2.setStudentCardId("CSTAD-1002");
            user2.setIsDeleted(false);
            user2.setIsBlocked(false);
            user2.setRoles(List.of(user, manager));

            User user3 = new User();
            user3.setUuid(UUID.randomUUID().toString());
            user3.setName("nan na");
            user3.setGender("Male");
            user3.setPhoneNumber("077432773");
            user3.setPin("3333");
            user3.setPassword(passwordEncoder.encode("qwe123"));
            user3.setNationalCardId("00005555");
            user3.setProfileImage("user/avatar.png");
            user3.setStudentCardId("CSTAD-1003");
            user3.setIsDeleted(false);
            user3.setIsBlocked(false);
            user3.setRoles(List.of(user, customer));

//            userRepository.save(user1);
//            userRepository.save(user2);
            userRepository.saveAll(List.of(user1, user2, user3));
        }
    }
}
