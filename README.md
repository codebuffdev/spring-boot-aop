# boot-aop

## Advice Annotations

1. @Before ("execution (in.codebuffdev.bootaop.dto.Student in.codebuffdev.bootaop.service.StudentService.createStudent
   (in.codebuffdev.bootaop.dto.Student))")
2. @AfterReturning (pointcut = "execution (in.codebuffdev.bootaop.dto.Student
   in.codebuffdev.bootaop.service.StudentService.createStudent (in.codebuffdev.bootaop.dto.Student))",
   returning = "result" // tells Spring to put the target method's return value into the advice method's parameter named
   X.
   )
3. @AfterThrowing (value = "execution (in.codebuffdev.bootaop.dto.Student
   in.codebuffdev.bootaop.service.StudentService.createStudent (in.codebuffdev.bootaop.dto.Student))" , throwing =
   "exception")
   public void logAfterThrowing (Throwable exception){
   System.out.println ("Target class throwing exception cause = " + exception.getMessage ());
   }
4. @After ("execution (* in.codebuffdev.bootaop.service.StudentService.createStudent (..))")
5. @Around ("execution (* in.codebuffdev.bootaop.service.StudentService.createStudent (..))")

## Pointcuts

tells us where to apply the advice (annotations + aspect code).

we use wildcard chars to effectively apply the advice.

1. `*` → any

* `..` → 0 to any no (also for intercepting any level of package)

For this we take the help of designator

1. execution → works at methods level
2. within → works at class level
3. annotation → work at methods level (is a method has a specific annotation)
4. bean → work at class (for the specific bean is qualified for advised)
5. @within → works at class level (pass me a class level anno & it'll interspect all the methods of the class (located
   at which class))
6. @target → works at class level (pass me a class level anno & it'll interspect all the methods of the class (worked
   with inheritance as well))
7. target → class level → Target class type  it'll interspect
8. args → method level (any method takes a particular arg as method param)
9. @args → method level (any method takes a particular arg as method param & that param should have a class level anno)
10. this → class level → proxy class type  it'll interspect

* 1st 4 are majorly used.

We can combine designator using any logical (&& , ||, !) bean.  