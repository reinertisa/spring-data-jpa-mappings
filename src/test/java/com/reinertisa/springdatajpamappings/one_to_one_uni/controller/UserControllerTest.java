package com.reinertisa.springdatajpamappings.one_to_one_uni.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reinertisa.springdatajpamappings.one_to_one_uni.dto.UserDto;
import com.reinertisa.springdatajpamappings.one_to_one_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_one_uni.request.UserRequest;
import com.reinertisa.springdatajpamappings.one_to_one_uni.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;


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

        assertThat(userService.getAllUser(PageRequest.of(0, 20)).getTotalElements()).isEqualTo(2);
    }

    @Test
    void getUserById_shouldReturnUser() throws Exception {
        UserDto user = new UserDto(1L, "Isa", "Reinert", "test@gmail.com", null);
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Isa"));

        assertThat(userService.getUserById(1L).getEmail()).isEqualTo("test@gmail.com");
    }

    @Test
    void getUserById_whenNotFound_shouldReturn404() throws Exception {
         when(userService.getUserById(99L))
                 .thenThrow(new ResourceNotFoundException("User not found"));

         mockMvc.perform(get("/api/v1/users/99"))
                 .andExpect(status().isNotFound());
    }

    @Test
    void createUser_shouldReturnCreatedUser() throws Exception {
        UserRequest request = UserRequest.builder()
                .firstName("Isa")
                .lastName("Reinert")
                .email("test@gmail.com")
                .build();

        UserDto createdUser = UserDto.builder()
                .id(1L)
                .firstName("Isa")
                .lastName("Reinert")
                .email("test@gmail.com")
                .build();

        when(userService.createUser(any(UserRequest.class))).thenReturn(createdUser);

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        assertThat(userService.createUser(request).getFirstName()).isEqualTo("Isa");
    }

    @Test
    void updateUser_shouldReturnUpdatedUser() throws Exception {
        UserRequest request = UserRequest.builder()
                .firstName("Isa")
                .lastName("Reinert")
                .build();
        UserDto updatedUser = UserDto.builder()
                .id(1L)
                .firstName("Isa")
                .lastName("Reinert")
                .build();

        given(userService.updateUser(eq(1L), any(UserRequest.class))).willReturn(updatedUser);

        mockMvc.perform(put("/api/v1/users/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Isa"));
    }

    @Test
    void deleteUser_shouldReturnNoContent() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void filterByName_shouldReturnFilteredUsers() throws Exception {
        List<UserDto> users = List.of(new UserDto(1L, "Isa", "Reinert", "test@gmail.com", null));
        given(userService.filterByName("Isa")).willReturn(users);

        mockMvc.perform(get("/api/v1/users/filter")
                .param("keyword", "Isa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Isa"));
    }
}