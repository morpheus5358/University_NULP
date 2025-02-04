package com.example.lab4.hotel.managment.util;

import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class UpdaterField {

	public <T> void updateField(T newValue, Consumer<T> setter) {
		if (newValue != null) {
			setter.accept(newValue);
		}
	}
}