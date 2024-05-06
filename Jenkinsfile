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
        sh "docker build -t ecommerce-backend:v1."
      }
    }
    stage('Deploy') {
      steps {
        sh "docker run --name ecommerce-backend -d -p 8080:8080 ecommerce-backend:v1"
      }
    }
    stage('Push to Docker Hub') {
       steps {
             withCredentials([usernamePassword(credentialsId: 'dockerhub_credentials',
            passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USERNAME')]) {
             sh "/Applications/Docker.app/Contents/Resources/bin/docker login -u $DOCKER_HUB_USERNAME -p $DOCKER_HUB_PASSWORD"
             sh "/Applications/Docker.app/Contents/Resources/bin/docker tag ecommerce-backend:latest mouniang/ecommerce-backend:v1"
             sh "/Applications/Docker.app/Contents/Resources/bin/docker push mouniang/ecommerce-backend:v1"
       }
    }
  }
}
}