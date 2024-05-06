pipeline {
  agent any
  tools{
      maven 'maven'
  }
  stages {
    stage('Build') {
      steps {
        sh "mvn clean package -DskipTests"
      }
    }
    stage('Build Docker image') {
      steps {
        sh "docker build -t ecommerce-back-end:v1 ."
      }
    }
    stage('Deploy') {
      steps {
        sh "docker run --name ecommerce-back-end -d -p 8080:8080 ecommerce-back-end:v1"
      }
    }
    stage('Push to Docker Hub') {
       steps {
             withCredentials([usernamePassword(credentialsId: 'dockerhub_credentials',
            passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USERNAME')]) {
             sh "/Applications/Docker.app/Contents/Resources/bin/docker login -u
            $DOCKER_HUB_USERNAME -p $DOCKER_HUB_PASSWORD"
             sh "/Applications/Docker.app/Contents/Resources/bin/docker tag ecommerce-back-end:latest
            mouniang/ecommerce-back-end:v1"
             sh "/Applications/Docker.app/Contents/Resources/bin/docker push
            mouniang/ecommerce-back-end:v1"
       }

  }
}
