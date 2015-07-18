    package com.example.ireneserrano35.pruebabq;

    import android.app.FragmentManager;
    import android.app.FragmentTransaction;
    import android.content.Intent;
    import android.app.Fragment;
    import android.support.v7.app.ActionBarActivity;
    import android.os.Bundle;
    import android.text.TextUtils;
    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.ListView;
    import android.widget.Toast;

    import com.evernote.client.android.EvernoteSession;
    import com.evernote.client.android.asyncclient.EvernoteCallback;
    import com.evernote.client.android.asyncclient.EvernoteNoteStoreClient;
    import com.evernote.edam.type.Note;
    import com.evernote.edam.type.Notebook;

    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.Collections;
    import java.util.Comparator;
    import java.util.List;


    public class NotesActivity extends ActionBarActivity {

        private static final String CONSUMER_KEY = "ireneserrano35";
        private static final String CONSUMER_SECRET = "56d836650a104ab0";

        private static final EvernoteSession.EvernoteService EVERNOTE_SERVICE = EvernoteSession.EvernoteService.SANDBOX;
        private static final boolean SUPPORT_APP_LINKED_NOTEBOOKS = true;

        ListView notebooksListView;
        ArrayAdapter<String> adaptador;
        List<String> namesList = new ArrayList<String>();
        List<Notebook> notebookList = new ArrayList<Notebook>();
        private Button btnNew;
        private Button btnNewNb;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_notes);

            new EvernoteSession.Builder(this)
                    .setEvernoteService(EVERNOTE_SERVICE)
                    .setSupportAppLinkedNotebooks(SUPPORT_APP_LINKED_NOTEBOOKS)
                    .build(CONSUMER_KEY, CONSUMER_SECRET)
                    .asSingleton();

            notebooksListView = (ListView) findViewById(R.id.ListaNotas);

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
                            notebookList.add(notebook);
                        }
                        String notebookNames = TextUtils.join(", ", namesList);
                        adaptador.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), notebookNames + " notebooks have been retrieved", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No notebooks.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onException(Exception exception) {
                    Toast.makeText(getApplicationContext(), "Error retrieving notebooks.", Toast.LENGTH_LONG).show();
                }
            });

            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namesList);
            notebooksListView.setAdapter(adaptador);

            btnNew = (Button) findViewById(R.id.btn_new_note);
            btnNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(NotesActivity.this, CreateNote.class);
                    startActivity(intent);

                }
            });

            btnNewNb = (Button) findViewById(R.id.btn_new_notebook);
            btnNewNb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(NotesActivity.this, CreateNotebook.class);
                    startActivity(intent);

                }
            });
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
            if (id == R.id.action_order_by_name) {

                Collections.sort(namesList, String.CASE_INSENSITIVE_ORDER);
                notebooksListView.setAdapter(adaptador);
                adaptador.notifyDataSetChanged();
                return true;
            }
            else if (id == R.id.action_order_by_date) {
                List<String> result = new ArrayList<String>();
                Collections.sort(notebookList, new Comparator<Notebook>() {
                    @Override
                    public int compare(Notebook nt1, Notebook nt2) {
                        return Long.compare(nt2.getServiceUpdated(), nt1.getServiceUpdated());
                    }
                });
                namesList.clear();
                for (int i = 0; i < notebookList.size(); i++) {
                    namesList.add(notebookList.get(i).getName());
                }
                adaptador.notifyDataSetChanged();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

    }
