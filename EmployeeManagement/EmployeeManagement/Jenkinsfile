pipeline {
    agent any

    tools {
        maven 'Maven 3.9.6' // Update this to match your Maven installation in Jenkins
    }

    environment {
        IMAGE_NAME = 'employee-management-app'
        CONTAINER_NAME = 'employee-management-container'
        PORT = '8080'
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
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                sh "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Stop Existing Container') {
            steps {
                echo 'Stopping and removing existing container (if any)...'
                sh "docker rm -f ${CONTAINER_NAME} || true"
            }
        }

        stage('Run Docker Container') {
            steps {
                echo 'Running application in Docker container...'
                sh "docker run -d --name ${CONTAINER_NAME} -p ${PORT}:8080 ${IMAGE_NAME}"
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