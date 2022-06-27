pipeline {
    agent any
    
//      agent {
//         label 'main'
//     }


    stages {
         stage('build') {
            steps {
                setBuildStatus message: 'Building', state: 'running'
                script {
                    try {
                        sh './gradlew clean build'
                        setBuildStatus message: 'Building', state: 'success'
                    } catch (exec) {
                        // this is so we can capture the results in 'finally' below
                        setBuildStatus message: 'Building', state: 'failed'
                        throw exec
                    }
                }
            }
        }

        stage('unit-test') {
            when {
                expression {
                    return env.GIT_BRANCH != 'origin/develop'
                }
            }
            steps {
                script {
                    try {
                        sh './gradlew test'
                        setBuildStatus message: 'unit test', state: 'success'
                    } catch (exec) {
                        setBuildStatus message: 'unit test', state: 'failed'
                        throw exec
                    }
                }
            }
        }
        stage('integration-test') {
                when {
                    expression {
                        return env.GIT_BRANCH != 'origin/develop'
                    }
                }
                steps {
                    script {
                        try {
                            sh './gradlew integrationTest'
                            setBuildStatus message: 'integration test', state: 'success'
                        } catch (exec) {
                            // this is so we can capture the results in 'finally' below
                            setBuildStatus message: 'integration test', state: 'failed'
                            throw exec
                        }
                    }
                }
            }

        
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
        
//          post {
//             always {
//                 // Cleans the workspace - so Jenkins will run fast and efficiently
//                 cleanWs()
//             }
//             success {
//                 setBuildStatus message:'Build', state: 'success'
//             }
//             failure {
//                 setBuildStatus message:'Build', state: 'failed'
//             }
//         }

    }
}
