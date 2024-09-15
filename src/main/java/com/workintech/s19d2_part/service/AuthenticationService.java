package com.workintech.s19d2_part.service;


import com.workintech.s19d2_part.entity.Member;
import com.workintech.s19d2_part.entity.Role;
import com.workintech.s19d2_part.repository.MemberRepository;
import com.workintech.s19d2_part.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public Member register(String email, String password) {
        Optional<Member> byEmail = memberRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            throw new RuntimeException("User with given email already exist! Email: " + email);
        }

        String encodedPassword = passwordEncoder.encode(password);

        List<Role> roleList = new ArrayList<>();

        /*Optional<Role> roleUser = roleRepository.findByAuthority("USER");
        if (!roleUser.isPresent()) {
            Role roleUserEntity = new Role();
            roleUserEntity.setAuthority("USER");
            roleList.add(roleRepository.save(roleUserEntity));
        } else {

            roleList.add(roleUser.get());
        }*/

        Optional<Role> roleAdmin = roleRepository.findByAuthority("ADMIN");
        if (!roleAdmin.isPresent()) {
            Role roleAdminEntity = new Role();
            roleAdminEntity.setAuthority("ADMIN");
            roleList.add(roleRepository.save(roleAdminEntity));
        } else {
            roleList.add(roleAdmin.get());
        }

        //roleList.add(userRole);


        Member member = new Member();
        member.setEmail(email);
        member.setPassword(encodedPassword);
        member.setRoles(roleList);
        return memberRepository.save(member);

    }

}
