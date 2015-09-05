var forwardSpeed : float = 700.0;
/*
		* increase forward speed in an incremental manner; 
			- so to appear more realistic, until our set speed; 
		
*/
var strafeSpeed : float = 600.0;
var rotationSpeed : float = 60.0;
var beginPos : Vector3;
var beginTime : float;
var delay : float = 5.0;
var readyNow : boolean = true;
var rotationCounter : int = 0;
var ranChecker : boolean = false;
var checker : boolean = false;
var flyer : Transform;
var translation_x : float;
var script : CameraScript;
var force: float=0.1;
var counterV: int= 0;
var joystickL: Joystick;
//var firstPersonScript: FirstPersonControl;

function Start()
{
	beginPos = transform.position;
	delay_it();
	script = transform.Find("Main Camera").GetComponent(CameraScript);
	//script = GetComponent(FirstPersonControl);
	//joystickL=transform.Find("LeftJoystick").GetComponent(Joystick);
	//firstPersonScript=transform.Find("Main Camera").GetComponent(FirstPersonControl);
	
}

function OnTriggerEnter(other : Collider) {
	if(other.gameObject.name != "mainBuilding" || other.gameObject.name != "finishSpot" || other.gameObject.name!= "finishPlatform" )
	 {
		//transform.position = beginPos;
		Application.LoadLevel("gameOver");
	}
	if(other.gameObject.name == "finishSpot"|| other.gameObject.name== "finishPlatform" )
	{
		Application.LoadLevel("gameWin");
	}
}

function Update()
{
	if(readyNow) {
    	characterMotion();
    }
    //print(Time.time);
}

function delay_it(){
	readyNow = false;
	yield WaitForSeconds(5);
	readyNow = true;
}

function characterMotion()
{
	//var movement = cameraTransform.TransformDirection( Vector3( moveJoystick.position.x, 0, moveJoystick.position.y ) );
	//var absJoyPos = Vector2( Mathf.Abs( moveJoystick.position.x ), Mathf.Abs( moveJoystick.position.y ) );
	var movement_x : float = strafeSpeed;
	movement_x *= Time.deltaTime;
	
	var translation_x : float = forwardSpeed;
    //var translation_z : float = Input.GetAxis("Horizontal") * strafeSpeed;
    //var translation_z: float = joystickL.position.x;
    //var movement= transform.TransformDirection( Vector3( joystickL.position.y,0,joystickL.position.x));
    var movement = transform.TransformDirection(Vector3(0,0,0));
    //translation_z.y=0;
    //var rotation : float = Input.GetAxis("Horizontal") * rotationSpeed;
    //Input.GetJoystickNames(
    // Make it move 10 meters per second instead of 10 meters per frame...
    //translation_z *= Time.deltaTime;
    translation_x *= Time.deltaTime;
    //translation_z = -2.5*translation_z; // fixed the left and right inversed controls; simply inversed them; -2.5
    //rotation *= Time.deltaTime;
    
    // If the camera is behind the player and we're done with the intro pan...
    if(transform.position.y >= 515 && transform.position.y <= 519.95)
	{
		//i+=0.75;
		//var forwardMoveAmount= Input.GetAxis("Vertical")*forwardSpeed;
		//rigidbody.AddRelativeForce(forwardMoveAmount,0,0);//(x,y,z)
		//checker=true;
		translation_x = joystickL.position.y * forwardSpeed; //Input.GetAxis("Vertical") * forwardSpeed;
		translation_x *= Time.deltaTime * (0.5);
		// Translate the player forward
		//transform.Translate(translation_z);  //translation_x, 0,0
		// Rotate the player forward
		//if(Input.GetAxis("Vertical") >= 1)
	    //var movement= transform.TransformDirection(Vector3(0,0,0));
		if(joystickL.position.y>0 || Input.GetAxis("Vertical") >= 1)
		{	
			movement = Vector3(1,0,0) * joystickL.position.y; // going too fast ! change; (* forwardSpeed;)
			transform.RotateAround(flyer.position, Vector3(0,0,-1), 50 * Time.deltaTime);
			transform.Translate(movement*0.5);
			print(joystickL.position.y);

			//transform.Translate(translation_x,0,0);
			//print("true");
		}
	} else {
		// Switch to 1st person camera
	   	script.orbitCamera();
	    //firstPersonScript.firstPersonControl();
	    // Multiply forward force
	    translation_x = 50.0 * translation_x;
		//translation_z= transform.TransformDirection( Vector3( joystickL.position.y,0,joystickL.position.x));
	    if(transform.position.x <= 600f)
	    {
	    	//Push player forward along the y-axis
	    	rigidbody.AddRelativeForce(0, translation_x, 0);	//(0, translation_x, 0)
	    }
	    
	    // Strafe player left/right in the air
	    /*if(transform.position.x>=-448f && transform.position.x<-254f)
	    {	 
	    	var translation_zz=Vector3(0,0,translation_z.z);
	    	transform.Translate(translation_zz);
	    	//transform.Translate(translation_z);// z*1.5, also changed from (0,0,translation_z);
	    
	    }*/
	    // if(joystickL.position.x>0)
	    //{
	    print(joystickL.position.x);
	    movement_x *= -joystickL.position.x * 10f;
	    flyer.Translate(0, 0, movement_x);
	    //}
	    velocityMod();
	    
	    
	    if(transform.position.y>700f || transform.position.y<-100 || transform.position.z>850 || transform.position.z<-850)
	    {
	    	Application.LoadLevel("gameOver");
	    	
	    }
	    // Rotate around our y-axis
	    //rigidbody.AddRelativeForce(Vector3.forward * 1000);
	    //transform.Rotate(0, 0, rotation);
	    //print(translation_x);
	    //print (Time.time+ "\n");
	    //print ("x= "+ transform.position.x+"\n");
	    //x = -344;  	x= -1.4;
	   
	    
    }
}

//function adjustThrottleDirection()
//{
//	transform.Translate(0, translation_x, 0);
//}

function velocityMod()
{
	 if(transform.position.x>=-448f && transform.position.x<-254f)
	    {
	    	force-=5f;
	    	//Add a halved positive x force, and a negative y force
	    	rigidbody.AddForce (Vector3(-force*0.5,force * 0.6,0));//force*0.7 on y axis;
	    
	    }
	    
	    if (transform.position.x>=-254f && transform.position.x<76f)
	    {
	    	++counterV;
	    	if(counterV==1)
	    	{
	    		//switches the force to positive;
	    			// if statement prevents from alternating between negative and positive.
	    		force=-force*0.5;
	    	}
	    	force+=5f;
	    	
	    	 rigidbody.AddForce (Vector3(1.2*force,force,0));
	    }
	        
	    if(transform.position.x>=76f && transform.position.x<380f)//440f
	    {
	    	
	    	//rigidbody.AddForce (Vector3(-force,0,0));
	     	if (rigidbody.velocity.x>=0)
	     	{
	     	rigidbody.AddForce (Vector3(-force*0.85,0,0));
	     	//print(rigidbody.velocity.x);
	    	}
	    
	    }
	    if(transform.position.x>=380f)
	    {
	    	rigidbody.AddForce(Vector3(0,-force*0.5,0));
	    	print (">=400f");
	   
	    }
}

/*
		*Notes for dev. of function:
			* first third ends at -254;
			* second third ends at 76;
			* Final Third ends at 494;
				* between -448 and -254 --> medium x, medium -y;
				* between -254 and 76 --> larger x, small y;
				* between 76 and 494 -->  larger -x, 0 y;
				
				
			* New coordinates for larger plane;
			
				* firs third 
				
				* second
				
				
*/


/* else if(!(transform.position.y >= 516 && transform.position.y <= 519.95) && ranChecker == false) {
		//checker=true;
		//transform.eulerAngles=Vector3(0,0,0);
		//transform.RotateAround(flyer.position,transform.eulerAngles,0.0);
	    //transform.RotateAround(transform.eulerAngles.z,i);
	    //rigidbody.AddRelativeForce(forwardSpeed,0,0);
	    //OnGUI();
	  	//yield WaitForSeconds(5);
    
    	ranChecker = true;
    	print("ranChecker set to true; (else if) ran;" + ranChecker);
    	print(transform.eulerAngles.z);
    }*/