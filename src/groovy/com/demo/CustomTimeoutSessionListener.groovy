package com.demo
import org.springframework.context.ApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils

import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener
//http://blog.tamashumi.com/2013/02/grails-session-timeout-without-xml_21.html

//used the following to make this work with 1.3.x (getting bean from application context)
//http://www.grailsforum.co.uk/showthread.php/139-Intercepting-the-HTTP-Session-Expiry-amp-SpringSecurity

// referencing beans from a SessionListener
// http://forum.springsource.org/showthread.php?73893-How-to-inject-a-bean-property-on-a-session-listener

// requestInitialized handler
// http://stackoverflow.com/questions/3679465/find-number-of-active-sessions-created-from-a-given-client-ip/3679783#3679783

class CustomTimeoutSessionListener implements HttpSessionListener {


//    @Override
//    public void requestInitialized(ServletRequestEvent event) {
//
//        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
//        HttpSession session = request.getSession();
//        if (session.isNew()) {
//            sessions.put(session, request.getRemoteAddr());
//        }
//    }

    @Override
    void sessionCreated(HttpSessionEvent httpSessionEvent) {

        String username = httpSessionEvent.session.getAttribute('user')
        String sessionId = httpSessionEvent.session.id

        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(httpSessionEvent.session.getServletContext());
        UserRegistry userRegistry = ctx.getBean("userRegistry");

        // if userRegistry bean was found, add our sessionId to the registry
        if(userRegistry) {
            println 'registering to session registry'
            userRegistry.registerSession(sessionId, [session: httpSessionEvent.session])
        }

        println "Session created for ${username} with sessionId ${sessionId}"

    }

    @Override
    void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        String username = httpSessionEvent.session.getAttribute('user')
        String sessionId = httpSessionEvent.session.id

        // get the context so we can get the userRegistry bean
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(httpSessionEvent.session.getServletContext());
        UserRegistry userRegistry = ctx.getBean("userRegistry");

        println "Session for ${username} with sessionId ${sessionId} has been destroyed!"

        // unregister the user
        userRegistry.unregisterUser(username)
        userRegistry.unregisterSession(sessionId)

    }

}
