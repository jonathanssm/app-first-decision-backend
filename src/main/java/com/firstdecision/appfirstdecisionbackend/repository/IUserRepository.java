package com.firstdecision.appfirstdecisionbackend.repository;

import com.firstdecision.appfirstdecisionbackend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("from User usr where ((:login is null or :login = '') or usr.login like %:login%)")
    Page<User> findByFilter(String login, Pageable pageable);

}
