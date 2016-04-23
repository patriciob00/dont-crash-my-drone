package dronsh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.software.shell.fab.FloatingActionButton;

public class Main extends AppCompatActivity {

    public FloatingActionButton fab ;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        toolbar = ( Toolbar ) findViewById( R.id.main_bar);

        toolbar.setTitle("Inicio");

    }
}
