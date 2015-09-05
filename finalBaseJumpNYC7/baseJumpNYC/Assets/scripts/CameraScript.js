var offset: Vector3= Vector3.forward;
var cameraRotSide: float;
var cameraRotUp: float;
var  cameraRotSideCur: float;
var cameraRotUpCur: float;
var distance: float;
var rotationSpeed : float;
var target : Transform;
var cam: Transform;
static var  count: int=0;
var joystickR: Joystick;

function Start()
{
	//camera0= transform;
	cameraRotSide= 90;//cam.eulerAngles.y;
	cameraRotSideCur= 450;//cam.eulerAngles.y;
	cameraRotUp = 400;//cam.eulerAngles.x;
	cameraRotUpCur = 400;//cam.eulerAngles.x;
	//distance = -cam.localPosition.z;
	distance= 20;//cam.position.z;
	//joystickR=transform.Find("RightJoystick").GetComponent(Joystick);

}


function Update () {
	
	//if (count!=1)
		//{
			cameraRotation();
			
		//}
		
	
	
	//print("count"+count);
	
	}
	
	
	
function cameraRotation()
{
	
	if(!(cam.position.z>=4.7 && cam.position.z<=5.5))
	{
		transform.RotateAround(target.position,Vector3(0,1,0),rotationSpeed * Time.deltaTime);
		
		transform.RotateAround(target.position,Vector3(0.2,0,0), 0.4*rotationSpeed * Time.deltaTime);
	}
	
	if(cam.position.z>=4.7 && cam.position.z<=5.5)
	{
		++count;
		
		
	}


}

function adjustCamera()
{

	transform.RotateAround(target.position,Vector3(0,0,1),80* Time.deltaTime);
	

}

function orbitCamera()
{
	//if(Input.GetMouseButton(0))
	//if(false)
	//{
	if( joystickR.position.x!=0 || joystickR.position.y!=0)
	{
		cameraRotSide+=joystickR.position.x;  //Input.GetAxis("Mouse X")*5;
		cameraRotUp+= joystickR.position.y; //Input.GetAxis("Mouse Y")*5;//-=
		cameraRotSideCur = Mathf.LerpAngle(cameraRotSideCur, cameraRotSide, Time.deltaTime*5);
		cameraRotUpCur = Mathf.Lerp(cameraRotUpCur, -cameraRotUp, Time.deltaTime*5);
	}
	//}
	//cameraRotSideCur = Mathf.LerpAngle(cameraRotSideCur, cameraRotSide, Time.deltaTime*5);
	//cameraRotUpCur = Mathf.Lerp(cameraRotUpCur, -cameraRotUp, Time.deltaTime*5);
		
		//if (Input.GetMouseButton(1))
		//if(false) 
		//{
		//	distance *= (1-0.1f* joystickR.position.y);//(1-0.1f*Input.GetAxis("Mouse Y"))
		//}
		//distance *= (1-1*Input.GetAxis("Mouse ScrollWheel"));
		
		var targetPoint:Vector3 = target.position; //target.position;
		transform.position = Vector3.Lerp(transform.position, targetPoint + offset, Time.deltaTime);
		transform.rotation = Quaternion.Euler(cameraRotUpCur, cameraRotSideCur, 0);
		
		var dist:float = Mathf.Lerp(-cam.transform.localPosition.z, distance, Time.deltaTime*5);
		cam.localPosition = -Vector3.forward * dist;

}