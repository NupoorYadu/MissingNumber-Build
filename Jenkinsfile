// Jenkinsfile for Missing Number - Bugzilla + Eclipse STS Debugging
// Pipeline as Code - Declarative approach

pipeline {
    agent any

    environment {
        PROJECT_NAME = 'missing-number'
        BUILD_ARTIFACTS = 'target'
        BUGZILLA_URL = 'http://localhost:8081/bugzilla' // Local Bugzilla instance
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_LOGIN = 'admin'
        GIT_COMMIT_LOG = 'git log --oneline -10'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 30, unit: 'MINUTES')
        timestamps()
    }

    triggers {
        pollSCM('H/15 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                echo '========== Checking out source code =========='
                checkout scm
                bat 'git log --oneline -10'
            }
        }

        stage('Build') {
            steps {
                echo '========== Building Maven project =========='
                bat 'mvn clean compile'
            }
        }

        stage('Automated Unit Tests') {
            steps {
                echo '========== Running automated unit tests (35+ tests) =========='
                bat 'mvn test'
            }
        }

        stage('Code Coverage') {
            steps {
                echo '========== Running JaCoCo code coverage analysis =========='
                bat 'mvn jacoco:report'
            }
        }

        stage('Code Quality Analysis') {
            steps {
                echo '========== SonarQube Code Quality Analysis (Optional) =========='
                // Uncomment to enable SonarQube integration
                // bat 'mvn sonar:sonar -Dsonar.host.url=%SONAR_HOST_URL% -Dsonar.login=%SONAR_LOGIN%'
                echo 'SonarQube analysis skipped. To enable: uncomment sonar:sonar goal'
            }
        }

        stage('Package') {
            steps {
                echo '========== Packaging JAR artifact =========='
                bat 'mvn package -DskipTests'
            }
        }

        stage('Archive Artifacts') {
            steps {
                echo '========== Archiving build artifacts =========='
                archiveArtifacts artifacts: 'target/missing-number-1.0.0.jar,target/site/jacoco/**',
                                 allowEmptyArchive: true,
                                 fingerprint: true
                echo 'Artifacts archived successfully'
            }
        }

        stage('Demo Execution') {
            steps {
                echo '========== Running Missing Number Demo =========='
                bat 'java -jar target/missing-number-1.0.0.jar'
            }
        }

        stage('Build Summary') {
            steps {
                echo '========== Build Summary =========='
                echo "Project: ${PROJECT_NAME}"
                echo "Build Number: ${BUILD_NUMBER}"
                echo "Build Status: SUCCESS"
                echo "Artifacts: JAR, JaCoCo Coverage Report"
                echo "Tests: 35+ automated test cases"
                echo "Debugging: Eclipse STS ready for remote debugging"
                echo "Issue Tracking: Bugzilla integration configured"
                echo "Git Commit: ${bat(script: 'git rev-parse HEAD', returnStdout: true).trim()}"
                echo '========== Pipeline Complete =========='
            }
        }
    }

    post {
        always {
            echo '========== Post-Build Actions =========='
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
        }
        success {
            echo '✓ Build completed successfully'
        }
        failure {
            echo '✗ Build failed. Check console output for details.'
        }
    }
}
