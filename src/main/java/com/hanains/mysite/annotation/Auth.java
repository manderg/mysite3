package com.hanains.mysite.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  //매서드, 파라미터, 클래스 세군대에 갖다 붙일 수 있다.
@Retention(RetentionPolicy.RUNTIME)  //런타임 시간에 디택트 되는 것.
public @interface Auth {
	
}
