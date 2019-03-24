/**
 * 
 */
package com.example.first.dal.data;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * @author Bean
 *
 */
public class IgnoreStrategy implements ExclusionStrategy {

	/**
	 * 
	 */
	public IgnoreStrategy() {
	}

	@Override
	public boolean shouldSkipField(FieldAttributes fieldAttributes) {
		Collection<Annotation> annotations = fieldAttributes.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation.annotationType() == Ignore.class) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}
}
