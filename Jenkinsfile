pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'tokengithub', url: 'https://github.com/Salamalaamiar/NutriWise-app.git', branch: 'main'
            }
        }

        stage('Compile') {
            steps {
                echo 'Compiling the code...'
                // Example compile command:
                bat 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                // Example test command:
                bat 'mvn test'
            }
        }
    }
}
