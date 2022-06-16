package com.javamachine.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javamachine.entity.Roles;

@Repository
public interface RoleReposiroty extends JpaRepository<Roles,Long>{

	Set<Roles> findByCode(String name);
	boolean existsRolesByCode(String code);
}
