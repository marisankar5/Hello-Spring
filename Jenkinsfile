pipeline {
    agent any
    stages {
        stage('Preparation') {
            steps { // for display purposes
                // Get some code from a GitHub repository
                git 'https://github.com/marisankar5/Hello-spring.git'
                // Get the Maven tool.
                // ** NOTE: This 'M3' Maven tool must be configured
                // **       in the global configuration.
            }
        }
        stage('Build') {
            steps {
                script{
                   mvnHome = tool 'M3'
                // Run the maven build
                   withEnv(["MVN_HOME=$mvnHome"]) {
                    if (isUnix()) {
                        sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package'
                    } else {
                        bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore clean package/)
                    }
                }
                script
                    {
                        junit '**/target/surefire-reports/TEST-*.xml'
                        archiveArtifacts 'target/*.war'
                }
                }
            }
        }
        stage("Upload to AWS") {
                steps {
                    sh 'echo \'Hello world Again!\''
                    s3Upload consoleLogLevel: 'INFO', dontSetBuildResultOnFailure: false, dontWaitForConcurrentBuildCompletion: false, entries: [[bucket: 'hellospring', excludedFile: '', flatten: false, gzipFiles: false, keepForever: false, managedArtifacts: false, noUploadOnFailure: false, selectedRegion: 'us-east-1', showDirectlyInBrowser: false, sourceFile: '**/target/*.war', storageClass: 'STANDARD', uploadFromSlave: false, useServerSideEncryption: false]], pluginFailureResultConstraint: 'FAILURE', profileName: 'hellos3', userMetadata: []
                }
            }
        stage("Deploy") {
                steps {
                    sshagent(['Servertwo']) {
                        sh "scp -o StrictHostKeyChecking=no /var/lib/jenkins/workspace/Final_result/target/*.war ubuntu@18.234.214.172:/var/lib/tomcat9/webapps/hello.war"
                    }
                }
            }

        }
    }
