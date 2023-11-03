package de.webui;

public class AdminHTML {
    public static String noAuthHTML = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Document</title>\n" +
            "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
            "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
            "    <link href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\" rel=\"stylesheet\">\n" +
            "    <link rel=\"stylesheet\" href=\"https://pro.fontawesome.com/releases/v5.15.4/css/all.css\">\n" +
            "    <link rel=\"stylesheet\" href=\"index.css\">\n" +
            "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js\"></script>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "    <div class=\"sidebar\">\n" +
            "        <div class=\"items\">\n" +
            "            <a class=\"item\" href=\"index.html\"><i class=\"fas fa-chart-pie\"></i>\n" +
            "                <div class=\"tooltip\">Leaderboard</div>\n" +
            "            </a>\n" +
            "            <a class=\"item\" href=\"players.html\"><i class=\"fas fa-users\"></i>\n" +
            "                <div class=\"tooltip\">Online Players</div>\n" +
            "            </a>\n" +
            "            <a class=\"item active\" href=\"admin.html\"><i class=\"fas fa-user-shield\"></i>\n" +
            "                <div class=\"tooltip\">Admin</div>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    <div class=\"main\">\n" +
            "        <div class=\"loginbox\">\n" +
            "            <i class=\"far fa-lock\"></i>\n" +
            "            <h2>Password Protected</h2>\n" +
            "            <p>Please enter the password to continue</p>\n" +
            "            <form action=\"\" method=\"post\">\n" +
            "                <input type=\"password\" name=\"password\" id=\"\" placeholder=\"Enter Password\">\n" +
            "                <input type=\"submit\" value=\"Login\">\n" +
            "            </form>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "\n" +
            "</html>";

    public static String authHTML = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Document</title>\n" +
            "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
            "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
            "    <link href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\" rel=\"stylesheet\">\n" +
            "    <link rel=\"stylesheet\" href=\"https://pro.fontawesome.com/releases/v5.15.4/css/all.css\">\n" +
            "    <link rel=\"stylesheet\" href=\"index.css\">\n" +
            "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js\"></script>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "    <div class=\"sidebar\">\n" +
            "        <div class=\"items\">\n" +
            "            <a class=\"item\" href=\"index.html\"><i class=\"fas fa-chart-pie\"></i>\n" +
            "                <div class=\"tooltip\">Leaderboard</div>\n" +
            "            </a>\n" +
            "            <a class=\"item\" href=\"players.html\"><i class=\"fas fa-users\"></i>\n" +
            "                <div class=\"tooltip\">Online Players</div>\n" +
            "            </a>\n" +
            "            <a class=\"item active\" href=\"admin.html\"><i class=\"fas fa-user-shield\"></i>\n" +
            "                <div class=\"tooltip\">Admin</div>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    <div class=\"main\">\n" +
            "        <div class=\"chat\">\n" +
            "            <input type=\"text\" placeholder=\"Enter Command to execute on the server\" id=\"command_input\">\n" +
            "            <input type=\"button\" value=\"Senden\" id=\"submit-btn\">\n" +
            "        </div>\n" +
            "        <div class=\"console\">\n" +
            "            <pre id=\"console\"></pre>\n" +
            "            <input type=\"button\" value=\"Clear\" id=\"clearconsole-btn\">\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    <script>\n" +
            "        function submit() {\n" +
            "            var val = entferneErstesSlash($(\"#command_input\").val());\n" +
            "            var xmlHttp = new XMLHttpRequest();\n" +
            "            xmlHttp.onreadystatechange = function() {\n" +
            "                if (xmlHttp.readyState == 4 && xmlHttp.status == 200)\n" +
            "                    callback(xmlHttp.responseText);\n" +
            "            }\n" +
            "            xmlHttp.open(\"GET\", \"/admin?command=\" + encodeURI(val), true);\n" +
            "            xmlHttp.send(null);\n" +
            "        }\n" +
            "        $(\"#submit-btn\").click(function() {\n" +
            "            submit();\n" +
            "        });\n" +
            "\n" +
            "        $(\"#clearconsole-btn\").click(function() {\n" +
            "            var xmlHttp = new XMLHttpRequest();\n" +
            "            xmlHttp.onreadystatechange = function() {\n" +
            "                if (xmlHttp.readyState == 4 && xmlHttp.status == 200)\n" +
            "                    console.log(xmlHttp.responseText);\n" +
            "            }\n" +
            "            xmlHttp.open(\"GET\", \"/clearconsole\", true);\n" +
            "            xmlHttp.send(null);\n" +
            "        });\n" +
            "\n" +
            "        $(\"#command_input\").keypress(function(e) {\n" +
            "            if (e.which == 13) {\n" +
            "                submit();\n" +
            "            }\n" +
            "        });\n" +
            "\n" +
            "        function entferneErstesSlash(text) {\n" +
            "            if (text.charAt(0) === '/') {\n" +
            "                text = text.slice(1);\n" +
            "            }\n" +
            "            return text;\n" +
            "        }\n" +
            "\n" +
            "        const interval = setInterval(function() {\n" +
            "            var xmlHttp = new XMLHttpRequest();\n" +
            "            xmlHttp.onreadystatechange = function() {\n" +
            "                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {\n" +
            "                    document.getElementById(\"console\").innerHTML = xmlHttp.responseText;\n" +
            "                }\n" +
            "            }\n" +
            "            xmlHttp.open(\"GET\", \"/console_log\", true);\n" +
            "            xmlHttp.send(null);\n" +
            "        }, 500);\n" +
            "    </script>\n" +
            "</body>\n" +
            "\n" +
            "</html>";
}
