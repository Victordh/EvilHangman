package com.example.bubbles.evilhangman;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.bubbles.evilhangman.Views.CrayonTextView;

import java.util.Set;

public class HighScoresActivity extends Activity {

    public static final String PREFERENCES_FILE_NAME = "settings";
    public SharedPreferences settings;
    SharedPreferences.Editor editor;

    TableLayout high_scores_table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        initialise();
        load_high_scores();
    }

    private void initialise() {
        settings = this.getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        high_scores_table = (TableLayout) findViewById(R.id.high_scores_table);
    }

    private void load_high_scores() {
        for(int i = 0; i < 9999; i++) {
            if (settings.getString(Integer.toString(i), null) != null) {
                String[] load = settings.getString(Integer.toString(i), null).split(",");
                TableRow inflate = (TableRow) View.inflate(this, R.layout.table_row, null);
                inflate.setTag(i);

                for(int j = 0; j < 5; j++) {
                    CrayonTextView view = (CrayonTextView) inflate.getChildAt(j);
                    view.setText(load[j]);
                }
                high_scores_table.addView(inflate);
            }
            else {
                break;
            }
        }
    }

    public void back_to_menu_button_click(View view) {
        Intent intent = new Intent(HighScoresActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    // clears all high scores and resets the table
    public void clear_high_scores_button_click(View view) {
        for(int i = 0; i < 9999; i++) {
            if (settings.getString(Integer.toString(i), null) != null) {
                editor = settings.edit();
                editor.remove(String.valueOf(i));
                editor.apply();
            }
            else {
                break;
            }
        }
        onCreate(new Bundle());
    }
}
