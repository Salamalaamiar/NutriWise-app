pipeline {
    agent none

    stages {
        stage('Build') {
            agent { label 'test-node' }
            steps {
                echo 'Building the project...'
                bat 'mvn clean install'
            }
        }

        stage('Unit Tests') {
            agent { label 'test-node' }
            steps {
                echo 'Running unit tests...'
                bat 'mvn test'
            }
        }

        stage('Code Coverage') {
            agent { label 'coverage-node' }
            steps {
                echo 'Running code coverage...'
                bat 'mvn jacoco:report'
            }
        }

        stage('Generate Documentation') {
            agent { label 'doc-node' }
            steps {
                echo 'Generating project documentation...'
                bat 'mvn site'
            }
        }

        stage('Package') {
            agent { label 'test-node' }
            steps {
                echo 'Packaging the project...'
                bat 'mvn package'
            }
        }
    }

    post {
        success {
            echo 'Build succeeded!'
            publishHTML(target: [
                reportName: 'Project Documentation',
                reportDir: 'target/site',
                reportFiles: 'index.html'
            ])
        }

        failure {
            echo 'Build failed!'
            node('test-node') {
                emailext(
                    subject: "Jenkins Build Failure: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                    body: "Build failed. Check the build logs for details.",
                    to: 'elharidioumaima@gmail.com'
                )
            }
        }
    }
}
