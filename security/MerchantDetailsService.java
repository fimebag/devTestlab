package com.fimepay.merchantapp.security;

import com.fimepay.merchantapp.model.Merchant;
import com.fimepay.merchantapp.repository.MerchantRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
public class MerchantDetailsService implements UserDetailsService {

    private final MerchantRepository merchantRepo;

    public MerchantDetailsService(MerchantRepository merchantRepo) {
        this.merchantRepo = merchantRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Merchant m = merchantRepo.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("No merchant with email: " + email));

        // You can customize roles/authorities here
        UserBuilder builder = org.springframework.security.core.userdetails.User
            .withUsername(m.getEmail())
            .password(m.getPasswordHash())
            .roles("MERCHANT");  

        return builder.build();
    }
}
