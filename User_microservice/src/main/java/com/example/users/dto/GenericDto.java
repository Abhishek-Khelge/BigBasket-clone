package com.example.users.dto;

import com.example.users.entity.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class GenericDto<T> {
    private T data;
    private String message;

}
