//this is the scripted method with groovy engine
/*
Ce Jenkinsfile permet de compiler et de deployer la partie web et/ou la partie batch du projet.
sur les environnements de DEV, TEST et PROD
Ce script se veut le plus generique possible et comporte 2 zones a editer specifiquement au projet
 */
import hudson.model.Result

node {

    // **** DEBUT DE ZONE A EDITER n°1 ****

    // Configuration du projet
    def gitURL = "https://github.com/abes-esr/apicom-referentiels.git"
    def gitCredentials = 'Github'
	//Pas utilisé
    def slackChannel = "#notif-ApiCom"
    def artifactoryBuildName = "wsReferentiels"

    // Definition du module web
    def backApplicationFileName = "wsReferentiels"
    def backTargetDir = "/usr/local/tomcat9-apicom/webapps/"
    def backServiceName = "tomcat9-apicom.service"


    // **** FIN DE ZONE A EDITER n°1 ****

    // Variables de configuration d'execution
    def candidateModules = []
    def executeBuild = []
    def executeTests = false
    def deployArtifactoy = false
    def buildNumber = -1
    def executeDeploy = []
    def backTargetHostnames = []

    // Variables globales
    def ENV
    def maventool
    def rtMaven
    def mavenProfil
    def artifactoryServer
    def downloadSpec

    // Definition des actions
    def choiceParams = ['Compiler', 'Compiler & Déployer', 'Déployer un précédent build']
																												
    // Configuration du job Jenkins
    // On garde les 5 derniers builds par branche
    // On scanne les branches et les tags du Git
    properties([
            buildDiscarder(
                    logRotator(
                            artifactDaysToKeepStr: '',
                            artifactNumToKeepStr: '',
                            daysToKeepStr: '',
                            numToKeepStr: '5')
            ),
            parameters([
                    choice(choices: choiceParams.join('\n'), description: 'Que voulez-vous faire ?', name: 'ACTION'),
                    gitParameter(
                            branch: '',
                            branchFilter: 'origin/(.*)',
                            defaultValue: 'develop',
                            description: 'Sélectionner la branche ou le tag',
                            name: 'BRANCH_TAG',
                            quickFilterEnabled: false,
                            selectedValue: 'NONE',
                            sortMode: 'DESCENDING_SMART',
                            tagFilter: '*',
                            type: 'PT_BRANCH_TAG'),
                    stringParam(defaultValue: '', description: "Numéro du build à déployer. Retrouvez vos précédents builds sur https://artifactory.abes.fr/artifactory/webapp/#/builds/${artifactoryBuildName}", name: 'BUILD_NUMBER'),
                    booleanParam(defaultValue: false, description: 'Voulez-vous deployer sur Artifactory ?', name: 'deployArtifactoy'),
                    booleanParam(defaultValue: false, description: 'Voulez-vous exécuter les tests ?', name: 'executeTests'),
                    choice(choices: ['DEV', 'TEST', 'PROD'], description: 'Sélectionner l\'environnement cible', name: 'ENV')
            ])
    ])

    //-------------------------------
    // Etape 1 : Configuration
    //-------------------------------
    stage('Set environnement variables') {
        try {
            // Java
            env.JAVA_HOME = "${tool 'Open JDK 11'}"
            env.PATH = "${env.JAVA_HOME}/bin:${env.PATH}"

            // Maven & Artifactory
            maventool = tool 'Maven 3.3.9'
            rtMaven = Artifactory.newMavenBuild()
            artifactoryServer = Artifactory.server '-1137809952@1458918089773'
            rtMaven.tool = 'Maven 3.3.9'
            rtMaven.opts = "-Xms1024m -Xmx4096m"

            // Action a faire
            if (params.ACTION == null) {
                throw new Exception("Variable ACTION is null")
            }

            // Branche a deployer
            if (params.BRANCH_TAG == null) {
                throw new Exception("Variable BRANCH_TAG is null")
            } else {
                echo "Branch to deploy =  ${params.BRANCH_TAG}"
            }

            // Booleen d'execution des tests
            if (params.executeTests == null) {
                executeTests = false
            } else {
                executeTests = params.executeTests
            }
            echo "executeTests =  ${executeTests}"

            // Booleen de deploiement sur Artifactory
            if (params.deployArtifactoy == null) {
                deployArtifactoy = false
            } else {
                deployArtifactoy = params.deployArtifactoy
            }
            echo "deployArtifactoy =  ${deployArtifactoy}"

            // Environnement de deploiement
            if (params.ENV == null) {
                throw new Exception("Variable ENV is null")
            } else {
                ENV = params.ENV
                echo "Target environnement =  ${ENV}"
            }

            if (ENV == 'DEV') {
                mavenProfil = "dev"
                backTargetHostnames.add('hostname.server-back-1-dev')
                backTargetHostnames.add('hostname.server-back-2-dev')
            } else if (ENV == 'TEST') {
                mavenProfil = "test"
                backTargetHostnames.add('hostname.server-back-1-test')
                backTargetHostnames.add('hostname.server-back-2-test')
            } else if (ENV == 'PROD') {
                mavenProfil = "prod"
                backTargetHostnames.add('hostname.server-back-1-prod')
                backTargetHostnames.add('hostname.server-back-2-prod')
            }

        } catch (e) {
            currentBuild.result = hudson.model.Result.NOT_BUILT.toString()
            //notifySlack(slackChannel, "Failed to set environnement variables: " + e.getLocalizedMessage())
            throw e
        }
    }

    if (buildNumber == -1) {

        //-------------------------------
        // Etape 2 : Recuperation du code
        //-------------------------------
        stage('SCM checkout') {
            try {
                checkout([
                        $class                           : 'GitSCM',
                        branches                         : [[name: "${params.BRANCH_TAG}"]],
                        doGenerateSubmoduleConfigurations: false,
                        extensions                       : [],
                        submoduleCfg                     : [],
                        userRemoteConfigs                : [[credentialsId: "${gitCredentials}", url: "${gitURL}"]]
                ])

            } catch (e) {
                currentBuild.result = hudson.model.Result.FAILURE.toString()
                //notifySlack(slackChannel, "Failed to fetch SCM: " + e.getLocalizedMessage())
                throw e
            }
        }
    }

	
	//-------------------------------
	// Etape 3.1 : Edition des fichiers de proprietes
	//-------------------------------
	stage("Edit properties files") {
		try {
			echo "Edition application-${ENV}.properties"
			echo "--------------------------"

			original = readFile "src/main/resources/application-${ENV}.properties"
			newconfig = original

			// **** DEBUT DE ZONE A EDITER n°2 ****

			/*
			  Cette zone permet d'editer les fichiers de proprietes pour les environnements cibles.
			  C'est ici que l'on insere les donnees sensibles dans les fichiers de proprietes (application.properties)
			  Les donnees sensibles sont stockees dans Jenkins comme des Credentials de type Secret Text.
			  A vous d'ajouter dans Jenkins vos credentials de donnees sensensibles et de les remplacer ici
			 */

			// Module web par défaut
            withCredentials([
                    string(credentialsId: "basexml.datasource.url-${mavenProfil}", variable: 'urlbaseXml'),
                    string(credentialsId: "basexml.datasource.username-${mavenProfil}", variable: 'usernameBaseXml'),
                    string(credentialsId: "basexml.datasource.password-${mavenProfil}", variable: 'passwordBaseXml')
            ]) {
                newconfig = newconfig.replaceAll("spring.datasource.url=*", "spring.datasource.url=${urlbaseXml}")
                newconfig = newconfig.replaceAll("spring.datasource.username=*", "spring.datasource.username=${usernameBaseXml}")
                newconfig = newconfig.replaceAll("spring.datasource.password=*", "spring.datasource.password=${passwordBaseXml}")
            }

			// **** FIN DE ZONE A EDITER n°2 ****

			writeFile file: "src/main/resources/application-${ENV}.properties", text: "${newconfig}"

		} catch (e) {
			currentBuild.result = hudson.model.Result.FAILURE.toString()
			notifySlack(slackChannel, "Failed to edit properties files: "+e.getLocalizedMessage())
			throw e
		}
	}
	
	//-------------------------------
    // Etape 3.2 : Compilation
    //-------------------------------
    stage('Compile package') {
        try {
            //Pas de module utilisé en fait, donc pas besoin de la commande : -pl ${candidateModules[moduleIndex]}
            sh "'${maventool}/bin/mvn' -Dmaven.test.skip='${!executeTests}' clean package -Dmaven.test.skip=${!executeTests}"
            // ATTENTION #1, rtMaven.run ne tient pas compte des arguments de compilation -D
            //buildInfo = rtMaven.run pom: 'pom.xml', goals: "clean package -Dmaven.test.skip=${!executeTests} -pl ${candidateModules[moduleIndex]} -am -P${mavenProfil} -DfinalName=${backApplicationFileName} -DwebBaseDir=${backTargetDir}${backApplicationFileName}".toString()

        } catch (e) {
            currentBuild.result = hudson.model.Result.FAILURE.toString()
            //notifySlack(slackChannel, "Failed to build module ${candidateModules[moduleIndex]}: "+e.getLocalizedMessage())
            throw e
        }
    }
	

    if (deployArtifactoy) {
        stage('Archive to Artifactory') {
            //-------------------------------
            // Etape 3.3 : Deploiement sur Artifactory
            //-------------------------------
            try {
                rtMaven.deployer server: artifactoryServer, releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local'
                buildInfo = rtMaven.run pom: 'pom.xml', goals: "clean package -Dmaven.test.skip=${!executeTests}".toString()
                buildInfo.name = "${artifactoryBuildName}"
                rtMaven.deployer.deployArtifacts buildInfo
                artifactoryServer.publishBuildInfo buildInfo

            } catch (e) {
                currentBuild.result = hudson.model.Result.FAILURE.toString()
                //notifySlack(slackChannel, "Failed to deploy and publish module ${candidateModules[moduleIndex]} to Artifactory: " + e.getLocalizedMessage())
                throw e
            }
        }
    }

    //-------------------------------
    // Etape 4 : Deploiement
    //-------------------------------

    if(buildNumber != -1) {		

        //-------------------------------
        // Etape 4.0 : On recupere depuis Artifactory
        //-------------------------------
        try {
            // On clean l'espace de travail
            sh("${maventool}/bin/mvn clean")
            sh("mkdir -p target")
						 
            downloadSpec = """{
             "files": [
              {
                  "aql": {
                        "items.find": {
                        "archive.item.artifact.module.build.name": {"\$eq":"${artifactoryBuildName}"},
                        "archive.item.artifact.module.build.number":{"\$eq":"${buildNumber}"},
                        "name":{"\$match":"*.war"}																										
                        }
                    },
                  "target": "target/",
                  "flat": true  
                }
             ]
            }"""

            artifactoryServer.download spec: downloadSpec
            // Suite au bug #1, on renomme le war																				
            sh("mv target/*.war target/${backApplicationFileName}.war")

        } catch (e) {
            currentBuild.result = hudson.model.Result.FAILURE.toString()
            //notifySlack(slackChannel, "Failed to retrieve module ${candidateModules[moduleIndex]} from Artifactory: " + e.getLocalizedMessage())
            throw e
        }
    }

    //-------------------------------
    // Etape 4.1 : Serveur Web
    //-------------------------------
    stage("Deploy to web servers") {

        for (int i = 0; i < backTargetHostnames.size(); i++) { //Pour chaque serveur
            withCredentials([
                    usernamePassword(credentialsId: 'tomcatuser', passwordVariable: 'pass', usernameVariable: 'username'),
                    string(credentialsId: "${backTargetHostnames[i]}", variable: 'hostname'),
                    string(credentialsId: 'service.status', variable: 'status'),
                    string(credentialsId: 'service.stop', variable: 'stop'),
                    string(credentialsId: 'service.start', variable: 'start')
            ]) {
															  
                echo "Stop service on ${backTargetHostnames[i]}"
                echo "--------------------------"
							 
                try {

                    try {
                        echo 'get service status'
                        sh "ssh -tt ${username}@${hostname} \"${status} ${backServiceName}\""

                        echo 'stop the service'
                        sh "ssh -tt ${username}@${hostname} \"${stop} ${backServiceName}\""
							 
                    } catch (e) {
                        // Maybe the tomcat is not running
                        echo 'maybe the service is not running'
					 
                        echo 'we try to start the service'
                        sh "ssh -tt ${username}@${hostname} \"${start} ${backServiceName}\""

                        echo 'get service status'
                        sh "ssh -tt ${username}@${hostname} \"${status} ${backServiceName}\""

                        echo 'stop the service'
                        sh "ssh -tt ${username}@${hostname} \"${stop} ${backServiceName}\""
                    }

                } catch (e) {
                    currentBuild.result = hudson.model.Result.FAILURE.toString()
                    //notifySlack(slackChannel, "Failed to stop the web service on ${backTargetHostnames[i]} :" + e.getLocalizedMessage())
                    throw e
                }
			 

                echo "Deploy to ${backTargetHostnames[i]}"
                echo "--------------------------"

                try {
                    sh "ssh -tt ${username}@${hostname} \"rm -rf ${backTargetDir}${backApplicationFileName} ${backTargetDir}${backApplicationFileName}.war\""
                    //ACT : scp *.war car le nom du war contient toujours SNAPSHOT...
                    sh "scp target/*.war ${username}@${hostname}:${backTargetDir}${backApplicationFileName}.war"

                } catch (e) {
                    currentBuild.result = hudson.model.Result.FAILURE.toString()
                    //notifySlack(slackChannel, "Failed to deploy the webapp to ${backTargetHostnames[i]} :" + e.getLocalizedMessage())
                    throw e
                }

                echo "Restart service on ${backTargetHostnames[i]}"
                echo "--------------------------"

                try {
                    echo 'start service'
                    sh "ssh -tt ${username}@${hostname} \"${start} ${backServiceName}\""
			 
                    echo 'get service status'
                    sh "ssh -tt ${username}@${hostname} \"${status} ${backServiceName}\""

                } catch (e) {
																	  		 
                    currentBuild.result = hudson.model.Result.FAILURE.toString()
																	
                    //notifySlack(slackChannel, "Failed to restrat the web service on ${backTargetHostnames[i]} :" + e.getLocalizedMessage())
                    throw e				  
                }
            }

        }//Pour chaque serveur
    }

    currentBuild.result = hudson.model.Result.SUCCESS.toString()
    //notifySlack(slackChannel,"Congratulation !")
}

def notifySlack(String slackChannel, String info = '') {
    def colorCode = '#848484' // Gray

    switch (currentBuild.result) {
        case 'NOT_BUILT':
            colorCode = '#FFA500' // Orange
            break
        case 'SUCCESS':
            colorCode = '#00FF00' // Green
            break
        case 'UNSTABLE':
            colorCode = '#FFFF00' // Yellow
            break
        case 'FAILURE':
            colorCode = '#FF0000' // Red
            break;
    }

    String message = """
        *Jenkins Build*
        Job name: `${env.JOB_NAME}`
        Build number: `#${env.BUILD_NUMBER}`
        Build status: `${currentBuild.result}`
        Branch or tag: `${params.BRANCH_TAG}`
        Target environment: `${params.ENV}`
        Message: `${info}`
        Build details: <${env.BUILD_URL}/console|See in web console>
    """.stripIndent()

    return slackSend(tokenCredentialId: "slack_token",
            channel: "${slackChannel}",
            color: colorCode,
            message: message)
}
