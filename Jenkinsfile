pipeline {
    agent any

    environment {
        REGISTRY = 'image-registry.openshift-image-registry.svc:5000' // Use single quotes to avoid variable interpolation
        APP_NAME = 'api-gateway'
        PROJECT_NAME = 'rupesh-kallepelli-dev'
        IMAGE_TAG = 'latest'
        OPENSHIFT_SERVER = 'https://api.sandbox-m4.g2pi.p1.openshiftapps.com:6443'
        TOKEN = 'sha256~3kcuBbstko8Ugt0Kw1ertI6pS0MrSi3bP4fuZ8PwXlA'
    }

    stages {
//         stage('Cleanup') {
//                 steps {
//                     script {
//                         // Delete everything in the workspace
//                         sh "rm -rf * .m2"
//                         sh "rm -rf *"
//                     }
//                 }
//         }
        stage('Build') {
            steps {
                dir('api-gateway') {
                    script {
                        sh "chmod +x mvnw"
                        sh "./mvnw clean install -DskipTests"
                    }
                }
            }
        }
        stage('Trigger OpenShift ImageStream') {
                steps {
                    dir('api-gateway') {
                        script{
                            try {
                                sh(label: "Login into oc", script: "oc login --token=${TOKEN} --server=${OPENSHIFT_SERVER}")
                                sh(label: "Create imagestream", script: "oc create -f ./helm/imagestream.yaml")
                            } catch (err) {
                                sh(label: "Replace imagestream oc", script: "oc replace -f ./helm/imagestream.yaml")
                            }
                        }
                    }
                }
        }

        stage('Trigger OpenShift Build') {
            steps {
                dir('api-gateway') {
                    script{
                        try {
                            sh(label: "Login into oc", script: "oc login --token=${TOKEN} --server=${OPENSHIFT_SERVER}")
                            sh(label: "Create buildconfig", script: "oc create -f ./helm/buildconfig.yaml")
                        } catch (err) {
                            sh(label: "Replace buildconfig oc", script: "oc replace -f ./helm/buildconfig.yaml")
                        } finally {
                            sh(label: "Start src build", script: "oc start-build ${APP_NAME} --follow")
                        }
                    }
                }
            }
        }
//         stage('Expose Service') {
//                     steps {
//                         dir('api-gateway') {
//                             script{
//                                 try {
//                                     sh(label: "Login into oc", script: "oc login --token=${TOKEN} --server=${OPENSHIFT_SERVER}")
//                                     sh(label: "Create buildconfig", script: "oc create -f ./helm/templates/service.yaml")
//                                 } catch (err) {
//                                     sh(label: "Replace buildconfig oc", script: "oc replace -f ./helm/templates/service.yaml")
//                                 } finally {
//                                     sh(label: "Start src build", script: "oc expose svc/${APP_NAME}")
//                                 }
//                             }
//                         }
//                     }
//                 }

        stage('Deploy to OpenShift') {
            steps {
                dir('api-gateway') {
                    sh "oc delete deployment ${APP_NAME} --ignore-not-found"
                    sh """
                    oc apply -f ./helm/templates/deployment.yaml
                    oc new-app ${REGISTRY}/${PROJECT_NAME}/${APP_NAME}:${IMAGE_TAG} --name=${APP_NAME} || oc rollout restart deployment ${APP_NAME}
                    """
                }
            }
        }
    }

   post {
        always {
            script {
                sh 'rm -rf ~/.m2'
                sh "rm -rf *"
            }
        }
    }
}
