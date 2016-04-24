package dronsh.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.software.shell.fab.ActionButton;
import com.software.shell.fab.FloatingActionButton;

public class Main extends AppCompatActivity {

    public FloatingActionButton fab ;
    private Toolbar toolbar;
    private Drawer Navigation;
    PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Drones");
    PrimaryDrawerItem item2 = new SecondaryDrawerItem().withName("Locais");
    PrimaryDrawerItem item3 = new SecondaryDrawerItem().withName("Dicas");
    private int mPositionClicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setFab();
        setToolBar();

        //NAVIGATION DRAWER
        Navigation = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withActionBarDrawerToggle(true)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem ) {
                        for (int count = 0, tam = Navigation.getDrawerItems().size(); count < tam; count++) {
                            if (count == mPositionClicked && mPositionClicked <= 3) {
                                PrimaryDrawerItem aux = (PrimaryDrawerItem) Navigation.getDrawerItems().get(count);
                                aux.withIcon(getResources().getDrawable(getCorrectDrawerIcon(count, false)));
                                break;
                            }
                        }
                        if ( position <= 3) {
                            ((PrimaryDrawerItem) drawerItem ).withIcon(getResources().getDrawable(getCorrectDrawerIcon( position, true )));
                        }
                        mPositionClicked = 1;
                        Navigation.getAdapter().notifyDataSetChanged();
                        return false;
                    }

                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(View view, int position, IDrawerItem drawerItem) {
                        return false;
                    }
                })
                .build();
        Navigation.addItem(new PrimaryDrawerItem().withName("Drones").withIcon(getResources().getDrawable(R.drawable.drones24dp)));
        Navigation.addItem(new PrimaryDrawerItem().withName("Locais").withIcon(getResources().getDrawable(R.drawable.localblack24dp)));
        Navigation.addItem(new PrimaryDrawerItem().withName("Dicas").withIcon(getResources().getDrawable(R.drawable.bubblelightblack24dp)));


    }
    private int getCorrectDrawerIcon( int position , boolean isSelected){
        switch ( position ){
            case 0:
                return ( isSelected ? R.drawable.dronesblue24dp : R.drawable.drones24dp);
            case 1:
                return ( isSelected ? R.drawable.localblue24dp : R.drawable.localblack24dp);
            case 2:
                return ( isSelected ? R.drawable.bubblelightblue24dp : R.drawable.bubblelightblack24dp);
                    }
        return (0);
    }
    public void setFab() {
        fab = ( FloatingActionButton ) findViewById( R.id.fab);
        fab.setButtonColor(getResources().getColor(R.color.colorPrimary));
        fab.setButtonColorPressed(getResources().getColor(R.color.colorPrimaryDark));
        fab.playShowAnimation();
        fab.playHideAnimation();
        fab.setImageDrawable(getResources().getDrawable(R.drawable.fab_plus_icon));
        fab.setType(ActionButton.Type.DEFAULT);
    }

    public void setToolBar() {
        toolbar = ( Toolbar ) findViewById( R.id.main_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar( ).setTitle("Dronsh");
        getSupportActionBar().setElevation(2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_localMy) {

            Intent intent = new Intent(this, MyLocal.class);
            startActivity( intent );
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (Navigation != null && Navigation.isDrawerOpen()) {
            Navigation.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
