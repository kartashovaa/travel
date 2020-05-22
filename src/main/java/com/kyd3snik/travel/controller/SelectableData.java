package com.kyd3snik.travel.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SelectableData<T> {
    private T data;
    private boolean isSelected;
}
