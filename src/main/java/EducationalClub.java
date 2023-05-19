import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EducationalClub extends TelegramLongPollingBot {
    final String botName;
    final String botToken;
    Integer i = 0;
    String chat_id = "";
    Integer flad_register = 0;
    Integer action = 0; //1-запись 2-отмена 3-вывод записей
    Person person = new Person();

    public EducationalClub(String botName, String botToken) {
        this.botName = botName;
        this.botToken = botToken;
    }

    @Override
    public String getBotUsername() {
        // геттер имени бота
        return this.botName;
    }

    @Override
    public String getBotToken() {
        // геттер токена бота
        return this.botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        //проверяем наличие сообщений
        if ((update.hasMessage() && update.getMessage().hasText()) || (update.hasCallbackQuery())) {
            //String message_text = update.getMessage().getText();            // Создаем переменную равную тексту присланного сообщения

            SendMessage message = new SendMessage(); // Создаем обект-сообщение

            if (i == 0) {
                chat_id = update.getMessage().getChatId().toString();// Создаем переменную равную ид чата присланного сообщения


                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

                InlineKeyboardButton ButtonPrice = new InlineKeyboardButton();
                ButtonPrice.setText("Узнать прайс и акции");
                InlineKeyboardButton ButtonTeachers = new InlineKeyboardButton();
                ButtonTeachers.setText("Узнать больше о преподавателях");
                InlineKeyboardButton ButtonInformation = new InlineKeyboardButton();
                ButtonInformation.setText("Узнать о клубе");
                InlineKeyboardButton ButtonTimetable = new InlineKeyboardButton();
                ButtonTimetable.setText("Узнать расписание");
                InlineKeyboardButton ButtonQuestion = new InlineKeyboardButton();
                ButtonQuestion.setText("Узнать часто задаваемые вопросы");
                InlineKeyboardButton ButtonSignUp = new InlineKeyboardButton();
                ButtonSignUp.setText("Записаться на занятие");
                InlineKeyboardButton ButtonCancel = new InlineKeyboardButton();
                ButtonCancel.setText("Отменить запись");
                InlineKeyboardButton ButtonEntaries = new InlineKeyboardButton();
                ButtonEntaries.setText("Посмотреть текущие записи");


                ButtonPrice.setCallbackData("ButtonPrice");
                ButtonTeachers.setCallbackData("ButtonTeachers");
                ButtonInformation.setCallbackData("ButtonInformation");
                ButtonTimetable.setCallbackData("ButtonTimetable");
                ButtonQuestion.setCallbackData("ButtonQuestion");
                ButtonSignUp.setCallbackData("ButtonSignUp");
                ButtonCancel.setCallbackData("ButtonCancel");
                ButtonEntaries.setCallbackData("ButtonEntaries");

                List<InlineKeyboardButton> row1 = new ArrayList<>();
                row1.add(ButtonPrice);

                List<InlineKeyboardButton> row2 = new ArrayList<>();
                row2.add(ButtonTeachers);

                List<InlineKeyboardButton> row3 = new ArrayList<>();
                row3.add(ButtonInformation);

                List<InlineKeyboardButton> row4 = new ArrayList<>();
                row4.add(ButtonTimetable);

                List<InlineKeyboardButton> row5 = new ArrayList<>();
                row5.add(ButtonQuestion);

                List<InlineKeyboardButton> row6 = new ArrayList<>();
                row6.add(ButtonSignUp);

                List<InlineKeyboardButton> row7 = new ArrayList<>();
                row7.add(ButtonCancel);

                List<InlineKeyboardButton> row8 = new ArrayList<>();
                row8.add(ButtonEntaries);

                List<List<InlineKeyboardButton>> row = new ArrayList<>();
                row.add(row1);
                row.add(row2);
                row.add(row3);
                row.add(row4);
                row.add(row5);
                row.add(row6);
                row.add(row7);
                row.add(row8);

                inlineKeyboardMarkup.setKeyboard(row);

                message.setReplyMarkup(inlineKeyboardMarkup);
                message.setText("Добро пожаловать в телеграмм-бот развивающего клуба Мотылёк" +
                        EmojiParser.parseToUnicode(":cherry_blossom: ") + "\n" + "Выбери нужную для себя опцию.");
            }
            else if (i == 1) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                if (callbackQuery.getData().equals("ButtonPrice")) {
                    SendPhoto msg = new SendPhoto();
                    msg.setPhoto(new InputFile(new File("C:\\Users\\Анастасия\\IdeaProjects\\EducationBot\\src\\main\\photo\\прайс.jpg")));
                    msg.setChatId(chat_id);
                    try {
                        execute(msg);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    message.setText("Более подробную консультацию по поводу прайса вы можете получить по контактному номеру." +
                            "\n" + "\n" + "Чтобы запустить бот заново нажми любую кнопку." +
                            EmojiParser.parseToUnicode(":wave: "));
                    i = -1;
                } else if (callbackQuery.getData().equals("ButtonTeachers")) {
                    SendPhoto msg = new SendPhoto();
                    msg.setPhoto(new InputFile(new File("C:\\Users\\Анастасия\\IdeaProjects\\EducationBot\\src\\main\\photo\\teachers.png")));
                    msg.setChatId(chat_id);
                    try {
                        execute(msg);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    message.setText("Более подробную консультацию по поводу преподавателей вы можете получить по контактному номеру." +
                            "\n" + "\n" + "Чтобы запустить бот заново нажми любую кнопку." +
                            EmojiParser.parseToUnicode(":wave: "));
                    i = -1;
                } else if (callbackQuery.getData().equals("ButtonInformation")) {
                    SendPhoto msg = new SendPhoto();
                    msg.setPhoto(new InputFile(new File("C:\\Users\\Анастасия\\IdeaProjects\\EducationBot\\src\\main\\photo\\Информация.jpg")));
                    msg.setChatId(chat_id);
                    try {
                        execute(msg);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    message.setText("Более подробную консультацию о нашем клубе вы можете получить по контактному номеру." +
                            "\n" + "Контактный номер : 89503218843" +
                            "\n" + "Часы работы: 11.00-20.00" +
                            "\n" + "Адрес: г.Казань ул.Чернышевского д.3" +
                            "\n" + "Наш инстаграмм @EducationalClubMoth" +
                            "\n" + "\n" + "Чтобы запустить бот заново нажми любую кнопку." +
                            EmojiParser.parseToUnicode(":wave: "));
                    i = -1;
                } else if (callbackQuery.getData().equals("ButtonTimetable")) {
                    SendPhoto msg = new SendPhoto();
                    msg.setPhoto(new InputFile(new File("C:\\Users\\Анастасия\\IdeaProjects\\EducationBot\\src\\main\\photo\\Расписание.png")));
                    msg.setChatId(chat_id);
                    try {
                        execute(msg);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    message.setText("Более подробную консультацию по поводу расписания вы можете получить по контактному номеру." +
                            "\n" + "\n" + "Чтобы запустить бот заново нажми любую кнопку." +
                            EmojiParser.parseToUnicode(":wave: "));
                    i = -1;
                } else if (callbackQuery.getData().equals("ButtonQuestion")) {
                    message.setText("ОТВЕТЫ НА ЧАСТО ЗАДАВАЕМЫЕ ВОПРОСЫ:" +
                            EmojiParser.parseToUnicode(":question: ") +
                            "\n" + "\n" +
                            EmojiParser.parseToUnicode(":diamonds: ") + "Проводите ли вы пробные занятия? \n" +
                            "Мы не практикуем бесплатных пробных занятий, потому что главный результат достигается благодаря систематичному посещению." +
                            "\n" + "\n" +
                            EmojiParser.parseToUnicode(":diamonds: ") + "Нужны ли дополнительные покупки материалов, помимо оплаты занятий? \n" +
                            "Все необходимые материалы выдаются на занятиях в клубе. В отдельных направлениях, по желанию родителей, возможно приобретение своих материалов для сохранения реультатов занятий." +
                            "\n" + "\n" +
                            EmojiParser.parseToUnicode(":diamonds: ") + " Как проходят занятия? \n" +
                            "Мы развиваем способности малышей с учетом психологических особенностей каждого ребенка. Занятия по всем программам проходят в увлекательной игровой форме с частой сменой деятельности. Смена видов деятельности и подача материала в игровой форме позволяют детям не уставать и легко воспринимать информацию. Для каждого вида дейтельности определена своя зона проведения - динамическая, интеллектуальная и игровая зоны. Это позволяет уменьшить возможность возникновения усталости у детей. Дети не устают и не теряют интерес к предлагаемым заданиям благодаря правильной структуре занятия: дети могут и двигаться, и смотреть, и слушать, и петь. " +
                            "\n" + "\n" +
                            EmojiParser.parseToUnicode(":diamonds: ") + "Сколько времени продолжается занятие? \n" +
                            "Продолжительность одного занятия - 45 минут. Желательно приводить ребенка за 5-10 минут до начала занятия, и забирать в течении 5-10 минут после окончания. " +
                            "\n" + "\n" +
                            EmojiParser.parseToUnicode(":diamonds: ") + " Что делает педагог, если заболевающего ребенка (с насморком или кашлем) привели в клуб? \n" +
                            "Если кто-то из малышей болен и пришел в клуб, мы настоятельно просим родителей с ребенком не оставаться на занятии в клубе, а вернуться домой до полного выздоровления." +
                            "\n" + "\n" + "Чтобы запустить бот заново нажми любую кнопку." +
                            EmojiParser.parseToUnicode(":wave: "));
                    i = -1;
                } else if (callbackQuery.getData().equals("ButtonSignUp")) {
                    action = 1;
                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

                    InlineKeyboardButton ButtonEnter = new InlineKeyboardButton();
                    ButtonEnter.setText("Войти!");
                    InlineKeyboardButton ButtonRegistration = new InlineKeyboardButton();
                    ButtonRegistration.setText("Зарегистрироваться!");

                    ButtonEnter.setCallbackData("ButtonEnter");
                    ButtonRegistration.setCallbackData("ButtonRegistration");

                    List<InlineKeyboardButton> row1 = new ArrayList<>();
                    row1.add(ButtonEnter);

                    List<InlineKeyboardButton> row2 = new ArrayList<>();
                    row2.add(ButtonRegistration);

                    List<List<InlineKeyboardButton>> row = new ArrayList<>();
                    row.add(row1);
                    row.add(row2);

                    inlineKeyboardMarkup.setKeyboard(row);

                    message.setReplyMarkup(inlineKeyboardMarkup);
                    message.setText("Требуется авторизация." +
                            "\n" + "Выбери нужную для себя категорию." +
                            EmojiParser.parseToUnicode(":arrow_down: "));
                } else if (callbackQuery.getData().equals("ButtonCancel")) {
                    action = 2;
                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

                    InlineKeyboardButton ButtonEnter = new InlineKeyboardButton();
                    ButtonEnter.setText("Войти!");

                    ButtonEnter.setCallbackData("ButtonEnter");

                    List<InlineKeyboardButton> row1 = new ArrayList<>();
                    row1.add(ButtonEnter);


                    List<List<InlineKeyboardButton>> row = new ArrayList<>();
                    row.add(row1);

                    inlineKeyboardMarkup.setKeyboard(row);

                    message.setReplyMarkup(inlineKeyboardMarkup);
                    message.setText("Требуется авторизация." +
                            "\n" + "Выбери нужную для себя категорию." +
                            EmojiParser.parseToUnicode(":arrow_down: "));
                } else if (callbackQuery.getData().equals("ButtonEntaries")) {
                    action = 3;
                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

                    InlineKeyboardButton ButtonEnter = new InlineKeyboardButton();
                    ButtonEnter.setText("Войти!");
                    InlineKeyboardButton ButtonRegistration = new InlineKeyboardButton();
                    ButtonRegistration.setText("Зарегистрироваться!");

                    ButtonEnter.setCallbackData("ButtonEnter");
                    ButtonRegistration.setCallbackData("ButtonRegistration");

                    List<InlineKeyboardButton> row1 = new ArrayList<>();
                    row1.add(ButtonEnter);

                    List<InlineKeyboardButton> row2 = new ArrayList<>();
                    row2.add(ButtonRegistration);

                    List<List<InlineKeyboardButton>> row = new ArrayList<>();
                    row.add(row1);
                    row.add(row2);

                    inlineKeyboardMarkup.setKeyboard(row);

                    message.setReplyMarkup(inlineKeyboardMarkup);
                    message.setText("Требуется авторизация." +
                            "\n" + "Выбери нужную для себя категорию." +
                            EmojiParser.parseToUnicode(":arrow_down: "));
                }
            } else if (i == 2) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                if (callbackQuery.getData().equals("ButtonEnter")) {
                    message.setText("Введите свой идентификационный номер: ");
                } else if (callbackQuery.getData().equals("ButtonRegistration")) {
                    message.setText("Введите ФИО ребёнка:");
                    flad_register += 1;
                }
            } else if ((i == 3) && (flad_register == 0)) {
                String message_text = update.getMessage().getText();
                try {
                    person.id = Integer.parseInt(message_text);
                    //запрос в бд с выборкой данных от пользователя
                    if (CheclId(Integer.parseInt(message_text))) {
                        message.setText("Вы зашли под именем пользователем: " +person.nameChild+
                                "\n" + "Введите любой символ,если готовы продолжить дальше.");
                        //заполнение полей person
                    } else {
                        message.setText("Ошибка! Данного идентификационного номера не существует!" +
                                "\n" + "\n" + "Чтобы запустить бот заново нажми любую кнопку." +
                                EmojiParser.parseToUnicode(":wave: "));
                        i = -1;
                        DeleteData();
                    }

                } catch (NumberFormatException e) {
                    message.setText("Ошибка! Данного идентификационного номера не существует!" +
                            "\n" + "\n" + "Чтобы запустить бот заново нажми любую кнопку." +
                            EmojiParser.parseToUnicode(":wave: "));
                    i = -1;
                    DeleteData();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else if ((i == 3) && (flad_register == 1)) {
                person.nameChild = update.getMessage().getText();
                message.setText("Введите свой номер(Пример: 89305328832):");
                flad_register += 1;
                i -= 1;
            } else if ((i == 3) && (flad_register == 2)) {
                String s=update.getMessage().getText();
                try {
                    Long l=Long.parseLong(update.getMessage().getText());
                    person.number = update.getMessage().getText();
                    if (s.charAt(0)=='8'&&s.charAt(1)=='9'&&s.length()==11){
                        message.setText("Введите своё ФИО:");
                        flad_register += 1;
                        i -= 1;
                    }
                    else {
                        System.out.println("="+s.charAt(0));
                        System.out.println(s.charAt(1));
                        System.out.println(s.length());
                        message.setText("Ошибка! Номер телефона должен состоять только из цифр,содержать не более 11 символов и начинаться на 89.." +
                            "\n" + "\n" + "Чтобы запустить бот заново нажми любую кнопку." +
                            EmojiParser.parseToUnicode(":wave: "));

                        i = -1;
                        DeleteData();
                   }
                } catch (NumberFormatException e) {
                    message.setText("Ошибкаa! Номер телефона должен состоять только из цифр,содержать не более 11 символов и начинаться на 89.." +
                            "\n" + "\n" + "Чтобы запустить бот заново нажми любую кнопку." +
                            EmojiParser.parseToUnicode(":wave: "));
                    i = -1;
                    DeleteData();
                }

            } else if ((i == 3) && (flad_register == 3)) {
                person.name = update.getMessage().getText();
                message.setText("Введите дату рождения ребёнка:");
                flad_register += 1;
                i -= 1;
            } else if ((i == 3) && (flad_register == 4)) {
                person.dataChild = update.getMessage().getText();

                try {
                    AddUser();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                message.setText("Ваш идентификационный номер: " +person.id+
                        "\n" +
                        EmojiParser.parseToUnicode(":sos: ") +
                        EmojiParser.parseToUnicode(":sos: ") +
                        EmojiParser.parseToUnicode(":sos: ") + "Внимание!Не собщайте никому данную информацию!" +
                        "\n" + "При потери идентификационного номера его восстановление возможно только через прямое обращение в клуб!" +
                        "\n" + "Введите любой символ,если готовы продолжить.");

            } else if (i == 4) {
                if (action == 1) {
                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

                    InlineKeyboardButton ButtonEnglish = new InlineKeyboardButton();
                    ButtonEnglish.setText("Английский язык 12.00 (Вторник)");
                    InlineKeyboardButton ButtonWorld = new InlineKeyboardButton();
                    ButtonWorld.setText("Весь мир на ладони 18.00 (Среда)");
                    InlineKeyboardButton ButtonLogic = new InlineKeyboardButton();
                    ButtonLogic.setText("Логическое мышление 15.00 (Четверг)");
                    InlineKeyboardButton ButtonOrigami = new InlineKeyboardButton();
                    ButtonOrigami.setText("Оригами 12.00 (Суббота)");


                    ButtonEnglish.setCallbackData("ButtonEnglish");
                    ButtonWorld.setCallbackData("ButtonWorld");
                    ButtonLogic.setCallbackData("ButtonLogic");
                    ButtonOrigami.setCallbackData("ButtonOrigami");


                    List<InlineKeyboardButton> row1 = new ArrayList<>();
                    row1.add(ButtonEnglish);

                    List<InlineKeyboardButton> row2 = new ArrayList<>();
                    row2.add(ButtonWorld);

                    List<InlineKeyboardButton> row3 = new ArrayList<>();
                    row3.add(ButtonLogic);

                    List<InlineKeyboardButton> row4 = new ArrayList<>();
                    row4.add(ButtonOrigami);


                    List<List<InlineKeyboardButton>> row = new ArrayList<>();
                    row.add(row1);
                    row.add(row2);
                    row.add(row3);
                    row.add(row4);

                    inlineKeyboardMarkup.setKeyboard(row);

                    message.setReplyMarkup(inlineKeyboardMarkup);
                    message.setText("Выберите одно из занятий." +
                            "\n" + "Внимание!Если выбранный день недели занятия прошёл,то запись происходит на этот же день, но на следующую неделю." +
                            EmojiParser.parseToUnicode(":arrow_down: "));
                } else if (action == 2) {

                    Boolean flag = true;
                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> row = new ArrayList<>();
                    try {
                        if (CheckLesson("English")) {
                            InlineKeyboardButton ButtonEnglish = new InlineKeyboardButton();
                            ButtonEnglish.setText("Английский язык 12.00 (Вторник)");
                            ButtonEnglish.setCallbackData("ButtonEnglish");

                            List<InlineKeyboardButton> row1 = new ArrayList<>();
                            row1.add(ButtonEnglish);
                            row.add(row1);
                            flag=false;

                        }if (CheckLesson("World")) {
                            InlineKeyboardButton ButtonWorld = new InlineKeyboardButton();
                            ButtonWorld.setText("Весь мир на ладони 18.00 (Среда)");
                            ButtonWorld.setCallbackData("ButtonWorld");

                            List<InlineKeyboardButton> row2 = new ArrayList<>();
                            row2.add(ButtonWorld);
                            row.add(row2);
                            flag=false;
                        } if (CheckLesson("Logic")) {
                            InlineKeyboardButton ButtonLogic = new InlineKeyboardButton();
                            ButtonLogic.setText("Логическое мышление 15.00 (Четверг)");
                            ButtonLogic.setCallbackData("ButtonLogic");

                            List<InlineKeyboardButton> row3 = new ArrayList<>();
                            row3.add(ButtonLogic);
                            row.add(row3);
                            flag=false;
                        }  if (CheckLesson("Origami")) {
                            InlineKeyboardButton ButtonOrigami = new InlineKeyboardButton();
                            ButtonOrigami.setText("Оригами 12.00 (Суббота)");
                            ButtonOrigami.setCallbackData("ButtonOrigami");

                            List<InlineKeyboardButton> row4 = new ArrayList<>();
                            row4.add(ButtonOrigami);
                            row.add(row4);
                            flag=false;
                        }
                            if (flag == false) {
                                message.setText("Ваши текущие записи: " +
                                        "\n" + "\n" + "Выберите то, что хотите удалить." +
                                        EmojiParser.parseToUnicode(":arrow_down: "));
                                inlineKeyboardMarkup.setKeyboard(row);

                                message.setReplyMarkup(inlineKeyboardMarkup);
                            }
                            else {
                                message.setText("У вас нет текущих записей." +
                                        "\n" + "\n" + "Чтобы запустить бот заново нажми любую кнопку." +
                                        EmojiParser.parseToUnicode(":wave: "));

                                i = -1;
                                DeleteData();
                            }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                } else if (action == 3) {
//запрос в бд на данные текущих записей
                    String s= "";
                    try {
                        s = ShowTimeTable();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (s==""){
                        s="У вас нет текущих записей.";
                    }
                    message.setText("Текущие записи: " +s+
                            "\n" + "\n" + "Чтобы запустить бот заново нажми любую кнопку." +
                            EmojiParser.parseToUnicode(":wave: "));
                    i = -1;
                }
            } else if (i == 5) {
                if (action == 1) {
                    CallbackQuery callbackQuery = update.getCallbackQuery();
                    if (callbackQuery.getData().equals("ButtonEnglish")) {
                        message.setText(SignUp("English"));

                    } else if (callbackQuery.getData().equals("ButtonWorld")) {
                        message.setText(SignUp("World"));
                    } else if (callbackQuery.getData().equals("ButtonLogic")) {
                        message.setText(SignUp("Logic"));
                    } else if (callbackQuery.getData().equals("ButtonOrigami")) {
                        message.setText(SignUp("Origami"));
                    }
                } else if (action == 2) {
                    CallbackQuery callbackQuery = update.getCallbackQuery();
                    if (callbackQuery.getData().equals("ButtonEnglish")) {
                        try {
                            DropLesson("English");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (callbackQuery.getData().equals("ButtonWorld")) {
                        try {
                            DropLesson("World");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (callbackQuery.getData().equals("ButtonLogic")) {
                        try {
                            DropLesson("Logic");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (callbackQuery.getData().equals("ButtonOrigami")) {
                        try {
                            DropLesson("Origami");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    message.setText("Выбранная запись отменена. " +
                            "\n" + "\n" + "Чтобы запустить бот заново нажми любую кнопку." +
                            EmojiParser.parseToUnicode(":wave: "));
                    i = -1;
                    DeleteData();
                }
            }
            i += 1;
            message.setChatId(chat_id);
            try {
                execute(message);                   // Отправляем обект-сообщение пользователю
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void DeleteData() {
        flad_register = 0;
        action = 0; //1-запись 2-отмена 3-вывод записей
        person = new Person();
    }
    public Connection ConnectionWitnBD() throws ClassNotFoundException,SQLException{
        String host="localhost";
        String port="3306";
        String user="root";
        String password="bovuzo6633";
        String dbname="bot";

        Connection connection;
        String connectionString="jdbc:mysql://"+host+":"+port+"/"+dbname;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection= DriverManager.getConnection(connectionString,user,password);
        return connection;
    }
    public void AddUser() throws SQLException, ClassNotFoundException {
        person.id= (int) ((Math.random() * ((100000 - 1) + 1)) + 1);

        String insert="INSERT INTO Child(Code,Name,Date,ContactNumber,Parent) VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement=ConnectionWitnBD().prepareStatement(insert);
        preparedStatement.setString(1, String.valueOf(person.id));
        preparedStatement.setString(2,person.nameChild);
        preparedStatement.setString(3,person.dataChild);
        preparedStatement.setString(4, person.number);
        preparedStatement.setString(5, person.name);
        preparedStatement.executeUpdate();
    }
    public void AddLesson(String table) throws SQLException, ClassNotFoundException {
        String insert="INSERT INTO "+table+"(CodeChild) VALUES(?)";
        PreparedStatement preparedStatement=ConnectionWitnBD().prepareStatement(insert);
        preparedStatement.setString(1, String.valueOf(person.id));
        preparedStatement.executeUpdate();
    }
    public void DropLesson(String table) throws SQLException, ClassNotFoundException {
        String insert="DELETE  FROM "+table+" WHERE CodeChild=?";
        PreparedStatement preparedStatement=ConnectionWitnBD().prepareStatement(insert);
        preparedStatement.setString(1, String.valueOf(person.id));
        preparedStatement.executeUpdate();
    }

    public Boolean CheclId(Integer id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet=null;
        String select="SELECT * FROM Child where Code=?";
        PreparedStatement preparedStatement=ConnectionWitnBD().prepareStatement(select);
        preparedStatement.setString(1, String.valueOf(id));
        resultSet=preparedStatement.executeQuery();
        if (resultSet.next()){
            person.id=id;
             person.nameChild=resultSet.getString("Name");
            person.dataChild=resultSet.getString("Date");
            person.number=resultSet.getString("ContactNumber");
            person.name=resultSet.getString("Parent");
            return true;

        }
        else{
            return false;
        }
    }
    public Boolean CheckLesson(String table) throws SQLException, ClassNotFoundException {
        ResultSet resultSet=null;
        String select="SELECT * FROM "+table+" where CodeChild=?;";
        PreparedStatement preparedStatement=ConnectionWitnBD().prepareStatement(select);
        preparedStatement.setString(1, String.valueOf(person.id));
        resultSet=preparedStatement.executeQuery();
        if (resultSet.next()){
            return true;
        }
        else{
            return false;
        }
    }
    public String ShowTimeTable() throws SQLException, ClassNotFoundException {

        String s="";

        if (CheckLesson("English")){
            s+="\n"+"Английский язык 12.00 (Вторник)";
        }
        if (CheckLesson("World")){
            s+="\n"+"Весь мир на ладони 18.00 (Среда)";
        }
        if (CheckLesson("Logic")){
            s+="\n"+"Логическое мышление 15.00 (Четверг)";
        }
        if (CheckLesson("Origami")){
            s+="\n"+"Оригами 12.00 (Суббота)";
        }

        return s;
    }
    //проверка на наличие мест
    public Boolean CheckFreeSpace(String table) throws SQLException, ClassNotFoundException {
        ResultSet resultSet=null;
        String select="SELECT * FROM "+table+" where CodeChild=?;";
        PreparedStatement preparedStatement=ConnectionWitnBD().prepareStatement(select);
        preparedStatement.setString(1, String.valueOf(person.id));
        resultSet=preparedStatement.executeQuery();
        int k=0;
        while (resultSet.next()){
            k+=1;
        }
        if (k<10){
            return true;
        }
        else{
            return false;
        }
    }
    public String SignUp(String table){
        String s="";
        try {
            if (CheckLesson(table)){
                s="У вас уже есть запись на данное занятие. "+EmojiParser.parseToUnicode(":astonished: ") +
                        "\n" + "\n" + "Чтобы запустить бот заново нажми любую кнопку." +
                        EmojiParser.parseToUnicode(":wave: ");
                i = -1;
                DeleteData();
            }
            else if (!CheckFreeSpace(table)){
                s="К сожалению, на данное занятие нет свободных мест. " +EmojiParser.parseToUnicode(":weary: ")+
                        "\n" + "\n" + "Чтобы запустить бот заново нажми любую кнопку." +
                        EmojiParser.parseToUnicode(":wave: ");
                i = -1;
                DeleteData();
            }
            else {
                try {
                    AddLesson(table);
                    s="Запись осуществлена. " +
                            "\n" + "\n" + "Чтобы запустить бот заново нажми любую кнопку." +
                            EmojiParser.parseToUnicode(":wave: ");
                    i = -1;
                    DeleteData();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return s;
    }
}

