package com.google.werkzeugkasten.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface Disposable {

	void dispose();

	@Retention(RetentionPolicy.CLASS)
	@Target(ElementType.METHOD)
	public @interface Dispose {
	}
}
