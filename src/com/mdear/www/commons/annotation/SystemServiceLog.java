
package com.mdear.www.commons.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author djx
 * @date 2015-8-12
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemServiceLog {
	 String description()  default "";    
}
