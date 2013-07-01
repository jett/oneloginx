import com.demo.CustomTimeoutSessionListener
import com.demo.UserRegistry

// Place your Spring DSL code here
beans = {

    userRegistry(UserRegistry) { bean->
        //bean.autowire = 'byName'
    }

    // sample from: http://code.google.com/p/springside/source/browse/springside3/trunk/examples/showcase/src/main/resources/schedule/applicationContext-executor.xml?r=1090

    sessionSweeper(com.demo.SessionSweeper) { bean ->
        bean.autowire = 'byName'
        userRegistry = ref('userRegistry')
    }

    scheduledExecutorTask(org.springframework.scheduling.concurrent.ScheduledExecutorTask) {
        period = 15000
        runnable = ref('sessionSweeper')
    }

    scheduledExecutor(org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean) {
        continueScheduledExecutionAfterException = true
        waitForTasksToCompleteOnShutdown = true
        poolSize = 1
        scheduledExecutorTasks = [ref('scheduledExecutorTask')]

    }

}
