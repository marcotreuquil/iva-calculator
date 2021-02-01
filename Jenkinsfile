pipeline {
    agent any
    stages {
        stage("Checkout code") {
            steps {
                checkout scm
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
                sh "docker run -d -p 5000:5000 --name iva-calculator-backend mtreuquilg/iva-calculator:${env.BUILD_ID}"
            }
        }
    }    
}