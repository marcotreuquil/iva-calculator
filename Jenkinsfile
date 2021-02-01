pipeline {
    agent any
    stages {
        stage("Checkout code") {
            steps {
                checkout scm
            }
        }
        stage('Unit Test') {
            steps{
				sh "chmod 777 gradlew"
				sh "./gradlew build"				
            }
        }
		stage('Sonnarqube') {
            steps{
				sh "chmod 777 gradlew"
				sh "./gradlew sonarqube"
            }
        }
		stage("Build image") {
            steps {
                script {
                    myapp = docker.build("mtreuquilg/iva-calculator:${env.BUILD_ID}")
                }
            }
        }
        stage("Push image") {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                            myapp.push("latest")
                            myapp.push("${env.BUILD_ID}")
                    }
                }
            }
        }
        stage('Deploy Application') {
            steps{
				sh "docker stop iva-calculator-backend"
				sh "docker rm iva-calculator-backend"
				sh "docker run -d -p 8001:8080 --name iva-calculator-backend mtreuquilg/iva-calculator:${env.BUILD_ID}"
            }
        }
    }    
}