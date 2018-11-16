#!/usr/bin/env groovy

pipeline {
    agent any

    environment {
        MAVEN_HOME = tool('Maven_3.5')
        JDK_HOME = tool('jdk8')
        PATH = "${MAVEN_HOME}/bin:${JDK_HOME}/jre/bin:${JDK_HOME}/bin:${PATH}"
    }

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('Build') {
            steps {
                // Erst einmal nur 'compile' ausführen, damit Kompilierungsfehler möglichst
                // früh im Build aufschlagen
                sh "mvn clean compile"
            }
        }

        stage('Unit Tests and Code Analysis') {
            steps {
                parallel(runTests: {
                    sh "mvn -DskipTests=false test"
                },
                        codeAnalysis: {
                            //sh 'mvn spotbugs:spotbugs'
                            sh 'mvn findbugs:findbugs'
                            // echo 'Code analysis is disabled'
                        })
            }
        }


        stage('Package') {
            steps {
                // Deployables werden erst nach erfolgreichem Build und Test gebaut:
                sh "mvn package"
            }
        }
    }

    post {
        always {
            warnings consoleParsers: [[parserName: 'Java Compiler (javac)']], defaultEncoding: 'UTF-8'
            openTasks defaultEncoding: 'UTF-8', high: 'FIXME', low: 'NOTE', normal: 'TODO,XXX', pattern: '**/*.java', excludePattern: '**/*Test.java'
            junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
            findbugs defaultEncoding: 'UTF-8', pattern: '**/findbugsXml.xml'

            // archive '**/target/*.war'
        }
        success {
            fingerprint '**/target/*.war'
            archive '**/target/*.war'
        }
    }

    options {
        // For example, we'd like to make sure we only keep 10 builds at a time, so
        // we don't fill up our storage!
        buildDiscarder(logRotator(numToKeepStr:'10'))

        // And we'd really like to be sure that this build doesn't hang forever, so
        // let's time it out after an hour.
        timeout(time:60, unit: 'MINUTES')
    }
}
