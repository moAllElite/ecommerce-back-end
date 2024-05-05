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
  }
}
