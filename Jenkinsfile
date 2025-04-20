pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
         jdk 'jdk-17.0.12'
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn install'
            }
        }
    }

    post {
        success {
            junit 'target/surefire-reports/**/*.xml'
        }
    }
}
