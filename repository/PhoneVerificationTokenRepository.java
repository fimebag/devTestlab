// Phone OTP
package com.fimepay.merchantapp.repository;
import com.fimepay.merchantapp.model.PhoneVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
public interface PhoneVerificationTokenRepository extends JpaRepository<PhoneVerificationToken, UUID> {
    Optional<PhoneVerificationToken> findByMerchant_EmailAndOtp(String email, String otp);
}
