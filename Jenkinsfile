pipeline {
    agent none
    stages {	
        stage('Example 1') {
            agent { 
		docker {
		    image 'mysql:latest' 
	    	    args '-p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=orderdb -e MYSQL_USER=userdb -e MYSQL_PASSWORD=passdb'
		}
	    } 
            steps {
                echo 'Hello, docker'
		sh 'docker ps'
            }
        }
    }
}
