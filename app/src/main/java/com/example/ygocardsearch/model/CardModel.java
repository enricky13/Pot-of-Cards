package com.example.ygocardsearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class CardModel implements Serializable {
    /**
     * Name: Name of Card
     * Type: Ex: Normal Monster, Effect Monster
     * desc: Description of Card
     * atk: Attack of card
     * def: Defense of card
     * level: Shows level of card even if it is another non level type, look up xyz monster or link monster
     * race: Ex: Machine, Dragon, Normal (For Spell of Trap)
     * archetype: What category of cards this card belongs to; ie: Cyber Dragon, Greed, Blue Eyes
     * image_url: Normal Image reference
     * image_url: Smaller picture of card
     */
    private String name, type, desc, atk, def, level, race, attribute, archetype, image_url,image_url_small;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getAtk() {
        return atk;
    }

    public String getDef() {
        return def;
    }

    public String getLevel() {
        return level;
    }

    public String getRace() {
        return race;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getArchetype() {
        return archetype;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getImage_url_small() {
        return image_url_small;
    }

}
