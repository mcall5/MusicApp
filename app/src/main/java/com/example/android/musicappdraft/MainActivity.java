
package com.example.android.musicappdraft;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////Library/////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
    //TextView to display library level
    TextView textHead;
    //TextView to display library file title
    TextView textFileTitle;
    //library level for drawable
    String libraryLevel;
    //library level for screen display
    String nameLevel;
    //library current key signature
    String keySig;
    //library current time signature
    String timeSig;
    //library drawable name
    String nameDrawable;
    //Randomizer to select random drawable
    Random rand = new Random();
    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////Key Signature Menu/////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////
    //library default key signature array
    String[] keysig_default_array = {"Cmajor", "Dmajor", "Emajor", "Gmajor", "Amajor", "Ebmajor", "Fmajor", "Abmajor", "Bbmajor", "Dbmajor", "F#major", "Bmajor"};
    //library custom key signature list
    List<String> keysig_custom_list = new ArrayList<String>(Arrays.asList(keysig_default_array));
    ListView listView;
    ArrayAdapter<String> adapter;
    String[] keysig_array = {"C Major", "D Major", "E Major", "G Major", "A Major", "E-flat Major", "F Major", "A-flat Major", "B-flat Major", "D-flat Major", "F-sharp Major", "B Major", "A Minor", "E Minor", "B Minor", "F-sharp Minor", "C-sharp Minor", "G-sharp Minor", "C-Minor", "G Minor", "D Minor", "E-flat Minor", "B-flat Minor", "F Minor"};
    //String to show items checked
    ArrayList<String> selectedItems = new ArrayList<String>(Arrays.asList(keysig_array));
    /////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////Time Signature Menu////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    //library default time signature array
    String[] timesig_default_array = {"2:2", "2:4", "3:2", "3:4", "4:2", "4:4"};
    //library custom key signature list
    List<String> timesig_custom_list = new ArrayList<String>(Arrays.asList(timesig_default_array));
    //Menu Buttons
    Button timeSig22;
    Button timeSig24;
    Button timeSig32;
    Button timeSig34;
    Button timeSig42;
    Button timeSig44;
    /////////////////////////////////////////////////////////////////////////////////////
    // ////////////////////////////Metronome://////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    //Increase speed
    Button metroUp;
    //Decrease speed
    Button metroDown;
    //Current BPM display
    TextView bpmText;
    //toggle switch for metronome
    Switch metroSwitch;
    //Current beat per minute on Metronome
    int metroBpm;
    //Default beat per minute to reset metronome
    int defaultBpm = 60;
    //1 = metronome is on
    int metronomeOn = 0;
    //1 = metronome will be saved as on when pausing
    int metroSave;
    //100 = volume; toneGenerator for metronome tone
    ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
    //readthread for metronome
    HandlerThread readThread;
    //handler for metronome
    Handler handler;
    //runnable for metronome
    Runnable runnable;
    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////Music rotation:///////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
    //stop rotating
    Button rotateStop;
    //start rotating
    Button rotateStart;
    //Slow rotation speed setting
    Button rotateSlow;
    //Fast rotation speed setting
    Button rotateFast;
    //Current rotation countdown (sec)
    int rotateCount;
    //Default rotation speed (sec)
    int rotateDelay = 5;
    //Manually rotate by clicking on image (has text that says "paused" when paused)
    Button musicImage;
    //Rotation countdown display
    TextView rotationCountDown;
    //Rotation title
    TextView rotationTitle;
    //Rotation on/off (1= on; 0= off)
    int imageRotation;
    //Rotation manually stopped (1 = stopped)
    int rotationStopped;
    //Save rotation setting while turning rotation off during pause (1 = stopped)
    int rotStoppedSave;
    //Imageview to display library drawables
    int imageLibrary;
    //readThread for image rotation countdown
    HandlerThread imageReadThread;
    //handler for image rotation countdown
    Handler imageHandler;
    //runnable for image rotation
    Runnable imageRunnable;
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////Practice page timer/pause///////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //Timer display
    TextView sightTimer;
    //Pause the whole page for a bathroom break
    Button pauseSightPractice;
    //If page is paused (1 = paused)
    int sightPracticePause;
    //If timer is on (1 = on)
    int totalTimerOn;
    //Second count for timer to track total sight practice time
    int sightPracticeTimer = 0;
    //Second count for timer after formatting
    int timerSecond = 0;
    //Minute count for timer after formatting
    int timerMinute = 0;
    //Hour count for timer after formatting
    int timerHour = 0;
    //String for timer to display
    String timerFormat = "";
    //readThread for timer to track total sight practice time
    HandlerThread timerReadThread;
    //handler for timer
    Handler timerHandler;
    //Runnable for timer
    Runnable timerRunnable;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////ONCREATE////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        metroBpm = defaultBpm;
    }

    //onClick: Go to the sight reading Main Menu
    public void sightMainMenu(View v) {
        setContentView(R.layout.sight_reading_main);
        pauseSightPractice();
    }

    //onClick: Go to the menu to select difficulty level
    public void setLevelLib(View v) {
        setContentView(R.layout.sight_level);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////Key Signature Menu/////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    //onClick: Go to Key Signature Menu from the Key sig button in the main menu
    public void sightKeyMenu(View v) {
        setContentView(R.layout.key_sig_menu);
        //Change this line when you figure out how to save page when leaving and coming back
        //Reset the selectedItems to the default list (because the screen reset)
        selectedItems = new ArrayList<String>(Arrays.asList(keysig_array));
        listView = (ListView) findViewById(R.id.key_listview);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        final TextView textView = (TextView) findViewById(R.id.textViewKeyRow);
        adapter = new ArrayAdapter<String>(this, R.layout.key_row_layout, R.id.textViewKeyRow, keysig_array);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String selectedItem = ((TextView)view).getText().toString();
                if(selectedItems.contains(selectedItem)){
                    //uncheck item
                    selectedItems.remove(selectedItem);
                }
                else{
                    //check item
                    selectedItems.add(selectedItem);
                }
                //view.setSelected(true);
/*                String clickedItem = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), clickedItem, Toast.LENGTH_SHORT ).show();*/

            }
        });
    }

    //onClick to leave the key signature menu (must have something selected)
    public void keySigCheckout (View v){
        if (selectedItems.size() > 0){
            setContentView(R.layout.sight_reading_main);
        }
        else{
            Toast.makeText(getApplicationContext(), R.string.nothing_selected, Toast.LENGTH_SHORT ).show();
        }
    }

    //onClick: (currently unused) button to display the selected items list
    public void showSelectedItems (View v){
        String items= "";
        for(String item:selectedItems){
            items+="-"+item+"\n";
        }
        Toast.makeText(this, "You have selected " + selectedItems.size() + " key signatures:\n"+items , Toast.LENGTH_LONG).show();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////Time Signature Menu///////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////
    //onClick to go to Time Signature Menu
    public void sightTimeMenu(View v) {
        setContentView(R.layout.time_sig_menu);
        //Set background colors for buttons contained in array
        timeSig22 = (Button) findViewById(R.id.time_sig_2_2);
        if (timesig_custom_list.contains("2:2")) {
            timeSig22.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
        else {
            timeSig22.setBackgroundColor(Color.TRANSPARENT);
        }
        timeSig24 = (Button) findViewById(R.id.time_sig_2_4);
        if (timesig_custom_list.contains("2:4")) {
            timeSig24.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
        else {
            timeSig24.setBackgroundColor(Color.TRANSPARENT);
        }
        timeSig32 = (Button) findViewById(R.id.time_sig_3_2);
        if (timesig_custom_list.contains("3:2")) {
            timeSig32.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
        else {
            timeSig32.setBackgroundColor(Color.TRANSPARENT);
        }
        timeSig34 = (Button) findViewById(R.id.time_sig_3_4);
        if (timesig_custom_list.contains("3:4")) {
            timeSig34.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
        else {
            timeSig34.setBackgroundColor(Color.TRANSPARENT);
        }
        timeSig42 = (Button) findViewById(R.id.time_sig_4_2);
        if (timesig_custom_list.contains("4:2")) {
            timeSig42.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
        else {
            timeSig42.setBackgroundColor(Color.TRANSPARENT);
        }
        timeSig44 = (Button) findViewById(R.id.time_sig_4_4);
        if (timesig_custom_list.contains("4:4")) {
            timeSig44.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
        else {
            timeSig44.setBackgroundColor(Color.TRANSPARENT);
        }
    }
    //onClick//////////////////////BUTTONS/////////////////////////
    public void timeSig24(View v){
        if (timesig_custom_list.contains("2:4")) {
            timesig_custom_list.remove(timesig_custom_list.indexOf("2:4"));
            timeSig24.setBackgroundColor(Color.TRANSPARENT);
        }
        else {
            timesig_custom_list.add("2:4");
            timeSig24.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }

    //onClick
    public void timeSig22(View v){
        if (timesig_custom_list.contains("2:2")) {
            timesig_custom_list.remove(timesig_custom_list.indexOf("2:2"));
            timeSig22.setBackgroundColor(Color.TRANSPARENT);
        }
        else {
            timesig_custom_list.add("2:2");
            timeSig22.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }
    //onClick
    public void timeSig32(View v){
        if (timesig_custom_list.contains("3:2")) {
            timesig_custom_list.remove(timesig_custom_list.indexOf("3:2"));
            timeSig32.setBackgroundColor(Color.TRANSPARENT);
        }
        else {
            timesig_custom_list.add("3:2");
            timeSig32.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }
    //onClick
    public void timeSig34(View v){
        if (timesig_custom_list.contains("3:4")) {
            timesig_custom_list.remove(timesig_custom_list.indexOf("3:4"));
            timeSig34.setBackgroundColor(Color.TRANSPARENT);
        }
        else {
            timesig_custom_list.add("3:4");
            timeSig34.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }
    //onClick
    public void timeSig42(View v){
        if (timesig_custom_list.contains("4:2")) {
            timesig_custom_list.remove(timesig_custom_list.indexOf("4:2"));
            timeSig42.setBackgroundColor(Color.TRANSPARENT);
        }
        else {
            timesig_custom_list.add("4:2");
            timeSig42.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }
    //onClick
    public void timeSig44(View v){
        if (timesig_custom_list.contains("4:4")) {
            timesig_custom_list.remove(timesig_custom_list.indexOf("4:4"));
            timeSig44.setBackgroundColor(Color.TRANSPARENT);
        }
        else {
            timesig_custom_list.add("4:4");
            timeSig44.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }
    //onClick to leave the time signature menu (must have something selected)
    public void timeSigCheckout (View v){
        if (timesig_custom_list.size() > 0){
            setContentView(R.layout.sight_reading_main);
        }
        else{
            Toast.makeText(getApplicationContext(), R.string.nothing_selected, Toast.LENGTH_SHORT ).show();
        }
    }

    //onClick: Goes to practice page for Beginner Level
    public void sightPracticeBeg(View v) {
        //set level to beginner
        nameLevel = "beginner";
        libraryLevel = "Beg";
        //go to practice page
        practiceLayout();
    }

    //onClick: Goes to practice page for Intermediate Level
    public void sightPracticeInt(View v) {
        //set level to intermediate
        nameLevel = "intermediate";
        libraryLevel = "Int";
        //go to practice page
        practiceLayout();
    }

    //onClick: Goes to practice page for Advanced Level
    public void sightPracticeAdv(View v) {
        //set level to advanced
        nameLevel = "advanced";
        libraryLevel = "Adv";
        //go to practice page
        practiceLayout();
    }

    /*******************************************************************************************
     ****************************  Go to practice page:********************************************
    *****************************Set header to display level*************************************
    **********************Assign buttons & textviews and set visibility************************
     * *******************************************************************************************/
    public void practiceLayout() {
        //Go to page
        setContentView(R.layout.sight_practice);
        //Set header to display level
        textHead = (TextView) findViewById(R.id.sight_practice_head);
        textFileTitle = (TextView) findViewById(R.id.music_file_title);
        textHead.setText(nameLevel);
        ////////////////Metronome:///////////////
        metroUp = (Button) findViewById(R.id.button_metro_up);
        metroDown = (Button) findViewById(R.id.button_metro_down);
        bpmText = (TextView) findViewById(R.id.bpm_text);
        metroSwitch = (Switch) findViewById(R.id.metronome_switch);
        metroSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    if(isChecked){
                        metronomeOn();
                    }
                    else{
                        metronomeOff();
                    }
                }
            });
        //Make metronome invisible
        metroButtonsOff();
        ////////Rotation://///////////
        rotateStop = (Button) findViewById(R.id.rotate_stop);
        rotateStart = (Button) findViewById(R.id.rotate_start);
        rotationTitle = (TextView) findViewById(R.id.rotation_text);
        rotateStart.setVisibility(View.INVISIBLE);
        rotateSlow = (Button) findViewById(R.id.rotate_slow);
        rotateFast = (Button) findViewById(R.id.rotate_fast);
        rotateCount = rotateDelay;
        musicImage = (Button) findViewById(R.id.music_image);
        rotationCountDown = (TextView) findViewById(R.id.rotation_countdown);
        rotationCountDown.setText("" + rotateCount);
        //Turn rotation on
        sightDisplayRandom();
        imageRotation = 1;
        rotationStopped = 0;
        rotStoppedSave = 0;
        imageLibrary = R.id.sight_practice_image;
        setRotateFastSlow();
        rotateImageTimerOn();
        /////////Timer:///////////
        sightTimer = (TextView) findViewById(R.id.sight_practice_timer);
        pauseSightPractice = (Button) findViewById(R.id.pause_sight_practice);
        //set timer so it displays 0 sec at start
        formatTimer();
        sightTimer.setText(timerFormat);
        //Start sight practice timer
        totalTimerOn = 1;
        sightPracticePause = 0;
        countTimerOn();
    }

    /***********************************************************************************************
    ******************************************METRONOME*********************************************
     * *********************************************************************************************/
    //Turn on metronome beep
    public void metronomeOn () {
        //Get looper from handler to allow while loops
        readThread = new HandlerThread("");
        readThread.quit();
        readThread.start();
        handler = new Handler(readThread.getLooper());
        final int delay = 60000/metroBpm;
        //Then play a beep
        runnable = new Runnable() {
            //Delay 200 seconds
            public void run() {
                if (metronomeOn == 1) {
                    //200 = duration in ms
                    toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
                }
                handler.postDelayed(this, delay);
            }
        };
        //beep as soon as turned on & turn on metronome
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
        metronomeOn = 1;
        handler.postDelayed(runnable, delay);
        metroButtonsOn();
    }
    //Turn on metronome display to visible
    public void metroButtonsOn(){
        metroUp.setVisibility(View.VISIBLE);
        metroDown.setVisibility(View.VISIBLE);
        bpmText.setText("" + metroBpm + " " + getResources().getText(R.string.bpm));
    }
    //Turn off metronome & make invisible
    public void metronomeOff () {
        metronomeLeave();
        metroButtonsOff();
    }
    //Turn off metronome (to leave the metronome screen)
    public void metronomeLeave() {
        metronomeOn = 0;
        //Get looper from handler to allow while loops
        readThread = new HandlerThread("");
        readThread.quit();
        readThread.start();
        handler = new Handler(readThread.getLooper());
        readThread.quit();
        handler.removeCallbacks(runnable);
        readThread.quit();
    }
    //Set metronome display to invisible
    public void metroButtonsOff(){
        metroUp.setVisibility(View.INVISIBLE);
        metroDown.setVisibility(View.INVISIBLE);
        bpmText.setText("");
    }
    //onClick to decrement metronome BPM each click until 30 BPM
    public void metroDecrement(View v) {
        if (metroBpm >= 30) {
            //disable metronome while resetting
            metronomeLeave();
            metroDown.setClickable(false);
            metroBpm = metroBpm - 5;
            timedDelay(1501);
        }
    }
    //onClick to increment metronome BPM each click until 200 BPM
    public void metroIncrement(View v) {
        if (metroBpm < 200) {
            //disable metronome while resetting
            metronomeLeave();
            metroUp.setClickable(false);
            metroBpm = metroBpm + 5;
            timedDelay(1501);
        }
    }
    //pause the metronome code for duration in ms, then turn metronome on
    public void timedDelay(int ms) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                metroUp.setClickable(true);
                metroDown.setClickable(true);
                metronomeOn();
            }
        }, ms);
    }

    /***********************************************************************************************
     ******************************************Rotation*********************************************
     * *********************************************************************************************/
    //onClick to slow music image rotation to 10 seconds
    public void setCountSlow(View v) {
        setCountSlow();
    }
    //Method for setCountSlow onClick
    public void setCountSlow() {
        rotateSlow.setVisibility(View.INVISIBLE);
        rotateFast.setVisibility(View.VISIBLE);
        rotateFast.setClickable(false);
        musicImage.setClickable(false);
        rotateDelay = 10;
        resetCountDown();
        delaySlowButton();
    }
    //onClick to speed up music image rotation to 5 seconds
    public void setCountFast(View v) {                                                              //onClick to speed music image rotation to 5 seconds
        setCountFast();
    }
    //Method for setCountFast onClick
    public void setCountFast() {
        rotateFast.setVisibility(View.INVISIBLE);
        rotateSlow.setVisibility(View.VISIBLE);
        rotateSlow.setClickable(false);
        musicImage.setClickable(false);
        rotateDelay = 5;
        resetCountDown();
        delayFastButton();
    }
    //Reset Countdown to rotateDelay
    public void resetCountDown() {
        rotateImageTimerOff();
        //start countdown
        rotateCount = rotateDelay;
        //Display rotation countdown
        rotationCountDown.setText("" + rotateCount);
        //only turn rotation back on if not manually stopped
        if (rotationStopped == 0) {
            //Do first second delay before setting imageRotation = 1
            timedResetCountDownDelay(1000);
        }
    }
    //delay Slow Button Clickability
    public void delaySlowButton() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rotateFast.setClickable(true);
                musicImage.setClickable(true);
            }
        }, 1000);
    }
    //delay Fast Button Clickability
    public void delayFastButton() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rotateSlow.setClickable(true);
                musicImage.setClickable(true);
            }
        }, 1000);
    }
    //Do first second delay before setting imageRotation = 1
    public void timedResetCountDownDelay(int delay) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rotateCount = rotateDelay - 1;
                //Display rotation countdown
                rotationCountDown.setText("" + rotateCount);
                //Start rotation through image
                imageRotation = 1;
                rotateImageTimerOn();
                //enable onClick
                musicImage.setClickable(true);
            }
        }, delay);
    }
    //onClick
    public void startRotation(View v) {                                                             //onClick start rotating images
        startRotation();
    }

    //manually start Rotation after pause
    public void startRotation() {
        rotateStart.setVisibility(View.INVISIBLE);
        rotationTitle.setVisibility(View.VISIBLE);
        rotateFast.setClickable(false);
        rotateSlow.setClickable(false);
        musicImage.setClickable(false);
        rotationStopped = 0;
        imageRotation = 1;
        rotateImageTimerOn();
        if (rotateDelay == 5) {
            rotateSlow.setVisibility(View.VISIBLE);
        }
        else {
            rotateFast.setVisibility(View.VISIBLE);
        }
        rotateStop.setVisibility(View.VISIBLE);
/*        rotateFast.setVisibility(View.VISIBLE);           These lines were commented out in original
        rotateSlow.setVisibility(View.VISIBLE);*/
        rotationCountDown.setVisibility(View.VISIBLE);
        delayRotationClicks();
    }
    //Delay rotation buttons
    public void delayRotationClicks() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (rotateDelay == 5) {
                    setCountFast();
                }
                else {
                    setCountSlow();
                }

//                rotateFast.setClickable(true);            These lines were commented out in original
//                rotateSlow.setClickable(true);
                musicImage.setClickable(true);
            }
        }, 1000);
    }
    //onClick
    public void stopRotation(View v) {                                                              //onClick stop rotating images
        stopRotation();
    }

    public void stopRotation() {
        rotationStopped = 1;
        rotateImageTimerOff();
        rotateStop.setVisibility(View.INVISIBLE);
        rotationTitle.setVisibility(View.INVISIBLE);
        rotateStart.setClickable(false);
        rotateStart.setVisibility(View.VISIBLE);
        rotateFast.setVisibility(View.INVISIBLE);
        rotateSlow.setVisibility(View.INVISIBLE);
        rotationCountDown.setVisibility(View.INVISIBLE);
        resetCountDown();
        delayStartButton();
    }
    //Delay rotateStart button Clickability
    public void delayStartButton() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rotateStart.setClickable(true);
            }
        }, 600);
    }
    //turn off image rotation timer
    public void rotateImageTimerOff () {
        imageRotation = 0;
        imageReadThread = new HandlerThread("");
        imageReadThread.quit();
        imageReadThread.start();
        imageHandler = new Handler(imageReadThread.getLooper());
        imageReadThread.quit();
        imageHandler.removeCallbacks(runnable);
        imageReadThread.quit();
    }
    //onClick to change drawable
    public void nextPractice(View v){
        //disable onClick if rotation is not manually turned off
        if (rotationStopped == 0) {
            musicImage.setClickable(false);
        }
        //Display new image
      sightDisplayRandom();
        //Reset Countdown
      resetCountDown();
    }
    //set the button visibility for fast/slow options
    public void setRotateFastSlow() {
        if (rotateDelay == 5) {
            rotateFast.setVisibility(View.INVISIBLE);
        }
        else {
            rotateSlow.setVisibility(View.INVISIBLE);
        }
    }
    //Display random drawable
    public void sightDisplayRandom(){
        //test: display level followed by randomized keysig and timesig
        textFileTitle.setText(selectedItems.get(new Random().nextInt(selectedItems.size())) + " " + timesig_custom_list.get(new Random().nextInt(timesig_custom_list.size())));
        sightDisplayRandomPrep();
//        getIdAndDisplay("practice" + idLibrary + levLibrary + ranLibrary, "drawable", imageLibrary);
    }
    //Timer to rotate through drawables
    public void rotateImageTimerOn() {
        //Get looper from handler to allow while loops
        imageReadThread = new HandlerThread("");
        imageReadThread.quit();
        imageReadThread.start();
        imageHandler = new Handler(imageReadThread.getLooper());
        //Delay 1 second then countdown
        final int second = 1000;
        imageRunnable = new Runnable() {
            public void run() {
                //run if rotation is turned on
                if (imageRotation == 1){
                    //Count down from rotateDelay
                    countDown();
                    final int count = rotateCount;
                    final TextView countText = (TextView) findViewById(R.id.rotation_countdown);
                    countText.post(new Runnable() {
                        @Override
                        public void run() {
                            countText.setText("" + count);
                        }
                    });
                    //rotate image after count down
                    if (count == rotateDelay) {
                        //NEEDS CLEANING UP: could not call method that resets imageView.  Fixed code, but look up "AsyncTask" to make cleaner
                        sightDisplayRandomPrep();
//                        final int value = getResources().getIdentifier("practice" + idLibrary + levLibrary + ranLibrary, "drawable", getPackageName());
                        final ImageView imageView = (ImageView) findViewById(imageLibrary);
                        imageView.post(new Runnable() {
                            public void run() {
 //                               imageView.setImageResource(value);
                            }
                        });
                        //test to display text rotation
                        final TextView textView = (TextView) findViewById(R.id.music_file_title);
                        textView.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(selectedItems.get(new Random().nextInt(selectedItems.size())) + " " + timesig_custom_list.get(new Random().nextInt(timesig_custom_list.size())));
                            }
                        });
                    }
                }
                imageHandler.postDelayed(this, second);
            }
        };
        imageHandler.postDelayed(imageRunnable, second);
    }
    //Countdown from rotateDelay for image rotation timer
    public void countDown () {
        if (rotateCount > 1) {
            rotateCount = rotateCount - 1;
        }
        else {
            rotateCount = rotateDelay;
        }
    }
    //Prepare sightDisplayRandom to call getIdAndDisplay() (to remove View from method for AsyncTask)
    public void sightDisplayRandomPrep() {
/*        if (ranMultiLib == 1) {                                                                         //Check if rotating through multiple libraries
            idLibrary = rand.nextInt(5) + 1;
            setlibToRan();                                                                              //Check if library is in use
            while (libToRan != 1) {                                                                     //Choose a different library until one in use is found
                idLibrary = rand.nextInt(5) + 1;
                setlibToRan();
            }
            getSightNumLib();                                                                           //Resets library size to current library
        }
        if (numLibrary > 1) {
            ranLibrary = rand.nextInt(numLibrary) + 1;                                                      //Pick random drawable in library & display
        }
        else {
            ranLibrary = numLibrary;
        }*/
    }
    /*********************************************************************************************
     ******************************************TIMER***********************************************
     **********************************************************************************************/
    //Turn timer on and start counting seconds
    public void countTimerOn () {
        //Get looper from handler to allow while loops
        timerReadThread = new HandlerThread("");
        timerReadThread.quit();
        timerReadThread.start();
        timerHandler = new Handler(timerReadThread.getLooper());
        final int delay = 1000;
        timerRunnable= new Runnable() {
            public void run() {
                if (totalTimerOn == 1) {
                    sightPracticeTimer = sightPracticeTimer + 1;
                    formatTimerCount();
                    final String increCount = timerFormat;
                    final TextView timerView = (TextView) findViewById(R.id.sight_practice_timer);
                    timerView.post(new Runnable() {
                        @Override
                        public void run() {
                            timerView.setText(increCount);
                        }
                    });
                }
                timerHandler.postDelayed(this, delay);
            }
        };
        timerHandler.postDelayed(timerRunnable, delay);
    }
    //Split timer count into hour, minute, second
    public void formatTimerCount() {
        if (timerSecond == 59) {
            timerSecond = 0;
            if (timerMinute == 59) {
                timerMinute = 0;
                timerHour = timerHour + 1;
            }
            else {
                timerMinute = timerMinute + 1;
            }
        }
        else {
            timerSecond = timerSecond + 1;
        }
        formatTimer();
    }
    //Format timerHour, timerMinute & timerSecond into String timerFormat
    public void formatTimer() {
        timerFormat = "";
        if (timerHour > 0) {
            timerFormat = "" + timerHour + " hr ";
        }
        if (timerMinute > 0) {
            timerFormat = timerFormat + timerMinute + " min ";
        }
        timerFormat = timerFormat + timerSecond + " sec";
    }
    //Pause the timer when off practice page
    public void countTimerOff () {
        totalTimerOn = 0;
        timerReadThread = new HandlerThread("");
        timerReadThread.quit();
        timerReadThread.start();
        timerHandler = new Handler(timerReadThread.getLooper());
        timerReadThread.quit();
        timerHandler.removeCallbacks(timerRunnable);
        timerReadThread.quit();
    }
    /*********************************************************************************************
     ******************************************PAUSE ALL*******************************************
     **********************************************************************************************/
    //onClick to turn pause on/off for sight practice metronome, timer, and rotation without leaving page
    public void onOffPause (View v) {
        //make pause button unclickable for 600 ms
        pauseSightPractice.setClickable(false);
        //if turning off
        if (sightPracticePause == 0) {
            //save metronome and rotation on/off state
            metroSave = metronomeOn;
            rotStoppedSave = rotationStopped;
            //put pause state to be paused & pause the page
            sightPracticePause = 1;
            metroSwitch.setEnabled(false);
            rotateStart.setEnabled(false);
            musicImage.setEnabled(false);
            rotateStart.setText(getText(R.string.paused));
            pauseSightPractice.setText(getText(R.string.play));
            musicImage.setTextColor(Color.parseColor("#f44336"));
            metronomeOff();
            stopRotation();
            countTimerOff();
        }
        else {
            //put pause state to be unpaused
            sightPracticePause = 0;
            //turn metronome & rotation on if saved as on before pause
            metroSwitch.setEnabled(true);
            rotateStart.setEnabled(true);
            musicImage.setEnabled(true);
            rotateStart.setText(getText(R.string.rotate));
            pauseSightPractice.setText(getText(R.string.pause));
            musicImage.setTextColor(Color.TRANSPARENT);

            if (metroSave == 1) {
                metronomeOn();
            }
            if (rotStoppedSave == 0) {
                startRotation();
            }
            //turn timer back on
            totalTimerOn = 1;
            countTimerOn();
        }
        final Handler pauseHandler = new Handler();
        pauseHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pauseSightPractice.setClickable(true);
            }
        }, 1000);
    }
    //Pause sight practice metronome, timer, and rotation when leaving page
    public void pauseSightPractice() {
        metronomeLeave();
        rotateImageTimerOff();
        countTimerOff();
    }
}