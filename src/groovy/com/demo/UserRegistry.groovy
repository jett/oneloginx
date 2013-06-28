package com.demo

class UserRegistry {

    HashMap<String, Date> loggedInUsers;

    UserRegistry() {
        loggedInUsers = [:]
    }

    Boolean registerUser(String username) {

        println 'checking ' + username + '...'

        if(loggedInUsers.containsKey(username)) {
            // user already exists, return false
            return false;
        } else {
            // user does not exist, add to registry
            loggedInUsers.put(username, new Date())
            return true;

        }

    }

    def unregisterUser(String username) {
        loggedInUsers.remove(username)
    }

    Map getLoggedInUsers() {
        return loggedInUsers
    }

}
