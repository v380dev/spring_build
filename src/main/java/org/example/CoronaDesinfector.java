package org.example;

    /**
     * Порушено принцип responsibility
     * щонайменше клас відповідає за 7 операцій (максимум - нескінченність)
     * (замість однієї - вміти дезинфікувати комнату):
     * 1 - знає який об'єкт Announcer створити
     * 2 - вміє його правильно створити
     * 3 - вміє його правильно налаштувати
     * 4,5,6 - теж саме для Policeman
     * 7 - і зрештою свою задачу: метод desinfect
     *
     */
public class CoronaDesinfector {

    private Announcer announcer = new ConsoleAnnouncer();

    private Policeman policeman = new PolicemanImpl();
    public void start (Room room) {
        announcer.announce ("Починаємо дезінфекцію, всі вон!");
        policeman.makePeopleLeaveRoom ();
        desinfect (room);
        announcer.announce ("Ризикніть увійти обратно");

    }

    private void desinfect(Room room) {
        System.out.println("зачитується молитва: 'корона зникни!' - молитва прочитана, вірус відправлено в ад");
    }
}
