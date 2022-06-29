pipeline {
    agent any



    stages {
         stage('build') {
             when{
                    expression{
                       environment name: 'CHANGE_ID', value: 'PR'
                    }
             }
            steps {
                //setBuildStatus message: 'Building', state: 'running'
                script {
                    try {
                        sh './gradlew clean build'
                        //setBuildStatus message: 'Building', state: 'success'
                    } catch (exec) {
                        // this is so we can capture the results in 'finally' below
                        //setBuildStatus message: 'Building', state: 'failed'
                        throw exec
                    }
                }
            }
        }

        stage('unit-test') {
             when{
                expression{
                      environment name: 'CHANGE_ID', value: 'PR'
                }
             }
            steps {
                script {
                    try {
                        sh './gradlew test'
                        //setBuildStatus message: 'unit test', state: 'success'
                    } catch (exec) {
                        //setBuildStatus message: 'unit test', state: 'failed'
                        throw exec
                    }
                }
            }
        }
        stage('integration-test') {
             when{
                expression{
                    environment name: 'CHANGE_ID', value: 'PR'
                }
             }
                steps {
                    script {
                        try {
                            sh './gradlew integrationTest'
                           // setBuildStatus message: 'integration test', state: 'success'
                        } catch (exec) {
                            // this is so we can capture the results in 'finally' below
                           // setBuildStatus message: 'integration test', state: 'failed'
                            throw exec
                        }
                    }
                }
            }

        
        stage('Deploy') {
             when{
                expression{
                    environment name: 'CHANGE_ID', value: 'PR'

                }
             }
            steps {
                echo 'Deploying....'
            }
        }
    }
    post {
         when{
            expression{
                environment name: 'CHANGE_ID', value: 'PR'
            }
         }
        always {
            // Cleans the workspace - so Jenkins will run fast and efficiently
            cleanWs()
        }
        success {
             mergePullRequest()
        }
        failure {
            commentPullRequest("[Failing Build](${env.BUILD_URL})")
        }
    }
}
