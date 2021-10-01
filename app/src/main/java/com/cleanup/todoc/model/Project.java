package com.cleanup.todoc.model;


import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * <p>Models for project in which tasks are included.</p>
 *
 * @author Gaëtan HERFRAY
 */
@Entity(tableName = "project_table")
public class Project {
    /**
     * The unique identifier of the project
     */
    @PrimaryKey(autoGenerate = true)
    private final long id;

    /**
     * The name of the project
     */
    @NonNull
    private final String name;

    /**
     * The hex (ARGB) code of the color associated to the project
     */
    @ColorInt
    private final int color;

    /**
     * Instantiates a new Project.
     *
     * @param id    the unique identifier of the project to set
     * @param name  the name of the project to set
     * @param color the hex (ARGB) code of the color associated to the project to set
     */
    public Project(long id, @NonNull String name, @ColorInt int color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    /**
     * GETTERS
     */

    public long getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }


    @Override
    @NonNull
    public String toString() {
        return getName();
    }
}
