<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Поиск слова</title>
</head>
<body>
<h1>Поиск слова в файле</h1>
<form action="findServlet" method="post">
    <label for="word">Введите слово:</label>
    <input type="text" id="word" name="word">
    <button type="submit">Поиск</button>
</form>
</body>
</html>