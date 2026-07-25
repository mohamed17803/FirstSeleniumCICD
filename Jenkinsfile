pipeline {
    agent any

    environment {
        TEMP_PROFILES = "${env.WORKSPACE}\\temp-profiles"
        DRIVER_LOGS = "${env.WORKSPACE}\\driver-logs"
        TESTNG_THREAD_COUNT = "${params.TESTNG_THREAD_COUNT ?: '3'}"
        WEBDRIVER_USER_DATA_DIR = "${TEMP_PROFILES}\\profile-${env.BUILD_ID}"
    }

    options {
        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '30'))
    }

    parameters {
        string(name: 'TESTNG_THREAD_COUNT', defaultValue: '3', description: '')
    }

    triggers {
        cron('H */12 * * *')
    }

    stages {
        stage('Prepare Workspace') {
            steps {
                script {
                    bat """
                        if not exist "${env.TEMP_PROFILES}" mkdir "${env.TEMP_PROFILES}"
                        if not exist "${env.DRIVER_LOGS}" mkdir "${env.DRIVER_LOGS}"
                    """
                }
            }
        }

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Prepare Drivers (optional)') {
            steps {
                script {
                    echo "Prepare drivers if needed"
                }
            }
        }

        stage('Regression Suite') {
            steps {
                script {
                    def mvnCmd = "mvn clean test -Dsurefire.suiteXmlFiles=testNG.xml -Dtestng.threadCount=${params.TESTNG_THREAD_COUNT} -Dwebdriver.userDataDir=\"${env.WEBDRIVER_USER_DATA_DIR}\""
                    bat mvnCmd
                }
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            archiveArtifacts artifacts: 'target/allure-results/**', allowEmptyArchive: true
            script {
                bat """
                    echo Collecting driver logs...
                    if exist "%WORKSPACE%\\msedgedriver.log" copy /Y "%WORKSPACE%\\msedgedriver.log" "${env.DRIVER_LOGS}\\msedgedriver-${env.BUILD_ID}.log" || echo no msedgedriver.log found
                    if exist "%WORKSPACE%\\chromedriver.log" copy /Y "%WORKSPACE%\\chromedriver.log" "${env.DRIVER_LOGS}\\chromedriver-${env.BUILD_ID}.log" || echo no chromedriver.log found
                    if exist "%WORKSPACE%\\geckodriver.log" copy /Y "%WORKSPACE%\\geckodriver.log" "${env.DRIVER_LOGS}\\geckodriver-${env.BUILD_ID}.log" || echo no geckodriver.log found
                """
                archiveArtifacts artifacts: 'driver-logs/**', allowEmptyArchive: true
            }
        }

        failure {
            script {
                echo "Build failed — ensure driver logs were archived above."
            }
        }

        cleanup {
            script {
                bat """
                    if exist "${env.WEBDRIVER_USER_DATA_DIR}" rmdir /S /Q "${env.WEBDRIVER_USER_DATA_DIR}" || echo no profile to remove
                """
            }
        }
    }
}
