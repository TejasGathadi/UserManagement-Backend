package com.tejas.user_management_proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejas.user_management_proj.model.User;
// write the data-type of primary key as we use long here write long
public interface UserRepository extends JpaRepository<User, Long> {

}
