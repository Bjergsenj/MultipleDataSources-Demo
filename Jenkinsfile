pipeline {
  agent {
    dockerfile {
      filename 'Dockerfile'
    }

  }
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

    stage('deploy') {
      agent {
        dockerfile {
          filename 'Dockerfile'
        }

      }
      steps {
        input(message: 'y/n?', ok: 'success')
      }
    }

  }
}