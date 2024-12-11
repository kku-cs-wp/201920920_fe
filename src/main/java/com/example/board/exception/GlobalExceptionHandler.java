package com.example.board.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ��� ���� ó��
    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/404"; // 404 �������� ����
    }
}
