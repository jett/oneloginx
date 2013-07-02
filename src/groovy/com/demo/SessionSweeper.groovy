package com.demo
import groovy.time.TimeCategory
import groovy.time.TimeDuration

import javax.servlet.http.HttpSession

/**
 * Created with IntelliJ IDEA.
 * User: Jett
 * Date: 6/30/13
 * Time: 8:33 AM
 * To change this template use File | Settings | File Templates.
 */
class SessionSweeper implements Runnable {

    def userRegistry

    @Override
    void run() {
        //To change body of implemented methods use File | Settings | File Templates.

//        println "scheduled task executed "
//        println "logged in users: " + userRegistry.getLoggedInUsers()
//        println "sessions: " + userRegistry.getSessions()


        Collections.synchronizedMap(userRegistry.getSessions()).each() { userSession ->

            Map properties = userSession.value
            Date lastPing = (Date) properties.get("lastPing")

            HttpSession session = (HttpSession) properties.session

            println "sweeper."

            // get lastPing and compute duration
            if(lastPing) {
                Date now = new Date();
                TimeDuration td = TimeCategory.minus( now, lastPing)

                // check against timeout and force expire
                if(td.seconds > 15) {

                    String sessionId = session.id
                    String username  = session.getAttribute("userName")

                    println "invalidating from the sweeper $username - $sessionId"

                    userRegistry.terminateSession(sessionId)

                    userRegistry.unregisterUser(username)
                    userRegistry.unregisterSession(sessionId)

                }

                println "$now - $lastPing = $td.seconds"

            }
        }
    }
}
