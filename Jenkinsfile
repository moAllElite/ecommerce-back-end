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
       sh 'docker build -t ecommerce-back-end:v1 .'
      }
    }
    stage('Push to Docker Hub') {
       steps {
            withCredentials([usernamePassword(credentialsId: 'dockerhub_credentials',
                 variable: 'dockerhub_credentials',
              passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USERNAME'
             )]) {
              sh 'docker login -u $DOCKER_HUB_USERNAME -p ${DOCKER_HUB_PASSWORD}'
             sh 'docker tag ecommerce-back-end:v1 mouniang/ecommerce-back-end:v1'
             sh 'docker push   mouniang/ecommerce-back-end:v1'    }
    }
  }
}
}