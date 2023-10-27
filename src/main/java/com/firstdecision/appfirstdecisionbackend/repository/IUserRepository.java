package com.firstdecision.appfirstdecisionbackend.repository;

import com.firstdecision.appfirstdecisionbackend.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("from User usr where " +
            "((:name is null or :name = '') or usr.name like %:name%)" +
            " and ((:email is null or :email = '') or usr.email like %:email%)"
    )
    Page<User> findByFilter(String name, String email, Pageable pageable);

    @Query("from User usr where :email = usr.email")
    User findByEmail(String email);

}
