package com.reinertisa.springdatajpamappings.one_to_one_uni.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reinertisa.springdatajpamappings.one_to_one_uni.dto.UserDto;
import com.reinertisa.springdatajpamappings.one_to_one_uni.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllUsers_shouldReturnPagedList() throws Exception {
        UserDto user1 = UserDto.builder()
                .id(1L)
                .firstName("Isa")
                .lastName("Reinert")
                .email("test1@gmail.com")
                .address(null)
                .build();

        UserDto user2 = UserDto.builder()
                .id(2L)
                .firstName("Sade")
                .lastName("Miller")
                .email("test2@gmail.com")
                .address(null)
                .build();

        when(userService.getAllUser(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(user1, user2), PageRequest.of(0, 20), 2));
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].firstName").value("Isa"));

        Assertions.assertThat(userService.getAllUser(PageRequest.of(0, 20)).getTotalElements()).isEqualTo(2);
    }
}