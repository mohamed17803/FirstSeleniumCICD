pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '30'))
    }

    triggers {
        // Roughly every 12 hours - same schedule as the old RegressionSuite_12H job
        cron('H */12 * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Regression Suite') {
            steps {
                bat 'mvn clean test -Dsurefire.suiteXmlFiles=testNG.xml'
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            archiveArtifacts artifacts: 'target/allure-results/**', allowEmptyArchive: true
        }
    }
}
