package com.example.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "FindServlet", value = "/findServlet")
public class FindServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(FindServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String word = request.getParameter("word").toLowerCase(); // Переводит ввееденное юзером слово в нижний рег
        int count = 0;
        LOGGER.info("Поиск слова: " + word);

        ServletContext context = getServletContext();

        Pattern pattern = Pattern.compile("[\\p{L}]+");  // Паттерн по которому строки разделяются на слова

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResourceAsStream("/WEB-INF/text.txt"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // разделяет строку на слова и проходится по каждому слову
                Matcher matcher = pattern.matcher(line.toLowerCase());
                while (matcher.find()) {
                    String w = matcher.group();
                    LOGGER.info("Читаемое слово: " + w);
                    if (w.equals(word)) { // сравнивает слово с введенным
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.getWriter().write("Ошибка чтения файла.");
            return;
        }

        response.setContentType("text/html");
        response.getWriter().write("<html><body>");
        response.getWriter().write("<h1>Результат поиска</h1>");
        response.getWriter().write("<p>Слово '" + word + "' встречается " + count + " раз в файле.</p>");
        response.getWriter().write("</body></html>");
    }
}
