pipeline {
    agent none
    stages {	
        stage('Example 1') {
            agent { 
		docker 'mysql:latest' 
	    	args '-p 3306:3306'
	    } 
            steps {
                echo 'Hello, docker'
            }
        }
    }
}