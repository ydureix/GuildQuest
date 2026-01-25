public class WorldClock{
    private int days;
    private int hours;
    private int minutes;

    public WorldClock(int days, int hours, int minutes){
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
    }
}

public class Realm{
    private String realmID;
    private String name;
    private String description;
    private String mapId;
    private LocalTimeRule localTimeRule;

    public Realm(String realmID, String name){
        this.realmID = realmID;
        this.name = name;
    }
}

public class LocalTimeRule{
    private int offsetDays;
    private int offsetHours;
    private int offsetMinutes;

    public LocalTimeRule(int offsetDays, int offsetHours, int offsetMinutes){
        this.offsetDays = offsetDays;
        this.offsetHours = offsetHours;
        this.offsetMinutes = offsetMinutes;
    }
}

public class QuestEvent{
    private String eventID;
    private WorldClock startTime;
    private WorldClock endTime;
    private String name;
    private Realm realm;
    private String description;
    public QuestEvent(String eventID, String name, WorldClock startTime, Realm realm){
        this.eventID = eventID;
        this.name = name;
        this.startTime = startTime;
        this.realm = realm;
    }
}


public class Campaign{
    private String campaignID;
    private String name;
    private User host;
    public Campaign(String campaignID, String name, User host){
        this.campaignID = campaignID;
        this.name = name;
        this.host = host;
    }
}

public class Inventory{
    private String inventoryID;
    private Character character;
    private Item[] items;
    public Inventory(String inventoryID, Character character){
        this.inventoryID = inventoryID;
        this.character = character;
    }
}



public class Character{
    private String characterID;
    private String name;
    private String characterClass;
    private int level;
    private Inventory inventory;
    private String description;
    public Character(String characterID, String name, String characterClass, int level){
        this.characterID = characterID;
        this.name = name;
        this.characterClass = characterClass;
        this.level = level;
    }
}

public class User{
    private int userID;
    private String username;
    private Campaign[] campaigns;
    private Character[] characters;
    public User(int userID, String username){
        this.userID = userID;
        this.username = username
    }


}





class guildQuest{
    public static void main(String[] args) {

    }
}