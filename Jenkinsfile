pipeline {
    agent any
    //make sure master node executors are set to 0
    //we only use slave node
    stages {
        stage('Build Jar') {
            agent {
                docker {
                    image 'maven:3-alpine' //install alpine + maven
                    args '-v /$HOME/.m2:/root/.m2'  //volume mapping for download anything in the machine
                }
            }
            steps {
                sh 'mvn clean package -DskipTests' //build jar files using maven
                sh 'ls -al' //debug
            }
        }
        stage('Build Image') {
            steps {
                script {
                    echo "debug checking current location inside container"
                    echo $PWD
                    sh 'ls -al' //debug
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