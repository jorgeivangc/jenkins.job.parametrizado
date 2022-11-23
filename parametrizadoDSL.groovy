job('ejemplo2-job-DSL') {
   description('Job DSL de jejemplo para el curso de Jenkins')
   scm {
     git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node ->
       node / gitConfigName('macloujulian')
       node / gitConfigEmail('macloujulian@gmail.com')
     }
  } 
  parameters {
      stringParam('nombre', defaultValue = 'Jorge', description = 'Parametro de cadena para el Job')
      choiceParam('planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Saturno', 'Urano'])
      booleanParam('agente', false)
  }  
  triggers {
    cron('H/7 * * * *')
  }
  steps {
    shell("bash jobscript.sh")
  }
  publishers {
    mailer('macloujulian@gmail.com', true, true)
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
