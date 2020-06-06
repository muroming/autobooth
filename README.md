Основные точки приема запросов лежат в папке Controllers. Там лежат
контроллеры для киосков, мероприятий и пользователей.
Все, что связано с JWT лежит в папке Security, там же лежит ещё один
контроллер JwtAuthenticationController, который отвечает за авторизацию
пользователей и киосков.
Отдельно стоит отметить папки Mail и Printing:
В Mail лежит фабрика, которая отдает бин MailSender, он отвечает за отправку
писем пользователем.
В папке Printing лежит PrintingService он отвечает за печать бейджей через
киоски. Подключение происходит по сокету, отправляется id киоска, размер
документата и сам документ, после этого происходит печать.
Оставшиеся 2 папки User и Meetup имеют одинаковую структуру, в data
находятся интерфейсы к базе данных в папке datasources, и сущности
относящиеся к дата слою. В папке domain находятся сущности доменного
слоя. Данные для подключения к базе данных находятся в
файле application.properties. Зависимости для проекта находятся в
build.gradle.kts, основные зависимости это spring boot, jsonwebtoken, postgresql,
cups4j и qrgen