package com.example.ireneserrano35.pruebabq;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.evernote.client.android.EvernoteSession;
import com.evernote.client.android.asyncclient.EvernoteCallback;
import com.evernote.client.android.asyncclient.EvernoteNoteStoreClient;
import com.evernote.edam.type.Notebook;

import java.util.ArrayList;
import java.util.List;


public class NotesActivity extends ActionBarActivity {

    private static final String CONSUMER_KEY = "ireneserrano35";
    private static final String CONSUMER_SECRET = "56d836650a104ab0";

    private static final EvernoteSession.EvernoteService EVERNOTE_SERVICE = EvernoteSession.EvernoteService.SANDBOX;
    private static final boolean SUPPORT_APP_LINKED_NOTEBOOKS = true;
    ArrayAdapter<String> adaptador;
    List<String> namesList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        new EvernoteSession.Builder(this)
                .setEvernoteService(EVERNOTE_SERVICE)
                .setSupportAppLinkedNotebooks(SUPPORT_APP_LINKED_NOTEBOOKS)
                .build(CONSUMER_KEY, CONSUMER_SECRET)
                .asSingleton();

        ListView notebooksListView = (ListView) findViewById(R.id.ListaNotas);

        if (!EvernoteSession.getInstance().isLoggedIn()) {
            return;
        }

        EvernoteNoteStoreClient noteStoreClient = EvernoteSession.getInstance().getEvernoteClientFactory().getNoteStoreClient();
        noteStoreClient.listNotebooksAsync(new EvernoteCallback<List<Notebook>>() {
            @Override
            public void onSuccess(List<Notebook> result) {
                if (result.size() > 0) {
                    for (Notebook notebook : result) {
                        namesList.add(notebook.getName());
                    }
                    String notebookNames = TextUtils.join(", ", namesList);
                    adaptador.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), notebookNames + " notebooks have been retrieved", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No notassss!!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onException(Exception exception) {
                //Log.e(LOGTAG, "Error retrieving notebooks", exception);
                int a =0;
            }
        });

        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, namesList);
        notebooksListView.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    public
    EvernoteNoteStoreClient noteStoreClient = EvernoteSession.getInstance().getEvernoteClientFactory().getNoteStoreClient();
    noteStoreClient.listNotebooksAsync(new EvernoteCallback<List<Notebook>>() {
        @Override
        public void onSuccess(List<Notebook> result) {
            List<String> namesList = new ArrayList<>(result.size());
            for (Notebook notebook : result) {
                namesList.add(notebook.getName());
            }
            String notebookNames = TextUtils.join(", ", namesList);
            Toast.makeText(getApplicationContext(), notebookNames + " notebooks have been retrieved", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onException(Exception exception) {
            Log.e(LOGTAG, "Error retrieving notebooks", exception);
        }
    });*/
}
