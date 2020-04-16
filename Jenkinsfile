pipeline {
    agent any
    stages {
        stage('Build') { 
            agent {
              docker {
               image 'maven:3.6.3-jdk-11-slim'
              }
            }
            steps {
                sh '(mvn clean package)'
                stash name: "app", includes: "**"
            }
        }
	    stage('QualityTest') { 
            agent {
              docker {
               image 'maven:3.6.3-jdk-11-slim'
              }
            }
            steps {
                unstash "app"
                sh '(mvn clean test)'
                sh '(echo TODO: Call tests here)'
            }
        }
    }
    post {
        always {
            echo 'always clean up'
            deleteDir()
        }
    }
}