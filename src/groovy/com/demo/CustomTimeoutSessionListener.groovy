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


class CustomTimeoutSessionListener implements HttpSessionListener {

    @Override
    void sessionCreated(HttpSessionEvent httpSessionEvent) {
        println "Session created!"
    }

    @Override
    void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        String username = httpSessionEvent.session.getAttribute('user')

        // get the context so we can get the userRegistry bean
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(httpSessionEvent.session.getServletContext());
        UserRegistry userRegistry = ctx.getBean("userRegistry");

        println "Session for ${username} has been destroyed!"

        // unregister the user
        userRegistry.unregisterUser(username)

    }

}
