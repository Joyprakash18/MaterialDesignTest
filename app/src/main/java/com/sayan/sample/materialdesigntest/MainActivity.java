package com.sayan.sample.materialdesigntest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = findViewById(R.id.grid);
        String[] buttons = {"TextField", "fetch credentials", "InflaterTest"};
        gridView.setNumColumns(3);
        gridView.setAdapter(new MyGridAdapter(this, buttons));
    }

    static class MyGridAdapter extends BaseAdapter {

        private final MainActivity mainActivity;
        private final String[] buttons;

        public MyGridAdapter(MainActivity mainActivity, String[] buttons) {

            this.mainActivity = mainActivity;
            this.buttons = buttons;
        }

        @Override
        public int getCount() {
            return buttons.length;
        }

        @Override
        public Object getItem(int position) {
            return buttons[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = mainActivity.getLayoutInflater().inflate(R.layout.main_grid_child, parent, false);
            String item = (String) getItem(position);
            Button gridChildButton = view.findViewById(R.id.gridChildButton);
            gridChildButton.setText(item);
            gridChildButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case 0:
                            toNextPage(TextFieldActivity.class);
                            break;
                        case 1:
                            toNextPage(FetchCredentials.class);
                            break;
                        case 2:
                            toNextPage(InflaterTest.class);
                            break;
                        default:
                            break;
                    }

                }
            });
            return view;
        }

        private void toNextPage(Class<?> activityClass) {
            Intent intent = new Intent(mainActivity, activityClass);
            mainActivity.startActivity(intent);
        }
    }
}
