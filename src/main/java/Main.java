import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        String botName = "EducationalClubBot"; // В место звездочек указываем имя созданного вами ранее Бота
        String botToken = "6228434237:AAESyCv25mv-KQwfKSCpC253DEFnVMyeb0c"; // В место звездочек указываем токен созданного вами ранее Бота
        TelegramBotsApi telegramBotsApi = null;
        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new EducationalClub(botName, botToken));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
