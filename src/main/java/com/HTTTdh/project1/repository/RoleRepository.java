package com.HTTTdh.project1.repository;

import com.HTTTdh.project1.models.ERole;
import com.HTTTdh.project1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
