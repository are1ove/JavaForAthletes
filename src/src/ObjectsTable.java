package src;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectsTable that = (ObjectsTable) o;
        return id == that.id &&
                Objects.equals(key, that.key) &&
                Objects.equals(name, that.name) &&
                Objects.equals(creator, that.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, name, creator, id);
    }

    @Override
    public String toString() {
        return "ObjectsTable{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", id=" + id +
                '}';
    }
}
