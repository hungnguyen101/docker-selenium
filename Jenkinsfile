pipeline {
    // master executor should be set to 0
    agent any
    stages {
        stage('Build Jar') {
            steps {
                //sh
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                //sh
                sh "docker build -t='gahung206/selenium-docker' ."
            }
        }
        stage('Push Image') {
            steps {
                //manually create a credentialsId :: dockerhub in Jenkins
			    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    //sh
			        sh "docker login --username=${user} --password=${pass}"
			        sh "docker push gahung206/selenium-docker:latest"
			    }                           
            }
        }
    }
}