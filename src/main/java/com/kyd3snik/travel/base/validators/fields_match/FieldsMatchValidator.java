package com.kyd3snik.travel.base.validators.fields_match;

import com.kyd3snik.travel.model.request.SignUpRequest;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FieldsMatchValidator implements ConstraintValidator<FieldsMatch, Object> {
    private List<Field> fields = new ArrayList<>();

    @Override
    public void initialize(FieldsMatch constraintAnnotation) {
        List<String> fieldNames = List.of(constraintAnnotation.fields());

        if (fieldNames.size() < 2)
            throw new IllegalStateException("Fields list is less that 2!");

        fieldNames.forEach(this::addFieldSafe);
    }

    @SneakyThrows
    @Override
    public boolean isValid(Object request, ConstraintValidatorContext context) {
        List<Object> values = getFieldValues(request);
        boolean result = areAllValuesSame(values);
        return result;

    }

    @SneakyThrows
    private void addFieldSafe(String fieldName) {
        fields.add(SignUpRequest.class.getDeclaredField(fieldName));
    }

    private List<Object> getFieldValues(Object obj) {
        return fields.stream()
                .map(field -> getFieldValueSafe(field, obj))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private Object getFieldValueSafe(Field field, Object obj) {
        field.setAccessible(true);
        return field.get(obj);
    }

    private boolean areAllValuesSame(List<Object> values) {
        if (values.isEmpty())
            return true;

        Object fistValue = values.get(0);
        return values.stream()
                .skip(1)
                .allMatch(v -> v.equals(fistValue));
    }
}
