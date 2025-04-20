pipeline {
    agent any

    

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
