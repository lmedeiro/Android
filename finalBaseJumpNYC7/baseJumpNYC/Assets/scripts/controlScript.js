var forwardSpeed: float =3;
var turnSpeed: float =2;



function Update () {


	var forwardMoveAmount= Input.GetAxis("Vertical")*forwardSpeed;	
		var turnAmount= Input.GetAxis("Horizontal")* turnSpeed;
		//roatate
		transform.Rotate(0,turnAmount,0); // transform.Roatate(x,y,z);
	
		rigidbody.AddRelativeForce(forwardMoveAmount,0,0);//(x,y,z)	

	//print(Time.time);

}