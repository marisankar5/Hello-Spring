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
                    sh 'pwd'
                    sh 'whoami'
                    s3Upload consoleLogLevel: 'INFO', dontSetBuildResultOnFailure: false, dontWaitForConcurrentBuildCompletion: false, entries: [[bucket: 'springjar-01', excludedFile: '', flatten: false, gzipFiles: false, keepForever: false, managedArtifacts: false, noUploadOnFailure: false, selectedRegion: 'ap-south-1', showDirectlyInBrowser: false, sourceFile: '**/target/*.war', storageClass: 'STANDARD', uploadFromSlave: false, useServerSideEncryption: false]], pluginFailureResultConstraint: 'FAILURE', profileName: 'hellos3', userMetadata: []
                }
            }
//         stage("Deploy") {
//                 steps {
//                     sshagent(['Ansible']){
//                 sh"ansible-playbook -i /etc/ansible/hosts /etc/ansible/script.yaml -u root"
//                 }
//                 }
//             }
                stage("Deploy") {
                   steps {
                sshagent(['deployuser']) {
                  sh "scp -o StrictHostKeyChecking=no /var/lib/jenkins/workspace/springBuilld@2/target/hellospring.war ubuntu@15.206.163.95 :/var/lib/tomcat9/webapps"
                }
            }
            }

        }
    }
