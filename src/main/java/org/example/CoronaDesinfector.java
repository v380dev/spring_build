package org.example;

/**
 * клас делегує функції створення і налаштування
 * Announcer та Policeman
 * ObjectFactory
 */
public class CoronaDesinfector {
    @InjectByType
    private Announcer announcer;
    @InjectByType
    private Policeman policeman;
//    private Policeman policeman = ObjectFactory.getInstance().createObject(Policeman.class);

    public void start(Room room) {
        announcer.announce("Починаємо дезінфекцію, всі вон!");
        policeman.makePeopleLeaveRoom();
        desinfect(room);
        announcer.announce("Ризикніть увійти обратно");

    }

    private void desinfect(Room room) {
        System.out.println("зачитується молитва: 'корона зникни!' - молитва прочитана, вірус відправлено в ад");
    }
}
