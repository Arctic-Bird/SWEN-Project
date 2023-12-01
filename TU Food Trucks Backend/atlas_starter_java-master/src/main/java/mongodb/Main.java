package mongodb;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
  // a few pre-wired recipes we can insert into the database as examples.
  public static List<Recipe> recipes = Arrays.asList(
      new Recipe("elotes",
              Arrays.asList("corn", "mayonnaise", "cotija cheese", "sour cream", "lime" ),
              35),
      new Recipe("loco moco",
              Arrays.asList("ground beef", "butter", "onion", "egg", "bread bun", "mushrooms" ),
              54),
      new Recipe("patatas bravas",
              Arrays.asList("potato", "tomato", "olive oil", "onion", "garlic", "paprika" ),
              80),
      new Recipe("fried rice",
              Arrays.asList("rice", "soy sauce", "egg", "onion", "pea", "carrot", "sesame oil" ),
              40)
  );

  //just for insertion purposes, mimicking above
  public static List<Food> foods = Arrays.asList(
          new Food(new FoodTruck("Pizza Boys", "SomeNumber York Road", 4.9, 1234, "www.fakesite.com"), "Sausage Pizza", true, false, false, false, 11.77, false, false, false),
          new Food(new FoodTruck("Pizza Boys", "SomeNumber York Road", 4.9, 1234, "www.fakesite.com"), "Hawaiian Pizza", true, false, false, false, 11.77, false, false, false),
          new Food(new FoodTruck("Pizza Boys", "SomeNumber York Road", 4.9, 1234, "www.fakesite.com"), "Pepperoni Pizza", true, false, false, false, 11.77, false, false, false)
  );

  public static List<Beverages> beverages = Arrays.asList(
          new Beverages(new FoodTruck("Tea Time", "SomeNumber Cross Campus Boulevard", 4.8, 12345, "www.fakesite2.com"), "Boba", 3.50, true),
          new Beverages(new FoodTruck("Tea Time", "SomeNumber Cross Campus Boulevard", 4.8, 12345, "www.fakesite2.com"), "Kombucha", 4.77, false),
          new Beverages(new FoodTruck("Third Truck", "SomeNumber Towsontown Boulevard", 3.9, 247, "www.fakesite3.com"), "Iced Tea", 4.12, false)
  );

  public static List<FoodTruck> trucks = Arrays.asList(
          new FoodTruck("Pizza Boys", "SomeNumber York Road", 4.9, 1234, "www.fakesite.com"),
          new FoodTruck("Tea Time", "SomeNumber Cross Campus Boulevard", 4.8, 12345, "www.fakesite2.com"),
          new FoodTruck("Third Truck", "SomeNumber Towsontown Boulevard", 3.9, 247, "www.fakesite3.com")
  );

  public static List<Users> users = Arrays.asList(
          new Users("user1", "don't_use_this_as_a_password"),
          new Users("user2", "don't_use_this_as_a_password_either"),
          new Users("user3", "really_choom_?")
  );

  public static void main(String[] args) {
    //beautiful monstrosity that is MongoDB
    Logger.getLogger( "org.mongodb.driver" ).setLevel(Level.WARNING);
    // TODO:
    // Replace the placeholder connection string below with your
    // Altas cluster specifics. Be sure it includes
    // a valid username and password! Note that in a production environment,
    // you do not want to store your password in plain-text here. "or do I?"
    ConnectionString mongoUri = new ConnectionString("mongodb+srv://mjharris581:d6Fp3xQ9Oa0hCGGY@tufoodtruckscluster.jx6fwbp.mongodb.net/");

    // Provide the name of the database and collection you want to use.
    // If they don't already exist, the driver and Atlas will create them
    // automatically when you first write data.
    String dbName = "myDatabase";
    String collectionName = "recipes";

    //separate database and sub-collection we'll make
    String dbName2 = "TUFoodTrucksDB";
    String foodCollectionName = "food";
    String beveragesCollectionName = "beverages";
    String truckCollectionName = "truck";
    String userCollectionName = "user";

    //we'll try to redo the CodecRegistry stuff here.
    //CodecRegistry necessary to convert Java classes to BSON objects to be viewable in this database.

    CodecRegistry pojoCodecRegistry = fromRegistries(CodecRegistries.fromCodecs(new FoodCodec(), new BeverageCodec(), new TruckCodec(), new UserCodec()), MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));


    // a CodecRegistry tells the Driver how to move data between Java POJOs (Plain Old Java Objects) and MongoDB documents
    //CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
    //        fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    // The MongoClient defines the connection to our MongoDB datastore instance (Atlas) using MongoClientSettings
    // You can create a MongoClientSettings with a Builder to configure codecRegistries, connection strings, and more
    MongoClientSettings settings = MongoClientSettings.builder()
            .codecRegistry(pojoCodecRegistry)
            .applyConnectionString(mongoUri).build();

    MongoClient mongoClient = null;
    try {
       mongoClient = MongoClients.create(settings);
    } catch (MongoException me) {
      System.err.println("Unable to connect to the MongoDB instance due to an error: " + me);
      System.exit(1);
    }

    // MongoDatabase defines a connection to a specific MongoDB database
    MongoDatabase database = mongoClient.getDatabase(dbName);

    // MongoCollection defines a connection to a specific collection of documents in a specific database
    MongoCollection<Recipe> collection = database.getCollection(collectionName, Recipe.class);

    //meddling
    MongoDatabase full_database = mongoClient.getDatabase(dbName2);
    MongoCollection<Food> food_collection = full_database.getCollection(foodCollectionName, Food.class);
    MongoCollection<Beverages> beverages_collection = full_database.getCollection(beveragesCollectionName, Beverages.class);
    MongoCollection<FoodTruck> truck_collection = full_database.getCollection(truckCollectionName, FoodTruck.class);
    MongoCollection<Users> user_collection = full_database.getCollection(userCollectionName, Users.class);



    /*      *** INSERT DOCUMENTS ***
     *
     * You can insert individual documents using collection.insert().
     * In this example, we're going to create 4 documents and then
     * insert them all in one call with insertMany().
     */

    System.out.println("We made it.");

    try {
      // recipes is a static variable defined above
      InsertManyResult result = collection.insertMany(recipes);
      System.out.println("Inserted " + result.getInsertedIds().size() + " documents.\n");
    } catch (MongoException me) {
      System.err.println("Unable to insert any recipes into MongoDB due to an error: " + me);
      System.exit(1);
    }

    System.out.println("They made it.");

    //Goal: add Food to the database.
    //more meddling hehe; error here but why are we reaching unhandled exception?
    try {
      InsertManyResult food_result = food_collection.insertMany(foods);
      System.out.println("Inserted " + food_result.getInsertedIds().size() + " documents.\n");
    } catch (MongoException me) {
      System.err.println("Unable to insert any recipes into MongoDB due to an error: " + me);
      System.exit(1);
    }

    //Goal: add Beverages to the database
    try {
      InsertManyResult beverages_result = beverages_collection.insertMany(beverages);
      System.out.println("Inserted " + beverages_result.getInsertedIds().size() + " documents.\n");
    } catch (MongoException me) {
      System.err.println("Unable to insert any recipes into MongoDB due to an error: " + me);
      System.exit(1);
    }

    try {
      InsertManyResult truck_result = truck_collection.insertMany(trucks);
      System.out.println("Inserted " + truck_result.getInsertedIds().size() + " documents.\n");
    } catch (MongoException me) {
      System.err.println("Unable to insert any recipes into MongoDB due to an error: " + me);
      System.exit(1);
    }

    try {
      InsertManyResult user_result = user_collection.insertMany(users);
      System.out.println("Inserted " + user_result.getInsertedIds().size() + " documents.\n");
    } catch (MongoException me) {
      System.err.println("Unable to insert any recipes into MongoDB due to an error: " + me);
      System.exit(1);
    }

    System.out.println("Errybody made it.");

    /*      *** FIND DOCUMENTS ***
     *
     * Now that we have data in Atlas, we can read it. To retrieve all of
     * the data in a collection, we call find() with an empty filter. We can
     * retrieve an iterator to return the results from our call to the find()
     * method. Here we use the try-with-resources pattern to automatically
     * close the cursor once we finish reading the recipes.
     */

    try (MongoCursor<Recipe> cursor = collection.find().iterator()) {
      while (cursor.hasNext()) {
        Recipe currentRecipe = cursor.next();
        System.out.printf("%s has %d ingredients and takes %d minutes to make\n", currentRecipe.getName(), currentRecipe.getIngredients().size(), currentRecipe.getPrepTimeInMinutes());
      }
    } catch (MongoException me) {
      System.err.println("Unable to find any recipes in MongoDB due to an error: " + me);
    }

    // We can also find a single document. Let's find the first document
    // that has the string "potato" in the ingredients list. We
    // use the Filters.eq() method to search for any values in any
    // ingredients list that match the string "potato":

    Bson findPotato = Filters.eq("ingredients", "potato");
    try {
      Recipe firstPotato = collection.find(findPotato).first();
      if (firstPotato == null) {
        System.out.println("Couldn't find any recipes containing 'potato' as an ingredient in MongoDB.");
        System.exit(1);
      }
    } catch (MongoException me) {
      System.err.println("Unable to find a recipe to update in MongoDB due to an error: " + me);
      System.exit(1);
    }

    /*      *** UPDATE A DOCUMENT ***
     *
     * You can update a single document or multiple documents in a single call.
     *
     * Here we update the PrepTimeInMinutes value on the document we
     * just found.
     */
    Bson updateFilter = Updates.set("prepTimeInMinutes", 72);

    // The following FindOneAndUpdateOptions specify that we want it to return
    // the *updated* document to us. By default, we get the document as it was *before*
    // the update.
    FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);

    // The updatedDocument object is a Recipe object that reflects the
    // changes we just made.
    try {
      Recipe updatedDocument = collection.findOneAndUpdate(findPotato,
              updateFilter, options);
      if (updatedDocument == null) {
        System.out.println("Couldn't update the recipe. Did someone (or something) delete it?");
      } else {
        System.out.println("\nUpdated the recipe to: " + updatedDocument);
      }
    } catch (MongoException me) {
      System.err.println("Unable to update any recipes due to an error: " + me);
    }

    /*      *** DELETE DOCUMENTS ***
     *
     *      As with other CRUD methods, you can delete a single document
     *      or all documents that match a specified filter. To delete all
     *      of the documents in a collection, pass an empty filter to
     *      the deleteMany() method. In this example, we'll delete 2 of
     *      the recipes.
     */
    Bson deleteFilter = Filters.in("name", Arrays.asList("elotes", "fried rice"));
    try {
      DeleteResult deleteResult = collection
              .deleteMany(deleteFilter);
      System.out.printf("\nDeleted %d documents.\n", deleteResult.getDeletedCount());
    } catch (MongoException me) {
      System.err.println("Unable to delete any recipes due to an error: " + me);
    }

    // always close the connection when done working with the client
    mongoClient.close();
  }

  // POJO (Plain Old Java Object) class defining a recipe. This class is a POJO because it contains getters and
  // setters for every member variable as well as an empty constructor.
  public static class Recipe {
    private String name;
    private List<String> ingredients;
    private int prepTimeInMinutes;

    public Recipe(String name, List<String> ingredients, int prepTimeInMinutes) {
      this.name = name;
      this.ingredients = ingredients;
      this.prepTimeInMinutes = prepTimeInMinutes;
    }

    // empty constructor required when we fetch data from the database -- getters and setters are later used to
    // set values for member variables
    public Recipe() {
      ingredients = new ArrayList<String>();
      name = "";
    }

    @Override
    public String toString() {
      final StringBuffer sb = new StringBuffer("Recipe{");
      sb.append("name=").append(name);
      sb.append(", ingredients=").append(ingredients);
      sb.append(", prepTimeInMinutes=").append(prepTimeInMinutes);
      sb.append('}');
      return sb.toString();
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public List<String> getIngredients() {
      return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
      this.ingredients = ingredients;
    }

    public int getPrepTimeInMinutes() {
      return prepTimeInMinutes;
    }

    public void setPrepTimeInMinutes(int prepTimeInMinutes) {
      this.prepTimeInMinutes = prepTimeInMinutes;
    }
  }

  //Object: FoodTruck

  public static class FoodTruck{
    private static String name;
    private String location; //if we have time, we'll try to do something cool with Google Maps API (ambitious)
    private double rating;
    private long schedule; //depends on import java.time.LocalDateTime; List can have two diff times. Odd is opening, even is closing.
    private String website;

    //private Food food;
    //private Beverages beverages;



    public FoodTruck (String name, String location, double rating, long schedule, String website) {
      this.name = name;
      this.location = location;
      this.rating = rating;
      this.schedule = schedule;
      this.website = website;

      //maybe add , Food food, Beverages beverages back to constructor later to serve as Menu workaround
      //this.food = food;
      //this.beverages = beverages;
    }

    public FoodTruck(){}

    //public void setMenu(List<Food> foodItems, List<Beverages> beverages) {
      //for compound items like this, how do we set to them in other methods
      //this.menu = new Menu(foodItems, beverages);
    //}

    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setSchedule(long schedule){
        this.schedule = schedule;
    }

    public void setWebsite(String website) {
      this.website = website;
    }

    public String getLocation() {
      return location;
    }

    public double getRating() {
      return rating;
    }

    public long getSchedule() {
      return schedule;
    }

    public String getWebsite() {
      return website;
    }

    //may keep these Food and Beverages methods, but they will not be involved with the database
    //public Food getFood() { return food; }
    //public void setFood(Food food) { this.food = food; }

    //public Beverages getBeverages() { return beverages; }
    //public void setBeverages(Beverages beverages) { this.beverages = beverages; }
  }

  //Codec: FoodTruck

  public static class TruckCodec implements Codec<FoodTruck>{
    @Override
    public FoodTruck decode(BsonReader reader, DecoderContext decoderContext) {
      FoodTruck truck = new FoodTruck();
      reader.readStartDocument();
      while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
        String readerItem = reader.readName();
        if (readerItem.equals("name")) {
          truck.setName(reader.readString());
        } else if (readerItem.equals("location")) {
          truck.setLocation(reader.readString());
        } else if (readerItem.equals("rating")) {
          truck.setRating(reader.readDouble());
        } else if (readerItem.equals("schedule")) {
          truck.setSchedule(reader.readDateTime());
        } else if (readerItem.equals("website")) {
          truck.setWebsite(reader.readString());
        } else {
          reader.skipValue();
        }
      }
      reader.readEndDocument();
      return truck;
    }

    @Override
    public void encode(BsonWriter writer, FoodTruck truck, EncoderContext encoderContext) {
      writer.writeStartDocument();
      writer.writeString("name", truck.getName());
      writer.writeString("location", truck.getLocation());
      writer.writeDouble("rating", truck.getRating());
      writer.writeDateTime("schedule", truck.getSchedule());
      writer.writeString("website", truck.getWebsite());
      writer.writeEndDocument();
    }


    @Override
    public Class<FoodTruck> getEncoderClass() {
      return FoodTruck.class;
    }
  }

  //Object: Users

  public static class Users{
    String username;
    String password;

    public Users (String username, String password) {
      this.username = username;
      this.password = password;
    }

    public Users (){}


    public String getUsername() {
      return username;
    }
    public void setUsername(String username) {
      this.username = username;
    }

    public String getPassword() {
      return password;
    }
    public void setPassword(String password) {
      this.password = password;
    }

  }

  //Codec: Users

  public static class UserCodec implements Codec<Users>{
    @Override
    public Users decode(BsonReader reader, DecoderContext decoderContext) {
      Users user = new Users();
      reader.readStartDocument();
      while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
        String readerItem = reader.readName();
        if (readerItem.equals("username")) {
          //Users.setUsername(reader.readString());
        } else if (readerItem.equals("password")) {
          //Users.setPassword(reader.readString());
        } else {
          reader.skipValue();
        }
      }
      reader.readEndDocument();
      return user;
    }

    @Override
    public void encode(BsonWriter writer, Users user, EncoderContext encoderContext) {
      writer.writeStartDocument();
      writer.writeString("username", user.getUsername());
      writer.writeString("password", user.getPassword());
      writer.writeEndDocument();
    }

    @Override
    public Class<Users> getEncoderClass() {
      return Users.class;
    }
  }

  //Object: Food

  public static class Food {
    //may need to add FoodTruck attribute so each food is linked with a FoodTruck instead of floating in space
    String provider;
    String name;
    boolean isMeat;
    boolean isVegetarian;
    boolean isVegan;
    boolean isSeafood;
    double cost;
    boolean isOnPromo;
    boolean isFish;
    boolean isSide;
    public Food (FoodTruck provider, String name, boolean isMeat, boolean isVegetarian, boolean isVegan, boolean isSeafood, double cost, boolean isOnPromo, boolean isFish, boolean isSide) {
      this.provider = provider.getName();
      this.name = name;
      this.isMeat = isMeat;
      this.isVegetarian = isVegetarian;
      this.isVegan = isVegan;
      this.isSeafood = isSeafood;
      this.cost = cost;
      this.isOnPromo = isOnPromo;
      this.isFish = isFish;
      this.isSide = isSide;
    }

    public Food(){}

    public String getProvider() { return provider; }
    public void setProvider() { provider = provider.toString(); } //question this

    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }

    public boolean getMeat() {
      return isMeat;
    }
    public void setMeat(boolean isMeat) {
      this.isMeat = isMeat;
    }

    public boolean getVegetarian() {
      return isVegetarian;
    }
    public void setVegetarian(boolean isVegetarian) { this.isVegetarian = isVegetarian;}

    public boolean getVegan() { return isVegan;}
    public void setVegan(boolean isVegan) { this.isVegan = isVegan;}

    public boolean getSeafood() { return isSeafood;}
    public void setSeafood(boolean isSeafood) { this.isSeafood = isSeafood;}

    public double getCost() {
      return cost;
    }
    public void setCost(double cost) {
      this.cost = cost;
    }

    public boolean getPromo() { return isOnPromo;}
    public void setPromo(boolean isOnPromo) { this.isOnPromo = isOnPromo;}

    public boolean getFish() { return isFish;}
    public void setFish(boolean isFish) { this.isFish = isFish;}

    public boolean getSide() { return isSide;}
    public void setSide(boolean isSide) { this.isSide = isSide;}
  }


  //Codec: Food

  public static class FoodCodec implements Codec<Food>{
    @Override
    public Food decode(BsonReader reader, DecoderContext decoderContext) {
      Food food = new Food();
      reader.readStartDocument();
      while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
        String readerItem = reader.readName();
        if (readerItem.equals("name")) {
          food.setName(reader.readString());
        } else if (readerItem.equals("isMeat")) {
          food.setMeat(reader.readBoolean());
        } else if (readerItem.equals("isVegetarian")) {
          food.setVegetarian(reader.readBoolean());
        } else if (readerItem.equals("isVegan")) {
          food.setVegan(reader.readBoolean());
        } else if (readerItem.equals("isSeafood")) {
          food.setSeafood(reader.readBoolean());
        } else if (readerItem.equals("cost")) {
          food.setCost(reader.readDouble());
        } else if (readerItem.equals("isOnPromo")) {
          food.setPromo(reader.readBoolean());
        } else if (readerItem.equals("isFish")) {
          food.setFish(reader.readBoolean());
        } else if (readerItem.equals("isSide")) {
          food.setSide(reader.readBoolean());
        } else {
          reader.skipValue();
        }
      }
      reader.readEndDocument();
      return food;
    }

    @Override
    public void encode(BsonWriter writer, Food food, EncoderContext encoderContext) {
      writer.writeStartDocument();
      writer.writeString("provider", food.getProvider());
      writer.writeString("name", food.getName());
      writer.writeBoolean("isMeat", food.getMeat());
      writer.writeBoolean("isVegetarian", food.getVegetarian());
      writer.writeBoolean("isVegan", food.getVegan());
      writer.writeBoolean("isSeafood", food.getSeafood());
      writer.writeDouble("cost", food.getCost());
      writer.writeBoolean("isOnPromo", food.getPromo());
      writer.writeBoolean("isFish", food.getFish());
      writer.writeBoolean("isSide", food.getSide());
      writer.writeEndDocument();
    }

    @Override
    public Class<Food> getEncoderClass() {
      return Food.class;
    }
  }

  //Object: Beverages

  public static class Beverages{
    private String provider;
    private String name;
    boolean isOnPromo;
    double cost;

    public Beverages (FoodTruck provider, String name, double cost, boolean isOnPromo) {
      this.provider = provider.getName();
      this.name = name;
      this.cost = cost;
      this.isOnPromo = isOnPromo;
    }

    public Beverages(){}

    public String getProvider() { return provider; }
    public void setProvider() { provider = provider.toString(); } //question this

    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }

    public double getCost() {
      return cost;
    }
    public void setCost(double cost) { this.cost = cost; }

    public boolean getPromo() { return isOnPromo;}
    public void setPromo(boolean isOnPromo) { this.isOnPromo = isOnPromo;}
  }

  //Codec: Beverages

  public static class BeverageCodec implements Codec<Beverages>{
    @Override
    public Beverages decode(BsonReader reader, DecoderContext decoderContext) {
      Beverages beverage = new Beverages();
      reader.readStartDocument();
      while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
        String readerItem = reader.readName();
        if (readerItem.equals("provider")) {
          beverage.setProvider();
        } else if (readerItem.equals("name")) {
          beverage.setName(reader.readString());
        } else if (readerItem.equals("cost")) {
          beverage.setCost(reader.readDouble());
        } else if (readerItem.equals("isOnPromo")) {
          beverage.setPromo(reader.readBoolean());
        } else {
          reader.skipValue();
        }
      }
      reader.readEndDocument();
      return beverage;
    }

    @Override
    public void encode(BsonWriter writer, Beverages beverage, EncoderContext encoderContext) {
      writer.writeStartDocument();
      writer.writeString("provider", beverage.getProvider());
      writer.writeString("name", beverage.getName());
      writer.writeDouble("cost", beverage.getCost());
      writer.writeBoolean("isOnPromo", beverage.getPromo());
      writer.writeEndDocument();
    }

    @Override
    public Class<Beverages> getEncoderClass() {
      return Beverages.class;
    }
  }

  //POJO: Menu

  //public static class Menu{
    //private List<Food> MenuFood;
    //private List<Beverages> MenuBeverages;

    //public Menu (List<Food> food, List<Beverages> beverages) {
    //  this.MenuFood = food;
    //  this.MenuBeverages = beverages;
    //}

    //public Menu(){}

    //public List<Food> getFood() {
    //  return MenuFood;
    }
    //public void setFood(List<Food> MenuFood) {this.MenuFood = MenuFood;}

    //public List<Beverages> getBeverages() {
    //  return MenuBeverages;
    //}
    //public void setBeverages(List<Beverages> MenuBeverages) {
    //  this.MenuBeverages = MenuBeverages;
    //}
  /*
  }
  Codec: Menu
  public static class MenuCodec implements Codec<Menu>{
    @Override
    public Menu decode(BsonReader reader, DecoderContext decoderContext) {
      Menu menu = new Menu();
      reader.readStartDocument();
      while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
        String readerItem = reader.readName();
        if (readerItem.equals("foodItems")) {
          //menu.setFood(reader.readObjectId());
        } else if (readerItem.equals("beverages")) {
  menu.setBeverages(reader.readObjectId());
        } else {
          reader.skipValue();
       }
     }
     reader.readEndDocument();
     return menu;
   }
   @Override
   public void encode(BsonWriter writer, Menu menu, EncoderContext encoderContext) {
     writer.writeStartDocument();
     //writer.writeObjectId(menu, "food", menu.getFood());
     //writer.writeObject(menu, "beverages", menu.getBeverages());
     writer.writeEndDocument();
   }
  @Override
  public Class<Menu> getEncoderClass() {
    return Menu.class;
  }
  */

    //          }
//}
