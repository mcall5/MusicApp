# MusicApp

This app is to help users develop their sight reading music skills. The practice screen features a timed auto-rotation to display randomized sheet music clips which can be customized based on the user's preferences. Secondary features include a metronome and a timer for lesson tracking.

##Practice Screen

![musicpractice](https://cloud.githubusercontent.com/assets/25094066/22000576/c500a25c-dbf4-11e6-99ed-5bcf7c1876ae.png)

###Features

* Sheet music image display

* Auto-rotation

* Metronome

* Session timer

* Pause button

#### Sheet music image display

![musicpractice_li 2](https://cloud.githubusercontent.com/assets/25094066/22003820/7bd1fcf4-dc0b-11e6-9e14-6e038350dc62.jpg)

Description:  Sheet music clips rotate randomly based on the key signatures and time signatures selected.  Rotation can be done through auto-rotation or by clicking on the image.

Progress: Randomization works well and only includes the key & time signatures selected for.  The current key & time signatures are displayed through a textView on the top of the imageView.  

Working on: Adding sheet music clips for the imageView to display.


####Auto-rotation

![musicpractice 3](https://cloud.githubusercontent.com/assets/25094066/22001243/87359974-dbf8-11e6-8980-683141fd79bb.png)
![musicrotate 2](https://cloud.githubusercontent.com/assets/25094066/22001268/b1394978-dbf8-11e6-8808-8a93f0305eec.png)

Description: The auto-rotate buttons control when the sheet music clips change.   Includes  “fast” and “slow” speeds and on/off buttons to allow the user to manually rotate.

Progress:  Rotation buttons interact well with each other and can disappear when not in use.

Working on:  Adding more speed options.  Allowing rotation to work properly everytime the speed button is clicked.

Bugs: Rotation countdown skips over when speed buttons are clicked many times in quick succession. 


####Metronome

![musicmetronome 2](https://cloud.githubusercontent.com/assets/25094066/22001230/7698aac0-dbf8-11e6-919d-356c13c0feb0.png)

Description: Metronome can switch on and play beats at varying speeds.

Progress:  The metronome switch can toggle the metronome buttons visibility and play audible beats.  The speed buttons can change the BPM and accurately display the current BPM.

Working on:  Speeding up the buttons to update quickly while keeping the beats playing properly. 

Bugs: Metronome beats glitch into overlapping rhythms when buttons are clicked many times in quick succession. 

####Session timer

![musicpractice 4](https://cloud.githubusercontent.com/assets/25094066/22001246/9468a294-dbf8-11e6-8301-ee7538e16ee7.png)

Description: Timer to track total practice running time.

Progress:  The timer displays ongoing practice time in hours minutes and seconds.  The timer runs continuously while the user is actively practicing and is able to pick back up every time the user comes back on the screen from the menu without counting the time off screen.

Working on:  Saving daily statistics to log practice times.

#### Pause button

![musicpracticepause](https://cloud.githubusercontent.com/assets/25094066/22001263/a6886a72-dbf8-11e6-921b-b8824652b022.png)

Pause button:

Description: Pause button to pause the session without leaving the screen.

Progress:  The pause button can pause the timer, auto-rotation and metronome & disable their buttons.  All the current settings are saved and restored properly after unpausing.  A large red “PAUSED” textView displays so the user can easily tell when the screen has been paused.

###Other app screen pages:

* Start Screen

* Menu Screen

* Key Signature Settings Screen

* Time Signature Settings

* Level Screen

####Startup Screen

![musicstart](https://cloud.githubusercontent.com/assets/25094066/22001914/cf7b671e-dbfc-11e6-98d5-59c0a126b4f1.png)

Description: Allows the user to start a practice session or go to the settings menu.

Progress: Simple appearance with working buttons.

####Menu Screen:

![musicmenu](https://cloud.githubusercontent.com/assets/25094066/22001904/befec17e-dbfc-11e6-89bf-6eeccaf8d82e.png)

Description: Allows the user to go into settings for key signatures and time signatures to specify which music clips to display on the practice page

Progress: Clean appearance with working buttons.

Working on: Additional setting options for increased user customization.

####Key Signature Settings:

![musickeymenu](https://cloud.githubusercontent.com/assets/25094066/22001896/b68713c0-dbfc-11e6-8f98-24fb8a0ab64c.png)

Description: Allows the user to select and unselect key signatures to include on the practice page.  Includes all of the major and minor key signatures. 

Progress: ListView  buttons display appropriate highlight color on clicks and connect to arrayList to update music display on the practice page.  

Working on: Saving previous settings when returning to the page so the buttons do not reset to default every time the user returns to make a change.  Providing image icons for each key signature to make page visually easier to skim.  Adding a “select all” and “unselect all” option.  Adding a toast pop-up to prevent the user from leaving the page with no selected items.

Bugs: The app crashes on the practice page if the user leaves the settings page with no selected items.

####Time Signature Settings

![musictimemenu](https://cloud.githubusercontent.com/assets/25094066/22001919/d5dd022a-dbfc-11e6-8d0a-6ecec956b16b.png)

Description: Allows the user to select and unselect time signatures to include on the practice page.

Progress: Buttons display appropriate highlight color on clicks and connect to arrayList to update music display on the practice page.  Previous settings save properly when the user returns to the page.

Working on: Providing more time signatures to choose from.

####Level Screen

![musiclevel](https://cloud.githubusercontent.com/assets/25094066/22001901/bb07dd12-dbfc-11e6-8e34-2f6bd79cb674.png)

Description: Allows the user to select difficulty level before entering the practice screen

Progress: Clean appearance with working buttons.  Automatically pops-up everytime the user clicks to start a practice session.

Working on: Make an option for the user to decide if they want to be asked the practice level with each session or to save it under their settings.


