package in.codebuffdev.bootaop.aspect;

import in.codebuffdev.bootaop.annotations.TrackExecutionTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SimpleAspect {

    @Before("execution(public String in.codebuffdev.bootaop.service.SimpleService.doSimpleTask())")
    public void logBefore() {
        System.out.println("Simple method introspected");
    }

    @Around("@annotation(trackExecutionTime)")
    public Object majorExecutionTimeAround(ProceedingJoinPoint proceedingJoinPoint, TrackExecutionTime trackExecutionTime) throws Throwable {
        long start = System.currentTimeMillis();

        // why? try block ? method we are proceeding to might through exception
        try {
            return proceedingJoinPoint.proceed();
        } finally {
            long end = System.currentTimeMillis();
            long duration = (end - start);

            String operationName = trackExecutionTime.operation();
            if (operationName.isBlank()) operationName = proceedingJoinPoint.getSignature().getName();

            if (duration >= trackExecutionTime.warnAfter()) System.out.println("Slow operation alert");

            System.out.println("Execution time taken by : " + operationName + " " + duration + "ms ");
        }
    }
}
