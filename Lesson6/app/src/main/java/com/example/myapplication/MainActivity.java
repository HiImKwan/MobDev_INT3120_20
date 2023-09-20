package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    Button button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        this.button1 = this.findViewById(R.id.button1);
        this.button2 = this.findViewById(R.id.button2);
        this.registerForContextMenu(this.button1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.option1) {
            Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
            return true;
        } else if (itemId == R.id.option2) {
            Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("Đây là Context menu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.layout_context_menu, menu);
    }
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.option1) {
            Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
        } else if (itemId == R.id.option2) {
            Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
        }
        return super.onContextItemSelected(item);
    }

    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, this.button2);
        popupMenu.inflate(R.menu.layout_popup_menu);
        Menu menu = popupMenu.getMenu();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
        popupMenu.show();
    }

}