pipeline {
    agent none
    stages {
	
        stage('Example 1') {
            agent { docker 'demo-mysql' } 
            steps {
                echo 'Hello, Maven'
            }
        }
    }
}