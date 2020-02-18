
pipeline {
  environment {
    registry = "rogerabad/video-rental"
    registryCredential = 'batroska83'
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
        sh "gradle build"
      }
    }
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
    stage('Deploy Image') {
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
        sh "docker rmi $registry:$BUILD_NUMBER"
      }
    }
  }
}