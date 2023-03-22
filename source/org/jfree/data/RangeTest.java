package org.jfree.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class RangeTest
{
	private Range rangeObjectUnderTest;
	private Range rangeObjectUnderTest2;
	private Range rangeObjectUnderTest3;
	private Range rangeObjectUnderTest4;
	private Range rangeObjectUnderTest5;

	@Before
	public void setUp() throws Exception
	{
		rangeObjectUnderTest = new Range(-1, 1);
		rangeObjectUnderTest2 = new Range(4, 9);
		rangeObjectUnderTest3 = new Range(5.0, 10.0);
		rangeObjectUnderTest4 = new Range(2, 4);
		rangeObjectUnderTest5 = new Range(0, 5);
	}

	@After
	public void tearDown() throws Exception
	{
	}
	
	// EXAMPLE TESTS
	
	@Test
	public void testCentralValueShouldBeZero()
	{
		assertEquals("The central value of -1 and 1 should be 0",
				0,
				rangeObjectUnderTest.getCentralValue(),
				0.000000001d);
	}
	
	@Test
	public void testGetLengthBothPositiveAndEqual()
	{
		Range r1 = new Range(2, 2);
		assertEquals("getLength: Did not return the expected output",
				0.0,
				r1.getLength(),
				0.000000001d);
	}
	
	@Test
	public void testGetLengthBothPositiveAndNotEqual()
	{
		Range r2 = new Range(4, 9);
		assertEquals("getLength: Did not return the expected output",
				5.0,
				r2.getLength(),
				0.000000001d);
	}
	
	@Test
	public void testGetLengthBothNegativeAndEqual()
	{
		Range r3 = new Range(-99, -99);
		assertEquals("getLength: Did not return the expected output",
				0.0,
				r3.getLength(),
				0.000000001d);
	}
	
	@Test
	public void testGetLengthBothNegativeAndNotEqual()
	{
		Range r4 = new Range(-11, -4);
		assertEquals("getLength: Did not return the expected output",
				7.0,
				r4.getLength(),
				0.000000001d);
	}
	
	@Test
	public void testGetLengthOnePositiveOneNegative()
	{
		Range r5 = new Range(-5, 3);
		assertEquals("getLength: Did not return the expected output",
				8.0,
				r5.getLength(),
				0.000000001d);
	}
	
	// ADDED TESTS
	
	// Method Under Test: intersects
	// Black Box Tests:
	
	@Test
	public void testIntersectsLowerAndUpperIsSameAsObject()
	{
		assertEquals("intersects: Did not return the expected output",
				true,
				rangeObjectUnderTest2.intersects(4, 9));
	}
	
	@Test
	public void testIntersectsLowerIsJustBelowLowerBound()
	{
		assertEquals("intersects: Did not return the expected output",
				true,
				rangeObjectUnderTest2.intersects(3, 4));
	}
	
	@Test
	public void testIntersectsUpperIsJustAboveUpperBound()
	{
		assertEquals("intersects: Did not return the expected output",
				true,
				rangeObjectUnderTest2.intersects(9, 10));
	}
	
	@Test
	public void testIntersectsSmallestPossibleRange()
	{
		assertEquals("intersects: Did not return the expected output",
				false,
				rangeObjectUnderTest2.intersects(0, 0));
	}
	
	@Test
	public void testIntersectsLargestPossibleRange()
	{
		assertEquals("intersects: Did not return the expected output",
				true,
				rangeObjectUnderTest2.intersects(Double.MIN_VALUE,
						Double.MAX_VALUE));
	}
	
	// White Box Tests
	
	@Test
	public void testIntersectsLowerAboveLowerBoundAndUpperBelowUpperBound()
	{
		assertEquals("intersects: Did not return the expected output",
				true,
				rangeObjectUnderTest2.intersects(5,
						5));
	}
	
	@Test
	public void testIntersectsLowerAboveLowerBoundAndUpperBelowLowerBound()
	{
		assertEquals("intersects: Did not return the expected output",
				false,
				rangeObjectUnderTest2.intersects(5,
						3));
	}
	
	// Method Under Test: constrain
	// Black Box Tests:
	
	@Test
	public void testConstrainValueEqualToLowerBound()
	{
		assertEquals("contrain: Did not return the expected output",
				4,
				rangeObjectUnderTest2.constrain(4),
				0.000000001d);
	}
	
	@Test
	public void testConstrainValueEqualToUpperBound()
	{
		assertEquals("contrain: Did not return the expected output",
				9,
				rangeObjectUnderTest2.constrain(9),
				0.000000001d);
	}
	
	@Test
	public void testConstrainValueJustBelowLowerBound()
	{
		assertEquals("contrain: Did not return the expected output",
				4,
				rangeObjectUnderTest2.constrain(3),
				0.000000001d);
	}
	
	@Test
	public void testConstrainValueJustAboveUpperBound()
	{
		assertEquals("contrain: Did not return the expected output",
				9,
				rangeObjectUnderTest2.constrain(10),
				0.000000001d);
	}
	
	@Test
	public void testConstrainValueFarBelowLowerBound()
	{
		assertEquals("contrain: Did not return the expected output",
				4,
				rangeObjectUnderTest2.constrain(-99),
				0.000000001d);
	}
	
	@Test
	public void testConstrainValueFarAboveUpperBound()
	{
		assertEquals("contrain: Did not return the expected output",
				9,
				rangeObjectUnderTest2.constrain(99),
				0.000000001d);
	}
	
	// Method Under Test: contains
	// Black Box Tests:
	
	@Test
	public void testContainsValueEqualToLowerBound()
	{
		assertTrue("contains: Did not return the expected output",
				rangeObjectUnderTest2.contains(4));
	}
	
	@Test
	public void testContainsValueEqualToUpperBound()
	{
		assertTrue("contains: Did not return the expected output",
				rangeObjectUnderTest2.contains(9));
	}
	
	@Test
	public void testContainsValueJustBelowLowerBound()
	{
		assertFalse("contains: Did not return the expected output",
				rangeObjectUnderTest2.contains(3));
	}
	
	@Test
	public void testContainsValueJustAboveUpperBound()
	{
		assertFalse("contains: Did not return the expected output",
				rangeObjectUnderTest2.contains(10));
	}
	
	@Test
	public void testContainsValueFarBelowLowerBound()
	{
		assertFalse("contains: Did not return the expected output",
				rangeObjectUnderTest2.contains(-99));
	}
	
	@Test
	public void testContainsValueFarAboveUpperBound()
	{
		assertFalse("contains: Did not return the expected output",
				rangeObjectUnderTest2.contains(99));
	}
	
	// Method Under Test: getCentralValue
	// Black Box Tests:
	
	@Test
	public void testGetCentralValueRangeHasOneValue()
	{
		Range r6 = new Range(2, 2);
		
		assertEquals("getCentralValue: Did not return the expected output",
				2,
				r6.getCentralValue(),
				0.000000001d);
	}
	
	@Test
	public void testGetCentralValueRangeHasTwoValues()
	{
		Range r7 = new Range(2, 3);
		
		assertEquals("getCentralValue: Did not return the expected output",
				2.5,
				r7.getCentralValue(),
				0.000000001d);
	}
	
	@Test
	public void testGetCentralValueRangeHasThreeValues()
	{
		Range r8 = new Range(2, 4);
		
		assertEquals("getCentralValue: Did not return the expected output",
				3,
				r8.getCentralValue(),
				0.000000001d);
	}
	
	@Test
	public void testGetCentralValueRangeHasFourValues()
	{
		Range r9 = new Range(2, 5);
		
		assertEquals("getCentralValue: Did not return the expected output",
				3.5,
				r9.getCentralValue(),
				0.000000001d);
	}
	
	// Method Under Test: expandToInclude
	// Black Box Tests:
	
	@Test
	public void testExpandToIncludeRangeIsNull()
	{
		Range r10 = null;
		
		assertEquals("expandToInclude: Did not return the expected output",
				new Range(5, 5),
				Range.expandToInclude(r10, 5));
	}
	
	@Test
	public void testExpandToIncludeRangeHasOneValue()
	{
		Range r11 = new Range(4, 4);
		
		assertEquals("expandToInclude: Did not return the expected output",
				new Range(3, 4),
				Range.expandToInclude(r11, 3));
	}
	
	@Test
	public void testExpandToIncludeRangeHasTwoValuesAndInputLessThanLowerBound()
	{
		Range r12 = new Range(4, 5);
		
		assertEquals("expandToInclude: Did not return the expected output",
				new Range(3, 5),
				Range.expandToInclude(r12, 3));
	}
	
	@Test
	public void testExpandToIncludeRangeHasTwoValuesAndInputWithinBounds()
	{
		Range r13 = new Range(4, 5);
		
		assertEquals("expandToInclude: Did not return the expected output",
				new Range(4, 5),
				Range.expandToInclude(r13, 4.5));
	}
	
	@Test
	public void testExpandToIncludeRangeHasTwoValuesAndInputMoreThanUpperBound()
	{
		Range r14 = new Range(4, 5);
		
		assertEquals("expandToInclude: Did not return the expected output",
				new Range(4, 6),
				Range.expandToInclude(r14, 6));
	}
	
	@Test
	public void testExpandToIncludeRangeHasMoreThanTwoValuesAndInputLessThanLowerBound()
	{
		Range r15 = new Range(3, 6);
		
		assertEquals("expandToInclude: Did not return the expected output",
				new Range(1, 6),
				Range.expandToInclude(r15, 1));
	}
	
	@Test
	public void testExpandToIncludeRangeHasMoreThanTwoValuesAndInputWithinBounds()
	{
		Range r16 = new Range(3, 6);
		
		assertEquals("expandToInclude: Did not return the expected output",
				new Range(3, 6),
				Range.expandToInclude(r16, 5));
	}
	
	@Test
	public void testExpandToIncludeRangeHasMoreThanTwoValuesAndInputMoreThanUpperBound()
	{
		Range r17 = new Range(3, 6);
		
		assertEquals("expandToInclude: Did not return the expected output",
				new Range(3, 8),
				Range.expandToInclude(r17, 8));
	}
	
	// Method Under Test: combine
	// White Box Tests:
	
	@Test
	public void testCombineBothRangesNull()
	{
		assertEquals("combine: Did not return the expected output",
				null,
				Range.combine(null, null));
	}
	
	@Test
	public void testCombineRange1Null()
	{
		assertEquals("combine: Did not return the expected output",
				new Range(5.0, 10.0),
				Range.combine(null, rangeObjectUnderTest3));
	}
	
	@Test
	public void testCombineRange2Null()
	{
		assertEquals("combine: Did not return the expected output",
				new Range(5.0, 10.0),
				Range.combine(rangeObjectUnderTest3, null));
	}
	
	@Test
	public void testCombineNeitherRangeNull()
	{
		assertEquals("combine: Did not return the expected output",
				new Range(5.0, 12.0),
				Range.combine(rangeObjectUnderTest3, new Range(8.0, 12.0)));
	}
	
	@Test
	public void testCombineBothLowerBoundsEqual()
	{
		assertEquals("combine: Did not return the expected output",
				new Range(5.0, 12.0),
				Range.combine(rangeObjectUnderTest3, new Range(5.0, 12.0)));
	}
	
	@Test
	public void testCombineBothUpperBoundsEqual()
	{
		assertEquals("combine: Did not return the expected output",
				new Range(2.0, 10.0),
				Range.combine(rangeObjectUnderTest3, new Range(2.0, 10.0)));
	}
	
	@Test
	public void testCombineSameUpperAndLowerBounds()
	{
		assertEquals("combine: Did not return the expected output",
				new Range(5.0, 10.0),
				Range.combine(rangeObjectUnderTest3, new Range(5.0, 10.0)));
	}
	
	// Method Under Test: expand
	// White Box Tests:
	
	@Test
	public void testExpandLowerAndUpperZero()
	{
		assertEquals("expand: Did not return the expected output",
				new Range(0, 10),
				Range.expand(new Range(0, 10), 0, 0));
	}
	
	@Test
	public void testExpandLowerAndUpperOne()
	{
		assertEquals("expand: Did not return the expected output",
				new Range(-10, 20),
				Range.expand(new Range(0, 10), 1, 1));
	}
	
	@Test
	public void testExpandNegativeLowerPositiveUpper()
	{
		assertEquals("expand: Did not return the expected output",
				new Range(-2.5, 15),
				Range.expand(new Range(0, 10), -0.25, 0.5));
	}
	
	@Test
	public void testExpandNullRange()
	{
		try
		{
			Range.expand(null, 0.1, 0.2);
			fail("expand: Did not throw IllegalArgumentException when Range is null");
		}
		catch(IllegalArgumentException e)
		{
			// Test passes
		}
	}
	
	@Test
	public void testExpandRangeWithLengthZero()
	{
		assertEquals("expand: Did not return the expected output",
				new Range(5, 5),
				Range.expand(new Range(5, 5), 0.1, 0.2));
	}
	
	// Method Under Test: shift
	// White Box Tests:
	
	@Test
	public void testShiftNullBase()
	{
		try
		{
			Range.shift(null, 5);
			fail("shift: Did not throw NullPointerException when base is null");
		}
		catch(NullPointerException e)
		{
			// Test passes
		}
	}
	
	@Test
	public void testShiftPositiveDelta()
	{
		assertEquals("shift: Did not return the expected output",
				new Range(5, 7),
				Range.shift(rangeObjectUnderTest4, 3));
	}
	
	@Test
	public void testShiftNegativeDelta()
	{
		assertEquals("shift: Did not return the expected output",
				new Range(1, 3),
				Range.shift(rangeObjectUnderTest4, -1));
	}
	
	@Test
	public void testShiftDeltaZero()
	{
		assertEquals("shift: Did not return the expected output",
				new Range(2, 4),
				Range.shift(rangeObjectUnderTest4, 0));
	}
	
	@Test
	public void testShiftNegativeDeltaWithZeroCrossing()
	{
		assertEquals("shift: Did not return the expected output",
				new Range(-1, 1),
				Range.shift(rangeObjectUnderTest4, -3, true));
	}
	
	@Test
	public void testShiftNegativeDeltaWithNoZeroCrossing()
	{
		assertEquals("shift: Did not return the expected output",
				new Range(0, 1),
				Range.shift(rangeObjectUnderTest4, -3, false));
	}
	
	@Test
	public void testShiftNegativeLowerBound()
	{
		assertEquals("shift: Did not return the expected output",
				new Range(0, 1),
				Range.shift(new Range(-3, 4), -3));
	}
	
	@Test
	public void testShiftLowerBoundZero()
	{
		assertEquals("shift: Did not return the expected output",
				new Range(0, 1),
				Range.shift(new Range(0, 4), -3));
	}
	
	// Method Under Test: equals
	// White Box Tests:
	
	@Test
	public void testEqualsLowerIsEqualAndUpperIsNot()
	{
		Range range2 = new Range(0, 6);
		
		assertEquals("equals: Did not return the expected output",
				false,
				rangeObjectUnderTest5.equals(range2));
	}
	
	// Method Under Test: Range
	// White Box Tests:
	
	@Test
	public void testRangeLowerGreaterThanUpper()
	{
		try
		{
			Range range = new Range(10, 5);
			fail("Range: Did not throw IllegalArgumentException when lower is greater than upper");
		}
		catch (IllegalArgumentException e)
		{
			// Test passes
		}
	}
}
