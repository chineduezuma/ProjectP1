/* This is a basic implementation of a store, which is basically global data storage

    Any data that you want to use throughout the app can reside here
    (We'll see this in a better way with Context API)

    For now, we'll just have an object that has fields for the data we want to store */

    export const store = {

        //Let's store loggedInUser info (filled after successful login)
        loggedInUser:{
            userId:0,
            username:"", 
            role:"",
        }
    
        //THIS ISN'T BEST PRACTICE BY THE WAY! For one, the data will be wiped if you refresh
        //Also, this data has nothing to do with state. So component won't rerender if you change the global state file... This isn't great, because it's not REACTive
    
    }