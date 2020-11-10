package com.mohamed.stories.Model;

public class Stories {
    public int id;
    public String title,story;

    public Stories(int id, String title, String story) {
        this.id = id;
        this.title = title;
        this.story = story;
    }

    public Stories() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
