pipeline {
    agent none

    stages {
        stage('Build') {
            agent { label 'test-node' }
            steps {
                echo 'Building the project...'
                bat 'mvn clean compile'
            }
        }

        stage('Unit Tests') {
            agent { label 'test-node' }
            steps {
                echo 'Running unit tests...'
                bat 'mvn test'
            }
        }
    }

    post {
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
