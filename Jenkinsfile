pipeline {
    agent none
    stages {	
        stage('Example 1') {
            agent { 
		docker {
		    image 'mysql:latest' 
	    	    args '-p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=orderdb'
		}
	    } 
            steps {
                echo 'Hello, docker'
            }
        }
    }
}
