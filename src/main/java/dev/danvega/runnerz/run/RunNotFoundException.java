package dev.danvega.runnerz.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/* @ResponseStatus is written to give a http request. These are written only to controller methods
and not to any construstors.
The @ResponseStatus annotation in Java is typically used to declare the HTTP status code returned by a Spring MVC controller handler method.
This annotation is not applicable to constructors.
This means that whenever an instance of RunNotFoundException is thrown within a Spring MVC controller method,
Spring will automatically handle it and send an HTTP response with a status code of 404 (NOT_FOUND)
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RunNotFoundException extends RuntimeException{
    public RunNotFoundException(){
        super("Run Not Found");                 //super-Constructor  //super.methodname to call methos
    }
}
