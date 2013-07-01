package com.demo

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
        println "scheduled task executed "
        println "logged in users: " + userRegistry.getLoggedInUsers()
        println "sessions: " + userRegistry.getSessions()
    }
}
