pipeline {
    agent any
    environment {
        SONAR_LOGIN_TOKEN = credentials('SONAR_LOGIN_TOKEN')
    }
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
                sh '(mvn checkstyle:check)'
                sh '(mvn clean test)'
                sh '(mvn sonar:sonar -Dsonar.projectKey=Blatoy_to-dont -Dsonar.organization=blatoy -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=$SONAR_LOGIN_TOKEN)'
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