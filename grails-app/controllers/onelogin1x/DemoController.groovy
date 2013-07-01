package onelogin1x

class DemoController {

    def userRegistry

    def index = {
        redirect(action: 'login')
    }

    def login = {
        render(view: 'login')
    }

    def authenticate = {

        if(!params.userid) {
            flash.message = "please enter a userid"
            forward(action: 'login')
        }

        if(params.userid) {
            //if(userRegistry.registerUser(params.userid)) {
            if(userRegistry.registerUserSession(params.userid, session.id)) {
                session['userName'] = params.userid
            } else {
                flash.message = 'you are already logged in from another terminal!'
            }
        }

        forward(action: 'login')
    }

    def logout = {

        // invalidate this session
        session.invalidate()

        flash.message = 'you have been logged out!'

        //render(view: "login")
        forward(action: 'index')
    }

    def check = {

        def users = userRegistry.getLoggedInUsers()
        def sessions = userRegistry.getSessions()


        render(view:'check', model: [users:users, sessions: sessions])

    }

    def kill = {

        //println "params are = " + params

        String sessionId = params.id
        if(sessionId) {
            userRegistry.terminateSession(params.id)
        }

        forward(action:'check')
    }


    def keepAlive = {

        // keep this session alive in the registry
        //println "keep alive called for session ${session.id}"
        //println params

        userRegistry.keepSessionAlive(session.id)
        render "."
    }
}
