package de.webui;

public class IndexCSS {
    public static String css = "* {\n" +
            "    padding: 0;\n" +
            "    margin: 0;\n" +
            "    position: relative;\n" +
            "    box-sizing: border-box;\n" +
            "    font-family: 'Poppins', sans-serif;\n" +
            "    color: #fff;\n" +
            "}\n" +
            "\n" +
            "body {\n" +
            "    height: 100svh;\n" +
            "    width: 100vw;\n" +
            "    display: flex;\n" +
            "}\n" +
            "\n" +
            ".sidebar {\n" +
            "    background-color: #0B0B0B;\n" +
            "    height: 100%;\n" +
            "    padding: 15px;\n" +
            "    display: flex;\n" +
            "    flex-direction: column;\n" +
            "    justify-content: center;\n" +
            "}\n" +
            "\n" +
            ".sidebar .items {\n" +
            "    display: flex;\n" +
            "    flex-direction: column;\n" +
            "    gap: 10px;\n" +
            "}\n" +
            "\n" +
            ".sidebar .items .item {\n" +
            "    font-size: 28px;\n" +
            "    padding: 10px;\n" +
            "    display: flex;\n" +
            "    align-items: center;\n" +
            "    justify-content: center;\n" +
            "    border-radius: 5px;\n" +
            "    cursor: pointer;\n" +
            "    width: 55px;\n" +
            "    aspect-ratio: 1.25/1;\n" +
            "    text-decoration: none;\n" +
            "}\n" +
            "\n" +
            ".sidebar .items .item:hover {\n" +
            "    background-color: #2c2c2c;\n" +
            "}\n" +
            "\n" +
            ".sidebar .items .item.active {\n" +
            "    background-color: #3190FF;\n" +
            "}\n" +
            "\n" +
            ".item:hover .tooltip {\n" +
            "    display: block;\n" +
            "}\n" +
            "\n" +
            ".tooltip {\n" +
            "    position: absolute;\n" +
            "    left: 120%;\n" +
            "    z-index: 2;\n" +
            "    background-color: #333333;\n" +
            "    padding: 5px 10px;\n" +
            "    font-size: 14px;\n" +
            "    border-radius: 3px;\n" +
            "    display: none;\n" +
            "    white-space: nowrap;\n" +
            "}\n" +
            "\n" +
            ".tooltip::after {\n" +
            "    content: \" \";\n" +
            "    position: absolute;\n" +
            "    top: 50%;\n" +
            "    right: 100%;\n" +
            "    /* To the left of the tooltip */\n" +
            "    margin-top: -5px;\n" +
            "    border-width: 5px;\n" +
            "    border-style: solid;\n" +
            "    border-color: transparent #333333 transparent transparent;\n" +
            "}\n" +
            "\n" +
            ".main {\n" +
            "    flex: 1;\n" +
            "    height: 100%;\n" +
            "    background-color: #141414;\n" +
            "    padding: 30px;\n" +
            "    overflow-y: auto;\n" +
            "}\n" +
            "\n" +
            ".main:has(.hero) {\n" +
            "    padding-top: 80px;\n" +
            "}\n" +
            "\n" +
            ".hero {\n" +
            "    display: flex;\n" +
            "    background-color: #2C2C2C;\n" +
            "    padding: 15px;\n" +
            "    min-height: 250px;\n" +
            "    border-radius: 15px;\n" +
            "    flex-wrap: wrap;\n" +
            "}\n" +
            "\n" +
            ".hero .img,\n" +
            ".hero .text {\n" +
            "    flex: 1;\n" +
            "}\n" +
            "\n" +
            ".hero .img img {\n" +
            "    height: 250px;\n" +
            "    max-height: 40vw;\n" +
            "    top: 50%;\n" +
            "    left: 50%;\n" +
            "    transform: translate(-50%, -50%) rotate(10deg) scale(1.2);\n" +
            "}\n" +
            "\n" +
            ".hero .text {\n" +
            "    display: flex;\n" +
            "    flex-direction: column;\n" +
            "    justify-content: space-evenly;\n" +
            "}\n" +
            "\n" +
            ".categories {\n" +
            "    display: flex;\n" +
            "    margin-top: 90px;\n" +
            "    gap: 15px;\n" +
            "    flex-wrap: wrap;\n" +
            "}\n" +
            "\n" +
            ".tag {\n" +
            "    background-color: #2C2C2C;\n" +
            "    padding: 5px 10px;\n" +
            "    border-radius: 5px;\n" +
            "    cursor: pointer;\n" +
            "}\n" +
            "\n" +
            ".tag:hover {\n" +
            "    background-color: #474747;\n" +
            "}\n" +
            "\n" +
            ".tag.active {\n" +
            "    background-color: #3190FF;\n" +
            "}\n" +
            "\n" +
            ".text {\n" +
            "    margin-top: 30px;\n" +
            "    margin-bottom: 30px;\n" +
            "}\n" +
            "\n" +
            ".leaderboard .header {\n" +
            "    display: flex;\n" +
            "    gap: 10px;\n" +
            "    padding: 10px;\n" +
            "}\n" +
            "\n" +
            ".leaderboard .header div:nth-child(2) {\n" +
            "    flex: 1;\n" +
            "}\n" +
            "\n" +
            ".leaderboard .header div:first-child {\n" +
            "    width: 50px;\n" +
            "}\n" +
            "\n" +
            ".leaderboard .header div:last-child {\n" +
            "    width: 100px;\n" +
            "    text-align: center;\n" +
            "}\n" +
            "\n" +
            ".leaderboard .line {\n" +
            "    display: flex;\n" +
            "    gap: 10px;\n" +
            "    align-items: center;\n" +
            "    padding: 10px;\n" +
            "    background-color: #2C2C2C;\n" +
            "    border-radius: 10px;\n" +
            "    margin-bottom: 10px;\n" +
            "}\n" +
            "\n" +
            ".leaderboard .line:hover {\n" +
            "    background-color: #3d3d3d;\n" +
            "}\n" +
            "\n" +
            ".leaderboard .line div {\n" +
            "    align-items: center;\n" +
            "}\n" +
            "\n" +
            ".leaderboard .line div:nth-child(2) {\n" +
            "    flex: 1;\n" +
            "}\n" +
            "\n" +
            ".leaderboard .line div:first-child {\n" +
            "    width: 50px;\n" +
            "    justify-content: center;\n" +
            "    padding-right: 10px;\n" +
            "}\n" +
            "\n" +
            ".leaderboard .line div:last-child {\n" +
            "    width: 100px;\n" +
            "    justify-content: center;\n" +
            "}\n" +
            "\n" +
            ".leaderboard .line img {\n" +
            "    height: 30px;\n" +
            "}\n" +
            "\n" +
            ".leaderboard .line div {\n" +
            "    display: flex;\n" +
            "    gap: 10px;\n" +
            "}\n" +
            "\n" +
            ".leaderboard_category {\n" +
            "    display: none;\n" +
            "}\n" +
            "\n" +
            ".card {\n" +
            "    background-color: #333333;\n" +
            "    margin-bottom: 10px;\n" +
            "    padding: 15px;\n" +
            "    border-radius: 10px;\n" +
            "}\n" +
            "\n" +
            ".flex {\n" +
            "    display: flex;\n" +
            "    gap: 10px;\n" +
            "    align-items: center;\n" +
            "}\n" +
            "\n" +
            ".card span {\n" +
            "    color: #a0a0a0;\n" +
            "}\n" +
            "\n" +
            ".hr {\n" +
            "    border-top: 1px solid #6e6e6e;\n" +
            "}\n" +
            "\n" +
            ".playerList {\n" +
            "    margin-bottom: 150px;\n" +
            "}\n" +
            "\n" +
            ".playerList .header {\n" +
            "    display: flex;\n" +
            "    gap: 10px;\n" +
            "    padding: 10px;\n" +
            "}\n" +
            "\n" +
            ".playerList .header div:first-child {\n" +
            "    flex: 1;\n" +
            "}\n" +
            "\n" +
            ".playerList .line {\n" +
            "    display: flex;\n" +
            "    gap: 10px;\n" +
            "    align-items: center;\n" +
            "    padding: 10px;\n" +
            "    background-color: #2C2C2C;\n" +
            "    border-radius: 10px;\n" +
            "    margin-bottom: 10px;\n" +
            "}\n" +
            "\n" +
            ".playerList .line div {\n" +
            "    align-items: center;\n" +
            "}\n" +
            "\n" +
            ".playerList .line:hover {\n" +
            "    background-color: #3d3d3d;\n" +
            "}\n" +
            "\n" +
            ".playerList .line div:last-child {\n" +
            "    width: 200px;\n" +
            "    white-space: nowrap;\n" +
            "    justify-content: end;\n" +
            "}\n" +
            "\n" +
            ".playerList .line div:first-child {\n" +
            "    flex: 1;\n" +
            "}\n" +
            "\n" +
            ".playerList .line img {\n" +
            "    height: 30px;\n" +
            "}\n" +
            "\n" +
            ".playerList .line div {\n" +
            "    display: flex;\n" +
            "    gap: 10px;\n" +
            "}\n" +
            "\n" +
            ".loginbox {\n" +
            "    position: absolute;\n" +
            "    left: 50%;\n" +
            "    top: 50%;\n" +
            "    transform: translate(-50%, -50%);\n" +
            "    gap: 10px;\n" +
            "    background-color: #2c2c2c;\n" +
            "    padding: 40px;\n" +
            "    border-radius: 5px;\n" +
            "    width: 400px;\n" +
            "    text-align: center;\n" +
            "}\n" +
            "\n" +
            ".loginbox form {\n" +
            "    width: 100%;\n" +
            "    display: flex;\n" +
            "    flex-direction: column;\n" +
            "    gap: 10px;\n" +
            "}\n" +
            "\n" +
            ".loginbox form input {\n" +
            "    appearance: none;\n" +
            "    border: none;\n" +
            "    outline: none;\n" +
            "    padding: 10px;\n" +
            "    border-radius: 7px;\n" +
            "}\n" +
            "\n" +
            ".loginbox form input[type=submit] {\n" +
            "    background-color: #3190FF;\n" +
            "}\n" +
            "\n" +
            ".loginbox form input[type=password] {\n" +
            "    background-color: #444444;\n" +
            "    margin-top: 20px;\n" +
            "}\n" +
            "\n" +
            "input {\n" +
            "    background-color: #444444;\n" +
            "    appearance: none;\n" +
            "    border: none;\n" +
            "    outline: none;\n" +
            "    padding: 10px;\n" +
            "    border-radius: 7px;\n" +
            "}\n" +
            "\n" +
            "input[type=button] {\n" +
            "    background-color: #3190FF;\n" +
            "    cursor: pointer;\n" +
            "}\n" +
            "\n" +
            "input[type=button]:active {\n" +
            "    transform: scale(0.9);\n" +
            "}\n" +
            "\n" +
            ".loginbox form input[type=password]::placeholder {\n" +
            "    color: #b4b4b4;\n" +
            "}\n" +
            "\n" +
            ".loginbox h2 {\n" +
            "    margin-top: 20px;\n" +
            "    margin-bottom: 20px;\n" +
            "}\n" +
            "\n" +
            ".loginbox i {\n" +
            "    padding: 15px;\n" +
            "    border: 2px solid #ffffff3d;\n" +
            "    border-radius: 50%;\n" +
            "}\n" +
            "\n" +
            ".chat {\n" +
            "    display: flex;\n" +
            "    width: 100%;\n" +
            "}\n" +
            "\n" +
            ".chat input:first-child {\n" +
            "    border-top-right-radius: 0;\n" +
            "    border-bottom-right-radius: 0;\n" +
            "    flex: 1;\n" +
            "}\n" +
            "\n" +
            ".chat input:last-child {\n" +
            "    border-top-left-radius: 0;\n" +
            "    border-bottom-left-radius: 0;\n" +
            "}\n" +
            "\n" +
            ".console {\n" +
            "    background-color: #444444;\n" +
            "    margin-top: 20px;\n" +
            "    padding: 10px;\n" +
            "    height: 600px;\n" +
            "    border-radius: 7px;\n" +
            "    display: flex;\n" +
            "    flex-direction: column;\n" +
            "    gap: 10px;\n" +
            "}\n" +
            "\n" +
            ".console pre {\n" +
            "    overflow-y: auto;\n" +
            "    display: flex;\n" +
            "    flex-direction: column-reverse;\n" +
            "    flex: 1;\n" +
            "}\n" +
            "\n" +
            ".console pre::-webkit-scrollbar {\n" +
            "    width: 7px;\n" +
            "    height: 0;\n" +
            "}\n" +
            "\n" +
            "\n" +
            "/* Track */\n" +
            "\n" +
            ".console pre::-webkit-scrollbar-track {\n" +
            "    background: transparent;\n" +
            "}\n" +
            "\n" +
            "\n" +
            "/* Handle */\n" +
            "\n" +
            ".console pre::-webkit-scrollbar-thumb {\n" +
            "    background: #888;\n" +
            "    border-radius: 5px;\n" +
            "}\n" +
            "\n" +
            "\n" +
            "/* Handle on hover */\n" +
            "\n" +
            ".console pre::-webkit-scrollbar-thumb:hover {\n" +
            "    background: #555;\n" +
            "}";
}
