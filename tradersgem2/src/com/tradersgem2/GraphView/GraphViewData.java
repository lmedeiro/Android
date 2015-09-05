package com.tradersgem2.GraphView;

public class GraphViewData implements GraphViewDataInterface
{

	public GraphViewData( double xValue, double yValue )
	{
		x=xValue;
		y=yValue;
		
	}
	
	@Override
	public double getX() {
		
		return x;
	}

	@Override
	public double getY() {
		
		return y;
	}
	
	
	private final double x,y;
}
