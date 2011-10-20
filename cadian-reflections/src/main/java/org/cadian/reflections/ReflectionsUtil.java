package org.cadian.reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.regex.Pattern;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.google.common.base.Predicate;

/**
 * Reflections utility class.
 * 
 * @author Joshua Hornsby
 */
public class ReflectionsUtil {

	public static void init() {
		 Instance.INSTANCE.getInstance()._init();
	}
	
	public static void dispose() {
		Instance.INSTANCE.getInstance()._dispose();
	}
	
	public static <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type) {
		return Instance.INSTANCE.getInstance()._getSubTypesOf(type);
	}

	public static Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation) {
		return Instance.INSTANCE.getInstance()._getTypesAnnotatedWith(annotation);
	}

	public static Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation, boolean honorInherited) {
		return Instance.INSTANCE.getInstance()._getTypesAnnotatedWith(annotation, honorInherited);
	}

	public static Set<Class<?>> getTypesAnnotatedWith(Annotation annotation) {
		return Instance.INSTANCE.getInstance()._getTypesAnnotatedWith(annotation);
	}

	public static Set<Class<?>> getTypesAnnotatedWith(Annotation annotation, boolean honorInherited) {
		return Instance.INSTANCE.getInstance()._getTypesAnnotatedWith(annotation, honorInherited);
	}

	public static Set<Method> getMethodsAnnotatedWith(Class<? extends Annotation> annotation) {
		return Instance.INSTANCE.getInstance()._getMethodsAnnotatedWith(annotation);
	}

	public static Set<Method> getMethodsAnnotatedWith(Annotation annotation) {
		return Instance.INSTANCE.getInstance()._getMethodsAnnotatedWith(annotation);
	}

	public static Set<Field> getFieldsAnnotatedWith(Class<? extends Annotation> annotation) {
		return Instance.INSTANCE.getInstance()._getFieldsAnnotatedWith(annotation);
	}

	public static Set<Field> getFieldsAnnotatedWith(Annotation annotation) {
		return Instance.INSTANCE.getInstance()._getFieldsAnnotatedWith(annotation);
	}

	public static Set<Method> getConverters(Class<?> from, Class<?> to) {
		return Instance.INSTANCE.getInstance()._getConverters(from, to);
	}

	public static Set<String> getResources(Predicate<String> namePredicate) {
		return Instance.INSTANCE.getInstance()._getResources(namePredicate);
	}

	public static Set<String> getResources(Pattern pattern) {
		return Instance.INSTANCE.getInstance()._getResources(pattern);
	}
	
	private enum Instance {
		INSTANCE();
		private ReflectionsUtil instance;
		private Instance() {
			instance = new ReflectionsUtil();
		}
		private ReflectionsUtil getInstance() {
			return instance;
		}
	}
	
	private static final Object[] $LOCK = new Object[0];
	private Reflections reflections;
	
	private void _init() {
		synchronized ($LOCK) {
			if(reflections == null) {
				reflections = _createReflections(); 
			}
		}
	}
	
	private void _dispose() {
		reflections = null;
	}
	
	private <T> Set<Class<? extends T>> _getSubTypesOf(Class<T> type) {
		if(reflections == null) {
			_init();
		}
		return reflections.getSubTypesOf(type);
	}

	private Set<Class<?>> _getTypesAnnotatedWith(Class<? extends Annotation> annotation) {
		if(reflections == null) {
			_init();
		}
		return reflections.getTypesAnnotatedWith(annotation);
	}

	private Set<Class<?>> _getTypesAnnotatedWith(Class<? extends Annotation> annotation, boolean honorInherited) {
		if(reflections == null) {
			_init();
		}
		return reflections.getTypesAnnotatedWith(annotation, honorInherited);
	}

	private Set<Class<?>> _getTypesAnnotatedWith(Annotation annotation) {
		if(reflections == null) {
			_init();
		}
		return reflections.getTypesAnnotatedWith(annotation);
	}

	private Set<Class<?>> _getTypesAnnotatedWith(Annotation annotation, boolean honorInherited) {
		if(reflections == null) {
			_init();
		}
		return reflections.getTypesAnnotatedWith(annotation, honorInherited);
	}

	private Set<Method> _getMethodsAnnotatedWith(Class<? extends Annotation> annotation) {
		if(reflections == null) {
			_init();
		}
		return reflections.getMethodsAnnotatedWith(annotation);
	}

	private Set<Method> _getMethodsAnnotatedWith(Annotation annotation) {
		if(reflections == null) {
			_init();
		}
		return reflections.getMethodsAnnotatedWith(annotation);
	}

	private Set<Field> _getFieldsAnnotatedWith(Class<? extends Annotation> annotation) {
		if(reflections == null) {
			_init();
		}
		return reflections.getFieldsAnnotatedWith(annotation);
	}

	private Set<Field> _getFieldsAnnotatedWith(Annotation annotation) {
		if(reflections == null) {
			_init();
		}
		return reflections.getFieldsAnnotatedWith(annotation);
	}

	private Set<Method> _getConverters(Class<?> from, Class<?> to) {
		if(reflections == null) {
			_init();
		}
		return reflections.getConverters(from, to);
	}

	private Set<String> _getResources(Predicate<String> namePredicate) {
		if(reflections == null) {
			_init();
		}
		return reflections.getResources(namePredicate);
	}

	private Set<String> _getResources(Pattern pattern) {
		if(reflections == null) {
			_init();
		}
		return reflections.getResources(pattern);
	}
	
	private Reflections _createReflections() {
		return new Reflections(new ConfigurationBuilder()
			.filterInputsBy(new FilterBuilder())
			.setUrls(ClasspathHelper.forPackage(""))
			.setScanners(new SubTypesScanner(),
						 new TypeAnnotationsScanner(),
						 new ResourcesScanner()));
	}
}