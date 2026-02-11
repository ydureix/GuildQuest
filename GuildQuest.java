
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    private String name;
    private String rarity;

    Item(String name, String rarity){
        this.name = name;
        this.rarity = rarity;
    }

    public String getItemName(){
        return name;
    }

    public String getItemRarity(){
        return rarity;
    }
    @Override
    public String toString(){
        return this.name;
    }
}


class Character{
    private String characterID;
    private String name;
    private String characterClass;
    private int level;
    private Inventory inventory;
    private String description;

    Character(String characterID, String name, String characterClass, int level){
        this.characterID = characterID;
        this.name = name;
        this.characterClass = characterClass;
        this.level = level;
        Random rand = new Random();
        int randomNum = rand.nextInt(100000);
        inventory = new Inventory(String.valueOf(randomNum), this);
    }

    public String getCharacterID(){
        return characterID;
    }

    public String getCharacterName(){
        return name;
    }

    public String getCharacterClass(){
        return characterClass;
    }

    public int getLevel(){
        return level;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public String toString(){
        return this.name;
    }
}

//World Clock Singleton
class WorldClock{

    private static WorldClock INSTANCE = new WorldClock();

    private WorldClock(){
    }


    LocalDateTime getCurrentTime(){
        return LocalDateTime.now();
    }

    public static WorldClock getInstance(){
        return INSTANCE;
    }

}

interface TimeStrategy {
    LocalDateTime computeLocalTime(LocalDateTime baseTime);
}

class OffsetTimeStrategy implements TimeStrategy {

    private int days;
    private int hours;
    private int minutes;

    public OffsetTimeStrategy(int d, int h, int m) {
        this.days = d;
        this.hours = h;
        this.minutes = m;
    }

    public LocalDateTime computeLocalTime(LocalDateTime baseTime) {
        return baseTime
                .plusDays(days)
                .plusHours(hours)
                .plusMinutes(minutes);
    }
}

class Realm{
    private String realmID;
    private String name;
    private String description;
    private String mapId;
    private TimeStrategy timeStrategy;


    Realm(String realmID, String name, TimeStrategy timeStrategy){
        this.realmID = realmID;
        this.name = name;
        this.timeStrategy = timeStrategy;
    }

    public LocalDateTime getLocalTime() {
        LocalDateTime worldTime =
                WorldClock.getInstance().getCurrentTime();

        return timeStrategy.computeLocalTime(worldTime);
    }

    public String getRealmID(){
        return realmID;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public String getMapId(){
        return mapId;
    }

}


class QuestEvent{
    private String eventID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String name;
    private Realm realm;
    private String description;
    QuestEvent(String eventID, String name, Realm realm){
        WorldClock clock = WorldClock.getInstance();
        this.eventID = eventID;
        this.name = name;
        this.startTime = clock.getCurrentTime();
        this.realm = realm;
    }

    public String getEventID(){
        return eventID;
    };
    public LocalDateTime getStartTime(){
        return startTime;
    };
    public LocalDateTime getEndTime(){
        return endTime;
    };
    public String getName(){
        return name;
    };
    public Realm getRealm(){
        return realm;
    };
    public String getDescription(){
        return description;
    };

    public void setEventName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }


}


class userFactory{
    public static Character createCharacter(String characterID, String name, String characterClass, int level){
        return new Character(characterID, name, characterClass, level);
    }

    public static Campaign createCampaign(String campaignID, String name, User host){
        return new Campaign(campaignID, name, host);
    }
}

class Campaign {
    private String campaignID;
    private String name;
    private User host;
    private ArrayList<QuestEvent> questEvents;
    private LocalDateTime createdAt;


    Campaign(String campaignID, String name, User host) {
        this.campaignID = campaignID;
        this.name = name;
        this.host = host;
        this.questEvents = new ArrayList<QuestEvent>();
        createdAt = LocalDateTime.now();
    }

    public String getCampaignID(){
        return campaignID;
    };
    public String getName(){
        return name;
    };
    public User getHost(){
        return host;
    };
    public List<QuestEvent> getQuestEvents(){
        return Collections.unmodifiableList(questEvents);
    };
    public LocalDateTime getCreatedAt(){
        return createdAt;
    };

    public void setCampaignName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }

    void addQuestEvent(QuestEvent event){
        questEvents.add(event);
    }

    void deleteQuestEvent(String eventID){
        this.questEvents.removeIf(e -> e.getEventID().equals(eventID));
    }

    void updateEvent(String eventID, String newEventName){
        QuestEvent found = questEvents.stream()
                .filter(e -> e.getEventID().equals(eventID))
                .findFirst()
                .orElse(null);

        if (found != null) {
            found.setEventName(newEventName);
        }

    }

}



class User{
    private int userID;
    private String username;
    private ArrayList<Campaign> campaigns;
    private ArrayList<Character> characters;
    private String email;
    private String password;
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
        this.campaigns.removeIf(c -> c.getCampaignID().equals(campaignID));
    }

    void updateCampaign(String campaignID, String newCampaignName){
        Campaign found = campaigns.stream()
                .filter(c -> c.getCampaignID().equals(campaignID))
                .findFirst()
                .orElse(null);

        if (found != null) {
            found.setCampaignName(newCampaignName);
        }

    }

    public int getUserID(){
        return userID;
    }
    public String getUsername(){
        return username;
    }
    public List<Campaign> getCampaigns(){
        return Collections.unmodifiableList(campaigns);
    };
    public List<Character> getCharacters(){
        return Collections.unmodifiableList(characters);
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