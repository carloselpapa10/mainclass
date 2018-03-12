pipeline {
    agent none
    stages {	
        stage('Example 1') {
            agent { 
		docker 'mysql:latest' 
	    } 
            steps {
                echo 'Hello, docker'
            }
        }
    }
}