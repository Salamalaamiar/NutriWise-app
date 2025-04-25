pipeline {
    agent any

    environment {
        RECIPIENT = 'elharidioumaima@gmail.com'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean install'
            }
            post {
                success {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Test') {
            parallel {
                stage('JUnit') {
                    steps {
                        bat 'mvn test'
                        echo 'JUnit unit tests executed'
                    }
                }
            }
        }

        stage('Code Analysis') {
            parallel {
                stage('PMD') {
                    steps {
                        bat 'mvn pmd:pmd'
                    }
                }
                stage('Checkstyle') {
                    steps {
                        bat 'mvn checkstyle:checkstyle'
                    }
                }
            }
        }

        stage('Code Coverage') {
            steps {
                echo 'Generating code coverage report...'
                bat 'mvn jacoco:report'
            }
        }

        stage('Packaging') {
            steps {
                echo 'Packaging the project...'
                bat 'mvn package'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Deployment') {
            parallel {
                stage('Nexus') {
                    steps {
                        echo 'Deploying to Nexus...'
                        bat 'mvn deploy'
                    }
                }
               
            }
        }

        stage('End') {
            steps {
                echo 'Pipeline completed successfully!'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo 'Build succeeded!'
            emailext (
                to: "${env.RECIPIENT}",
                subject: "SUCCESS Pipeline ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """The pipeline completed successfully.

- Job : ${env.JOB_NAME}
- Build : #${env.BUILD_NUMBER}
- URL : ${env.BUILD_URL}
"""
            )
        }
        failure {
            echo 'Build failed. Sending failure notification email.'
            emailext (
                to: "${env.RECIPIENT}",
                subject: "FAILURE Pipeline ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """The pipeline has failed.

- Job : ${env.JOB_NAME}
- Build : #${env.BUILD_NUMBER}
- Status : ${currentBuild.currentResult}
- URL : ${env.BUILD_URL}

Please check the logs for more details.
""",
                attachLog: true
            )
        }
    }
}
