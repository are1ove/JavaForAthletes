package src;

public class ObjectsTable {
    String key,name,creator;
    int id;

    public ObjectsTable(int id, String key, String name, String creator) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
