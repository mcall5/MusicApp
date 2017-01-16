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

![musicpractice 2](https://cloud.githubusercontent.com/assets/25094066/22001210/5f3040a0-dbf8-11e6-8e40-f8f142706e08.png)

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
