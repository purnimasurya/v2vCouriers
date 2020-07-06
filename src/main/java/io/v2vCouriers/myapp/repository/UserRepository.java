package io.v2vCouriers.myapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.v2vCouriers.myapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByemail(String email);

}
