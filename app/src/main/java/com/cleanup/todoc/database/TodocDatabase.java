package com.cleanup.todoc.database;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.ProjectDataRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Project.class, Task.class},version = 1)
public abstract class TodocDatabase extends RoomDatabase {


    private static TodocDatabase instance;
    public static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

    public abstract ProjectDao projectDao();

    public abstract TaskDao taskDao();


    public static synchronized TodocDatabase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TodocDatabase.class,"todoc_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);


            ContentValues contentValuesProject1 = new ContentValues();
            contentValuesProject1.put("project_id", 0);
            contentValuesProject1.put("name", "Projet Tartampion");
            contentValuesProject1.put("color",  0xFFEADAD1);

            ContentValues contentValuesProject2 = new ContentValues();
            contentValuesProject2.put("project_id", 1);
            contentValuesProject2.put("name", "Projet Lucidia");
            contentValuesProject2.put("color",  0xFFB4CDBA);

            ContentValues contentValuesProject3 = new ContentValues();
            contentValuesProject3.put("project_id", 2);
            contentValuesProject3.put("name", "Projet Circus");
            contentValuesProject3.put("color",  0xFFA3CED2);

            ContentValues contentValuesTask1 = new ContentValues();
            contentValuesTask1.put("task_id", 1);
            contentValuesTask1.put("name", "Nettoyer les vitres");
            contentValuesTask1.put("projectId", 0);
            contentValuesTask1.put("creationTimestamp", 1000);

            ContentValues contentValuesTask2 = new ContentValues();
            contentValuesTask2.put("task_id", 2);
            contentValuesTask2.put("name", "Vider le lave vaisselle");
            contentValuesTask2.put("projectId", 1);
            contentValuesTask2.put("creationTimestamp", 1001);

            ContentValues contentValuesTask3 = new ContentValues();
            contentValuesTask3.put("task_id", 3);
            contentValuesTask3.put("name", "Arroser les plantes");
            contentValuesTask3.put("projectId", 2);
            contentValuesTask3.put("creationTimestamp", 1002);


            db.insert("project_table", OnConflictStrategy.IGNORE, contentValuesProject1);
            db.insert("project_table", OnConflictStrategy.IGNORE, contentValuesProject2);
            db.insert("project_table", OnConflictStrategy.IGNORE, contentValuesProject3);
            db.insert("task_table", OnConflictStrategy.IGNORE, contentValuesTask1);
            db.insert("task_table", OnConflictStrategy.IGNORE, contentValuesTask2);
            db.insert("task_table", OnConflictStrategy.IGNORE, contentValuesTask3);

        }
    };


}
