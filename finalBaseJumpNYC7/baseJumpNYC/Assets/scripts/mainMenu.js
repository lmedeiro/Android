
// Use this for initialization
function Start () {
}

// Update is called once per frame
function Update () {
}

function OnGUI () {
	var titleText = "Basejump N.Y.C";
	GUI.Label(Rect((Screen.width/2f) - (98f/2f), 30f, 98f, 200f), titleText);
	
	if(	GUI.Button(Rect((Screen.width/2f) - ((Screen.width * .3)/2f), 70f, Screen.width * .3, Screen.height * .15), "New Game")  ){
		Application.LoadLevel("scene0");
	}
	if(	GUI.Button(Rect((Screen.width/2f) - ((Screen.width * .3)/2f), 170f, Screen.width * .3, Screen.height * .15), "Options")  ){
		Application.LoadLevel("optionsMenu");
	}
	if(	GUI.Button(Rect((Screen.width/2f) - ((Screen.width * .3)/2f), 270f, Screen.width * .3, Screen.height * .15), "Quit")  ){
		Application.Quit();
	}
}