pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'JDK'
    }

    environment {
        RECIPIENT = 'elharidioumaima@gmail.com'
    }

    stages {
        stage('Start') {
            steps {
                echo ' Démarrage du pipeline CI/CD'
            }
        }

        stage('ScrutationSCM') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Construction du projet...'
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
                        echo 'Tests unitaires JUnit exécutés'
                    }
                }
                
            }
        }

        stage('Analyse du code') {
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
                echo '📊 Rapport de couverture de code...'
                bat 'mvn jacoco:report'
            }
        }

        stage('Documentation') {
            steps {
                echo '📚 Génération de la documentation JavaDoc...'
                bat 'mvn site'
            }
        }

        stage('Packaging') {
            steps {
                echo '📦 Packaging du projet...'
                bat 'mvn package'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Déploiement') {
            parallel {
                stage('Nexus') {
                    steps {
                        echo '📤 Déploiement sur Nexus...'
                        bat 'mvn deploy'
                    }
                }
                // Docker (optionnel, décommenter si nécessaire)
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
                echo '✅ Pipeline terminé avec succès !'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo '🎉 Build réussi ! Envoi du rapport HTML.'
            publishHTML(target: [
                reportName: 'Documentation du projet',
                reportDir: 'target/site',
                reportFiles: 'index.html'
            ])
            emailext (
                to: "${env.RECIPIENT}",
                subject: "✅ Succès Pipeline ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """Le pipeline s'est terminé avec succès.

- 📁 Job : ${env.JOB_NAME}
- 🔢 Build : #${env.BUILD_NUMBER}
- 🔗 URL : ${env.BUILD_URL}
"""
            )
        }
        failure {
            echo '❌ Build échoué. Envoi du mail de notification.'
            emailext (
                to: "${env.RECIPIENT}",
                subject: "❌ ÉCHEC Pipeline ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """Le pipeline a échoué.

- 📁 Job : ${env.JOB_NAME}
- 🔢 Build : #${env.BUILD_NUMBER}
- 🧾 Étape : ${currentBuild.currentResult}
- 🔗 URL : ${env.BUILD_URL}

Veuillez vérifier les logs pour plus d'informations.
""",
                attachLog: true
            )
        }
    }
}
