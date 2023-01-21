package com.modong.backend.auth.refreshToken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

}