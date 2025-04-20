pipeline {
    agent any

    environment {
        RECIPIENT = 'elharidioumaima@gmail.com'
    }

   

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

        stage('Documentation') {
            steps {
                echo 'Generating JavaDoc documentation...'
                bat 'mvn site'
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
                // Docker (optional, uncomment if needed)
                /*
                stage('Docker Image') {
                    steps {
                        script {
                            docker.build("ecommerce-image", ".")
                            docker.withRegistry('https://your-registry', 'docker-creds') {
                                docker.image("ecommerce-image").push()
                            }
                        }
                    }
                }
                */
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
            echo 'Build succeeded! Sending HTML report.'
            publishHTML(target: [
                reportName: 'Project Documentation',
                reportDir: 'target/site',
                reportFiles: 'index.html'
            ])
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
