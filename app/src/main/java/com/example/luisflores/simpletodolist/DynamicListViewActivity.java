package com.example.luisflores.simpletodolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class DynamicListViewActivity extends AppCompatActivity {

    private EditText item;
    private Button add;
    private ListView dynamicListView;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_list_view);

        //Get references to UI elements
        item = (EditText) findViewById(R.id.itemEditText);
        add = (Button) findViewById(R.id.addItemButton);
        dynamicListView = (ListView) findViewById(R.id.itemsListView);

        //initialize the list and add item
        list = new ArrayList<>();

        //initilize the array adapter
        adapter = new ArrayAdapter<String>(DynamicListViewActivity.this, android.R.layout.simple_list_item_1, list);

        //setting the adapter to the listview
        dynamicListView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String todoItem = item.getText().toString();
                if (todoItem.length() > 0) {
                    //add editText value to the list
                    list.add(item.getText().toString());
                    //refresh the listView
                    adapter.notifyDataSetChanged();
                    //clear the editText for the new Item
                    item.setText("");
                }
            }
        });

        //delete item on the long click
        dynamicListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                //remove the item from list
                list.remove(position);
                //apply changes to the adapter to refresh the listview
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
