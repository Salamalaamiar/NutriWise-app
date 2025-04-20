pipeline {
    agent none // No default agent, we will specify agents for each stage

    stages {
        // Build Stage
        stage('Build') {
            agent { label 'test-node' } // Run on the test-node
            steps {
                echo 'Building the project...'
                bat 'mvn clean install' // Replace with 'sh' for Linux agents
            }
        }

        // Unit Test Stage
        stage('Unit Tests') {
            agent { label 'test-node' } // Run on the test-node
            steps {
                echo 'Running unit tests...'
                bat 'mvn test' // Replace with 'sh' for Linux agents
            }
        }

        // Code Coverage Stage
        stage('Code Coverage') {
            agent { label 'coverage-node' } // Run on the coverage-node
            steps {
                echo 'Running code coverage...'
                bat 'mvn jacoco:report' // Replace with 'sh' for Linux agents
            }
        }

        // Documentation Generation Stage
        stage('Generate Documentation') {
            agent { label 'doc-node' } // Run on the doc-node
            steps {
                echo 'Generating project documentation...'
                bat 'mvn site' // Replace with 'sh' for Linux agents
            }
        }

        // Packaging Stage
        stage('Package') {
            agent { label 'test-node' } // Run on the test-node
            steps {
                echo 'Packaging the project...'
                bat 'mvn package' // Replace with 'sh' for Linux agents
            }
        }
    }

    post {
        // If the build is successful, publish the HTML report
        success {
            echo 'Build succeeded!'
            publishHTML(target: [
                reportName: 'Project Documentation',
                reportDir: 'target/site', // Path to the generated site documentation
                reportFiles: 'index.html' // Main HTML file for the site
            ])
        }

        // If the build fails, send an email notification
        post {
    failure {
        echo 'Build failed!'
        node {
            emailext(
                subject: "Jenkins Build Failure: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                body: "Build failed. Check the build logs for details.",
                to: 'elharidioumaima@gmail.com'
            )
        }
    }
}

    }
}
