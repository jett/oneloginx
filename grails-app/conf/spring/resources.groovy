import com.demo.CustomTimeoutSessionListener
import com.demo.UserRegistry

// Place your Spring DSL code here
beans = {

    userRegistry(UserRegistry) { bean->
        //bean.autowire = 'byName'
    }

//    customTimeoutSessionListener(CustomTimeoutSessionListener) { bean ->
//        bean.autowire = 'byName'
//        userRegistry = ref('userRegistry')
//    }

}
