package com.teluguSkillHub.taskproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teluguSkillHub.taskproject.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

}
