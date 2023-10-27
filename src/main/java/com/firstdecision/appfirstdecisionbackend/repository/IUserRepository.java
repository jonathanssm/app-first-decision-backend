package com.firstdecision.appfirstdecisionbackend.repository;

import com.firstdecision.appfirstdecisionbackend.model.entity.User;
import com.firstdecision.appfirstdecisionbackend.model.filter.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("from User usr where " +
            "((:#{#filter.name} is null or :#{#filter.name} = '') or unaccent(lower(usr.name)) like unaccent(lower('%'+:#{#filter.name}+'%')))" +
            " or ((:#{#filter.email} is null or :#{#filter.email} = '') or unaccent(lower(usr.email)) like unaccent(lower('%'+:#{#filter.email}+'%')))"
    )
    Page<User> findByFilter(@Param("filter") UserFilter filter, Pageable pageable);

    @Query("from User usr where :email = usr.email")
    User findByEmail(String email);

}
