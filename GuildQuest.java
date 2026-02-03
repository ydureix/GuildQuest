import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

class Inventory{
    String inventoryID;
    Character character;
    ArrayList<Item> items;
    Inventory(String inventoryID, Character character){
        this.inventoryID = inventoryID;
        this.character = character;
        this.items = new ArrayList<Item>();
    }

    void addItem(Item item){
        items.add(item);
    }

    void removeItem(Item item){
        items.remove(item);
    }
}

class Item{
    String name;
    String rarity;

    Item(String name, String rarity){
        this.name = name;
        this.rarity = rarity;
    }

    @Override
    public String toString(){
        return this.name;
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
        Random rand = new Random();
        int randomNum = rand.nextInt(100000);
        inventory = new Inventory(String.valueOf(randomNum), this);
    }

    @Override
    public String toString(){
        return this.name;
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

    void printCurrentTime(){
        LocalDateTime now = LocalDateTime.now();
        this.days = (int) now.toLocalDate().toEpochDay();
        this.hours = now.getHour();
        this.minutes = now.getMinute();
    }

    @Override
    public String toString(){
        return String.format("%s, %s:%s", days, hours, minutes);
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

    @Override
    public String toString(){
        return this.name;
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
        this.questEvents = new ArrayList<QuestEvent>();
        LocalDateTime now = LocalDateTime.now();
        int days = (int) now.toLocalDate().toEpochDay();
        int hours = now.getHour();
        int minutes = now.getMinute();
        createdAt = new WorldClock(days, hours, minutes);
    }

    @Override
    public String toString(){
        return this.name;
    }

    void addQuestEvent(QuestEvent event){
        questEvents.add(event);
    }

    void deleteQuestEvent(String eventID){
        this.questEvents.removeIf(e -> e.eventID.equals(eventID));
    }

    void updateEvent(String eventID, String newEventName){
        QuestEvent found = questEvents.stream()
                .filter(e -> e.eventID.equals(eventID))
                .findFirst()
                .orElse(null);

        if (found != null) {
            found.name = newEventName;
        }

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
        this.characters = new ArrayList<Character>();
    }

    void addCampaign(Campaign campaign){
        this.campaigns.add(campaign);
    }

    void removeCampaign(String campaignID){
        this.campaigns.removeIf(c -> c.campaignID.equals(campaignID));
    }

    void updateCampaign(String campaignID, String newCampaignName){
        Campaign found = campaigns.stream()
                .filter(c -> c.campaignID.equals(campaignID))
                .findFirst()
                .orElse(null);

        if (found != null) {
            found.name = newCampaignName;
        }

    }

    void addCharacter(Character character){
        this.characters.add(character);
    }
}


public class GuildQuest {
    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}