package com.firstdecision.appfirstdecisionbackend.resource;

import com.firstdecision.appfirstdecisionbackend.dto.UserDTO;
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

    @PostMapping
    protected ResponseEntity<UserDTO> insert(@Valid @RequestBody UserDTO dto) {
        return ResponseEntity.ok().body(userService.insert(dto));
    }

    @GetMapping
    protected ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/paginated")
    protected ResponseEntity<Page<UserDTO>> findByFilter(@RequestParam(required = false) String login, @PageableDefault() Pageable pageable) {
        return ResponseEntity.ok().body(userService.findByFilter(login, pageable));
    }

}
