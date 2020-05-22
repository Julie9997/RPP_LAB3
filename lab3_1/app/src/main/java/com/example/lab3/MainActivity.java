package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    Button b3;

    SQLiteDatabase database;
    DatabaseHelper db;

    ArrayList<String> names;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.openDB);
        Button b2 = (Button) findViewById(R.id.addItem);
        Button b3 = (Button) findViewById(R.id.replace);

        db = new DatabaseHelper(this);
        database = db.getWritableDatabase();
        database.execSQL("DELETE FROM students");
        names = new ArrayList<>();

        info();

        db.close();

        b1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, ShowDB.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                database = db.getWritableDatabase();

                Random random = new Random();
                int number = random.nextInt(names.size());
                Date today = new Date();
                String time = sdf.format(today);

                String[] fio = names.get(number).split(" ");

                database.execSQL("INSERT INTO students(last_name, first_name, middle_name, time) " +
                        "VALUES (\'" + fio[0] + "\', \'" + fio[1] + "\', \'" + fio[2] + "\', \'" + time + "');");
                names.remove(number);

                db.close();

                Toast.makeText(getApplicationContext(), "Запись добавлена", Toast.LENGTH_SHORT).show();
            }
        });

        b3.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                database = db.getWritableDatabase();

                database.execSQL("UPDATE students SET last_name = 'Иванов', first_name = 'Иван', middle_name = 'Иванович' " +
                        "WHERE _id = (SELECT MAX(_id) FROM students)");

                db.close();

                Toast.makeText(getApplicationContext(), "Последняя запись изменена", Toast.LENGTH_SHORT).show();
            }
        });
    }
    void info()
    {
        names = new ArrayList<>();
        names.add("Аббасов Васиф Элшан Оглы");
        names.add("Бакулин Артём Александрович");
        names.add("Ванин Максим Валерьевич");
        names.add("Волкова Наталья Игоревна");
        names.add("Гармаш Юлия Валерьевна");
        names.add("Кашкина Кристина Викторовна");
        names.add("Квасов Алексей Владиславович");
        names.add("Колобаев Максим Александрович");
        names.add("Косандяк Семён Игоревич");
        names.add("Леонов Никита Алексеевич");
        names.add("Лосев Артём Олегович");
        names.add("Лукьянов Святослав Валерьевич");
        names.add("Матвеев Даниил Эдуардович");
        names.add("Невретдинова Диляра Фаиковна");
        names.add("Ненашев Иван Алексеевич");
        names.add("Петров Анатолий Валерьевич");
        names.add("Принтц Василий Георгиевич");
        names.add("Романченко Алексей Евгеньевич");
        names.add("Саляхутдинов Темур Рустамович");
        names.add("Сигалов Давид Игоревич");
        names.add("Соловьёв Илья Игоревич");
        names.add("Теймуров Теймур Октаевич");
        names.add("Ткаченко Димитрий Игоревич");
        names.add("Уфимцев Георгий Александрович");
        names.add("Фомина Юлия Игоревна");
        names.add("Шамкова Анна Андреевна");
        names.add("Шуралёв Сергей Игоревич");

        Random random = new Random();
        int number;

        for (int i = 0; i < 5; i++) {

            number = random.nextInt(names.size());

            Date today = new Date();
            String time = sdf.format(today);

            database.execSQL("INSERT INTO students(name, time) VALUES (\'" + names.get(number) + "\',\'" + time + "\');");
            names.remove(number);
        }
    }
}
