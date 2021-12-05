package com.cleanup.todoc.database;

import android.content.ContentValues;
import android.content.Context;

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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {


    private static TodocDatabase instance;
    private static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

    public abstract ProjectDao projectDao();

    public abstract TaskDao taskDao();


    public static synchronized TodocDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TodocDatabase.class, "todoc_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static final Callback roomCallback = new Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //Projects
            ContentValues contentValuesProject1 = new ContentValues();
            contentValuesProject1.put("project_id", 0);
            contentValuesProject1.put("name", "Projet Tartampion");
            contentValuesProject1.put("color", 0xFFEADAD1);

            ContentValues contentValuesProject2 = new ContentValues();
            contentValuesProject2.put("project_id", 1);
            contentValuesProject2.put("name", "Projet Lucidia");
            contentValuesProject2.put("color", 0xFFB4CDBA);

            ContentValues contentValuesProject3 = new ContentValues();
            contentValuesProject3.put("project_id", 2);
            contentValuesProject3.put("name", "Projet Circus");
            contentValuesProject3.put("color", 0xFFA3CED2);

            //Tasks
            ContentValues contentValuesTask1 = new ContentValues();
            contentValuesTask1.put("name", "Ajouter un header sur le site");
            contentValuesTask1.put("projectId", 0);
            contentValuesTask1.put("creationTimestamp", 0);

            ContentValues contentValuesTask2 = new ContentValues();
            contentValuesTask2.put("name", "Modifier la couleur des textes");
            contentValuesTask2.put("projectId", 1);
            contentValuesTask2.put("creationTimestamp", 1);

            ContentValues contentValuesTask3 = new ContentValues();
            contentValuesTask3.put("name", "Appeler le client");
            contentValuesTask3.put("projectId", 1);
            contentValuesTask3.put("creationTimestamp", 2);

            ContentValues contentValuesTask4 = new ContentValues();
            contentValuesTask4.put("name", "Intégrer Google Analytics");
            contentValuesTask4.put("projectId", 0);
            contentValuesTask4.put("creationTimestamp", 3);

            ContentValues contentValuesTask5 = new ContentValues();
            contentValuesTask5.put("name", "Ajouter un header sur le site");
            contentValuesTask5.put("projectId", 2);
            contentValuesTask5.put("creationTimestamp", 4);


            databaseWriteExecutor.execute(() -> {
                db.insert("project_table", OnConflictStrategy.IGNORE, contentValuesProject1);
                db.insert("project_table", OnConflictStrategy.IGNORE, contentValuesProject2);
                db.insert("project_table", OnConflictStrategy.IGNORE, contentValuesProject3);
                db.insert("task_table", OnConflictStrategy.IGNORE, contentValuesTask1);
                db.insert("task_table", OnConflictStrategy.IGNORE, contentValuesTask2);
                db.insert("task_table", OnConflictStrategy.IGNORE, contentValuesTask3);
                db.insert("task_table", OnConflictStrategy.IGNORE, contentValuesTask4);
                db.insert("task_table", OnConflictStrategy.IGNORE, contentValuesTask5);

            });


        }
    };


}
