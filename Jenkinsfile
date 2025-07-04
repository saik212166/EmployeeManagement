pipeline {
    agent any

    tools {
        maven 'MAVEN' // Make sure this Maven tool is configured in Jenkins -> Global Tool Configuration
    }

    environment {
        IMAGE_NAME = 'employee-management-app'
        CONTAINER_NAME = 'employee-management-container'
        HOST_PORT = '9090'        // Host port
        CONTAINER_PORT = '9090'   // Container port (app listens here)
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out the source code...'
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                bat "docker build -t %IMAGE_NAME% ."
            }
        }

        stage('Stop Existing Container') {
            steps {
                echo 'Stopping and removing existing container (if any)...'
                bat "docker rm -f %CONTAINER_NAME% || exit 0"
            }
        }

        stage('Run Docker Container') {
            steps {
                echo 'Running application in Docker container on port %HOST_PORT%...'
                bat "docker run -d --name %CONTAINER_NAME% -p %HOST_PORT%:%CONTAINER_PORT% %IMAGE_NAME%"
            }
        }
    }

    post {
        success {
            echo '✅ Deployment successful!'
        }
        failure {
            echo '❌ Build or deployment failed.'
        }
    }
}
