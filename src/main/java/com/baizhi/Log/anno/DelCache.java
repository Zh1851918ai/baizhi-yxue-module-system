package com.baizhi.Log.anno;

import java.lang.annotation.*;

@Documented
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface DelCache {
}
