pipeline {
    agent any
    //make sure master node executors are set to 0
    //we only use slave node
    stages {
        stage('Build Jar') {
            agent {
                any {
                    image 'maven:3-alpine'
                    args '-v /root/.m2:/root/.m2'  //volume mapping for download anything in the machine
                }
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Image') {
            steps {
                script {
                	app = docker.build("gahung206/selenium-docker") //build the image and store in the variable `app`
                }
            }
        }
        stage('Push Image') {
            steps {
                script {
			        docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {//after login
			        	app.push("${BUILD_NUMBER}") //add this tag
			            app.push("latest") // add this tag
			        }
                }
            }
        }
    }
}