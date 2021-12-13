package UIMethods;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.openqa.selenium.logging.LogType;

import java.util.List;

public class MyUIMethods {

    private static List logList;

    private static List getConsoleLog() {
        return Selenide.getWebDriverLogs(LogType.PERFORMANCE);
    }

    public static void checkLogs() {
        logList = getConsoleLog();
        if (logList.size() > 0)
            addConsoleLogToReport();
    }

    private static String addConsoleLogToReport() {
        StringBuilder sb = new StringBuilder();
        for (Object line : logList) {
            sb.append(line);
            sb.append("\t");
        }
        Allure.addAttachment("Console log: ", sb.toString());
        return sb.toString();
    }
}
