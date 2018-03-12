pipeline {
    agent none
    stages {	
        stage('Example 1') {
            agent { 
		image 'maven:3-alpine'
		label 'my-defined-label'
		args  '-v /tmp:/tmp'
	    } 
            steps {
                echo 'Hello, docker'
            }
        }
    }
}
