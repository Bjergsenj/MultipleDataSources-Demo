pipeline {
  agent any
  stages {
    stage('build') {
      agent {
        docker {
          image 'maven:3-alpine'
          args '-v /root/.m2:/root/.m2'
        }

      }
      steps {
        sh 'mvn clean -gs settings.xml package -Dmaven.test.skip=true'
        sh 'echo 123'
      }
    }

  }
}