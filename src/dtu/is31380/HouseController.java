package dtu.is31380;

import java.util.ArrayList;

public class HouseController extends AbstractHouseController {

	double[] SP = {21.0,21.0,21.0,21.0,21.0,21.0,21.0,21.0};
	double[] delta = {2.0,2.0,2.0,2.0,2.0,2.0,2.0,2.0};
	
	public HouseController() {
		super(5000); //set timestep to 5000ms
	}
  
  @Override
  protected void execute() {
    HouseControllerInterface intf=getInterface();
    // loop through "rooms"
    for (int i=1; i<7; i++) {
    	System.out.println(i); 
    	
        System.out.println("T_room"+ (i+1)+"="+intf.getSensorValue("s_tempr"+ (i+1)));

        // conditions for turning on
    	if (SP[i]- intf.getSensorValue("s_tempr"+ (i+1)) > delta[i] ) {
            System.out.println(SP[i]- intf.getSensorValue("s_tempr"+ (i+1))+" : turning ON");
    	}
        else {
        	System.out.println(SP[i]- intf.getSensorValue("s_tempr"+ (i+1))+" : turning OFF");
        }

        // turning actuator on
        if (intf.getActuatorSetpoint("a_htrr"+(i+1)+"_1")<0.5) {
            intf.setActuator("a_htrr"+(i+1)+"_1", 1.0); //switch heater in room 1 on
          }
    	
    	// turning actuator off
    	else {
    		intf.setActuator("a_htrr"+(i+1)+"_1", 0.0); //switch heater in room 1 off
    	}
    }
    //System.out.println("T_room1="+intf.getSensorValue("s_tempr1"));
    
  }

  @Override
  protected void init() {
	  // Just for show
	    BuildingConfig bc=getInterface().getBuildingConfig();
	    ArrayList<RoomConfig> rooms=bc.getRooms();
	    System.out.println("Rooms: "+rooms.toString());
  }
  
  

}
