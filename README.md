Jemin Ryoo jryoo3
Weina Dai wdai11

# Time Difference App

## About the App
This is an Android app that can be used to practice estimating and calculating elapsed time.

## Functionality
<ol>
  <li> Calculate: A user can enter the start and the end time in military format and the app will display the elapsed time between them, assuming it is within 24 hours.</li>
  
  <li> Learn: A user can choose either the military format or the starndard format, and the app will randomly display a start time and an end time. A user will
  first decide which of the three categories, <8hr, 8-16hr, or >16hr, the elasped time belongs. After the result is displayed, the user can retry or go to the next stage, where they will input the exact time (in hours and seconds) elapsed.</li>
</ol>

## Notes (a3 Reflections)

Instead of the original design where the user-selected  hourglass  is  shown  with a check  on  it  if  it  was  correct 
or  X  if incorrect on the EasyResult screen, we decided to implement a highlighted border that showed red on incorrect 
guesses and green on correct guesses, as this was easier to implement and more intuitive in our opinion.

We thought having six (or more) activities for this simple version of the app was a little overkill, and thought that
combining the results page could make the design easier to navigate and digest for the user. 

We tested whether the app displayed the correct results for each level (short, medium, long) in the easy learn activities
by calculating the elaspsed time and comparing our answers to what is displayed by the app. We implemented a highlighted border 
that showed red on incorrect guesses and green on correct guesses to make the display intuitive and easy to understand. 

## Issues
<ul>
  <li>We could not seem to align the title of each screen to the center or set the text color to black, even with the help of TAs and StackOverflow.</li>
  <li>No matter how we change the arragement of the elements in our app layout, the app's UI will not look good on older APIs uless we compromise its look on newer APIs.</li>
</ul>

