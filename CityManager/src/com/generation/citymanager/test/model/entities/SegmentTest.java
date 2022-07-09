package com.generation.citymanager.test.model.entities;

import org.junit.jupiter.api.Test;

import com.generation.citymanager.model.entities.Segment;

class SegmentTest
{

	@Test
	void testGetLength()
	{
		Segment segment = new Segment(2,5);
		assert(segment.getLength()==3);
	}


	@Test
	void testCollides()
	{
		Segment a = new Segment(4,9);
		Segment b = new Segment(3,7);
		
		assert(a.collides(b));
		assert(b.collides(a));
		
		Segment c = new Segment(2,3);
		Segment d = new Segment(4,5);
		
		assert(!c.collides(d));
		assert(!d.collides(c));
	}
}
