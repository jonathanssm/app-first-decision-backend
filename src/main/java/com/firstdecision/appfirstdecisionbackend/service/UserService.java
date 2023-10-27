package com.firstdecision.appfirstdecisionbackend.service;

import com.firstdecision.appfirstdecisionbackend.model.dto.UserCredentialDTO;
import com.firstdecision.appfirstdecisionbackend.model.dto.UserDTO;
import com.firstdecision.appfirstdecisionbackend.model.entity.User;
import com.firstdecision.appfirstdecisionbackend.model.filter.UserFilter;
import com.firstdecision.appfirstdecisionbackend.repository.IUserRepository;
import com.firstdecision.appfirstdecisionbackend.util.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final IUserRepository userRepository;

    @Transactional(readOnly = true)
    public boolean login(UserCredentialDTO dto) {
        log.debug("Init login");

        User user = userRepository.findByEmail(dto.getEmail());

        if (user == null || !verifyPassword(dto.getPassword(), user.getPassword())) {
            log.debug("End login without success");

            return false;
        }

        log.debug("End login with success");

        return true;
    }

    @Transactional
    public UserDTO insert(UserDTO dto) {
        log.debug("Init user insert");

        User entity = Converter.dtoToEntity(dto, User.class);

        entity.setPassword(hashPassword(entity.getPassword()));

        userRepository.save(entity);

        log.debug("End user insert");

        return Converter.entityToDto(entity, UserDTO.class);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        log.debug("Init find all users");

        List<UserDTO> dtoList = Converter.entityListToDtoList(userRepository.findAll(), UserDTO.class);

        log.debug("End find all users");

        return dtoList;
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findByFilter(UserFilter filter, Pageable pageable) {
        log.debug("Init find users by filter");

        Page<User> page = userRepository.findByFilter(filter, pageable);

        log.debug("End find users by filter");

        return Converter.entityPageToDtoPage(page, UserDTO.class);
    }

    private static String hashPassword(String password) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }

    private static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
