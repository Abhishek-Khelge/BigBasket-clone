package com.example.users.repository;


import com.example.users.entity.PersonalDetails;
import com.example.users.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails,Long> {
    Optional<PersonalDetails> findByUserDetails(UserDetails userDetails);
}
