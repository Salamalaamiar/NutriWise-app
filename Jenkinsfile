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
                sh './compile.sh'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                // Example test command:
                sh './run-tests.sh'
            }
        }
    }
}
