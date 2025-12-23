package com.example.umc9th.auth.repository;

import com.myApp.auth.redis.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
