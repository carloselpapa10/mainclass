pipeline {
    agent { 
	docker {
            image 'maven:3-alpine'
        }
    }
    stages {
	/*
	stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('Test') {
            steps {
		sh 'mvn test'
            }
	    //post {
                //always {
                    //junit 'target/surefire-reports/*.xml' 
                //}
            //}
        }
	stage('Docker Build'){
	    steps {
		sh 'mvn clean package docker:build'
            }	   
	}*/
	stage('Delivery'){
	    steps {
		sh './4.Testing/deliver.sh'
            }	   
	}
    }
}
