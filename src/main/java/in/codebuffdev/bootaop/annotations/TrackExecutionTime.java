package in.codebuffdev.bootaop.annotations;

import java.lang.annotation.*;

/**
 * As of now it's a marker annotation*/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TrackExecutionTime {

    // annotation elements (configured annotations)

    long warnAfter() default 2000;
    String operation()  default "";

}
