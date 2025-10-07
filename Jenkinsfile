pipeline {
    agent any

    environment {
        JAVA_HOME = "C:\\Program Files\\Java\\jdk-21"
        PATH = "${env.JAVA_HOME}\\bin;${env.PATH}"
        MAVEN_HOME = "C:\\Program Files\\Apache\\maven\\bin"
        PATH = "${MAVEN_HOME};${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Клонируем репозиторий"
                git branch: 'master', url: 'https://github.com/SergUstin/advertising-tg-bot-service.git'
            }
        }

        stage('Build') {
            steps {
                echo "Сборка проекта с помощью Maven"
                bat 'mvn clean package -DskipTests=false'
            }
        }

        stage('Test') {
            steps {
                echo "Запуск тестов"
                bat 'mvn test'
            }
        }

        stage('Run') {
            steps {
                echo "Запуск Telegram-бота"
                // Путь до main-класса Kotlin Spring Boot
                bat 'java -cp target/advertising-tg-bot-service-0.0.1-SNAPSHOT.jar advertising.tg.bot.advertisingtgbotservice.AdvertisingTgBotServiceApplicationKt'
            }
        }
    }

    post {
        success {
            echo 'Сборка прошла успешно!'
        }
        failure {
            echo 'Сборка провалилась!'
        }
    }
}
