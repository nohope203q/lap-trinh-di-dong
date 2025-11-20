package com.haoht.ex4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull; // Cần thiết cho onOptionsItemSelected
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.haoht.ex4.adapters.NotesAdapter;
import com.haoht.ex4.data.DatabaseHandler;
import com.haoht.ex4.model.NotesModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler databaseHandler;
    ListView listView;
    ArrayList<NotesModel> arrayList;
    NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });



        InitDatabaseSQLite();

        listView = findViewById(R.id.listView1);
        arrayList = new ArrayList<>();
        adapter = new NotesAdapter(MainActivity.this, R.layout.row_notes, arrayList);
        listView.setAdapter(adapter);
        DatabaseSQLite();
    }

    private void CreateDatabase() {
        databaseHandler.QueryDatabase("INSERT INTO Notes VALUES (null, 'This is the first note')");
        databaseHandler.QueryDatabase("INSERT INTO Notes VALUES (null, 'This is the second note')");
        databaseHandler.QueryDatabase("INSERT INTO Notes VALUES (null, 'This is the third note')");
    }

    private void InitDatabaseSQLite() {
        databaseHandler = new DatabaseHandler(this, "NotesDB", null, 1);
        databaseHandler.QueryDatabase("CREATE TABLE IF NOT EXISTS Notes (Id INTEGER PRIMARY KEY AUTOINCREMENT, NameNotes VARCHAR(200))");
    }


    public void DatabaseSQLite(){
        Cursor cursor = databaseHandler.GetData("SELECT * FROM Notes");
        arrayList.clear();
        try {
            while(cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                arrayList.add(new NotesModel(id, name));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAddNotes) {
            DialogThem();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void DialogThem() {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_notes);

        final EditText editText = dialog.findViewById(R.id.editTextName);
        Button buttonAdd = dialog.findViewById(R.id.buttonAdd);
        Button buttonHuy = dialog.findViewById(R.id.buttonHuy);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString().trim();
                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên Notes", Toast.LENGTH_SHORT).show();
                } else {
                    databaseHandler.QueryDatabase("INSERT INTO Notes VALUES (null, '" + name + "')");
                    Toast.makeText(MainActivity.this, "Đã thêm Notes", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    DatabaseSQLite();
                }
            }
        });

        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void DialogCapNhatNotes(String name, int id) {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.update_notes);

        final EditText editText = dialog.findViewById(R.id.editTextName);
        Button buttonEdit = dialog.findViewById(R.id.buttonAdd);
        Button buttonHuy = dialog.findViewById(R.id.buttonHuy);

        editText.setText(name);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = editText.getText().toString().trim();

                if (newName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Tên ghi chú không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                String sql = "UPDATE Notes SET NameNotes = '" + newName + "' WHERE Id = " + id;
                databaseHandler.QueryDatabase(sql);

                Toast.makeText(MainActivity.this, "Đã cập nhật Notes thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

                DatabaseSQLite();
            }
        });

        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void DialogDelete(String name, final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("Bạn có muốn xóa Notes \"" + name + "\" này không?");

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sql = "DELETE FROM Notes WHERE Id = " + id;
                databaseHandler.QueryDatabase(sql);

                Toast.makeText(MainActivity.this, "Đã xóa Notes " + name + " thành công", Toast.LENGTH_SHORT).show();

                DatabaseSQLite();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.show();
    }
}