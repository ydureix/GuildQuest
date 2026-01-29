import java.util.ArrayList;

class Inventory{
    String inventoryID;
    Character character;
    Inventory(String inventoryID, Character character){
        this.inventoryID = inventoryID;
        this.character = character;
    }
}



class Character{
    String characterID;
    String name;
    String characterClass;
    int level;
    Inventory inventory;
    String description;

    Character(String characterID, String name, String characterClass, int level){
        this.characterID = characterID;
        this.name = name;
        this.characterClass = characterClass;
        this.level = level;
    }
}

class WorldClock{
    int days;
    int hours;
    int minutes;

    WorldClock(int days, int hours, int minutes){
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
    }
}

class Realm{
    String realmID;
    String name;
    String description;
    String mapId;
    LocalTimeRule localTimeRule;

    Realm(String realmID, String name){
        this.realmID = realmID;
        this.name = name;
    }
}

class LocalTimeRule{
    int offsetDays;
    int offsetHours;
    int offsetMinutes;

    LocalTimeRule(int offsetDays, int offsetHours, int offsetMinutes){
        this.offsetDays = offsetDays;
        this.offsetHours = offsetHours;
        this.offsetMinutes = offsetMinutes;
    }
}

class QuestEvent{
    String eventID;
    WorldClock startTime;
    WorldClock endTime;
    String name;
    Realm realm;
    String description;
    QuestEvent(String eventID, String name, WorldClock startTime, Realm realm){
        this.eventID = eventID;
        this.name = name;
        this.startTime = startTime;
        this.realm = realm;
    }
}


class Campaign {
    String campaignID;
    String name;
    User host;
    ArrayList<QuestEvent> questEvents;
    WorldClock createdAt;

    Campaign(String campaignID, String name, User host) {
        this.campaignID = campaignID;
        this.name = name;
        this.host = host;
    }

    @Override
    public String toString(){
        return this.name;
    }

}



class User{
    int userID;
    String username;
    ArrayList<Campaign> campaigns;
    ArrayList<Character> characters;
    String email;
    String password;
    User(int userID, String username){
        super();
        this.userID = userID;
        this.username = username;
        this.campaigns = new ArrayList<Campaign>();
    }

    void addCampaign(Campaign campaign){
        this.campaigns.add(campaign);
    }

    void removeCampaign(String campaignID){
        this.campaigns.removeIf(c -> c.campaignID.equals(campaignID));
    }

    void updateCampaign(String campaignID){

    }
}


public class GuildQuest {
    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}