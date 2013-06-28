package onelogin1x

import com.demo.UserRegistry

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

        println 'userRegistry Bean: ' + userRegistry


        if(userRegistry.registerUser(params.userid)) {
            session['user'] = params.userid
        } else {
            flash.message = 'you are already logged in from another terminal!'
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

        render(view:'check', model: [users:users])

    }
}
