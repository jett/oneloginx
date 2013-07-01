package com.demo

import javax.servlet.http.HttpSession

class UserRegistry {

    HashMap<String, Object> loggedInUsers;
    HashMap<String,Object> sessionIdRegistry;

    UserRegistry() {
        loggedInUsers = [:]
        sessionIdRegistry = [:]
    }

    void registerSession(String sessionId, Map sessionDetails) {
        sessionIdRegistry.put(sessionId, sessionDetails)
    }

    Boolean registerUser(String username) {

        println 'checking ' + username + '...'

        if(loggedInUsers.containsKey(username)) {
            // user already exists, return false
            return false;
        } else {
            // user does not exist, add to registry
            loggedInUsers.put(username, [registrationDate: new Date()])
            return true;
        }
    }

    Boolean registerUserSession(String username, String sessionId) {

        println 'checking ' + username + '...'

        if(loggedInUsers.containsKey(username)) {
            // user already exists, return false
            return false;
        } else {
            // user does not exist, add to registry
            loggedInUsers.put(username, [registrationDate: new Date(), sessionId: sessionId])

            Map existingDetails = sessionIdRegistry.get(sessionId)
            existingDetails.put("userid", username)

            sessionIdRegistry.put(sessionId, existingDetails)

            return true;
        }
    }

    void keepSessionAlive(String sessionId) {


        Map existingDetails = sessionIdRegistry.get(sessionId)

        // check if there is a userid, only then will we add a session alive attribute
        if(existingDetails?.userid) {
            existingDetails.put("lastPing", new Date())
        }

        sessionIdRegistry.put(sessionId, existingDetails)

    }

    void terminateSession(String sessionId) {

        Map sessionDetails = sessionIdRegistry.get(sessionId)

        if(sessionDetails) {

            // get the session object for the specified sessionId
            HttpSession session = (HttpSession) sessionDetails.get("session")

            println "invalidating sessionid ${session.id}"

            session.invalidate()

        } else {

            println "session does not exist!"

        }

    }

    def unregisterUser(String username) {
        loggedInUsers.remove(username)
    }

    def unregisterSession(String sessionId) {
        sessionIdRegistry.remove(sessionId)
    }

    Map getLoggedInUsers() {
        return loggedInUsers
    }

    Map getSessions() {
        return sessionIdRegistry
    }

}
