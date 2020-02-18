
pipeline {
  environment {
    registry = "rogerabad/video-rental"
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
  agent any
  stages {
    stage('Cloning Git') {
      steps {
        git 'https://github.com/batro83/videoRentalStore.git'
      }
    }
    stage('Building jar') {
      steps{
      	sh "chmod +x ./gradlew"
        sh "gradle clean build"
      }
    }
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build registry
        }
      }
    }
    stage('Push Image') {
      steps{
        script {
          docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
          }
        }
      }
    }
    stage('Remove Unused docker image') {
      steps{
        //sh "docker rmi $registry:$BUILD_NUMBER"
        sh "docker rmi $registry"
      }
    }
    stage('Deploy Image') {
      steps{
        script {
          docker.withRegistry( '', registryCredential ) {
          	dockerImage.stop()
            dockerImage.pull()
            sh "docker run -p 8081:8081 -d --net='host' -it $registry"
          }
        }
      }
    }
  }
}