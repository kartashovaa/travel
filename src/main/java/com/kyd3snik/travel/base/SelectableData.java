package com.kyd3snik.travel.base;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SelectableData<T> {
    private T data;
    private boolean isSelected;
}
