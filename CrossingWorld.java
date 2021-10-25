import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * CrossingWorld functions as the "Stage" for this Greenfoot
 * project which helps students understand inheritance
 * 
 * INSERT YOUR WRITTEN TASK HERE:
 * 
 * @version October 2021
 * @since March 2015
 */
public class CrossingWorld extends World
{
    private int randomize;
    /**
     * Spawn Rates:
     * Lower number means more spawns
     * 3:spawnRate chance per act of spawning a random Vehicle
     * 1:pedSpawn chance per act of spawning a Pedestrian
     */
    private int spawnRate = 100; // must be higher than 3 ... should be higher than 30
    private int pedSpawn = 100; 

    // LaneSpawnChecks are transparent rectangles the size of vehicles to determine if a 
    // Vehicle exists in a given post. These ones are used to determine if a lane is available
    // for spawning a new Vehicle, so that a new Vehicle can be safely spawned without overlap.
    private LaneSpawnCheck[] laneChecks;

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public CrossingWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 

        laneChecks = new LaneSpawnCheck [6];
        for (int i = 0; i < 6; i++){
            laneChecks[i] = new LaneSpawnCheck();
            addObject (laneChecks[i], 40, getYPosition (i));
        }

        prepare();
    }

    public void act ()
    {
        // Rum methods to see if any pedestrians or vehicles are going to be 
        // spawned this act:
        spawnPedestrians();
        spawnVehicles();    

    }

    private void spawnVehicles()
    {
        // Generate a random number to add a random element
        // to Vehicle spawning
        randomize = Greenfoot.getRandomNumber(spawnRate);

        // Chose a random lane in case a vehicle spawns
        int lane = Greenfoot.getRandomNumber (6);

        // Check if randomize is a low number (not just 3, to leave room to add vehicles) and then
        // check if there is already a vehicle present (in which case, don't spawn anything, which will
        // affect the spawn rate, but that's okay).
        if (randomize < 10 && laneChecks[lane].vehiclePresent() == false){
            // determine the Y position of the desired lane
            int spawnY = getYPosition (lane);

            if (randomize == 1)
            {
                // spawn a Car
                addObject (new Car(), 10, spawnY);
            }
            else if (randomize == 2)
            {
                // spawn a Bus
                addObject (new Bus(), 10, spawnY);
            }

            else if (randomize == 3)
            {
                // spawn a Car
                addObject (new Ambulance(), 10, spawnY);
            }
        }  

    }

    private void spawnPedestrians()
    {
        // spawn pedestrians
        Random rn = new Random();
        int n = rn.nextInt(2);
        if (Greenfoot.getRandomNumber(pedSpawn) == 1)
        {
            if(n == 1)
            {
            addObject (new Adult(), Greenfoot.getRandomNumber(600), 395);
            }
            else
            {
             addObject (new Child(), Greenfoot.getRandomNumber(600), 395);
            }
        }
        
    }

    /**
     * Returns the appropriate y coordinate for a given lane
     */
    private int getYPosition (int inLane)
    {
        // Manually input values based on the background graphic
        switch (inLane)
        {
            case 0: 
                return 79;

            case 1:
                return 127;

            case 2:
                return 175;

            case 3:
                return 222;

            case 4:
                return 272;

            case 5: 
                return 320;

        }  
        // In case an invalid value is passed in
        return -1;
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}

