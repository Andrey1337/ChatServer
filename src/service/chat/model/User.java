package service.chat.model;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public boolean isUserValid()
    {
        return (this.name.length() != 0 && this.name.length() < 10 );
    }

    public String getName() {
        return name;
    }
}
