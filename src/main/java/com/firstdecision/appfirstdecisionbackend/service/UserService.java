package com.firstdecision.appfirstdecisionbackend.service;

import com.firstdecision.appfirstdecisionbackend.dto.UserDTO;
import com.firstdecision.appfirstdecisionbackend.entity.User;
import com.firstdecision.appfirstdecisionbackend.repository.IUserRepository;
import com.firstdecision.appfirstdecisionbackend.util.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Transactional
    public UserDTO insert(UserDTO dto) {
        log.debug("Init user insert");

        User entity = Converter.dtoToEntity(dto, User.class);

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
    public Page<UserDTO> findByFilter(String login, Pageable pageable) {
        log.debug("Init find users by filter");

        Page<User> page = userRepository.findByFilter(login, pageable);

        log.debug("End find users by filter");

        return Converter.entityPageToDtoPage(page, UserDTO.class);
    }

}
