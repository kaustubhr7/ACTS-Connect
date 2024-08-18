package com.actsconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actsconnect.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {

	PasswordResetToken findByToken(String token);

}
