/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.exception;

import java.util.Iterator;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;

/**
 *
 * @author matik06
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

    private static final Logger log = Logger.getLogger(CustomExceptionHandler.class.getCanonicalName());
    private ExceptionHandler wrapped;

    CustomExceptionHandler(ExceptionHandler exception) {
        this.wrapped = exception;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {

        final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
        while (i.hasNext()) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context =
                    (ExceptionQueuedEventContext) event.getSource();

            // get the exception from context
            Throwable t = context.getException();

            final FacesContext fc = FacesContext.getCurrentInstance();
            final Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
            final NavigationHandler nav = fc.getApplication().getNavigationHandler();

            //here you do what ever you want with exception
            try {

                if(ExceptionUtils.getRootCause(t) instanceof AccessDeniedException ) {
                    throw (AccessDeniedException)ExceptionUtils.getRootCause(t);
                }
                                
                log.error("złapano wyjątek: nieprzechwycony przez controller");                
                log.error(t.getMessage(), t);      
                t.printStackTrace();

                //redirect error page
                requestMap.put("exceptionMessage", t.getMessage());
                nav.handleNavigation(fc, null, "/error");
                fc.renderResponse();

                // remove the comment below if you want to report the error in a jsf error message
//                JsfUtil.addErrorMessage(t.getMessage());

            } finally {
                //remove it from queue
                i.remove();
            }
        }
        //parent hanle
        getWrapped().handle();
    }
}
