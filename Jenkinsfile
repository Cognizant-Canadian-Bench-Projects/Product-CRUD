pipeline {
    agent any

    stages {
         stage('build') {
            steps {
               steps {
                       sh './gradlew clean build'
                     }
            }
        }

        stage('unit-test') {
            steps {
                    sh './gradlew test'
            }
        }

        stage('integration-test') {
            when { not { changeRequest() } }
            steps {
              sh './gradlew integrationTest'
            }
        }

        stage('Deploy') {
            when { branch 'main' }
            steps {
                echo 'Deploying....'
            }
        }
    }

    post {
        success {
             mergePullRequest()
        }
        failure {
            commentPullRequest("[Failing Build](${env.BUILD_URL})")
        }
    }
}
