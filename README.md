# Garbage-Tracker
working is very simple I have used firebase as Backend to store,retrive and update percentage of dustbin present on Google map
The app working is like you first login to the app
then click on dustbin icon you want to collect Scan the qrcode written on it then boom value from firebase also changes
i have used some external library for 
like zxing and karumi
you have to add this in your gradel file 
implementation 'me.dm7.barcodescanner:zxing:1.9.13'
implementation 'com.karumi:dexter:6.2.2'
