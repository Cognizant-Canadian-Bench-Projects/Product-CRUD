pipeline {
    agent any



    stages {
         stage('build') {
             when{
                expression{
                    (BRANCH_NAME == 'develop' || BRANCH_NAME == 'main')
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
                      (BRANCH_NAME == 'develop' || BRANCH_NAME == 'main')
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
                    (BRANCH_NAME == 'develop' || BRANCH_NAME == 'main')
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
                    (BRANCH_NAME == 'develop' || BRANCH_NAME == 'main')

                }
             }
            steps {
                echo 'Deploying....'
            }
        }
    }
    post {
//                 always {
//                     // Cleans the workspace - so Jenkins will run fast and efficiently
//                     cleanWs()
//                 }
                success {
                echo env.CHANGE_ID
                     mergePullRequest()
                }
                failure {
                echo env.CHANGE_ID
                    commentPullRequest("[Failing Build](${env.BUILD_URL})")
                }
            }
}
