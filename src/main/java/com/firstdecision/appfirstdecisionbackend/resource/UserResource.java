package com.firstdecision.appfirstdecisionbackend.resource;

import com.firstdecision.appfirstdecisionbackend.model.dto.UserCredentialDTO;
import com.firstdecision.appfirstdecisionbackend.model.dto.UserDTO;
import com.firstdecision.appfirstdecisionbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private final UserService userService;

    @PostMapping("/login")
    protected ResponseEntity<Boolean> login(@Valid @RequestBody UserCredentialDTO dto) {
        return ResponseEntity.ok().body(userService.login(dto));
    }

    @PostMapping
    protected ResponseEntity<UserDTO> insert(@Valid @RequestBody UserDTO dto) {
        return ResponseEntity.ok().body(userService.insert(dto));
    }

    @GetMapping
    protected ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/paginated")
    protected ResponseEntity<Page<UserDTO>> findByFilter(String name, String email
            , @PageableDefault() Pageable pageable) {
        return ResponseEntity.ok().body(userService.findByFilter(name, email, pageable));
    }

}
