package com.tools.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface API {
	public String id() default "";
	public String name() default "";
	public ParamsInfo[] request() default {};
	public ParamsInfo[] response() default {};
	public String info() default "";
	public Class<?>[] entity() default {};
}
